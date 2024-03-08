package org.parrilla.parriJava.alertas;

import org.parrilla.parriJava.validacion.FalloDTO;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice
public class AlertaMalaSolicitud {
    private final String TEXTO_SOLICITUD_INCORECTA = "Solicitud incorrecta: ";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public FalloDTO alertaCamposIncorrectos(MethodArgumentNotValidException argumentos_incorrectos) {
        List<FieldError> campos_incorrectos = argumentos_incorrectos.getBindingResult().getFieldErrors();
        List<String> mensajes_de_fallo = new ArrayList<>();
        for (FieldError campo_incorrecto : campos_incorrectos) {
            mensajes_de_fallo.add(
                    campo_incorrecto.getDefaultMessage()
            );
        }
        return new FalloDTO(
                HttpStatus.BAD_REQUEST.value(),
                TEXTO_SOLICITUD_INCORECTA + mensajes_de_fallo.toString()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(HandlerMethodValidationException.class)
    public FalloDTO alertaFalloEnLaFormaDeValidacion(HandlerMethodValidationException validaciones_incorrectas) {
        List<ParameterValidationResult> todas_las_validaciones_resueltas = validaciones_incorrectas.getAllValidationResults();
        List<String> mensajes_de_fallo = new ArrayList<>();
        for (ParameterValidationResult validacion_resuelta : todas_las_validaciones_resueltas) {
            for (MessageSourceResolvable fuente_del_mensaje_resuelto : validacion_resuelta.getResolvableErrors()){
                mensajes_de_fallo.add(
                        fuente_del_mensaje_resuelto.getDefaultMessage()
                );
            }
        }
        return new FalloDTO(
                HttpStatus.BAD_REQUEST.value(),
                TEXTO_SOLICITUD_INCORECTA + mensajes_de_fallo.toString()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public FalloDTO alertaMensajeNoLegible(HttpMessageNotReadableException fallo_mensaje) {
        return new FalloDTO(
                HttpStatus.BAD_REQUEST.value(),
                TEXTO_SOLICITUD_INCORECTA + "no se recibió el cuerpo."
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(NoSuchElementException.class)
    public FalloDTO alertaMensajeNoLegible(NoSuchElementException fallo_mensaje) {
        return new FalloDTO(
                HttpStatus.NOT_FOUND.value(),
                TEXTO_SOLICITUD_INCORECTA + "Identificador inexistente."
        );
    }
}
