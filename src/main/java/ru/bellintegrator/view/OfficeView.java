package ru.bellintegrator.view;

import javax.validation.constraints.NotEmpty;
/**
 * Офис
 * */
public class OfficeView {

    /**
     * Уникальный идентификатор
     * */
    @NotEmpty
    public Long id;

    /**
     * Идентификатор организации
     * */
    @NotEmpty
    public Long orgId;

    /**
     * Название офиса
     * */
    @NotEmpty
    public String name;

    /**
     * Адрес
     * */
    @NotEmpty
    public String address;

    /**
     * Телефон
     * */
    public String phone;

    /**
     * Состояние офиса (работает - прекратил деятельность)
     * */
    public Boolean isActive;

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id +
                "\", \"orgId\": \"" + orgId +
                "\", \"name\": \"" + name +
                "\", \"address\": \"" + address +
                "\", \"phone\": \"" + phone +
                "\", \"isActive\": \"" + isActive +
                "\"}";
    }
}
