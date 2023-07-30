package com.chenivy66.springbootmall.rowmapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.chenivy66.springbootmall.model.User;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        
        User user = new User();

        user.setEmail(resultSet.getString("email"));
        user.setCreatedDate(resultSet.getTimestamp("created_date"));
        user.setLastzModifiedDate(resultSet.getTimestamp("last_modified_date"));
        user.setUserId(resultSet.getInt("user_id"));
        user.setPassword(resultSet.getString("password"));

        return user;
        
    }

    
    
}
