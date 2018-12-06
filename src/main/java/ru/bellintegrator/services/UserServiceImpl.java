package ru.bellintegrator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.dao.countrydao.CountryDao;
import ru.bellintegrator.dao.docdao.DocDao;
import ru.bellintegrator.dao.officedao.OfficeDao;
import ru.bellintegrator.dao.userdao.UserDao;
import ru.bellintegrator.model.*;
import ru.bellintegrator.view.UserView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;
    private final OfficeDao officeDao;
    private final CountryDao countryDao;
    private final DocDao docDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, OfficeDao officeDao, CountryDao countryDao, DocDao docDao) {
        this.userDao = userDao;
        this.officeDao = officeDao;
        this.countryDao = countryDao;
        this.docDao = docDao;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public List<UserView> getUsersByViewParam(UserView userViewParam) {
        List<User> users = userDao.loadByViewParam(userViewParam);
        List<UserView> userViews = new ArrayList<>();
        for (User user : users) {
            userViews.add(createUserView(user));
        }
        return userViews;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public UserView findById(Long id) {
        User user = userDao.loadById(id);
        return createUserView(user);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void saveNewUser(UserView userView) throws ParseException {
        User user = new User();
        if(userView.officeId != null){
            Office office = officeDao.loadById(userView.officeId);
            user.setOffice(office);
        }
        if(userView.citizenshipName != null || userView.citizenshipCode != null){
            Country country = null;
            if(userView.citizenshipCode != null){
                country = countryDao.loadByCode(userView.citizenshipCode);
            }else {
                country = countryDao.loadByName(userView.citizenshipName);
            }
            user.setCountry(country);
        }
        DocType docType = null;
        if(userView.docCode !=null || userView.docName != null){
            if(userView.docCode != null){
                 docType  = docDao.loadByCode(userView.docCode);
            }else{
                 docType  = docDao.loadByName(userView.docName);
            }

        }
        Doc doc = null;
        if(userView.docNumber != null){
            doc = new Doc();
            doc.setDocNumber(userView.docNumber);
        }

        if(userView.docDate != null){
            SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dsf.parse(userView.docDate);
            if(doc == null){
                doc = new Doc();
            }
            doc.setDocDate(date);
        }
        if (docType != null){
            doc.setDocType(docType);
        }
        user.setFirstName(userView.firstName);
        if(userView.secondName != null){
            user.setSecondName(userView.secondName);
        }
        if(userView.middleName != null){
            user.setMiddleName(userView.middleName);
        }
        user.setPosition(userView.position);
        if(doc != null){
            user.setDoc(doc);
        }
        if(userView.phone != null){
            user.setPhone(userView.phone);
        }
        if(userView.isIdentified != null){
            user.setIdentified(userView.isIdentified);
        }

        userDao.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void userUpdate(UserView userView) throws ParseException {
        User user = userDao.loadById(userView.id);
        if (userView.officeId != null){
            Office office = user.getOffice();
            user.setOffice(office);
        }
        if(userView.citizenshipCode != null){
            user.getCountry().setCode(userView.citizenshipCode);
        }
        if(userView.citizenshipName != null){
            user.getCountry().setName(userView.citizenshipName);
        }
        Doc doc = user.getDoc();
        if(userView.docCode != null){
            doc.getDocType().setCode(userView.docCode);
            user.setDoc(doc);
        }
        if(userView.docName != null){
            doc.getDocType().setName(userView.docName);
            user.setDoc(doc);
        }
        if(userView.docNumber != null){
            doc.setDocNumber(userView.docNumber);
            user.setDoc(doc);
        }
        if(userView.docDate != null){
            SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM-dd");
            doc.setDocDate(dsf.parse(userView.docDate));
            user.setDoc(doc);
        }
        if(userView.firstName != null){
            user.setFirstName(userView.firstName);
        }
        if(userView.secondName != null){
            user.setSecondName(userView.secondName);
        }
        if(userView.middleName != null){
            user.setMiddleName(userView.middleName);
        }
        if(userView.position != null){
            user.setPosition(userView.position);
        }
        if(userView.phone != null){
            user.setPhone(userView.phone);
        }
        if(userView.isIdentified != null){
            user.setIdentified(userView.isIdentified);
        }
        userDao.update(user);
    }

    private UserView createUserView(User user){
        UserView userView = new UserView();
        userView.id = user.getId();
        userView.officeId = user.getOffice().getId();
        userView.firstName = user.getFirstName();
        if(user.getSecondName()!=null){
            userView.secondName = user.getSecondName();
        }
        if(user.getMiddleName()!=null){
            userView.middleName = user.getMiddleName();
        }
        userView.position = user.getPosition();
        if(user.getDoc() != null){
            if(user.getDoc().getDocType() !=null){
                userView.docName = user.getDoc().getDocType().getName();
            }
            if(user.getDoc().getDocType()!=null){
                userView.docCode = user.getDoc().getDocType().getCode();
            }
            if(user.getDoc().getDocNumber()!=null){
                userView.docNumber = user.getDoc().getDocNumber();
            }
            if(user.getDoc().getDocDate() != null){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String sDate = sdf.format(user.getDoc().getDocDate());
                userView.docDate = sDate;
            }
        }
        if(user.getCountry() != null){
            userView.citizenshipName = user.getCountry().getName();
        }
        if(user.getCountry() != null){
            userView.citizenshipCode = user.getCountry().getCode();
        }
        if(user.getPhone() != null){
            userView.phone = user.getPhone();
        }
        if(user.isIdentified() != null){
            userView.isIdentified = user.isIdentified();
        }
        return userView;}
}
