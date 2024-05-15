package com.inpad.spring.inpadspringboot.dao;

import com.inpad.spring.inpadspringboot.entity.User;

import java.util.List;

public interface UserDao {
    public List<User> getAllUsers();
//    public void saveUser(User user);
    public User getUser(int id);
    public void deleteUser(int id);
}
