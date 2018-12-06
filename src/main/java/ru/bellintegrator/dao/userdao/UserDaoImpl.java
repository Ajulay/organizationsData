package ru.bellintegrator.dao.userdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.model.User;
import ru.bellintegrator.view.UserView;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) { this.em = em; }
    /**
     * {@inheritDoc}
     */
    @Override
    public User loadById(Long id) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(User user) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> loadByViewParam(UserView userView) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(User user) {

    }
}
