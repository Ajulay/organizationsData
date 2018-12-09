package ru.bellintegrator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.services.DocTypeService;
import ru.bellintegrator.view.DocTypeView;

import javax.ws.rs.Produces;
import java.util.List;

/**
 * Controller управления справочной информацией по типам документов
 */
@RestController
@RequestMapping("/api/")
@Produces("application/json")
public class DocTypeController {

    private final DocTypeService docTypeService;

    @Autowired
    public DocTypeController(DocTypeService docTypeService) {
        this.docTypeService = docTypeService;
    }

    /**
     * Получает все объекты DocTypeView
     *
     * @return 'List<DocTypeView>'
     */
    @PostMapping("/docs")
    public List<DocTypeView> getDocs() throws Exception {
        return docTypeService.getDocTypes();
    }
}
