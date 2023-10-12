/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anvu.datetimechecker.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import lombok.Getter;

/**
 *
 * @author hoang
 */
class ErrSerializer<T> extends JsonSerializer<ErrBox<T>> {
    @Override
    public void serialize(ErrBox<T> err, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeObject(err);
    }
}

@Getter
public class ErrDTO<T> {
    @JsonSerialize(using = ErrSerializer.class)
    private ErrBox<T> error;
    private int status;
    private String message;

    public ErrDTO(T description, ArrayList<ErrorCode> codes, Response.Status status, String message) {
        this.error = new ErrBox(description, codes);
        this.status = status.getStatusCode();
        this.message = message;
    }
}
