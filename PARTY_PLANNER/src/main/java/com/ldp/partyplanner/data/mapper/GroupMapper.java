package com.ldp.partyplanner.data.mapper;

import com.ldp.partyplanner.models.Group;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupMapper implements RowMapper<Group> {

    @Override
    public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
        Group group = new Group();
        group.setGroupId(rs.getInt("group_id"));
        group.setEmail(rs.getString("email"));
        group.setPassword(rs.getString("password"));
        group.setGroupSize(rs.getInt("group_size"));
        return group;
    }

}
