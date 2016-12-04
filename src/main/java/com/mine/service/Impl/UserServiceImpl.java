package com.mine.service.Impl;

import com.mine.dao.UserDao;
import com.mine.model.User;
import com.mine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional // It must be here, in service. NOT IN DAO. IMPORTANT!
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getUsers() {
        return this.userDao.getUsers();
    }
}
