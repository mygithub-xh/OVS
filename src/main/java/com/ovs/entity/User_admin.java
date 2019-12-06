package com.ovs.entity;

public class User_admin {
    Integer id;
    String name;
    String loginname;
    String  psw;

    public User_admin() {
    }

    public User_admin(Integer id, String name, String loginname, String psw) {
        this.id = id;
        this.name = name;
        this.loginname = loginname;
        this.psw = psw;
    }

    public User_admin(String loginname, String psw) {
        this.loginname = loginname;
        this.psw = psw;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    @Override
    public String toString() {
        return "User_admin{" +
                "in=" + id +
                ", name='" + name + '\'' +
                ", loginname='" + loginname + '\'' +
                ", psw='" + psw + '\'' +
                '}';
    }
}
