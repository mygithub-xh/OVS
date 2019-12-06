package com.ovs.service.impl;


import com.ovs.dao.AdminMapper;
import com.ovs.dao.UserMapper;
import com.ovs.entity.User;
import com.ovs.entity.User_admin;
import com.ovs.service.AdminService;
import com.ovs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper userMapper;
    @Override
    public User_admin findByUserName(String username) {
        // TODO Auto-generated method stub
        return userMapper.findByUserName(username);
    }


    @Override
    public int add(User_admin user) {
        // TODO Auto-generated method stub
        return userMapper.add(user);
    }
    @Override
    public List<User_admin> findList(Map<String,Object> queryMap) {
        // TODO Auto-generated method stub
        return userMapper.findList(queryMap);
    }
    @Override
    public int getTotal(Map<String, Object> queryMap) {
        // TODO Auto-generated method stub
        return userMapper.getTotal(queryMap);
    }

    @Override
    public User_admin login_check(User_admin user_admin) {
        return userMapper.login_check(user_admin);
    }

    @Override
    public int edit(User_admin user) {
        // TODO Auto-generated method stub
        return userMapper.edit(user);
    }
    @Override
    public int delete(String ids) {
        // TODO Auto-generated method stub
        return userMapper.delete(ids);
    }

}
