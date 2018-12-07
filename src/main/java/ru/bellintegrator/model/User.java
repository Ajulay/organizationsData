package ru.bellintegrator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Сотрудник
 */
@Entity
@Table(name = "_user")
public class User {

    /**
     * Уникальный идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Ссылка на офис
     */
    @ManyToOne
    @JoinColumn(name = "office_id", nullable = false)
    private Office office;

    /**
     * Фамилия
     */
    @Column(length = 50, name = "first_name", nullable = false)
    private String firstName;

    /**
     * Имя
     */
    @Column(length = 50, name = "second_name")
    private String secondName;

    /**
     * Отчество
     */
    @Column(length = 50, name = "middle_name")
    private String middleName;

    /**
     * Должность
     */
    @Column(length = 50, name = "pozition", nullable = false)
    private String position;

    /**
     * Телефон
     */
    @Column(length = 16)
    private String phone;

    /**
     * Документ, удостоверяющий личность
     */
    @OneToOne
    @JoinColumn(name = "doc_id")
    private Doc doc;

    /**
     * Гражданство
     */
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    /**
     * Сведения о подтверждении личности
     */
    @Column(name = "identified")
    private Boolean identified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public String getFirstName() {
        return firstName.trim();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        if (secondName != null) {
            secondName.trim();
        }
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        if (middleName != null) {
            middleName.trim();
        }
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position.trim();
    }

    public void setPosition(String position) {
        this.position = position;
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

    public Doc getDoc() {
        return doc;
    }

    public void setDoc(Doc doc) {
        this.doc = doc;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Boolean isIdentified() {
        return identified;
    }

    public void setIdentified(Boolean identified) {
        this.identified = identified;
    }
}
