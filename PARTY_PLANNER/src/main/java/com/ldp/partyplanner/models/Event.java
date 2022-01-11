package com.ldp.partyplanner.models;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Event {

    private int eventId;
    private String eventName;
    private String venueAddress;
    private Timestamp startTime;
    private Timestamp endTime;

    public Event(){

    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getVenueAddress() {
        return venueAddress;
    }

    public void setVenueAddress(String venueAddress) {
        this.venueAddress = venueAddress;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString(){
        return "Event{" +
                "event_id=" + eventId +
                ", event_name=" + eventName +
                ", venue_address=" + venueAddress +
                ", start_time=" + startTime +
                ", end_time=" + endTime +
                "}";
    }
}
