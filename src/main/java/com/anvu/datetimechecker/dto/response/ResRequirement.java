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
class ResRequirement {
    protected int status;
    protected String message;
    
    public ResRequirement(Response.Status status, String message) {
        this.status = status.getStatusCode();
        this.message = message;
    }
}
