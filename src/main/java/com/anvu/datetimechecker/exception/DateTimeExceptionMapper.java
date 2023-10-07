/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anvu.datetimechecker.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 *
 * @author hoang
 */
@Provider
public class DateTimeExceptionMapper implements ExceptionMapper<MyDateTimeException> {
    @Override
    public Response toResponse(MyDateTimeException exception) {
        return exception.getResponse();
    }
}
