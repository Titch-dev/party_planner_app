package com.ldp.partyplanner.data;

import com.ldp.partyplanner.data.mapper.EventMapper;
import com.ldp.partyplanner.models.Event;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class EventJdbcTRepository implements EventRepository {

    JdbcTemplate repository;

    EventJdbcTRepository(JdbcTemplate jdbcTemplate){
        this.repository = jdbcTemplate;
    }

    @Override
    public List<Event> findAll(){

        final String sql = "select * from `event`;";

        return repository.query(sql, new EventMapper());

    }

    @Override
    public Event findById(int eventId) {

        final String sql = "select * from `event` where event_id = ?;";

        return repository.query(sql, new EventMapper(), eventId).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Event add(Event event) {

        final String sql = "insert into `event` (event_name, venue_address, start_time, end_time)" +
                            "values(?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = repository.update(connection ->{
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, event.getEventName());
            ps.setString(2, event.getVenueAddress());
//            need to convert to LocalDateTime somehow
            ps.setTimestamp(3, event.getStartTime());
            ps.setTimestamp(4, event.getEndTime());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0){
            return null;
        }

        event.setEventId(keyHolder.getKey().intValue());
        return event;

    }

    @Override
    public boolean update(Event event) {

        final String sql = "update `event` set " +
                "event_name = ?, " +
                "venue_address = ?, " +
                "start_time = ?, " +
                "end_time = ? " +
                "where event_id = ?;";

        return repository.update(sql,
                event.getEventName(),
                event.getVenueAddress(),
                event.getStartTime(),
                event.getEndTime(),
                event.getEventId()) > 0;
    }

//    need to make transactional and delete from EventGuest where event_id...
    @Override
    public boolean deleteById(int eventId) {

        final String sql = "delete from `event` where event_id = ?;";
        return repository.update(sql, eventId) > 0;
    }
}
