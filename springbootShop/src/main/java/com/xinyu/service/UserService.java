package com.xinyu.service;

import com.xinyu.entity.User;

import java.util.List;

public interface UserService {
    User login(String username, String password);
    List<User> getUserList(User user);
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean removeUser(Integer id);
    boolean updateUserState(Integer id, Boolean stateB);
}
