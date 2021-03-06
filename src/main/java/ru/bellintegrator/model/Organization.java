package ru.bellintegrator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Организация
 */
@Entity
@Table(name = "organization")
public class Organization {

    /**
     * Уникальный идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название организации, не может быть без названия
     */
    @Column(length = 50, nullable = false)
    private String name;

    /**
     * Полное(юридическое) название организации
     */
    @Column(length = 150, name = "fullname", nullable = false)
    private String fullName;

    /**
     * Инн организации, не может отсутствовать
     */
    @Column(length = 10, nullable = false)
    private String inn;

    /**
     * КПП организации, не может отсутствовать
     */
    @Column(length = 9, nullable = false)
    private String kpp;

    /**
     * Адрес организации, не может отсутствовать
     */
    @Column(nullable = false)
    private String address;

    /**
     * Телефон организации
     */
    @Column(length = 16)
    private String phone;

    /**
     * Сведения о ведении деятельности
     */
    @Column
    private Boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        if (name != null) {
            name = name.trim();
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        if (fullName != null) {
            fullName = fullName.trim();
        }
        return fullName;
    }

    public void setFullName(String fullname) {
        this.fullName = fullname;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        if (address != null) {
            address = address.trim();
        }
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        if (phone != null) {
            phone.trim();
        }
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
