/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anvu.datetimechecker.config;

import com.anvu.datetimechecker.exception.DateTimeExceptionMapper;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author hoang
 */
@ApplicationPath("api")
public class Application extends ResourceConfig{
    
    public Application() {
        register(DateTimeExceptionMapper.class);
        packages("com.anvu.datetimechecker.resource");
    }
    
}
