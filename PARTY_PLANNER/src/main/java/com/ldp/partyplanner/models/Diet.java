package com.ldp.partyplanner.models;

public class Diet {

    private int dietId;
    private String requirement;

    public Diet(){

    }

    public int getDietId() {
        return dietId;
    }

    public void setDietId(int dietId) {
        this.dietId = dietId;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    @Override
    public String toString(){
        return "Diet{" +
                "diet_id=" + dietId +
                ", requirement=" + requirement +
                "}";
    }
}
