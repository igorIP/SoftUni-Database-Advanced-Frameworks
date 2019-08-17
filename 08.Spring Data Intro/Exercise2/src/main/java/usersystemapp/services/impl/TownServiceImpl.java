package usersystemapp.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usersystemapp.domain.entities.models.Country;
import usersystemapp.domain.entities.models.Town;
import usersystemapp.repository.CountryRepository;
import usersystemapp.repository.TownRepository;
import usersystemapp.services.api.TownService;
import usersystemapp.util.FileUtil;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.LongAdder;

@Service
@Transactional
public class TownServiceImpl implements TownService {

    private TownRepository townRepository;
    private CountryRepository countryRepository;
    private FileUtil fileUtil;

    private static final String TOWNS_FIEL_PATH =
            "/Users/igor/Desktop/GIT_Projects/SoftUni-DatabaseAdvanced-Frameworks/08.Spring Data Intro/Exercise2/src/main/resources/towns";

    public TownServiceImpl(final TownRepository townRepository,
                           final CountryRepository countryRepository,
                           final FileUtil fileUtil) {
        this.townRepository = townRepository;
        this.countryRepository = countryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedTowns() {
        if (this.townRepository.count() != 0) return;

        List<String> townsNames = this.fileUtil.getFileInput(TOWNS_FIEL_PATH);

        LongAdder count = new LongAdder();
        count.add(1);
        for (String townsName : townsNames) {
            Town town = new Town();
            town.setName(townsName);
            Country country = this.countryRepository.findById(count.longValue()).get();
            count.increment();
            town.setCountry(country);
            System.out.println();
            this.townRepository.save(town);
        }
    }

//    private Country getRandomAuthor() {
//        Random random = new Random();
//        return this.countryRepository
//                .findById((long) (random.nextInt((int) ((countryRepository.count() - 1) + 1)) + 1)).get();
//    }
}
