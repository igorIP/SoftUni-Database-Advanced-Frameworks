package app.ccb.services;


import app.ccb.domain.dtos.BankAccountImportDto;
import app.ccb.domain.dtos.BankAccountImportRootDto;
import app.ccb.domain.entities.BankAccount;
import app.ccb.domain.entities.Client;
import app.ccb.repositories.BankAccountRepository;
import app.ccb.repositories.ClientRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final static String BANK_ACCOUNTS_JSON_FILE_PATH =
            "/Users/igor/Desktop/GIT_Projects/SoftUni-DatabaseAdvanced-Frameworks/13.WORKSHOP–MVC PROJECT-SPRING-MVC-SPRING DATA/ColonialCouncilBank/src/main/resources/files/xml/bank-accounts.xml";

    private final BankAccountRepository bankAccountRepository;
    private final ClientRepository clientRepository;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;


    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, ClientRepository clientRepository, FileUtil fileUtil, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.clientRepository = clientRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean bankAccountsAreImported() {
        return this.bankAccountRepository.count() != 0;
    }

    @Override
    public String readBankAccountsXmlFile() throws IOException {
        return this.fileUtil.readFile(BANK_ACCOUNTS_JSON_FILE_PATH);
    }

    @Override
    public String importBankAccounts() throws JAXBException, FileNotFoundException {
        StringBuilder resultMessage = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(BANK_ACCOUNTS_JSON_FILE_PATH))));

        JAXBContext context = JAXBContext.newInstance(BankAccountImportRootDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        BankAccountImportRootDto dtos = (BankAccountImportRootDto) unmarshaller.unmarshal(reader);

        for (BankAccountImportDto dto : dtos.getBankAccountImportDtos()) {
            if (!this.validationUtil.isValid(dto)) {
                resultMessage
                        .append("Error: Incorrect Data!")
                        .append(System.lineSeparator());
                continue;
            }

            Client clientEntity = this.clientRepository.findDistinctFirstByFullNameEquals(dto.getClientName()).orElse(null);
            if (clientEntity == null) {
                resultMessage
                        .append("Error: Incorrect Data!")
                        .append(System.lineSeparator());
                continue;
            }

            BankAccount bankAccountEntity = this.modelMapper.map(dto, BankAccount.class);
            bankAccountEntity.setClient(clientEntity);

            this.bankAccountRepository.saveAndFlush(bankAccountEntity);

            clientEntity.setBankAccount(bankAccountEntity);
            this.clientRepository.saveAndFlush(clientEntity);

            resultMessage.append("Successfully imported  Bank Account")
                    .append("- ")
                    .append(bankAccountEntity.getAccountNumber())
                    .append(System.lineSeparator());
        }
        return resultMessage.toString().trim();
    }
}
