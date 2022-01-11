package com.ldp.partyplanner.models;

public class Guest {

    private int guestId;
    private String firstName;
    private String lastName;
    private boolean isLead;
    private int groupId;

    public Guest(){

    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean getIsLead() {
        return isLead;
    }

    public void setIsLead(boolean lead) {
        isLead = lead;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString(){
        return "Guest{" +
                "guest_id=" + guestId +
                ", first_name=" + firstName +
                ", last_name=" + lastName +
                ", is_lead=" + isLead +
                ", lead_id=" + groupId +
                "}";
    }

}
