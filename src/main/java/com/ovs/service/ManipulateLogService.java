package com.ovs.service;


import com.ovs.dao.ManipulateLogMapper;
import com.ovs.entity.ManipulateLog;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ManipulateLogService {
    public ManipulateLog findByUserName(String username);
    public int add(ManipulateLog manipulateLog);
    /*public int edit(ManipulateLogMapper user);*/
    public int delete(String ids);
    public List<ManipulateLog> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
}
