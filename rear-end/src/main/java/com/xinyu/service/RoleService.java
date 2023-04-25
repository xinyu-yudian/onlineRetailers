package com.xinyu.service;

import com.xinyu.entity.Role;

public interface RoleService {
    String selRoleAndPower();

    boolean addRole(Role role);

    boolean removeRole(Integer id);

    boolean updateRole(Role role);
}
