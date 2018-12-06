package ru.bellintegrator.dao.countrydao;

import ru.bellintegrator.model.Country;

import java.util.List;

/**
 * Получение данных о Country'ах из базы данных
 *
 */
public interface CountryDao {

    /**
     * Получение списка Country
     *
     */
    List<Country> getCountries();

    /**
     * Получение Country по коду
     *
     */
    Country loadByCode(String citizenshipCode);
}
