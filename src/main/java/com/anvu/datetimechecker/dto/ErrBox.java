/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anvu.datetimechecker.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author hoang
 */
@AllArgsConstructor
@Getter
@JsonAutoDetect
public class ErrBox<T> {
    T description;
    ArrayList<ErrorCode> codes;
}
