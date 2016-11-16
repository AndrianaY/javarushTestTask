package com.javarush.mvcapp.service;

import com.javarush.mvcapp.dao.UserDao;
import com.javarush.mvcapp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Andriana on 14.11.2016.
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }
    @Transactional
    @Override
    public List<User> listUser() {
        return userDao.listUser();
    }
    @Transactional
    @Override
    public void removeUser(Integer id) {
        userDao.removeUser(id);
    }
    @Transactional
    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
    @Transactional
    @Override
    public User getUser(Integer id) {
        return userDao.getUserById(id);
    }
    @Transactional
    @Override
    public User getUser() {
        return userDao.getNewUser();
    }
}
