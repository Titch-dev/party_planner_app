package com.ldp.partyplanner.data.mapper;

import com.ldp.partyplanner.models.Guest;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GuestMapper implements RowMapper<Guest> {

    public Guest mapRow(ResultSet rs, int rowNum) throws SQLException {
        Guest guest = new Guest();
        guest.setGuestId(rs.getInt("guest_id"));
        guest.setFirstName(rs.getString("first_name"));
        guest.setLastName(rs.getString("last_name"));
        guest.setIsLead(rs.getBoolean("is_lead"));
        guest.setGroupId(rs.getInt("group_id"));
        return guest;
    }
}
