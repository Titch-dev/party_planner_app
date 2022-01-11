package com.ldp.partyplanner.models;

public class GuestDiet {

    private int guestDietId;
    private int guestId;
    private int dietId;

    public GuestDiet(){
    }

    public int getGuestDietId() {
        return guestDietId;
    }

    public void setGuestDietId(int guestDietId) {
        this.guestDietId = guestDietId;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public int getDietId() {
        return dietId;
    }

    public void setDietId(int dietId) {
        this.dietId = dietId;
    }

    @Override
    public String toString(){
        return "GuestDiet{" +
                "guest_diet_id=" + guestDietId +
                ", guest_id="+ guestId +
                ", diet_id=" + dietId +
                "}";
    }
}
