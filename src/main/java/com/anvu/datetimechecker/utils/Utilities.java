/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anvu.datetimechecker.utils;

/**
 *
 * @author hoang
 */
public class Utilities {
    
    private static Utilities instance;
    
    private Utilities () {
        
    }
    
    public static Utilities getInstance() {
        if (Utilities.instance == null) {
            Utilities.instance = new Utilities();
        }
        return Utilities.instance;
    }
    
    public int calculateDayInMonth(int year, int month) {
        switch (month) {
            case 1: case 3: case 5: case 7: case 8:case 10: case 12: {
                return 31;
            }
            case 4: case 6: case 9: case 11: {
                return 30;
            }
            case 2: {
                if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
                    return 29;
                } else {
                    return 28;
                }
                
            }
            default: {
                return 0;
            }
        }
    }
    
    public boolean isValidDate(int year, int month, int day) {
        boolean isValid = false;
        if (month <= 12 && month >= 1 && day >= 1 && day <= calculateDayInMonth(year, month)) {
            isValid = true;
        }
        return isValid;
    }
}
