package com.ldp.partyplanner.data.mapper;

import com.ldp.partyplanner.models.EventGuest;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventGuestMapper implements RowMapper<EventGuest> {

    public EventGuest mapRow(ResultSet rs, int i) throws SQLException{
        EventGuest eg = new EventGuest();
        eg.setEventId(rs.getInt("event_id"));
        eg.setGuestId(rs.getInt("guest_id"));
        eg.setAttending(rs.getBoolean("is_attending"));
        return eg;
    }

}
