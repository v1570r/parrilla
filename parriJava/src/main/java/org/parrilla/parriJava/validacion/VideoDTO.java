package org.parrilla.parriJava.validacion;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public record VideoDTO(
        @NotEmpty
        LocalDateTime momento_inicial,
        @NotEmpty
        String identificador_video,
        @NotEmpty
        String miniatura
) {}
