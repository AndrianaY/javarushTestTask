package com.javarush.mvcapp.dao;

import com.javarush.mvcapp.domain.User;

import java.util.List;

/**
 * Created by Andriana on 14.11.2016.
 */
public interface UserDao {
    public void addUser(User user);

    public List<User> listUser(Integer offset, Integer maxResults);

    public void removeUser(String id);

    public void updateUser(User user);

    public User getUserById(String id);

    public User getNewUser();

    public Long count();

    public List<User> listUser();

    public List<User> searchUser(String searchText);

//    public Criteria createEntityCriteria();
//
//    public void save();
}
