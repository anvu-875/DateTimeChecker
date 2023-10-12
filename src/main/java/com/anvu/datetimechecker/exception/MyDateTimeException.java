/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anvu.datetimechecker.exception;

import com.anvu.datetimechecker.dto.response.ErrDTO;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author hoang
 * @param <T>
 */
public class MyDateTimeException extends WebApplicationException  {
    public MyDateTimeException(ErrDTO body, Response.Status status) {
        super(Response.status(status).entity(body).build());
    }
}
