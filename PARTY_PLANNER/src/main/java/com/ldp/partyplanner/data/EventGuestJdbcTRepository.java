package com.ldp.partyplanner.data;

import com.ldp.partyplanner.models.EventGuest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EventGuestJdbcTRepository implements EventGuestRepository{

    JdbcTemplate repository;

    public EventGuestJdbcTRepository(JdbcTemplate jdbcTemplate){
        this.repository = jdbcTemplate;
    }

    @Override
    public boolean add(EventGuest eventGuest) {

        final String sql = "insert into event_guest(event_id, guest_id, is_attending) " +
                "values(?,?,?)";

        return repository.update(sql,
                eventGuest.getEventId(),
                eventGuest.getGuestId(),
                eventGuest.isAttending()) > 0;
    }

    @Override
    public boolean update(EventGuest eventGuest) {

        final String sql = "update event_guest set " +
                "is_attending = ? " +
                "where event_id = ?  and guest_id = ?;";

        return repository.update(sql,
                eventGuest.isAttending(),
                eventGuest.getEventId(),
                eventGuest.getGuestId()) > 0;
    }

    @Override
    public boolean delete(int eventId, int guestId) {

        final String sql = "delete from event_guest where event_id = ? and guest_id = ?;";

        return repository.update(sql, eventId, guestId) > 0;
    }
}
