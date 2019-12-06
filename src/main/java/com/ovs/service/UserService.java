package com.ovs.service;


import com.ovs.entity.User;
import org.springframework.stereotype.Service;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;

@Service
public interface UserService {
    public User findByUserName(String username);
    public int add(User student);
    public int edit(User student);
    public User login_check(User user);
    public int delete(String ids);
    public List<User> findList(Map<String,Object> queryMap);
    public List<User> findAll();
    public int getTotal(Map<String,Object> queryMap);

}
