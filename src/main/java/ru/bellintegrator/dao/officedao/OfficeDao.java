package ru.bellintegrator.dao.officedao;

import ru.bellintegrator.model.Office;
import ru.bellintegrator.view.OfficeView;

import java.util.List;

/**
 * Получение данных об Office'ах из базы данных
 */
public interface OfficeDao {

    /**
     * Получить Office по идентификатору
     */
    Office loadById(Long id);

    /**
     * Сохранить Office
     */
    void save(Office office);

    /**
     * Обновить Office
     */
    void update(Office office);

    /**
     * Получить список Office'ов по id организации
     */
    List<Office> loadByOrgId(Long orgId);

    /**
     * Получить список Office'ов по переданным параметрам
     */
    List<Office> loadByOfficeViewParam(OfficeView officeViewParam);
}
