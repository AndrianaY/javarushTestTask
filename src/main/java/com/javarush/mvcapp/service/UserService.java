package com.javarush.mvcapp.service;

import com.javarush.mvcapp.domain.User;

import java.util.List;

/**
 * Created by Andriana on 14.11.2016.
 */
public interface UserService {
    public void addUser(User user);

    public List<User> listUser();

    public void removeUser(Integer id);

    public void updateUSer(Integer id);
}
