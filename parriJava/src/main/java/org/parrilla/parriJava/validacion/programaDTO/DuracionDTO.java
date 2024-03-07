package org.parrilla.parriJava.validacion.programaDTO;

public enum DuracionDTO{
    SIEMPRE("SIEMPRE"),
    REPETICION("REPETICION"),
    HASTA("HASTA");

    private String duracion;

    DuracionDTO(String duracion) {
        this.duracion = duracion;
    }

    public String getDuracion() {
        return duracion;
    }
}
