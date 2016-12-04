package com.mine.dao;

import com.mine.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();
    User getUserByName(String name);
}
