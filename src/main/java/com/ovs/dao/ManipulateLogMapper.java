package com.ovs.dao;


import com.ovs.entity.ManipulateLog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ManipulateLogMapper {
    public ManipulateLog findByUserName(String username);
    public int add(ManipulateLog manipulateLog);
    /*public int edit(ManipulateLogMapper user);*/
    public int delete(String ids);
    public List<ManipulateLog> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
}
