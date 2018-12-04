package ru.bellintegrator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.model.Country;
import ru.bellintegrator.view.CountryView;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class CountryServiceImpl implements CountryService{

    private final CountryDao countryDao;

    @Autowired
    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public List<CountryView> getCountries(){
        List<Country> countries = dao.getCountries();
        List<CountryView> countryViews = new ArrayList<>();
        for (Country country : countries) {
            countryViews.add(createCountryView(country));
        }
        return countryViews;
    }

    private CountryView createCountryView(Country country){
        CountryView countryView = new CountryView();
        countryView.name = country.getName();
        countryView.code = country.getCode();
        return countryView;}
}
