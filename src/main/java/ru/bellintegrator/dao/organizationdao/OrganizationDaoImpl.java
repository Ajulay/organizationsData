package ru.bellintegrator.dao.organizationdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.model.Organization;
import ru.bellintegrator.view.OrganizationView;

import javax.persistence.EntityManager;
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
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager em;

    @Autowired
    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization loadById(Long id) {
        return em.find(Organization.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Organization organization) {
        em.persist(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> loadByViewParam(OrganizationView organizationView) {
        CriteriaQuery<Organization> criteriaQuery = buildCriteria(organizationView);
        return em.createQuery(criteriaQuery).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Organization organization) {
        em.merge(organization);
    }

    private CriteriaQuery<Organization> buildCriteria(OrganizationView organizationView) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class);

        Root<Organization> organization = criteria.from(Organization.class);

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (organizationView.id != null) {
            predicates.add(builder.equal(organization.get("id"), organizationView.id));
        }
        if (organizationView.name != null && !"".equals(organizationView.name)) {
            predicates.add(builder.equal(organization.get("name"), organizationView.name));
        }
        if (organizationView.fullName != null && !"".equals(organizationView.fullName)) {
            predicates.add(builder.equal(organization.get("fullName"), organizationView.fullName));
        }
        if (organizationView.inn != null && !"".equals(organizationView.inn)) {
            predicates.add(builder.equal(organization.get("inn"), organizationView.inn));
        }
        if (organizationView.kpp != null && !"".equals(organizationView.kpp)) {
            predicates.add(builder.equal(organization.get("kpp"), organizationView.kpp));
        }
        if (organizationView.address != null && !"".equals(organizationView.address)) {
            predicates.add(builder.equal(organization.get("address"), organizationView.address));
        }
        if (organizationView.phone != null && !"".equals(organizationView.phone)) {
            predicates.add(builder.equal(organization.get("phone"), organizationView.phone));
        }
        if (organizationView.isActive != null) {
            predicates.add(builder.equal(organization.get("active"), organizationView.isActive));
        }

        criteria.where(predicates.toArray(new Predicate[]{}));

        return criteria;
    }
}
