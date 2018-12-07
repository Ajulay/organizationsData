package ru.bellintegrator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.dao.officedao.OfficeDao;
import ru.bellintegrator.dao.organizationdao.OrganizationDao;
import ru.bellintegrator.model.Office;
import ru.bellintegrator.model.mapper.MapperFacade;
import ru.bellintegrator.view.OfficeView;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDao officeDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao, OrganizationDao organizationDao, MapperFacade mapperFacade) {
        this.officeDao = officeDao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public List<OfficeView> getOfficesByOrgId(Long orgId) {
        List<Office> offices = officeDao.loadByOrgId(orgId);
        return mapperFacade.mapAsList(offices, OfficeView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public List<OfficeView> getOfficesByOfficeViewParam(OfficeView officeViewParam) {
        List<Office> offices = officeDao.loadByOfficeViewParam(officeViewParam);
        return mapperFacade.mapAsList(offices, OfficeView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public OfficeView getOfficeById(Long id) {
        Office office = officeDao.loadById(id);
        return mapperFacade.map(office, OfficeView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void saveNewOffice(OfficeView officeView) {
        Office office = mapperFacade.map(officeView, Office.class);
        officeDao.save(office);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void officeUpdate(OfficeView officeView) {
        Office office = mapperFacade.map(officeView, Office.class);
        officeDao.update(office);
    }
}
