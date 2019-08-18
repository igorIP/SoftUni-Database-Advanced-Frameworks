package app.ccb.services;


import app.ccb.domain.dtos.BranchImportDto;
import app.ccb.domain.entities.Branch;
import app.ccb.repositories.BranchRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BranchServiceImpl implements BranchService {

    private final static String BRANCHES_JSON_FILE_PATH =
            "/Users/igor/Desktop/GIT_Projects/SoftUni-DatabaseAdvanced-Frameworks/13.WORKSHOP–MVC PROJECT-SPRING-MVC-SPRING DATA/ColonialCouncilBank/src/main/resources/files/json/branches.json";

    private final BranchRepository branchRepository;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, FileUtil fileUtil, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson) {
        this.branchRepository = branchRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public Boolean branchesAreImported() {
        return this.branchRepository.count() != 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        String branchesJson = this.fileUtil.readFile(BRANCHES_JSON_FILE_PATH);
        return branchesJson;
    }

    @Override
    public String importBranches(String branchesJson) {
        //parse the json string to import dto(use gson)
        //check each dto if isValid
        //if ok, ModelMapper.map(dto, entity)
        //save every single dto
        StringBuilder importResult = new StringBuilder();
        BranchImportDto[] dtos = this.gson.fromJson(branchesJson, BranchImportDto[].class);
        for (BranchImportDto dto : dtos) {
            if (!this.validationUtil.isValid(dto)) {
                importResult
                        .append("Error: Incorrect Data!")
                        .append(System.lineSeparator());
                continue;
            }
            Branch branchEntity = this.modelMapper.map(dto, Branch.class);
            this.branchRepository.saveAndFlush(branchEntity);
            importResult
                    .append("Successfully imported Branch - ")
                    .append(branchEntity.getName())
                    .append(" Branch.")
                    .append(System.lineSeparator());

        }
        return importResult.toString().trim();
    }
}
