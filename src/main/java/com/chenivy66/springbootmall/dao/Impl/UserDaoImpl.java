package com.chenivy66.springbootmall.dao.Impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.chenivy66.springbootmall.dao.UserDao;
import com.chenivy66.springbootmall.dto.UserQueryPamas;
import com.chenivy66.springbootmall.dto.UserRegisterRequest;
import com.chenivy66.springbootmall.model.User;
import com.chenivy66.springbootmall.rowmapper.UserRowMapper;

@Component
public class UserDaoImpl implements UserDao {
    
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {
        String sql="INSERT INTO user (email, password, created_date, last_modified_date)"
                + " VALUES "
                + " (" +
                ":email," +
                ":password," +
                ":createdDate," +
                ":lastModifiedDate" +
                ")";
        
        Date now = new Date();

        Map<String,Object> map=new HashMap<>();
        map.put("email", userRegisterRequest.getEmail());
        map.put("password",userRegisterRequest.getPassword());
        map.put("createdDate",now);
        map.put("lastModifiedDate",now);
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map),keyHolder);

        Integer userId = keyHolder.getKey().intValue();

        return userId;
    }

    @Override
    public User getByUserId(Integer userId) {
        String sql = "select * from user where user_Id=:userId";

        Map<String,Object> map = new HashMap<>();
        map.put("userId", userId);

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        if(userList.size()>0)
        {
            return userList.get(0);
        }
        else
        {
            return null;
        }
        
        
    }

    @Override
    public Integer countUsers(UserQueryPamas userQueryPamas) {
        String sql = "SELECT count(*)"
                    + " from user WHERE 1=1";

        Map<String,Object> map = new HashMap<>();
        addFilterSql(sql,map,userQueryPamas);

        Integer total = namedParameterJdbcTemplate.queryForObject(
            sql,
            map,
            Integer.class
        );

        return total;

    }



    @Override
    public List<User> getUsers(UserQueryPamas userQueryPamas) {
        String sql = "select * from user where 1=1";

        Map<String,Object> map = new HashMap<>();

        addFilterSql(sql, map, userQueryPamas);

        //排序
        if(userQueryPamas.getOrderBy()!=null)
        {
            sql=sql + " order by " + userQueryPamas.getOrderBy();
        }
        
        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        if(userList.size()>0)
        {
            return userList;
        }
        else
        {
            return null;
        }
        
    }

    @Override
    public User getUserByEmail(String email) {
        
        String sql = "select * from user where email=:email";

        Map<String,Object> map = new HashMap<>();
        map.put("email", email);

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        if(userList.size()>0)
        {
            return userList.get(0);
        }
        else
        {
            return null;
        }
    }

    private void addFilterSql(String sql, Map<String, Object> map, UserQueryPamas userQueryPamas) {
   
        //搜尋
        if(userQueryPamas.getSearch()!=null)
        {
            map.put("search","%"+userQueryPamas.getSearch()+"%");
            sql=sql + " AND user_name like :search";
        }
    }

}
