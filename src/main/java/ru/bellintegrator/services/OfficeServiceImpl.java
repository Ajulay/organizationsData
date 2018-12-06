package ru.bellintegrator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.dao.officedao.OfficeDao;
import ru.bellintegrator.dao.organizationdao.OrganizationDao;
import ru.bellintegrator.model.Office;
import ru.bellintegrator.model.Organization;
import ru.bellintegrator.view.OfficeView;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class OfficeServiceImpl implements OfficeService{

    private final OfficeDao officeDao;
    private final OrganizationDao organizationDao;

    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao, OrganizationDao organizationDao) {
        this.officeDao = officeDao;
        this.organizationDao = organizationDao;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public List<OfficeView> getOfficesByOrgId(Long orgId) {
        List<Office> offices = officeDao.loadByOrgId(orgId);
        List<OfficeView> officeViews = new ArrayList<>();
        for (Office office: offices) {
            officeViews.add(createOfficeView(office));
        }
        return officeViews;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public List<OfficeView> getOfficesByOfficeViewParam(OfficeView officeViewParam) {
        List<Office> offices = officeDao.loadByOfficeViewParam(officeViewParam);
        List<OfficeView> officeViews = new ArrayList<>();
        for (Office office: offices) {
            officeViews.add(createOfficeView(office));
        }
        return officeViews;
    }
    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public OfficeView getOfficeById(Long id) {
        Office office = officeDao.loadById(id);

        return createOfficeView(office);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void saveNewOffice(OfficeView officeViewParam) {
        Office office = new Office();
        Organization organization = organizationDao.loadById(officeViewParam.orgId);
        office.setOrganization(organization);
        office.setName(officeViewParam.name);
        office.setAddress(officeViewParam.address);
        office.setPhone(officeViewParam.phone);
        office.setActive(officeViewParam.isActive);
        officeDao.save(office);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void officeUpdate(OfficeView officeViewParam) {
        Office office = officeDao.loadById(officeViewParam.id);
        if(officeViewParam.orgId != null){
        Organization organization = organizationDao.loadById(officeViewParam.orgId);
        office.setOrganization(organization);
        }
        office.setName(officeViewParam.name);
        office.setAddress(officeViewParam.address);
        if(officeViewParam.phone != null) {
            office.setPhone(officeViewParam.phone);
        }
        if(officeViewParam.isActive != null) {
            office.setActive(officeViewParam.isActive);
        }
    }

    private OfficeView createOfficeView(Office office){
        OfficeView officeView = new OfficeView();
        officeView.id = office.getId();
        officeView.orgId = office.getOrganization().getId();
        officeView.name = office.getName();
        officeView.address = office.getAddress();
        officeView.phone = office.getPhone();
        officeView.isActive = office.isActive();
        return officeView;
    }
}
