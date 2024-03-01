package org.parrilla.parriJava.llamadas;

import org.parrilla.parriJava.basededatos.AlmacenParrilla;
import org.parrilla.parriJava.basededatos.TablaParrilla;
import org.parrilla.parriJava.modulador.BaseDatosADTO;
import org.parrilla.parriJava.validacion.ParrillaDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ControlEmision {
    private final AlmacenParrilla almacen_parrilla;

    public ControlEmision(AlmacenParrilla almacenParrilla) {
        this.almacen_parrilla = almacenParrilla;
    }

    @GetMapping("/emision")
    public ParrillaDTO obtenerEmision(){
        List<TablaParrilla> tabla_parrilla = almacen_parrilla.findByMomentoGreaterThanEqualOrderByMomentoAsc(
                LocalDateTime.now()
        );

        return BaseDatosADTO.annadirMensajeAParrillaDTODesdeTablaParrilla(
                new ParrillaDTO(new ArrayList<>()),
                tabla_parrilla
        );
    }
}
