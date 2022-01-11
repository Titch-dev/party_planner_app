package com.ldp.partyplanner.data;

import com.ldp.partyplanner.data.mapper.GroupMapper;
import com.ldp.partyplanner.models.Group;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class GroupJdbcTRepository implements GroupRepository {

    JdbcTemplate repository;

    public GroupJdbcTRepository(JdbcTemplate jdbcTemplate){
        this.repository = jdbcTemplate;
    }

    @Override
    public Group findById(int groupId) {

        final String sql = "select * from `group` where group_id = ?;";

        return repository.query(sql, new GroupMapper(), groupId).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Group add(Group group) {

        final String sql = "insert into `group` (email, `password`, group_size)" +
                "values (?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = repository.update(connection ->{
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, group.getEmail());
            ps.setString(2, group.getPassword());
            ps.setInt(3, group.getGroupSize());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0){
            return null;
        }

        group.setGroupId(keyHolder.getKey().intValue());
        return group;
    }

    @Override
    public boolean update(Group group) {

        final String sql = "update `group` set " +
                "email = ?, " +
                "`password` = ?," +
                "group_size = ? " +
                "where group_id = ?;";

        return repository.update(sql,
                group.getEmail(),
                group.getPassword(),
                group.getGroupSize(),
                group.getGroupId()) > 0;
    }

    @Override
    public boolean deleteById(int groupId) {

        final String sql = "delete from `group` where group_id = ?;";
        return repository.update(sql, groupId) > 0;
    }
}
