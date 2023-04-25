package com.xinyu.service.impl;

import com.alibaba.fastjson.JSON;
import com.xinyu.entity.Power;
import com.xinyu.entity.Role;
import com.xinyu.mapper.power.PowerMapper;
import com.xinyu.mapper.role.RoleMapper;
import com.xinyu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PowerMapper powerMapper;

    @Autowired
    private Power power;

    public String selRoleAndPower(){
        power = new Power();
        List<Role> roleList = roleMapper.selRoleAndPower();
        Map<String,Object> map1;
        List<Object> list1 = new ArrayList<>();
        for (Role role : roleList) {
            map1 = new HashMap<>();
            map1.put("id", role.getId());
            map1.put("roleName", role.getRoleName());
            map1.put("roleDesc", role.getRoleDesc());
            power.setPid(null);
            power.setId(role.getPid());
            List<Power> powerListo = powerMapper.selPowers(power);
            power.setId(null);
            Map<String,Object> mapo;
            List<Object> listo = new ArrayList<>();
            for (Power powero : powerListo) {
                mapo = new HashMap<>();
                mapo.put("id", powero.getId());
                mapo.put("powerName", powero.getPowerName());
                mapo.put("path", powero.getPath());
                power.setPid(powero.getId());
                List<Power> powerList1 = powerMapper.selPowers(power);
                Map<String,Object> map2;
                List<Object> list2 = new ArrayList<>();
                for (Power power1 : powerList1) {
                    map2 = new HashMap<>();
                    map2.put("id", power1.getId());
                    map2.put("powerName", power1.getPowerName());
                    map2.put("path", power1.getPath());
                    power.setPid(power1.getId());
                    List<Power> powerList2 = powerMapper.selPowers(power);
                    Map<String,Object> map3;
                    List<Object> list3 = new ArrayList<>();
                    for (Power power2 : powerList2) {
                        map3 = new HashMap<>();
                        map3.put("id", power2.getId());
                        map3.put("powerName", power2.getPowerName());
                        map3.put("path", power2.getPath());
                        list3.add(map3);
                        map3 = null;
                    }
                    map2.put("children", JSON.toJSON(list3));
                    list2.add(map2);
                    map2 = null;
                }
                mapo.put("children", JSON.toJSON(list2));
                listo.add(mapo);
                mapo = null;
            }
            map1.put("children", JSON.toJSON(listo));
            list1.add(map1);
            map1 = null;
        }
        return JSON.toJSONString(list1);
    }

        public boolean addRole(Role role){
            return roleMapper.addRole(role)>0;
        }

        public boolean removeRole(Integer id){
            return roleMapper.removeRole(id)>0;
        }

        public boolean updateRole(Role role){
            return roleMapper.updateRole(role)>0;
        }

}
