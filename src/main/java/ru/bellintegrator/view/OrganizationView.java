package ru.bellintegrator.view;

import javax.validation.constraints.NotEmpty;
/**
 * Организация
 * */
public class OrganizationView {

    /**
     * Уникальный идентификатор
     * */
    @NotEmpty
    public Long id;

    /**
     * Название
     * */
    @NotEmpty(message = "name cannot be null")
    public String name;

    /**
     * Официальное (юридическое) название
     * */
    @NotEmpty(message = "fullname cannot be null")
    public String fullName;

    /**
     * ИНН
     * */
    @NotEmpty(message = "inn cannot be null")
    public String inn;

    /**
     * КПП
     * */
    @NotEmpty(message = "kpp cannot be null")
    public String kpp;

    /**
     * Адрес
     * */
    @NotEmpty(message = "address cannot be null")
    public String address;

    /**
     * Телефон
     * */
    public String phone;

    /**
     * Состояние организации (работает - прекратило деятельность)
     * */
    public Boolean isActive;


    @Override
    public String toString() {
        return "{" +
                "\"id\": \"" + id +
                "\"; \"name\": \"" + name.trim() +
                "\"; \"fullName\": \"" + fullName.trim() +
                "\"; \"inn\": \"" + inn.trim() +
                "\"; \"kpp\": \"" + kpp.trim() +
                "\"; \"address\": \"" + address.trim() +
                "\"; \"phone: \"" + (phone == null ? "" : phone.trim()) +
                "\"; \"isActive: \"" + isActive +
                "\"}";
    }

}
