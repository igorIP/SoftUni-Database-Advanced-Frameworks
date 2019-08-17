package usersystemapp.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usersystemapp.domain.entities.models.Country;
import usersystemapp.domain.entities.models.Town;
import usersystemapp.repository.CountryRepository;
import usersystemapp.repository.TownRepository;
import usersystemapp.services.api.CountryService;
import usersystemapp.util.FileUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;
    private FileUtil fileUtil;

    private final static String COUNTRIES_FILE_PATH =
            "/Users/igor/Desktop/GIT_Projects/SoftUni-DatabaseAdvanced-Frameworks/08.Spring Data Intro/Exercise2/src/main/resources/countries.txt";

    public CountryServiceImpl(final CountryRepository countryRepository,
                              final FileUtil fileUtil) {
        this.countryRepository = countryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedCountries() {

        if (this.countryRepository.count() != 0) return;

        List<String> countriesNames = this.fileUtil.getFileInput(COUNTRIES_FILE_PATH);
        for (String countriesName : countriesNames) {
            Country country = new Country();
            country.setName(countriesName);

            this.countryRepository.save(country);

        }
    }


}
