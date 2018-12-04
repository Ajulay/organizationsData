package ru.bellintegrator.services;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.view.UserView;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

/**
 * Сервис
 * */
@Validated
public interface UserService {

    /**
     * Получить список сотрудников по id офиса и дополнительным параметрам
     *
     * @return {@UserView}
     */
    List<UserView> getUsersByViewParam(UserView userViewParam);

    /**
     * Получить сотрудника по id
     *
     * @return {@UserView}
     */
    UserView findById(Long id);

    /**
     * Сохранить данные нового сотрудника
     *
     */
    void saveNewUser(@Valid UserView userView) throws ParseException;

    /**
     * Обновить данные сотрудника
     *
     */
    void userUpdate(@Valid UserView userView) throws ParseException;
}
