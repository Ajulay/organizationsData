package ru.bellintegrator.dao.officedao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.model.Office;
import ru.bellintegrator.view.OfficeView;

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
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office loadById(Long id) {
        return em.find(Office.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Office office) {
        em.persist(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Office office) {
        em.merge(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> loadByOrgId(Long orgId) {
        TypedQuery<Office> query = em.createQuery("SELECT o FROM Office o WHERE o.organization =:orgId", Office.class);
        query.setParameter("orgId", orgId);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> loadByOfficeViewParam(OfficeView officeViewParam) {
        CriteriaQuery<Office> criteriaQuery = buildCriteria(officeViewParam);
        TypedQuery<Office> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    private CriteriaQuery<Office> buildCriteria(OfficeView officeView) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteria = builder.createQuery(Office.class);
        Root<Office> office = criteria.from(Office.class);
        List<Predicate> predicates = new ArrayList<Predicate>();

        if (officeView.id != null && !"".equals(officeView.id)) {
            predicates.add(builder.equal(office.get("id"), officeView.id));
        }
        if (officeView.name != null && !"".equals(officeView.name)) {
            predicates.add(builder.equal(office.get("name"), officeView.name));
        }
        if (officeView.orgId != null && !"".equals(officeView.orgId)) {
            predicates.add(builder.equal(office.get("organization").get("id"), officeView.orgId));
        }

        if (officeView.address != null && !"".equals(officeView.address)) {
            predicates.add(builder.equal(office.get("address"), officeView.address));
        }
        if (officeView.phone != null && !"".equals(officeView.phone)) {
            predicates.add(builder.equal(office.get("phone"), officeView.phone));
        }
        if (officeView.isActive != null) {
            predicates.add(builder.equal(office.get("active"), officeView.isActive));
        }
        criteria.where(predicates.toArray(new Predicate[]{}));

        return criteria;
    }

}
