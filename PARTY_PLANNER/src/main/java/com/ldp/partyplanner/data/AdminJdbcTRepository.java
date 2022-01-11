package com.ldp.partyplanner.data;

import com.ldp.partyplanner.data.mapper.AdminMapper;
import com.ldp.partyplanner.models.Admin;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class AdminJdbcTRepository implements AdminRepository {

    private final JdbcTemplate repository;

    public AdminJdbcTRepository(JdbcTemplate jdbcTemplate){
        this.repository = jdbcTemplate;
    }

    @Override
    public List<Admin> findAll(){

        final String sql = "select admin_id, first_name, last_name, email, `password` from `admin`;";

        return repository.query(sql, new AdminMapper());
    }

    @Override
    public Admin findById(int adminId) {

        final String sql = "select admin_id, first_name, last_name, email, password from `admin` where admin_id = ?;";

        return repository.query(sql, new AdminMapper(), adminId)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Admin add(Admin admin){

        final String sql = "insert into `admin` (first_name, last_name, email, password)" +
                "values(?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = repository.update(connection->{
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, admin.getFirstName());
            ps.setString(2, admin.getLastName());
            ps.setString(3, admin.getEmail());
            ps.setString(4, admin.getPassword());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0){
            return null;
        }

        admin.setAdminId(keyHolder.getKey().intValue());
        return admin;
    }

    @Override
    public boolean update(Admin admin) {

        final String sql = "update admin set " +
                "first_name = ?, " +
                "last_name = ?, " +
                "email = ?, " +
                "`password` = ?" +
                "where admin_id = ?;";

        return repository.update(sql,
                admin.getFirstName(),
                admin.getLastName(),
                admin.getEmail(),
                admin.getPassword(),
                admin.getAdminId()) > 0;
    }

    @Override
    public boolean deleteById(int adminId) {

        final String sql = "delete from `admin` where admin_id = ?;";

        return repository.update(sql, adminId) > 0;
    }
}
