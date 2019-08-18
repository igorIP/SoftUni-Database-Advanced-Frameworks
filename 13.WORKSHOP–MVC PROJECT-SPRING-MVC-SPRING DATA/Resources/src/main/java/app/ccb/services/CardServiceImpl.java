package app.ccb.services;


import app.ccb.domain.dtos.CardImportDto;
import app.ccb.domain.dtos.CardImportRootDto;
import app.ccb.domain.entities.BankAccount;
import app.ccb.domain.entities.Card;
import app.ccb.repositories.BankAccountRepository;
import app.ccb.repositories.CardRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;

@Service
public class CardServiceImpl implements CardService {

    private final static String CARD_JSON_FILE_PATH =
            "/Users/igor/Desktop/GIT_Projects/SoftUni-DatabaseAdvanced-Frameworks/13.WORKSHOP–MVC PROJECT-SPRING-MVC-SPRING DATA/ColonialCouncilBank/src/main/resources/files/xml/cards.xml";


    private final CardRepository cardRepository;
    private final BankAccountRepository bankAccountRepository;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public CardServiceImpl(CardRepository cardRepository, BankAccountRepository bankAccountRepository, FileUtil fileUtil, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.cardRepository = cardRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean cardsAreImported() {
        return this.cardRepository.count() != 0;
    }

    @Override
    public String readCardsXmlFile() throws IOException {
        return this.fileUtil.readFile(CARD_JSON_FILE_PATH);
    }

    @Override
    public String importCards() throws FileNotFoundException, JAXBException {
        StringBuilder resultMessage = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(CARD_JSON_FILE_PATH))));

        JAXBContext context = JAXBContext.newInstance(CardImportRootDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        CardImportRootDto dtos = (CardImportRootDto) unmarshaller.unmarshal(reader);

        for (CardImportDto dto : dtos.getCardImportDtos()) {
            if (!this.validationUtil.isValid(dto)) {
                resultMessage
                        .append("Error: Incorrect Data!")
                        .append(System.lineSeparator());
                continue;
            }

            Card cardEntity = this.modelMapper.map(dto, Card.class);
            BankAccount bankAccountEntity = this.bankAccountRepository.findFirstByAccountNumberEquals(dto.getAccountNumber().trim()).orElse(null);
            if (bankAccountEntity == null) {
                resultMessage
                        .append("Error: Incorrect Data!")
                        .append(System.lineSeparator());
                continue;
            }
            cardEntity.setBankAccount(bankAccountEntity);

            this.cardRepository.saveAndFlush(cardEntity);

            resultMessage.append("Successfully imported Card")
                    .append("- ")
                    .append(cardEntity.getCardNumber())
                    .append(System.lineSeparator());
        }
        return resultMessage.toString().trim();
    }
}
