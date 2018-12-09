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
    public List<UserView> getUsersByViewParam(UserView userViewParam) throws Exception {
        List<User> users = userDao.loadByViewParam(userViewParam);
        if (users.size() == 0) {
            throw new Exception("No data");
        }
        return mapperFacade.mapAsList(users, UserView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public UserView findById(Long id) throws Exception {
        User user = userDao.loadById(id);
        if (user == null) {
            throw new Exception("No data");
        }
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
}
