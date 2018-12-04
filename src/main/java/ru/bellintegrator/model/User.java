package ru.bellintegrator.model;

import javax.persistence.*;

/**
 * Сотрудник
 */
@Entity
@Table(name="\"user\"")
public class User {

    /**
     * Уникальный идентификатор
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /**
     * Ссылка на офис
     */
    @ManyToOne
    @JoinColumn(name = "office_id")
    private Office office;

    /**
     * Фамилия
     */
    @Column(name="first_name", nullable = false)
    private String firstName; //обязательный параметр

    /**
     * Имя
     */
    @Column(name = "second_name")
    private String secondName;

    /**
     * Отчество
     */
    @Column(name="middle_name")
    private String middleName;

    /**
     * Должность
     */
    @Column(name = "pozition", nullable = false)
    private String position; //обязательный параметр

    /**
     * Телефон
     */
    @Column
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
    @Column(name = "country_id")
    private Country country;

    /**
     * Сведения о подтверждении личности
     */
    @Column(name="identified")
    private boolean identified;

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
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
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

    public boolean isIdentified() {
        return identified;
    }

    public void setIdentified(boolean identified) {
        this.identified = identified;
    }
}
