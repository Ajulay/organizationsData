package ru.bellintegrator.dao.organizationdao;

import ru.bellintegrator.model.Organization;
import ru.bellintegrator.view.OrganizationView;

import java.util.List;

public interface OrganizationDao {

    /**
     * Получить Organization по идентификатору
     */
    Organization loadById(Long id);

    /**
     * Сохранить Organization
     */
    void save(Organization organization);

    /**
     * Получить Organization по параметрам
     */
    List<Organization> loadByViewParam(OrganizationView organizationView);

    /**
     * Обновить Organization
     */
    void update(Organization organization);
}
