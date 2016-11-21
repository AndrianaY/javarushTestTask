package com.javarush.mvcapp.service;

import com.javarush.mvcapp.domain.User;
import java.util.List;

/**
 * Created by Andriana on 14.11.2016.
 */
public interface UserService {
    public void addUser(User user);

    public List<User> listUser(Integer offset, Integer maxResults);

    public void removeUser(String id);

    public void updateUser(User user);

    public User getUser(String id);

    public User getUser();

    public List<User> searchUser(String searchText, Integer offset, Integer maxResults);
//
    public Integer count();

    public List<User> listUser();
//    public void save();
}
