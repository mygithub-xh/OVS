package com.ovs.entity;

import org.springframework.stereotype.Component;

@Component
public class User {
    Integer  user_id;
    String  name;
    String  psw;
    String loginname;
    String email;
    String phone;
    String sex;
    String photo;
    public User() {
    }


    public User(String loginname, String psw) {
        this.psw = psw;
        this.loginname = loginname;
    }

    public User(Integer user_id, String name, String psw, String loginname, String email, String phone, String sex) {
        this.user_id = user_id;
        this.name = name;
        this.psw = psw;
        this.loginname = loginname;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPassword() {
        return psw;
    }

    public void setPassword(String password) {
        this.psw = password;
    }

    public Integer getId() {
        return user_id;
    }

    public void setId(Integer id) {
        this.user_id = id;
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

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", psw='" + psw + '\'' +
                ", loginname='" + loginname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
