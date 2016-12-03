package com.mine.service.Impl;

import com.mine.dao.ContactDao;
import com.mine.model.Contact;
import com.mine.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("contactService")
@Transactional
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactDao contactDao;

    @Override
    public List<Contact> getContacts() {
        return this.contactDao.getContacts();
    }
}
