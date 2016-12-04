package com.mine.dao.Impl;

import com.mine.dao.UserDao;
import com.mine.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSessionFactory() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<User> getUsers() {
        return this.getSessionFactory().createQuery("from User", User.class).list();
    }

    @Override
    public User getUserByName(String name) {
        List<User> users = this.getSessionFactory()
                .createQuery("from User where name=?", User.class)
                .setParameter(0, name)
                .list();
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }
}
