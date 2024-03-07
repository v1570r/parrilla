package org.parrilla.parriJava.validacion.programaDTO;

public enum RepetirDTO{
    NUNCA("NUNCA"),
    DIARIAMENTE("DIARIAMENTE"),
    SEMANALMENTE("SEMANALMENTE"),
    MENSUALMENTE("MENSUALMENTE"),
    ANUALMENTE("ANUALMENTE");

    private String repeticion;
    private RepetirDTO (String repeticion){
        this.repeticion = repeticion;
    }
    public String getRepeticion() {
        return repeticion;
    }
}