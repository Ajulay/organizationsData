package ru.bellintegrator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.util.Response;
import ru.bellintegrator.view.OrganizationView;

import javax.ws.rs.Produces;


/**
 * Controler управления информацией об организациях
 */

@RestController
@RequestMapping("/api/organization")
@Produces("application/json")
public class OrganizationController {

    private final OrganizationService organizationService;


    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;

    }

    /**
     * Получает все объекты Organization по указанным параметрам
     *
     *@param organizationViewParam
     * @return
     */
    @PostMapping("/list")
    public String organizations(@RequestBody OrganizationView organizationViewParam) {
        return new Response("data").sendData();
    }

    /**
     * Получает объект Organization по id
     *
     *@param id
     * @return
     */
    @GetMapping("/{id}")
    public String getOrganizationById(@PathVariable("id") long id) {

        return new Response<>("data").sendData();

    }

    /**
     * Обновляет объект Organization по указанным параметрам
     *
     *@param organizationView
     * @return
     */
    @PostMapping("/update")
    public String getOrganizationById(@RequestBody OrganizationView organizationView) {

        return new Response<>("data").sendResult();
    }

    /**
     * Сохраняет новый объект Organization
     *
     * @param organizationView
     * @return
     */
    @PostMapping("/save")
    public String saveNewOrganization(@RequestBody OrganizationView organizationView){

        return new Response<>("data").sendResult();

    }

}
