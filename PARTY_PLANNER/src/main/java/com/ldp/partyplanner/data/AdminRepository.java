package com.ldp.partyplanner.data;

import com.ldp.partyplanner.models.Admin;
import java.util.List;

public interface AdminRepository {

    List<Admin> findAll();
    Admin findById(int adminId);
    Admin add(Admin admin);
    boolean update(Admin admin);
    boolean deleteById(int adminId);

}
