package com.bookstore.mappers;

import com.bookstore.models.UserEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserEntity> {
    @Override
    public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserEntity(
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("role"));
    }
}
