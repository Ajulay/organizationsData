package ru.bellintegrator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.util.Response;
import ru.bellintegrator.view.OfficeView;

import javax.ws.rs.Produces;


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
    public String getOfficesByParam(@PathVariable("orgId") long orgId, @RequestBody OfficeView officeView) {

            return new Response("data").sendData();


}
    /**
     * Получает объект Office по id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public String getOfficeById(@PathVariable("id") long id) {

            return  new Response<>("data").sendResult();

    }

    /**
     * Сохраняет новый объект Office
     *
     * @param officeView
     * @return
     */
    @PostMapping("/save")
    public String saveNewOffice(@RequestBody OfficeView officeView){

            return new Response<>("data").sendData();
    }

    /**
     * Обновляет данные запрашиваемого Office
     *
     * @param officeView
     * @return
     */
    @PostMapping("/update")
    public String officeUpdate(@RequestBody OfficeView officeView) {

            return new Response<>("data").toString();

    }

}
