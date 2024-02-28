package org.parrilla.parriJava.validacion;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ParrillaDTO(
    @NotEmpty List<VideoDTO> parrilla
) {}
