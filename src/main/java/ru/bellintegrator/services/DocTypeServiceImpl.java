package ru.bellintegrator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.model.DocType;
import ru.bellintegrator.view.DocTypeView;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class DocTypeServiceImpl implements DocTypeService{

    private final DocTypeDao docTypeDao;

    @Autowired
    public DocTypeServiceImpl(DocTypeDao docTypeDao) {
        this.docTypeDao = docTypeDao;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public List<DocTypeView> getDocTypes(){
        List<DocType> docTypes = dao.getDocTypes();
        List<DocTypeView> docTypeViews = new ArrayList<>();
        for (DocType docType : docTypes) {
            docTypeViews.add(createDocTypeView(docType));
        }
        return docTypeViews;
    }

    private DocTypeView createDocTypeView(DocType docType){
        DocTypeView docTypeView = new DocTypeView();
        docTypeView.name = docType.getName();
        docTypeView.code = docType.getCode();
        return docTypeView;}
}
