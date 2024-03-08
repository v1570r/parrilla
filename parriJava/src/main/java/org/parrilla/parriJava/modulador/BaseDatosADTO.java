package org.parrilla.parriJava.modulador;

import org.apache.tomcat.util.json.JSONParser;
import org.parrilla.parriJava.basededatos.TablaCalendario;
import org.parrilla.parriJava.basededatos.TablaParrilla;
import org.parrilla.parriJava.validacion.ParrillaDTO;
import org.parrilla.parriJava.validacion.ProgramaDTO;
import org.parrilla.parriJava.validacion.VideoDTO;
import org.parrilla.parriJava.validacion.programaDTO.*;

import java.util.ArrayList;
import java.util.List;

public class BaseDatosADTO {
    public static ParrillaDTO annadirMensajeAParrillaDTODesdeTablaParrilla(ParrillaDTO parrillaDTO, List<TablaParrilla> tablaParrilla){
        for (TablaParrilla fila : tablaParrilla) {
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

    public static ProgramaDTO transformarAProgramaDTODesdeTablaCalendario(TablaCalendario programa){
        ArrayList<DiaDeLaSemanaDTO> dias_de_la_semana = new ArrayList<>();
        String vector_dias_de_la_semana = programa.getDias_de_la_semana().substring(
                1,
                programa.getDias_de_la_semana().length() - 1
        );
        for( String dia_de_la_semana : vector_dias_de_la_semana.split(", ") ){
            dias_de_la_semana.add(DiaDeLaSemanaDTO.valueOf(dia_de_la_semana));
        }
        return new ProgramaDTO(
                programa.getMomento_inicial(),
                programa.getIdentificador_video(),
                programa.getIdentificadores_de_lista(),
                programa.getIdentificadores_de_canal(),
                OrdenDTO.valueOf(programa.getOrden()),
                RepetirDTO.valueOf(programa.getRepetir()),
                programa.getRepetir_cada(),
                dias_de_la_semana,
                CoincidenciaDTO.valueOf(programa.getCoincidencia()),
                DuracionDTO.valueOf(programa.getDuracion()),
                programa.getNumero_de_repeticiones(),
                programa.getCaducidad(),
                programa.getExcepciones(),
                programa.getOmitidos()
        );
    }
}
