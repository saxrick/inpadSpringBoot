package com.inpad.spring.inpadspringboot.dao;

import com.inpad.spring.inpadspringboot.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private EntityManager entityManager;
    @Override
    @Transactional
    public List<User> getAllUsers() {
        Query query = entityManager.createQuery("from User");
        List<User> userList = query.getResultList();
        return userList;
    }

//    @Override
//    public void saveUser(User user) {
//        User newUser = entityManager.merge(user);
//        user.setId(newUser.getId());
//    }

    @Override
    public User getUser(int id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public void deleteUser(int id) {
        Query query = entityManager.createQuery("delete User where id =:userId");
        query.setParameter("userId", id);
        query.executeUpdate();
    }
}
