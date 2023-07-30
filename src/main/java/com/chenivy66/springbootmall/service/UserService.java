package com.chenivy66.springbootmall.service;

import java.util.List;

import com.chenivy66.springbootmall.dto.UserLoginRequest;
import com.chenivy66.springbootmall.dto.UserQueryPamas;
import com.chenivy66.springbootmall.dto.UserRegisterRequest;
import com.chenivy66.springbootmall.model.User;

public interface UserService {
    Integer register(UserRegisterRequest userRegisterRequest);

    User login(UserLoginRequest userLoginRequest);
    
    User getByUserId(Integer userId);

    User getUserByEmail(String email);
    
    Integer countUsers(UserQueryPamas userQueryPamas);

    List<User> getUsers(UserQueryPamas userQueryPamas);
    

}
