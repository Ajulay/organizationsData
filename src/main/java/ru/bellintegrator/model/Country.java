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

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    /**
     * Название страны, не может быть без названия
     */
    @Column(length = 35, nullable = false)
    private String name;

    /**
     * Код страны, должен быть присвоен
     */
    @Column(length = 3, nullable = false)
    private String code;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getCode() {
        if (code != null) {
            code = code.trim();
        }
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
