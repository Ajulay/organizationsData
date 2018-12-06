package ru.bellintegrator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.services.UserService;
import ru.bellintegrator.view.UserView;
import javax.validation.Valid;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Controller управления информацией об сотрудниках
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
     * Получает всех сотрудников по указанным параметрам
     *
     * @param userView
     * @return
     */
    @PostMapping("/list")
    public List<UserView> getUsersByParam(@RequestBody UserView userView) throws Exception {
        if(userView.officeId == null){
            throw new Exception("Where is office id?");
        }
        return userService.getUsersByViewParam(userView);
    }

    /**
     * Получает сотрудника по id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public UserView getUserById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    /**
     * Сохраняет нового сотрудника
     *
     * @param userView
     * @return
     */
    @PostMapping("/save")
    public void saveNewUser(@Valid @RequestBody UserView userView) throws Exception {
        if(userView.officeId == null){
            throw new Exception("Where is office id?");
        }
        userService.saveNewUser(userView);
    }

    /**
     * Обновляет данные запрашиваемого сотрудника
     *
     * @param userView
     * @return
     */
    @PostMapping("/update")
    public void userUpdate(@Valid @RequestBody UserView userView) throws Exception {
        if(userView.id == null){
            throw new Exception("Where is id?");
        }
        userService.userUpdate(userView);
    }
}
