package com.javarush.mvcapp.dao;

import com.javarush.mvcapp.domain.User;

import java.util.List;

/**
 * Created by Andriana on 14.11.2016.
 */
public interface UserDao {
    public void addUser(User user);

    public List<User> listUser();

    public void removeUser(Integer id);

    public void updateUser(User user);

    public User getUserById(Integer id);

    public User getNewUser();
}
