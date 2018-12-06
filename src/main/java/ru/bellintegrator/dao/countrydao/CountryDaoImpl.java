package ru.bellintegrator.dao.countrydao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.model.Country;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class CountryDaoImpl implements CountryDao {

    private final EntityManager em;

    @Autowired
    public CountryDaoImpl(EntityManager em) { this.em = em; }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Country> getCountries() {
        TypedQuery<Country> query = em.createQuery("SELECT c FROM Country c", Country.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Country loadByCode(String code) {
        TypedQuery<Country> query = em.createQuery("SELECT c FROM Country c WHERE c.code =: code", Country.class);
        query.setParameter("code", code);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Country loadByName(String name) {
        TypedQuery<Country> query = em.createQuery("SELECT c FROM Country c WHERE c.name =: name", Country.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
