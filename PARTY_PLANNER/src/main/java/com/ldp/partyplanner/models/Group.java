package com.ldp.partyplanner.models;

public class Group {

    private int groupId;
    private String email;
    private String password;
    private int groupSize;

    public Group(){

    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    @Override
    public String toString(){
        return "Lead{"+
                "lead_id=" + groupId +
                ", email=" + email +
                ", password=" + password +
                ", group_size=" + groupSize +
                "}";
    }
}
