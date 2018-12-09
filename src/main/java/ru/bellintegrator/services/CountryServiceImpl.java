package ru.bellintegrator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.dao.countrydao.CountryDao;
import ru.bellintegrator.model.Country;
import ru.bellintegrator.model.mapper.MapperFacade;
import ru.bellintegrator.view.CountryView;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class CountryServiceImpl implements CountryService {

    private final CountryDao countryDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public CountryServiceImpl(CountryDao countryDao, MapperFacade mapperFacade) {
        this.countryDao = countryDao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public List<CountryView> getCountries() throws Exception {
        List<Country> countries = countryDao.getCountries();
        if (countries.size() == 0) {
            throw new Exception("No data");
        }
        return mapperFacade.mapAsList(countries, CountryView.class);
    }
}
