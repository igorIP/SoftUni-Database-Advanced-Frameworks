package app.ccb.services;

import app.ccb.domain.dtos.EmployeeImportDto;
import app.ccb.domain.entities.Branch;
import app.ccb.domain.entities.Employee;
import app.ccb.repositories.BranchRepository;
import app.ccb.repositories.EmployeeRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final static String EMPLOYEES_JSON_FILE_PATH =
            "/Users/igor/Desktop/GIT_Projects/SoftUni-DatabaseAdvanced-Frameworks/13.WORKSHOP–MVC PROJECT-SPRING-MVC-SPRING DATA/ColonialCouncilBank/src/main/resources/files/json/employees.json";

    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, BranchRepository branchRepository, FileUtil fileUtil, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson) {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() != 0;
    }

    @Override
    public String readEmployeesJsonFile() throws IOException {
        String importResults = this.fileUtil.readFile(EMPLOYEES_JSON_FILE_PATH);
        return importResults;
    }

    @Override
    public String importEmployees(String employees) {
        //parse-map the json string to importDto(use gson)
        //check each dto if isValid
        //if ok, ModelMapper.map(dto, entity)
        //save every single dto

        StringBuilder resultToDisplay = new StringBuilder();
        EmployeeImportDto[] dtos = this.gson.fromJson(employees, EmployeeImportDto[].class);
        for (EmployeeImportDto dto : dtos) {
            if (!this.validationUtil.isValid(dto)) {
                resultToDisplay
                        .append("Error: Incorrect Data!")
                        .append(System.lineSeparator());
                continue;
            }
            //importDto -> entity
            Employee employeeEntity = this.modelMapper.map(dto, Employee.class);

            //SET ALL MISSING FIELDS FOR THE ENTITY:
            //full name -> first and last
            //date -> localDate
            //branch_name ->: getBranchName->getEntityBranch with this name-> employeeEntity.setBranch.
            List<String> firstLastNameTokens = Arrays.asList(dto.getFullName().split("\\s+"));
            employeeEntity.setFirstName(firstLastNameTokens.get(0));
            employeeEntity.setLastName(firstLastNameTokens.get(1));

            LocalDate localDate = LocalDate.parse(dto.getStartedOn(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            employeeEntity.setStartedOn(localDate);

            Branch branchEntity = this.branchRepository.findFirstByNameEquals(dto.getBranchName()).orElse(null);
            if (branchEntity == null) {
                resultToDisplay
                        .append("Error: Incorrect Data!")
                        .append(System.lineSeparator());
                continue;
            }
            employeeEntity.setBranch(branchEntity);
            this.employeeRepository.saveAndFlush(employeeEntity);
        }
        return resultToDisplay.toString().trim();
    }

    @Override
    public String exportTopEmployees() {
        List<Employee> topEmployees = this.employeeRepository.findTopEmployees();

        StringBuilder resultMessage = new StringBuilder();
        for (Employee employee : topEmployees) {
            resultMessage
                    .append("Full Name: ")
                    .append(employee.getFirstName())
                    .append(" ")
                    .append(employee.getLastName())
                    .append(System.lineSeparator())
                    .append(employee.getSalary())
                    .append(System.lineSeparator())
                    .append(employee.getStartedOn())
                    .append(System.lineSeparator())
                    .append("Clients")
                    .append(System.lineSeparator());

            employee.getClients()
                    .forEach(e -> resultMessage
                            .append("\t")
                            .append(e.getFullName())
                            .append(System.lineSeparator()));
        }
        resultMessage.append(System.lineSeparator());
        return resultMessage.toString();
    }
}
