package org.parrilla.parriJava.validacion.programaDTO;

public enum CoincidenciaDTO{
    NUMERICA("NUMERICA"),
    SEMANAL("SEMANAL"),
    ULTIMA_SEMANAL("ULTIMA_SEMANAL"),
    ULTIMO_MENSUAL("ULTIMA_MENSUAL");

    private String coincidencia;

    CoincidenciaDTO(String coincidencia) {
        this.coincidencia = coincidencia;
    }

    public String getCoincidencia() {
        return coincidencia;
    }

}
