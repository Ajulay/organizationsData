package ru.bellintegrator.services;

import ru.bellintegrator.view.CountryView;

import java.util.List;

/**
 * Сервис
 */
public interface CountryService {

    /**
     * Получить список стран
     *
     * @return {@CountryView}
     */
    List<CountryView> getCountries() throws Exception;
}
