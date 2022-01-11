package com.ldp.partyplanner.services;

public class Validations {

    public static boolean isNullOrBlank(String value){
        return value == null || value.isBlank();
    }

    // maybe write isDateTimeFormatted
}
