package com.ldp.partyplanner.data;

import com.ldp.partyplanner.models.EventGuest;

public interface EventGuestRepository {

    boolean add(EventGuest eventGuest);
    boolean update(EventGuest eventGuest);
    boolean delete(int eventId, int guestId);

}
