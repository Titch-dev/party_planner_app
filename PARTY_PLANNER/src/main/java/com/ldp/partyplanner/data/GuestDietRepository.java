package com.ldp.partyplanner.data;

import com.ldp.partyplanner.models.GuestDiet;

public interface GuestDietRepository {

    boolean add(GuestDiet guestDiet);
    boolean delete(int guestId, int dietId);
}
