package ru.bellintegrator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.dao.docdao.DocDao;
import ru.bellintegrator.model.DocType;
import ru.bellintegrator.model.mapper.MapperFacade;
import ru.bellintegrator.view.DocTypeView;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class DocTypeServiceImpl implements DocTypeService {

    private final DocDao docDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public DocTypeServiceImpl(DocDao docDao, MapperFacade mapperFacade) {
        this.docDao = docDao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public List<DocTypeView> getDocTypes() {
        List<DocType> docTypes = docDao.getDocTypes();
        return mapperFacade.mapAsList(docTypes, DocTypeView.class);
    }
}
