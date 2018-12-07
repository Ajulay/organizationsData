package ru.bellintegrator.dao.userdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.model.Country;
import ru.bellintegrator.model.DocType;
import ru.bellintegrator.model.User;
import ru.bellintegrator.view.UserView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User loadById(Long id) {
        return em.find(User.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(User user) {
        em.persist(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> loadByViewParam(UserView userView) {
        return em.createQuery(buildCriteria(userView)).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(User user) {
        em.merge(user);
    }

    private CriteriaQuery<User> buildCriteria(UserView userView) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);
        List<Predicate> predicates = new ArrayList<Predicate>();

        if (userView.id != null) {
            predicates.add(builder.equal(userRoot.get("id"), userView.id));
        }

        if (userView.officeId != null) {
            predicates.add(builder.equal(userRoot.get("office").get("id"), userView.officeId));
        }
//
        if (userView.firstName != null && !"".equals(userView.firstName)) {
            predicates.add(builder.equal(userRoot.get("firstName"), userView.firstName));
        }

        if (userView.secondName != null && !"".equals(userView.secondName)) {
            predicates.add(builder.equal(userRoot.get("secondName"), userView.secondName));
        }

        if (userView.middleName != null && !"".equals(userView.middleName)) {
            predicates.add(builder.equal(userRoot.get("middleName"), userView.middleName));
        }

        if (userView.position != null && !"".equals(userView.position)) {
            predicates.add(builder.equal(userRoot.get("position"), userView.position));
        }

        if (userView.phone != null && !"".equals(userView.phone)) {
            predicates.add(builder.equal(userRoot.get("phone"), userView.phone));
        }

        if (userView.docNumber != null && !"".equals(userView.docNumber)) {
            predicates.add(builder.equal(userRoot.get("doc").get("docNumber"), userView.docNumber));
        }

        if (userView.docDate != null && !"".equals(userView.docDate)) {
            predicates.add(builder.equal(userRoot.get("doc").get("docDate"), userView.docDate));
        }

        if (userView.docName != null && !"".equals(userView.docName) ||
                userView.docCode != null && !"".equals(userView.docCode)
                ) {
            predicates.add(builder.equal(userRoot.get("doc").get("docType").get("id"), getIdDoc(userView.docCode, userView.docName)));
        }

        if (userView.citizenshipName != null && !"".equals(userView.citizenshipName) ||
                userView.citizenshipCode != null && !"".equals(userView.citizenshipCode)
                ) {
            predicates.add(builder.equal(userRoot.get("country").get("id"), getIdCountry(userView.citizenshipName, userView.citizenshipCode)));
        }

        if (userView.isIdentified != null) {
            predicates.add(builder.equal(userRoot.get("identified"), userView.isIdentified));
        }

        criteria.where(predicates.toArray(new Predicate[]{}));
        // criteria.select(userRoot);
        return criteria;
    }

    private Long getIdDoc(String docCode, String docName) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<DocType> criteria = builder.createQuery(DocType.class);
        Root<DocType> docRoot = criteria.from(DocType.class);
        if (docCode != null && !"".equals(docCode)) {
            criteria.where(builder.equal(docRoot.get("code"), docCode));
        } else if (docName != null && !"".equals(docName)) {
            criteria.where(builder.equal(docRoot.get("name"), docName));
        }

        TypedQuery<DocType> query = em.createQuery(criteria);
        Long id = query.getSingleResult().getId();

        return id;
    }

    private Long getIdCountry(String citizenshipName, String citizenshipCode) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Country> criteria = builder.createQuery(Country.class);
        Root<Country> countryRoot = criteria.from(Country.class);
        if (citizenshipName != null && !"".equals(citizenshipName)) {
            criteria.where(builder.equal(countryRoot.get("name"), citizenshipName));
        } else if (citizenshipCode != null && !"".equals(citizenshipCode)) {
            criteria.where(builder.equal(countryRoot.get("code"), citizenshipCode));
        }
        TypedQuery<Country> query = em.createQuery(criteria);

        return query.getSingleResult().getId();
    }
}
