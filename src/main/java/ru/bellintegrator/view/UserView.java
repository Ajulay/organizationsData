package ru.bellintegrator.view;

import javax.validation.constraints.NotEmpty;

/**
 * Сотрудник
 * */
public class UserView {

    /**
     * Уникальный идентификатор
     * */
    public Long id;

    /**
     * Идентификатор офиса
     * */
    public Long officeId;

    /**
     * Фамилия
     * */
    @NotEmpty
    public String firstName;

    /**
     * Имя
     * */
    public String secondName;

    /**
     * Отчество
     * */
    public String middleName;

    /**
     * Должность
     * */
    @NotEmpty
    public String position;

    /**
     * Телефон
     * */
    public String phone;

    /**
     * Название документа
     * */
    public String docName;

    /**
     * Номер документа
     * */
    public String docNumber;

    /**
     * Дата выдачи документа
     * */
    public String docDate;

    /**
     * Код документа
     * */
    public String docCode;

    /**
     * Гражданство
     * */
    public String citizenshipName;

    /**
     * Код страны
     * */
    public String citizenshipCode;

    /**
     * Сведения о подтверждении личности
     * */
    public Boolean isIdentified;

}
