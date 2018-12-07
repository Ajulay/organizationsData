package ru.bellintegrator.view;

import javax.validation.constraints.NotEmpty;

/**
 * Тип документа
 */
public class DocTypeView {

    /**
     * Название типа документа по российской квалификации
     */
    @NotEmpty
    public String name;

    /**
     * Уникальный код документа по российской квалификации
     */
    @NotEmpty
    public String code;

    @Override
    public String toString() {
        return "{" +
                "\"name\": \"" + name +
                "\", \"code\": \"" + code +
                "\"}";
    }
}
