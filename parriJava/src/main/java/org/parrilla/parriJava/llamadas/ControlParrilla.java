package org.parrilla.parriJava.llamadas;

import org.parrilla.parriJava.basededatos.AlmacenParrilla;
import org.parrilla.parriJava.basededatos.TablaParrilla;
import org.parrilla.parriJava.validacion.ParrillaDTO;
import org.parrilla.parriJava.validacion.VideoDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ControlParrilla {
    private final AlmacenParrilla almacen_parrilla;

    public ControlParrilla(AlmacenParrilla almacenParrilla) {
        this.almacen_parrilla = almacenParrilla;
    }

    private ParrillaDTO annadirMensaje(ParrillaDTO parrillaDTO, List<TablaParrilla> tablaParrilla){
        for (int i = 0; i < tablaParrilla.size(); i++){
            TablaParrilla fila = tablaParrilla.get(i);
            parrillaDTO.parrilla().add(
                    new VideoDTO(
                            fila.getMomento(),
                            fila.getIdentificador_video(),
                            fila.getMiniatura()
                    )
            );
        }
        return parrillaDTO;
    }

    @GetMapping("/parrilla")
    public ParrillaDTO obtenerParrilla(){
        DayOfWeek dia_de_la_semana = LocalDate.now().getDayOfWeek();
        LocalDate primer_dia_de_la_semana = LocalDate.now().minusDays(
                dia_de_la_semana.getValue() - 1
        );
        LocalDateTime ultimo_dia_de_la_semana = primer_dia_de_la_semana.plusWeeks(1).atStartOfDay();
        ultimo_dia_de_la_semana = ultimo_dia_de_la_semana.minusNanos(1);

        if (dia_de_la_semana.getValue() > DayOfWeek.FRIDAY.getValue()) {
            ultimo_dia_de_la_semana = ultimo_dia_de_la_semana.plusWeeks(1);
        }

        List<TablaParrilla> tabla_parrilla = almacen_parrilla.findByMomentoBetweenOrderByMomentoAsc(
                primer_dia_de_la_semana.atStartOfDay(),
                ultimo_dia_de_la_semana
        );

        ParrillaDTO parrillaDTO = new ParrillaDTO(new ArrayList<>());

        return annadirMensaje(
                parrillaDTO,
                tabla_parrilla
        );
    }
}
