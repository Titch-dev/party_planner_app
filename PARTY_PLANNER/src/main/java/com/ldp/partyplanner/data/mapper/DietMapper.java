package com.ldp.partyplanner.data.mapper;


import com.ldp.partyplanner.models.Diet;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DietMapper implements RowMapper<Diet> {

    public Diet mapRow(ResultSet rs, int rowNum) throws SQLException {
        Diet diet = new Diet();
        diet.setDietId(rs.getInt("diet_id"));
        diet.setRequirement(rs.getString("requirement"));
        return diet;
    }
}
