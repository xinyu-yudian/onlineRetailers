package com.xinyu.mapper;

import com.xinyu.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    User login(@Param("username") String username,@Param("password") String password);
    List<User> getUserList(User user);
    int addUser(User user);
    int updateUser(User user);
    int removeUser(Integer id);
    int updateUserState(@Param("id") Integer id,@Param("state") Integer state);
}
