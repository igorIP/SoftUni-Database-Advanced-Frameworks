package app.ccb.services;

import app.ccb.domain.dtos.ClientsImportDto;
import app.ccb.domain.entities.Client;
import app.ccb.domain.entities.Employee;
import app.ccb.repositories.ClientRepository;
import app.ccb.repositories.EmployeeRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ClientServiceImpl implements ClientService {

    private final static String CLIENTS_JSON_FILE_PATH =
            "/Users/igor/Desktop/GIT_Projects/SoftUni-DatabaseAdvanced-Frameworks/13.WORKSHOP–MVC PROJECT-SPRING-MVC-SPRING DATA/ColonialCouncilBank/src/main/resources/files/json/clients.json";

    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final ModelMapper modelMapper;


    public ClientServiceImpl(ClientRepository clientRepository, EmployeeRepository employeeRepository, FileUtil fileUtil, ValidationUtil validationUtil, Gson gson, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean clientsAreImported() {
        return this.clientRepository.count() != 0;
    }

    @Override
    public String readClientsJsonFile() throws IOException {
        return this.fileUtil.readFile(CLIENTS_JSON_FILE_PATH).trim();
    }

    @Override
    public String importClients(String clients) {
        StringBuilder result = new StringBuilder();
        //(use gson) clientsJson -> importClientsDto
        ClientsImportDto[] clientsImportDto = this.gson.fromJson(clients, ClientsImportDto[].class);
        for (ClientsImportDto dto : clientsImportDto) {
            if (!this.validationUtil.isValid(dto)) {
                result
                        .append("Error: Incorrect Data!")
                        .append(System.lineSeparator());
                continue;

            }
            //(use modelMapper) dto -> entity
            Client clientEntity = this.modelMapper.map(dto, Client.class);

            StringBuilder fullNameBuilder = new StringBuilder();
            String fullName = fullNameBuilder
                    .append(dto.getFirstName())
                    .append(" ")
                    .append(dto.getLastName())
                    .toString().trim();
            clientEntity.setFullName(fullName);

            final List<String> appointedEmployee = Arrays.asList(dto.getAppointedEmployee().split(("\\s+")));
            Employee employeeEntity = this.employeeRepository
                    .findFirstByFirstNameEqualsAndLastNameEquals(appointedEmployee.get(0), appointedEmployee.get(1)).orElse(null);
            if (employeeEntity == null) {
                result
                        .append("Error: Incorrect Data!")
                        .append(System.lineSeparator());
                continue;
            }
            List<Client> clientsEntity = employeeEntity.getClients();
            clientsEntity.add(clientEntity);
            employeeEntity.setClients(clientsEntity);

            this.clientRepository.saveAndFlush(clientEntity);
            this.employeeRepository.saveAndFlush(employeeEntity);

            result.append("Successfully imported Client ")
                    .append("- ")
                    .append(clientEntity.getFullName())
                    .append(System.lineSeparator());
        }
        return result.toString().trim();
    }

    @Override
    public String exportFamilyGuy() {
        Client familyGuy = this.clientRepository.findFamilyGuy();
        return familyGuy.getFullName();
    }
}
