package ru.bellintegrator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.dao.organizationdao.OrganizationDao;
import ru.bellintegrator.model.Organization;
import ru.bellintegrator.model.mapper.MapperFacade;
import ru.bellintegrator.view.OrganizationView;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationDao dao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao dao, MapperFacade mapperFacade) {
        this.dao = dao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public List<OrganizationView> getOrganizationsByViewParam(OrganizationView organizationViewParam) throws Exception {
        List<Organization> organizations = dao.loadByViewParam(organizationViewParam);
        if (organizations.size() == 0) {
            throw new Exception("No data");
        }
        return mapperFacade.mapAsList(organizations, OrganizationView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public OrganizationView findById(Long id) throws Exception {
        Organization organization = dao.loadById(id);
        if (organization == null) {
            throw new Exception("No data");
        }
        return mapperFacade.map(organization, OrganizationView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void organizationUpdate(OrganizationView organizationView) {
        Organization organization = mapperFacade.map(organizationView, Organization.class);
        dao.update(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void saveNewOrganization(OrganizationView organizationView) {
        Organization organization = mapperFacade.map(organizationView, Organization.class);
        dao.save(organization);
    }
}
