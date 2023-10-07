/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anvu.datetimechecker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author hoang
 */
@Getter
@AllArgsConstructor
public class MyDateDTO extends MonthYearDTO {
    private int day;
    
    public void setDay(int day) throws Exception {
        if (day < 1 || day > 31) {
            throw new Exception("day must be in range from 1 to 31");
        }
        this.day = day;
    }
    
    public static String getJSONSchema() {
        return "{" +
            "\"type\": \"object\"," +
            "\"properties\": {" +
                "\"year\": {" +
                    "\"type\": \"integer\"," +
                    "\"minimum\": 1" +
                "}," +
                "\"month\": {" +
                    "\"type\": \"integer\"," +
                    "\"minimum\": 1," +
                    "\"maximum\": 12" +
                "}," +
                "\"day\": {" +
                    "\"type\": \"integer\"," +
                "}" +
            "}," +
            "\"required\": [" +
                "\"year\"," +
                "\"month\"," +
                "\"day\"" +
            "]," +
            "\"additionalProperties\": false" +
        "}";
    }
    
    public static boolean isIntValidDay(int num) {
        if (num < 1 || num > 31) {
            return false;
        }
        return true;
    }
}
