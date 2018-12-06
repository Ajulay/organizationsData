package ru.bellintegrator.services;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.view.OfficeView;

import javax.validation.Valid;
import java.util.List;
/**
 * Сервис
 * */
@Validated
public interface OfficeService {

    /**
     * Получить список офисов по переданным параметрам
     *
     * @return {@OfficeView}
     */
    List<OfficeView> getOfficesByOfficeViewParam(OfficeView officeViewParam);

    /**
     * Получить офис по id
     *
     * @return {@OfficeView}
     */
    OfficeView getOfficeById(Long id);

    /**
     * Сохранить данные нового офиса
     *
     */
    void saveNewOffice(@Valid OfficeView officeViewParam);

    /**
     * Обновить данные офиса
     *
     */
    void officeUpdate(@Valid OfficeView officeViewParam);

    /**
     * Получить список офисов по id организации
     *
     */
    List<OfficeView> getOfficesByOrgId(Long orgId);
}
