package ru.bellintegrator.dao.docdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.model.Country;
import ru.bellintegrator.model.DocType;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class DocDaoImpl implements DocDao{

    private final EntityManager em;

    @Autowired
    public DocDaoImpl(EntityManager em) { this.em = em; }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DocType> getDocTypes() {
        TypedQuery<DocType> query = em.createQuery("SELECT dt FROM DocType dt", DocType.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DocType loadByCode(String docCode) {
        return null;
    }
}
