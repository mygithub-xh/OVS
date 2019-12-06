package com.ovs.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ManipulateLog {

    public int id;
    public String userName;//操作人姓名
    public String mpDescribe;//操作描述
    public String createTime;//操作时间
    public String remark;//备用字段

    {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
         createTime=format.format(date);
    }
    //无参构造函数
    public ManipulateLog() {
    }

    public ManipulateLog(String userName, String mpDescribe, String remark) {
        this.userName = userName;
        this.mpDescribe = mpDescribe;
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMpDescribe() {
        return mpDescribe;
    }

    public void setMpDescribe(String mpDescribe) {
        this.mpDescribe = mpDescribe;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ManipulateLog{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", mpDescribe='" + mpDescribe + '\'' +
                ", createTime='" + createTime + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}