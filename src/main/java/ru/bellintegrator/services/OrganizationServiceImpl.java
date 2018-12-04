package ru.bellintegrator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.model.Organization;
import ru.bellintegrator.view.OrganizationView;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class OrganizationServiceImpl implements OrganizationService{

    private final OrganizationDao dao;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao dao) {
        this.dao = dao;
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
        return createOrganizationView(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void organizationUpdate(OrganizationView organizationViewParam){
        Organization organization = dao.loadById(organizationViewParam.id);
        organization.setName(organizationViewParam.name);
        organization.setFullName(organizationViewParam.fullName);
        organization.setInn(organizationViewParam.inn);
        organization.setKpp(organizationViewParam.kpp);
        organization.setAddress(organizationViewParam.address);
        if(organizationViewParam.phone != null){
            organization.setPhone(organizationViewParam.phone);
        }
        if(organizationViewParam.isActive != null){
            organization.setActive(organizationViewParam.isActive);
        }
        dao.update(organization);
        }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void saveNewOrganization(OrganizationView organizationView){
        Organization organization = new Organization();
        organization.setName(organizationView.name);
        organization.setFullName(organizationView.fullName);
        organization.setInn(organizationView.inn);
        organization.setKpp(organizationView.kpp);
        organization.setAddress(organizationView.address);
        organization.setPhone(organizationView.phone);
        organization.setActive(organizationView.isActive);
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
        return organizationView;}
}
