package ru.bellintegrator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.view.OfficeView;

import javax.ws.rs.Produces;
import java.util.List;


/**
 * Controler управления информацией об офисах
 *
 * */
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
    public List<OfficeView> getOfficesByParam(@PathVariable("orgId") Long orgId, @RequestBody OfficeView officeView) {
            return null;
    }

    /**
     * Получает объект Office по id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public OfficeView getOfficeById(@PathVariable("id") Long id) {
            return  null;
    }

    /**
     * Сохраняет новый объект Office
     *
     * @param officeView
     * @return
     */
    @PostMapping("/save")
    public void saveNewOffice(@RequestBody OfficeView officeView){
    }

    /**
     * Обновляет данные запрашиваемого Office
     *
     * @param officeView
     * @return
     */
    @PostMapping("/update")
    public void officeUpdate(@RequestBody OfficeView officeView) {
    }
}
