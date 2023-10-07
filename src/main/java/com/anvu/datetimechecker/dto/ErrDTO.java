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
class Err<T> {
    T error;
}

class ErrSerializer<T> extends JsonSerializer<Err<T>> {
    @Override
    public void serialize(Err<T> err, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeObject(err);
    }
}

@Getter
public class ErrDTO<T> {
    @JsonSerialize(using = ErrSerializer.class)
    private Err<T> body;
    private int status;

    public ErrDTO(T error, Response.Status status) {
        this.body = new Err(error);
        this.status = status.getStatusCode();
    }
}
