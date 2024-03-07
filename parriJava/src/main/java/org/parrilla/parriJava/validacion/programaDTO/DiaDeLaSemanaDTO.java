package org.parrilla.parriJava.validacion.programaDTO;

public enum DiaDeLaSemanaDTO {
    LUNES("L"),
    MARTES("M"),
    MIERCOLES("X"),
    JUEVES("J"),
    VIERNES("V"),
    SABADO("S"),
    DOMINGO("D");

    private String acronimo;

    DiaDeLaSemanaDTO(String acronimo) {
        this.acronimo = acronimo;
    }

    public String getAcronimo() {
        return acronimo;
    }
}
