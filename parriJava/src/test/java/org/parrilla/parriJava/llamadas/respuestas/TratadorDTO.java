package org.parrilla.parriJava.llamadas.respuestas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.test.web.servlet.ResultMatcher;

import java.text.SimpleDateFormat;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class TratadorDTO {
    public static ResultMatcher ObjectAResultMatcher(Object objeto) throws JsonProcessingException {
        ObjectMapper conversor = new ObjectMapper();
        conversor.registerModule(new JavaTimeModule());
        conversor.setDateFormat(new SimpleDateFormat());
        String respuesta_en_json = conversor.writeValueAsString(objeto);
        return content().json(respuesta_en_json);
    }
}
