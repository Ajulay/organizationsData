package ru.bellintegrator.dao.docdao;

import ru.bellintegrator.model.Country;
import ru.bellintegrator.model.DocType;

import java.util.List;

/**
 * Получение данных о Doc'ах из базы данных
 *
 */
public interface DocDao {

    /**
     * Получение данных о DocType
     *
     */
    List<DocType> getDocTypes();

    DocType loadByCode(String docCode);
}
