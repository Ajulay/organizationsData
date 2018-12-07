package ru.bellintegrator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.services.OfficeService;
import ru.bellintegrator.view.OfficeView;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import java.util.List;


/**
 * Controler управления информацией об офисах
 */
@RestController
@RequestMapping("api/office")
@Produces("application/json")
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    /**
     * Получает все объекты Office по указанным параметрам
     *
     * @param orgId
     * @param officeView
     * @return
     */
    @PostMapping("/list/{orgId}")
    public List<OfficeView> getOfficesByParam(@PathVariable("orgId") Long orgId, @RequestBody OfficeView officeView) throws Exception {
        if (orgId == null && officeView.orgId == null) {
            throw new Exception("Where is orgId?");
        }
        if (officeView.orgId == null) {
            officeView.orgId = orgId;
        }
        return officeService.getOfficesByOfficeViewParam(officeView);
    }

    /**
     * Получает объект Office по id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public OfficeView getOfficeById(@PathVariable("id") Long id) {
        OfficeView officeView;
        try {
            officeView = officeService.getOfficeById(id);
        } catch (NullPointerException e) {
            return new OfficeView();
        }
        return officeView;
    }

    /**
     * Сохраняет новый объект Office
     *
     * @param officeView
     * @return
     */
    @PostMapping("/save")
    public void saveNewOffice(@Valid @RequestBody OfficeView officeView) {
        officeService.saveNewOffice(officeView);
    }

    /**
     * Обновляет данные запрашиваемого Office
     *
     * @param officeView
     * @return
     */
    @PostMapping("/update")
    public void officeUpdate(@Valid @RequestBody OfficeView officeView) throws Exception {
        if (officeView.id == null) {
            throw new Exception("Where is id?");
        }
        officeService.officeUpdate(officeView);
    }
}
