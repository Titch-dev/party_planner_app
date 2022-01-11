package com.ldp.partyplanner.data;

import com.ldp.partyplanner.models.GuestDiet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GuestDietJdbcTRepository implements GuestDietRepository{

    JdbcTemplate repository;

    public GuestDietJdbcTRepository(JdbcTemplate jdbcTemplate){
        this.repository = jdbcTemplate;
    }

    @Override
    public boolean add(GuestDiet guestDiet) {

        final String sql = "insert into guest_diet(guest_id, diet_id) " +
                "values (?,?);";

        return repository.update(sql,
                guestDiet.getGuestId(),
                guestDiet.getDietId()) > 0;
    }

    @Override
    public boolean delete(int guestId, int dietId) {

        final String sql = "delete from guest_diet where guest_id = ? and diet_id = ?;";

        return repository.update(sql, guestId, dietId) > 0;
    }
}
