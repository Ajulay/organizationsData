package ru.bellintegrator.services;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.view.OrganizationView;

import javax.validation.Valid;
import java.util.List;

/**
 * Сервис
 */
@Validated
public interface OrganizationService {

    /**
     * Получить список организаций
     *
     * @return {@OrganizationView}
     */
    List<OrganizationView> getOrganizationsByViewParam(OrganizationView organizationViewParam);

    /**
     * Получить организацию по id
     *
     * @return {@OrganizationView}
     */
    OrganizationView findById(Long id);

    /**
     * Обновить данные организации
     */
    void organizationUpdate(@Valid OrganizationView organizationViewParam);

    /**
     * Сохранить данные новой организации
     */
    void saveNewOrganization(@Valid OrganizationView organizationView);
}
