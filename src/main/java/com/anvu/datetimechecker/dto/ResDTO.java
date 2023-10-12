/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anvu.datetimechecker.dto;

import jakarta.ws.rs.core.Response;
import lombok.Getter;

/**
 *
 * @author hoang
 */

@Getter
public class ResDTO<T> {
    private T data;
    private int status;
    private String message;
    
    public ResDTO(T data, Response.Status status, String message) {
        this.data = data;
        this.status = status.getStatusCode();
        this.message = message;
    }
}
