package com.ldp.partyplanner.models;

public class EventGuest {

    private int guestId;
    private int eventId;
    private boolean isAttending;

    public EventGuest(){
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public boolean isAttending() {
        return isAttending;
    }

    public void setAttending(boolean attending) {
        isAttending = attending;
    }

    @Override
    public String toString(){
        return "Attendance{"+
                "guest_id=" + guestId +
                ", event_id=" + eventId +
                ", is_attending=" + isAttending +
                "}";
    }
}
