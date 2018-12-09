package ru.bellintegrator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.services.CountryService;
import ru.bellintegrator.view.CountryView;

import javax.ws.rs.Produces;
import java.util.List;

/**
 * Controller управления справочной информацией по странам
 */

@RestController
@RequestMapping("/api/")
@Produces("application/json")
public class CountryController {
    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    /**
     * Получает все объекты CountryView
     *
     * @return 'List<CountryView>'
     */

    @PostMapping("countries")
    public List<CountryView> getCountries() throws Exception {
        return countryService.getCountries();
    }
}