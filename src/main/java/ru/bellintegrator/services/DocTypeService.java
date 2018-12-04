package ru.bellintegrator.services;

import ru.bellintegrator.view.DocTypeView;
import java.util.List;

/**
 * Сервис
 * */
public interface DocTypeService {

    /**
     * Получить список типов документов
     *
     * @return {@DocTypeView}
     */
    List<DocTypeView> getDocTypes();
}
