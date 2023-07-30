package com.chenivy66.springbootmall.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
    
    private Integer userId;
    private String email;

    @JsonIgnore
    private String password;
    
    private Date createdDate;
    private Date lastzModifiedDate;
    
    public Integer getUserId() {
        return userId;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public Date getLastzModifiedDate() {
        return lastzModifiedDate;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public void setLastzModifiedDate(Date lastzModifiedDate) {
        this.lastzModifiedDate = lastzModifiedDate;
    }




}
