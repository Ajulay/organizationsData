package ru.bellintegrator.controllers;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.view.OrganizationView;

import javax.ws.rs.Produces;
import java.util.List;


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
    public List<OrganizationView> organizations(@RequestBody OrganizationView organizationViewParam) {
        return null;
    }

    /**
     * Получает объект Organization по id
     *
     *@param id
     * @return
     */
    @GetMapping("/{id}")
    public OrganizationView getOrganizationById(@PathVariable("id") long id) {
        return null;
    }

    /**
     * Обновляет объект Organization по указанным параметрам
     *
     *@param organizationView
     * @return
     */
    @PostMapping("/update")
    public void getOrganizationById(@RequestBody OrganizationView organizationView) {
    }

    /**
     * Сохраняет новый объект Organization
     *
     * @param organizationView
     * @return
     */
    @PostMapping("/save")
    public void saveNewOrganization(@RequestBody OrganizationView organizationView){
    }
}
