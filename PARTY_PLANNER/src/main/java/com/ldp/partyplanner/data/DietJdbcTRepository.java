package com.ldp.partyplanner.data;

import com.ldp.partyplanner.data.mapper.DietMapper;
import com.ldp.partyplanner.models.Diet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class DietJdbcTRepository implements DietRepository {

    JdbcTemplate repository;

    DietJdbcTRepository(JdbcTemplate jdbcTemplate){
        this.repository = jdbcTemplate;
    }

    @Override
    public List<Diet> findAll(){

        final String sql = "select * from diet;";

        return repository.query(sql, new DietMapper());
    }

    @Override
    public Diet findById(int dietId) {

        final String sql = "select * from diet where diet_id = ?;";

        return repository.query(sql, new DietMapper(), dietId).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Diet add(Diet diet) {

        final String sql = "insert into diet (requirement)" +
                "values (?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = repository.update(connection ->{
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, diet.getRequirement());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0){
            return null;
        }

        diet.setDietId(keyHolder.getKey().intValue());
        return diet;
    }

    @Override
    public boolean deleteById(int dietId) {

        final String sql = "delete from diet where diet_id = ?;";

        return repository.update(sql, dietId) > 0;
    }
}
