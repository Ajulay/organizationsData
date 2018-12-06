package ru.bellintegrator.dao.officedao;

import ru.bellintegrator.model.Office;
import ru.bellintegrator.view.OfficeView;

import java.util.List;

/**
 * Получение данных об Office'ах из базы данных
 *
 */
public interface OfficeDao {

//    /**
//     * Получить все объекты Office
//     *
//     * @return
//     */
//    List<Office> all();

    /**
     * Получить Office по идентификатору
     *
     */
    Office loadById(Long id);
//
//    /**
//     * Получить Office по названию
//     *
//     * @param name
//     * @return
//     */
//    Office loadByName(String name);

    /**
     * Сохранить Office
     *
     */
    void save(Office office);

    /**
     * Обновить Office
     *
     */
    void update(Office office);

    /**
     * Получить список Office'ов по id организации
     *
     */
    List<Office> loadByOrgId(Long orgId);

    /**
     * Получить список Office'ов по переданным параметрам
     *
     */
    List<Office> loadByOfficeViewParam(OfficeView officeViewParam);
}
