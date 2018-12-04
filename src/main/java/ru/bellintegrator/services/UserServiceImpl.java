package ru.bellintegrator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    public UserServiceImpl(UserDao userDao, OfficeDao officeDao) {
        this.userDao = userDao;
        this.officeDao = officeDao;
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
        Office office = officeDao.loadById(userView.officeId);
        Country country = countryDao.loadByCode(userView.citizenshipCode);
        DocType docType = docTypeDao.loadByCode(userView.docCode);
        Doc doc = new Doc();
        SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dsf.parse(userView.docDate);
        doc.setDocDate(date);
        doc.setDocNumber(userView.docNumber);
        doc.setDocType(docType);
        user.setOffice(office);
        user.setFirstName(userView.firstName);
        user.setSecondName(userView.secondName);
        user.setMiddleName(userView.middleName);
        user.setPosition(userView.position);
        user.setCountry(country);
        user.setDoc(doc);
        user.setPhone(userView.phone);
        user.setIdentified(userView.isIdentified);
        dao.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void userUpdate(UserView userView) throws ParseException {
        User user = dao.loadById(userView.id);
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
        dao.update(user);
    }

    private UserView createUserView(User user){
        UserView userView = new UserView();
        userView.id = user.getId();
        userView.officeId = user.getOffice().getId();
        userView.firstName = user.getFirstName();
        userView.secondName = user.getSecondName();
        userView.middleName = user.getMiddleName();
        userView.position = user.getPosition();
        userView.docName = user.getDoc().getDocType().getName();
        userView.docCode = user.getDoc().getDocType().getCode();
        userView.docNumber = user.getDoc().getDocNumber();
        userView.docDate = user.getDoc().getDocDate().toString();
        userView.citizenshipName = user.getCountry().getName();
        userView.citizenshipCode = user.getCountry().getCode();
        userView.phone = user.getPhone();
        userView.isIdentified = user.isIdentified();
        return userView;}
}
