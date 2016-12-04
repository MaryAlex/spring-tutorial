package com.mine.dao.Impl;

import com.mine.dao.ContactDao;
import com.mine.model.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("contactDao")
public class ContactDaoImpl implements ContactDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSessionFactory() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Contact> getContacts() {
        return this.getSessionFactory().createQuery("from Contact", Contact.class).list();
    }
}
