package org.parrilla.parriJava.validacion.programaDTO;

public enum OrdenDTO {
    ASCENDENTE("ASCENDENTE"),
    DESCENDENTE("DESCENDENTE");

    private String orden;
    private OrdenDTO (String repeticion){
        this.orden = repeticion;
    }
    public String getOrden() {
        return orden;
    }
}
