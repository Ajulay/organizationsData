package ru.bellintegrator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.util.Response;
import ru.bellintegrator.view.UserView;

import javax.ws.rs.Produces;


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
    public String getUsersByParam(@RequestBody UserView userView) {


        return new Response("data").sendData();
    }

    /**
     * Получает объект User по id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") long id) {
        if (id <= 0) {
            return new Response<>("Введите корректный 'id'").sendError();
        }
        return new Response<>("data").sendResult();
    }

    /**
     * Сохраняет новый объект User
     *
     * @param userView
     * @return
     */
    @PostMapping("/save")
    public String saveNewUser(@RequestBody UserView userView) {

        if (userView.firstName == null || userView.firstName.equals("") ||
                userView.officeId <= 0 ||
                userView.position == null || userView.firstName.equals("")
                ) {
            return new Response<>("Введите обязательные реквизиты").sendError();
        }
        return new Response<>("data").sendData();

    }

    /**
     * Обновляет данные запрашиваемого User
     *
     * @param userView
     * @return
     */
    @PostMapping("/update")
    public String userUpdate(@RequestBody UserView userView) {
        if (userView.id <= 0 ||
                userView.firstName == null || userView.firstName.equals("") ||
                userView.position == null || userView.position.equals("")) {


            return new Response<>("Введите обязательные реквизит: id, firstName, position").sendError();

        }
        return new Response<>("data").sendResult();

    }

}
