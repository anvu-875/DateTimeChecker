/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anvu.datetimechecker.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author hoang
 */
@AllArgsConstructor
@Getter
@JsonAutoDetect
class Nor<T> {
    T data;
}

class ResSerializer<T> extends JsonSerializer<Nor<T>> {
    @Override
    public void serialize(Nor<T> nor, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeObject(nor);
    }
}

@Getter
public class ResDTO<T> {
    @JsonSerialize(using = ResSerializer.class)
    private Nor<T> body;
    private int status;
    
    public ResDTO(T data, Response.Status status) {
        this.body = new Nor(data);
        this.status = status.getStatusCode();
    }
}
