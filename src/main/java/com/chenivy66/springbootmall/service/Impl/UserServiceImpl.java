package com.chenivy66.springbootmall.service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.chenivy66.springbootmall.dao.UserDao;
import com.chenivy66.springbootmall.dto.UserLoginRequest;
import com.chenivy66.springbootmall.dto.UserQueryPamas;
import com.chenivy66.springbootmall.dto.UserRegisterRequest;
import com.chenivy66.springbootmall.model.User;
import com.chenivy66.springbootmall.service.UserService;

@Component
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        //重複檢查
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());
        
        if(user!=null)
        {
            log.warn("此 email : {} 已被 UserId : {} 註冊",userRegisterRequest.getEmail(),user.getUserId());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
         
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());

        if(user==null)
        {
            log.warn("此 Email : {} 尚未註冊",userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        else
        {
            if(user.getPassword().equals(userLoginRequest.getPassword()))
            {
                return user;
            }
            else
            {
                log.warn("密碼錯誤");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
        
    }


    @Override
    public User getByUserId(Integer userId) {
        return userDao.getByUserId(userId);
    }

    @Override
    public Integer countUsers(UserQueryPamas userQueryPamas) {
        
        return userDao.countUsers(userQueryPamas);
    }

    @Override
    public List<User> getUsers(UserQueryPamas userQueryPamas) {
        
        return userDao.getUsers(userQueryPamas);
    }

    @Override
    public User getUserByEmail(String email) {
        
        return userDao.getUserByEmail(email);
    }
}
