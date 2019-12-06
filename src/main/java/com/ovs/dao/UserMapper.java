package com.ovs.dao;


import com.ovs.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    public User findByUserName(String username);
    public User login_check(User user);
    public int add(User student);
    public int edit(User student);
    public int delete(String ids);
    public List<User> findList(Map<String,Object> queryMap);
    public List<User> findAll();
    public int getTotal(Map<String,Object> queryMap);
}
