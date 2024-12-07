package com.test.technicaltest.utis;

public class Utils {

    public static String formatTime(String hourString) {

        int hour = Integer.parseInt(hourString);
    
        if (hour < 8 || hour > 17) {
            throw new IllegalArgumentException("La hora debe estar entre 8 y 17.");
        }
    
        return hour + ":00";
    }
    
    
}
