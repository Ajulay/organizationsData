package ru.bellintegrator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Офис
 */
@Entity
@Table(name = "office")
public class Office {
    /**
     * Уникальный идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Связь с организацией
     */
    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    /**
     * Название офиса
     */
    @Column(length = 150, nullable = false)
    private String name;

    /**
     * Адрес офиса
     */
    @Column(nullable = false)
    private String address;

    /**
     * Телефон
     */
    @Column(length = 16)
    private String phone;

    /**
     * Сведения о функционировании
     */
    @Column(name = "active")
    private Boolean active;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
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
            phone = phone.trim();
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
