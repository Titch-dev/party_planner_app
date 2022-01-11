package com.ldp.partyplanner.data.mapper;

import com.ldp.partyplanner.models.Admin;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminMapper implements RowMapper<Admin> {

    public Admin mapRow(ResultSet resultSet, int i) throws SQLException {
        Admin admin = new Admin();
        admin.setAdminId(resultSet.getInt("admin_id"));
        admin.setFirstName(resultSet.getString("first_name"));
        admin.setLast_name(resultSet.getString("last_name"));
        admin.setEmail(resultSet.getString("email"));
        admin.setPassword(resultSet.getString("password"));
        return admin;
    }
}
