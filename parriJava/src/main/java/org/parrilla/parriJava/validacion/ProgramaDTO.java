package org.parrilla.parriJava.validacion;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import org.parrilla.parriJava.validacion.programaDTO.RepetirDTO;
import org.parrilla.parriJava.validacion.programaDTO.OrdenDTO;
import org.parrilla.parriJava.validacion.programaDTO.CoincidenciaDTO;
import org.parrilla.parriJava.validacion.programaDTO.DiaDeLaSemanaDTO;
import org.parrilla.parriJava.validacion.programaDTO.DuracionDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public record ProgramaDTO(
        @NotNull(message = "momento_inicial es obligatorio")
        LocalDateTime momento_inicial,
        String identificador_video,
        List<String> identificadores_de_lista,
        List<String> identificadores_de_canal,
        OrdenDTO orden,
        RepetirDTO repetir,
        Integer repetir_cada,
        List<DiaDeLaSemanaDTO>dias_de_la_semana,
        CoincidenciaDTO coincidencia,
        DuracionDTO duracion,
        Integer numero_de_repeticiones,
        LocalDate caducidad,
        List<LocalDate> excepciones,
        List<String> omitidos

){
        public ProgramaDTO(
                @NotNull(message = "momento_inicial es obligatorio")
                LocalDateTime momento_inicial,
                String identificador_video,
                List<String> identificadores_de_lista,
                List<String> identificadores_de_canal,
                OrdenDTO orden,
                RepetirDTO repetir,
                Integer repetir_cada,
                List<DiaDeLaSemanaDTO> dias_de_la_semana,
                CoincidenciaDTO coincidencia,
                DuracionDTO duracion,
                Integer numero_de_repeticiones,
                LocalDate caducidad,
                List<LocalDate> excepciones,
                List<String> omitidos
        ) {
                this.momento_inicial = momento_inicial;
                this.identificador_video = identificador_video;
                this.identificadores_de_lista = identificadores_de_lista;
                this.identificadores_de_canal = identificadores_de_canal;
                this.repetir = Objects.requireNonNullElse(repetir, RepetirDTO.NUNCA);
                this.orden = Objects.requireNonNullElse(orden, OrdenDTO.DESCENDENTE);
                this.repetir_cada = repetir_cada;
                this.dias_de_la_semana = dias_de_la_semana;
                this.coincidencia = Objects.requireNonNullElse(coincidencia, CoincidenciaDTO.NUMERICA);
                this.duracion = Objects.requireNonNullElse(duracion, DuracionDTO.SIEMPRE);
                this.numero_de_repeticiones = numero_de_repeticiones;
                this.caducidad = caducidad;
                this.excepciones = excepciones;
                this.omitidos = omitidos;
        }
}
