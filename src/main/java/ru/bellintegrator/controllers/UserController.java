package ru.bellintegrator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.view.UserView;

import javax.ws.rs.Produces;
import java.util.List;


/**
 * Controler управления информацией об сотрудниках
 */
@RestController
@RequestMapping("/api/user")
@Produces("application/json")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Получает все объекты User по указанным параметрам
     *
     * @param userView
     * @return
     */
    @PostMapping("/list")
    public List<UserView> getUsersByParam(@RequestBody UserView userView) {
        return null;
    }

    /**
     * Получает объект User по id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public UserView getUserById(@PathVariable("id") long id) {
        if (id <= 0) {
            return null;
        }
        return null;
    }

    /**
     * Сохраняет новый объект User
     *
     * @param userView
     * @return
     */
    @PostMapping("/save")
    public void saveNewUser(@RequestBody UserView userView) {
        if (userView.firstName == null || "".equals(userView.firstName)
                || userView.officeId <= 0
                || userView.position == null || "".equals(userView.firstName)) {
            return;
        }
    }

    /**
     * Обновляет данные запрашиваемого User
     *
     * @param userView
     * @return
     */
    @PostMapping("/update")
    public void userUpdate(@RequestBody UserView userView) {
        if (userView.id <= 0 ||
                userView.firstName == null || "".equals(userView.firstName) ||
                userView.position == null || "".equals(userView.position)) {
            return;
        }
    }
}
