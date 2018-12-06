package ru.bellintegrator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.dao.organizationdao.OrganizationDao;
import ru.bellintegrator.model.Organization;
import ru.bellintegrator.model.mapper.MapperFacade;
import ru.bellintegrator.view.OrganizationView;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class OrganizationServiceImpl implements OrganizationService{

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
    public List<OrganizationView> getOrganizationsByViewParam (OrganizationView organizationViewParam){
        List<Organization> organizations = dao.loadByViewParam(organizationViewParam);
        List<OrganizationView> organizationViews = new ArrayList<>();
        for (Organization organization : organizations) {
            organizationViews.add(createOrganizationView(organization));
        }
        return organizationViews;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public OrganizationView findById (Long id){
        Organization organization = dao.loadById(id);
      //  return createOrganizationView(organization);
        return createOrganizationViewByMapper(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void organizationUpdate(OrganizationView organizationViewParam){
        Organization organization = createOrganizationByMapper(organizationViewParam);
        Organization oldOrganization = dao.loadById(organization.getId());
        if(organization.getPhone() == null){
            organization.setPhone(oldOrganization.getPhone());
        }
        if(organization.isActive() == null){
            organization.setActive(oldOrganization.isActive());
        }
        dao.update(organization);
        }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void saveNewOrganization(OrganizationView organizationView){
        Organization organization = createOrganizationByMapper(organizationView);
        dao.save(organization);
    }

    private OrganizationView createOrganizationView(Organization organization){
        OrganizationView organizationView = new OrganizationView();
        organizationView.id = organization.getId();
        organizationView.name = organization.getName();
        organizationView.fullName = organization.getFullName();
        organizationView.inn = organization.getInn();
        organizationView.kpp = organization.getKpp();
        organizationView.address = organization.getAddress();
        organizationView.phone = organization.getPhone();
        organizationView.isActive = organization.isActive();
        return organizationView;
    }

    private OrganizationView createOrganizationViewByMapper(Organization organization){
        OrganizationView organizationView = mapperFacade.map(organization, OrganizationView.class);
        organizationView.isActive = organization.isActive();
        return organizationView;
    }

    private Organization createOrganizationByMapper(OrganizationView organizationView){
        Organization organization = mapperFacade.map(organizationView, Organization.class);
        organization.setActive(organizationView.isActive);
        return organization;
    }
}
