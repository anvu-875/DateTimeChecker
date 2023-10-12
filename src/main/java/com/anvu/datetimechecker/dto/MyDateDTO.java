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
        switch(this.month) {
            case 1: case 3: case 5: case 7: case 8:case 10: case 12: {
                if (day < 1 || day > 31) {
                    throw new Exception("day must be in range from 1 to 31");
                }
                break;
            }
            case 4: case 6: case 9: case 11: {
                if (day < 1 || day > 30) {
                    throw new Exception("day must be in range from 1 to 30");
                }
                break;
            }
            case 2: {
                if (this.year % 400 == 0 || (this.year % 4 == 0 && this.year % 100 != 0)) {
                    if (day < 1 || day > 29) {
                        throw new Exception("day must be in range from 1 to 29");
                    }
                } else {
                    if (day < 1 || day > 28) {
                        throw new Exception("day must be in range from 1 to 28");
                    }
                }
                break;
            }
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
}
