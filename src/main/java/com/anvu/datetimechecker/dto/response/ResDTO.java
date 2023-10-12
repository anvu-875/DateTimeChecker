/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anvu.datetimechecker.dto.response;

import jakarta.ws.rs.core.Response;
import lombok.Getter;

/**
 *
 * @author hoang
 */

@Getter
public class ResDTO<T> extends ResRequirement{
    private T data;
    
    public ResDTO(T data, Response.Status status, String message) {
        super(status, message);
        this.data = data;
    }
}
