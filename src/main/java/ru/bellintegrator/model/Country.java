package ru.bellintegrator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Страна
 */
@Entity
public class Country {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;

    /**
     * Название страны, не может быть без названия
     */
    @Column(nullable = false)
    private String name;

    /**
     * Код страны, должен быть присвоен
     */
    @Column(nullable = false)
    private String code;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name.trim();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
