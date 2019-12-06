package com.ovs.service.impl;


import com.ovs.dao.UserMapper;
import com.ovs.entity.User;
import com.ovs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public int add(User user) {
        // TODO Auto-generated method stub
        return userMapper.add(user);
    }

    @Override
    public int edit(User user) {
        // TODO Auto-generated method stub
        return userMapper.edit(user);
    }

    @Override
    public User login_check(User user) {
        return userMapper.login_check(user);
    }

    @Override
    public int delete(String ids) {
        // TODO Auto-generated method stub
        return userMapper.delete(ids);
    }

    @Override
    public List<User> findList(Map<String, Object> queryMap) {
        // TODO Auto-generated method stub
        return userMapper.findList(queryMap);
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return userMapper.findAll();
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        // TODO Auto-generated method stub
        return userMapper.getTotal(queryMap);
    }

    @Override
    public User findByUserName(String username) {
        // TODO Auto-generated method stub
        return userMapper.findByUserName(username);
    }

}
