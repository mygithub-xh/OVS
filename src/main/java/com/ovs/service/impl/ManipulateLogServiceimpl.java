package com.ovs.service.impl;


import com.ovs.dao.AdminMapper;
import com.ovs.dao.ManipulateLogMapper;
import com.ovs.entity.ManipulateLog;
import com.ovs.entity.User_admin;
import com.ovs.service.AdminService;
import com.ovs.service.ManipulateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class ManipulateLogServiceimpl implements ManipulateLogService {
    @Autowired
    private ManipulateLogMapper manipulateLogMapper;
    @Override
    public ManipulateLog findByUserName(String username) {
        // TODO Auto-generated method stub
        return manipulateLogMapper.findByUserName(username);
    }

    @Override
    public int add(ManipulateLog manipulateLog) {
        return manipulateLogMapper.add(manipulateLog);
    }

    @Override
    public List<ManipulateLog> findList(Map<String,Object> queryMap) {
        // TODO Auto-generated method stub
        return manipulateLogMapper.findList(queryMap);
    }
    @Override
    public int getTotal(Map<String, Object> queryMap) {
        // TODO Auto-generated method stub
        return manipulateLogMapper.getTotal(queryMap);
    }

    @Override
    public int delete(String ids) {
        // TODO Auto-generated method stub
        return manipulateLogMapper.delete(ids);
    }

}
