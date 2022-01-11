package com.ldp.partyplanner.models;

public class Admin {

    private int adminId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public Admin(){
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
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

    public void setLast_name(String lastName) {
        this.lastName = lastName;
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

    public String toString(){
        return "Admin{"+
                "adminId=" + adminId +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", email=" + email +
                ", password=" + password +
                "}";
    }
}
