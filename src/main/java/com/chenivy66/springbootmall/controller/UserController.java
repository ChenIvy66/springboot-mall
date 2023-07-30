package com.chenivy66.springbootmall.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chenivy66.springbootmall.constant.ProductCategory;
import com.chenivy66.springbootmall.dto.ProductQueryPamas;
import com.chenivy66.springbootmall.dto.UserQueryPamas;
import com.chenivy66.springbootmall.dto.UserRegisterRequest;
import com.chenivy66.springbootmall.model.Product;
import com.chenivy66.springbootmall.model.User;
import com.chenivy66.springbootmall.service.UserService;
import com.chenivy66.springbootmall.util.Page;

@Validated
@RestController
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        
        Integer userId = userService.register(userRegisterRequest);

        User user = userService.getByUserId(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Integer userId){
        User user = userService.getByUserId(userId);

        if(user!=null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        else 
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/users")
    public ResponseEntity<Page<User>> getUsers(
        @RequestParam(required = false) String search,
        @RequestParam(defaultValue = "created_date") String orderBy
    ){
        UserQueryPamas userQueryPamas = new UserQueryPamas();
        userQueryPamas.setOrderBy(orderBy);
        userQueryPamas.setSearch(search);
        List<User> userList = userService.getUsers(userQueryPamas);
        
        Integer total=userService.countUsers(userQueryPamas);

        Page page=new Page();
        page.setTotal(null);
        page.setResults(userList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }


}
