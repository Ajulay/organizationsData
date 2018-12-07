package ru.bellintegrator.view;

import javax.validation.constraints.NotEmpty;

/**
 * Страна
 */
public class CountryView {

    /**
     * Название страны
     */
    @NotEmpty
    public String name;

    /**
     * Уникальный код страны по российской классификации
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
