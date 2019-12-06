package com.ovs.service;


import com.ovs.entity.User;
import com.ovs.entity.User_admin;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface AdminService {
    public User_admin findByUserName(String username);
    public int add(User_admin user);
    public int edit(User_admin user);
    public int delete(String ids);
    public List<User_admin> findList(Map<String,Object> queryMap);
    public int getTotal(Map<String,Object> queryMap);
    public User_admin login_check(User_admin user_admin);
}
