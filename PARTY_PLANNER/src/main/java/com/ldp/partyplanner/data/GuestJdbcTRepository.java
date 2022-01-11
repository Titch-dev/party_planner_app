package com.ldp.partyplanner.data;

import com.ldp.partyplanner.data.mapper.GuestMapper;
import com.ldp.partyplanner.models.Guest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class GuestJdbcTRepository implements GuestRepository{

    JdbcTemplate repository;

    public GuestJdbcTRepository(JdbcTemplate jdbcTemplate){
        this.repository = jdbcTemplate;
    }

    @Override
    public List<Guest> findAll() {

        final String sql = "select guest_id, first_name, last_name, is_lead, group_id from `guest`;";

        return repository.query(sql, new GuestMapper());
    }

    @Override
    public List<Guest> findAllLeads(){

        final String sql = "select * from `guest` where is_lead = 1;";

        return repository.query(sql, new GuestMapper());

    }

    @Override
    public Guest findById(int guestId) {

        final String sql = "select * from guest where guest_id = ?;";

        return repository.query(sql, new GuestMapper(), guestId).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Guest add(Guest guest) {

        final String sql = "insert into guest (first_name, last_name, is_lead, group_id) " +
                "values(?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = repository.update(connection ->{
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, guest.getFirstName());
            ps.setString(2, guest.getLastName());
            ps.setBoolean(3, guest.getIsLead());
            ps.setInt(4, guest.getGroupId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0){
            return null;
        }

        guest.setGuestId(keyHolder.getKey().intValue());
        return guest;
    }

    @Override
    public boolean update(Guest guest) {

        final String sql = "update guest set " +
                "first_name = ?, " +
                "last_name = ?, " +
                "is_lead = ?, " +
                "group_id = ? " +
                "where guest_id = ?;";

        return repository.update(sql,
                guest.getFirstName(),
                guest.getLastName(),
                guest.getIsLead(),
                guest.getGroupId(),
                guest.getGuestId()) > 0;
    }

    @Override
    public boolean deleteById(int guestId) {

        final String sql = "delete from guest where guest_id = ?;";
        return repository.update(sql, guestId) > 0;
    }
}
