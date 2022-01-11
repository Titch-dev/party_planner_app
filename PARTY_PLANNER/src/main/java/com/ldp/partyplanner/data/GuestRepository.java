package com.ldp.partyplanner.data;

import com.ldp.partyplanner.models.Guest;

import java.util.List;

public interface GuestRepository {

    List<Guest> findAll();
    List<Guest> findAllLeads();
    Guest findById(int guestId);
    Guest add(Guest guest);
    boolean update(Guest guest);
    boolean deleteById(int guestId);

}
