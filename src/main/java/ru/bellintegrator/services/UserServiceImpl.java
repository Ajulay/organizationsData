package ru.bellintegrator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.dao.countrydao.CountryDao;
import ru.bellintegrator.dao.docdao.DocDao;
import ru.bellintegrator.dao.officedao.OfficeDao;
import ru.bellintegrator.dao.userdao.UserDao;
import ru.bellintegrator.model.User;
import ru.bellintegrator.model.mapper.MapperFacade;
import ru.bellintegrator.view.UserView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final OfficeDao officeDao;
    private final CountryDao countryDao;
    private final DocDao docDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public UserServiceImpl(UserDao userDao, OfficeDao officeDao, CountryDao countryDao, DocDao docDao, MapperFacade mapperFacade) {
        this.userDao = userDao;
        this.officeDao = officeDao;
        this.countryDao = countryDao;
        this.docDao = docDao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public List<UserView> getUsersByViewParam(UserView userViewParam) {
        List<User> users = userDao.loadByViewParam(userViewParam);
        return mapperFacade.mapAsList(users, UserView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public UserView findById(Long id) {
        User user = userDao.loadById(id);
        return mapperFacade.map(user, UserView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void saveNewUser(UserView userView) {
        User user = mapperFacade.map(userView, User.class);
        userDao.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void userUpdate(UserView userView) {
        User user = mapperFacade.map(userView, User.class);
        userDao.update(user);
    }

    private UserView createUserView(User user) {
        UserView userView = new UserView();
        userView.id = user.getId();
        userView.officeId = user.getOffice().getId();
        userView.firstName = user.getFirstName();
        if (user.getSecondName() != null) {
            userView.secondName = user.getSecondName();
        }
        if (user.getMiddleName() != null) {
            userView.middleName = user.getMiddleName();
        }
        userView.position = user.getPosition();
        if (user.getDoc() != null) {
            if (user.getDoc().getDocType() != null) {
                userView.docName = user.getDoc().getDocType().getName();
            }
            if (user.getDoc().getDocType() != null) {
                userView.docCode = user.getDoc().getDocType().getCode();
            }
            if (user.getDoc().getDocNumber() != null) {
                userView.docNumber = user.getDoc().getDocNumber();
            }
            if (user.getDoc().getDocDate() != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String sDate = sdf.format(user.getDoc().getDocDate());
                userView.docDate = sDate;
            }
        }
        if (user.getCountry() != null) {
            userView.citizenshipName = user.getCountry().getName();
        }
        if (user.getCountry() != null) {
            userView.citizenshipCode = user.getCountry().getCode();
        }
        if (user.getPhone() != null) {
            userView.phone = user.getPhone();
        }
        if (user.isIdentified() != null) {
            userView.isIdentified = user.isIdentified();
        }
        return userView;
    }
}
