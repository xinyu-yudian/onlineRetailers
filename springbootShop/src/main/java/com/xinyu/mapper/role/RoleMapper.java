package com.xinyu.mapper.role;

import com.xinyu.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    List<Role> selRoleAndPower();

    int addRole(Role role);

    int removeRole(Integer id);

    int updateRole(Role role);
}
