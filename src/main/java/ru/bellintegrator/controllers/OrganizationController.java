package ru.bellintegrator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.services.OrganizationServiceImpl;
import ru.bellintegrator.view.OrganizationView;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import java.util.List;


/**
 * Controler управления информацией об организациях
 */
@RestController
@RequestMapping("/api/organization")
@Produces("application/json")
public class OrganizationController {

    private final OrganizationServiceImpl organizationService;

    @Autowired
    public OrganizationController(OrganizationServiceImpl organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * Получает все объекты Organization по указанным параметрам
     *
     *@param organizationView
     * @return
     */
    @PostMapping("/list")
    public List<OrganizationView> organizations(@RequestBody OrganizationView organizationView) throws Exception {
        if(organizationView.name == null || "".equals(organizationView.name)){
            throw new Exception("Where is name?");
        }
        return organizationService.getOrganizationsByViewParam(organizationView);
    }

    /**
     * Получает объект Organization по id
     *
     *@param id
     * @return
     */
    @GetMapping("/{id}")
    public OrganizationView getOrganizationById(@PathVariable("id") Long id) {
        return organizationService.findById(id);
    }

    /**
     * Обновляет объект Organization по указанным параметрам
     *
     *@param organizationView
     */
    @PostMapping("/update")
    public void getOrganizationById(@Valid @RequestBody OrganizationView organizationView) throws Exception {
        if(organizationView.id == null){
            throw new Exception("Where is id?");
        }
        organizationService.organizationUpdate(organizationView);
    }

    /**
     * Сохраняет новый объект Organization
     *
     * @param organizationView
     */
    @PostMapping("/save")
    public void saveNewOrganization(@Valid @RequestBody OrganizationView organizationView){
        organizationService.saveNewOrganization(organizationView);
    }
}
