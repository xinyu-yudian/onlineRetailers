package com.xinyu.service.impl;

import com.xinyu.entity.User;
import com.xinyu.mapper.UserMapper;
import com.xinyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User login(String username, String password){
       return userMapper.login(username, password);
    }

    @Override
    public List<User> getUserList(User user) {
        return userMapper.getUserList(user);
    }
    public boolean addUser(User user){
        return userMapper.addUser(user)>0;
    }
    public boolean updateUser(User user){
        return userMapper.updateUser(user)>0;
    }
    public boolean removeUser(Integer id){
        return userMapper.removeUser(id)>0;
    }
    public boolean updateUserState(Integer id, Boolean stateB){
        int stateI = stateB ? 1 : 0;
        return userMapper.updateUserState(id,stateI)>0;
    }
}
