/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anvu.datetimechecker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author hoang
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MonthYearDTO {

    private int month;
    private int year;

    public void setMonth(int month) throws Exception {
        if (month < 1 || month > 12) {
            throw new Exception("month must be in range from 1 to 12");
        }
        this.month = month;
    }

    public void setYear(int year) throws Exception {
        if (year < 1) {
            throw new Exception("year can not be lower than 1");
        }
        this.year = year;
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
                "}" +
            "}," +
            "\"required\": [" +
                "\"year\"," +
                "\"month\"" +
            "]," +
            "\"additionalProperties\": false" +
        "}";
    }
}
