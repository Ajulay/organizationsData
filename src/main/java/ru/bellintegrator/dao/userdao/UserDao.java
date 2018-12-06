package ru.bellintegrator.dao.userdao;


import ru.bellintegrator.model.User;
import ru.bellintegrator.view.UserView;

import java.util.List;

/**
 * DAO для работы с User
 */
public interface UserDao {

        /**
         * Получить User по идентификатору
         *
         */
        User loadById(Long id);

        /**
         * Сохранить User
         *
         */
        void save(User user);

        /**
         * Получить список User'ов по параметрам
         *
         */
        List<User> loadByViewParam(UserView userView);

        /**
         * Обновить User
         *
         */
        void update(User user);
}

