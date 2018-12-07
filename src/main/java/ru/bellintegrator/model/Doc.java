package ru.bellintegrator.model;


import javax.persistence.*;
import java.util.Date;

/**
 * Документ
 */
@Entity
public class Doc {

    /**
     * Уникальный идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Номер документа
     */
    @Column(length = 16, name = "doc_number")
    private String docNumber;

    /**
     * Дата документа
     */
    @Column(name = "doc_date")
    @Temporal(TemporalType.DATE)
    private Date docDate;

    /**
     * Тип документа, обязательный параметр
     */
    @ManyToOne
    @JoinColumn(name = "doc_type_id")
    private DocType docType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDocNumber() {
        if (docNumber != null) {
            docNumber.trim();
        }
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public DocType getDocType() {
        return docType;
    }

    public void setDocType(DocType docType) {
        this.docType = docType;
    }
}
