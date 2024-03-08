package org.parrilla.parriJava.basededatos;

import org.parrilla.parriJava.validacion.ProgramaDTO;
import org.parrilla.parriJava.validacion.programaDTO.*;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class BaseDeDatosCalendario {
    private Logger base_de_datos;

    public BaseDeDatosCalendario(Logger base_de_datos) {
        this.base_de_datos = base_de_datos;
    }
    public CommandLineRunner descargarBaseDeDatosCalendario(AlmacenCalendario almacen_calendario){
        ArrayList<String> identificadores_de_lista = new ArrayList<>();
        identificadores_de_lista.add("https://www.youtube.com/watch?list=PL9hW1uS6HUfva3BDhnfnGBOBkvl3Q1WGw");
        ArrayList<String> identificadores_de_canal = new ArrayList<>();
        identificadores_de_canal.add("https://www.youtube.com/watch?list=UCMDQxm7cUx3yXkfeHa5zJIQ");
        ArrayList<DiaDeLaSemanaDTO> dias_de_la_semana = new ArrayList<>();
        dias_de_la_semana.add(DiaDeLaSemanaDTO.MARTES);
        dias_de_la_semana.add(DiaDeLaSemanaDTO.VIERNES);
        dias_de_la_semana.add(DiaDeLaSemanaDTO.SABADO);
        ArrayList<LocalDate> excepciones = new ArrayList<>();
        excepciones.add(LocalDate.of(2004,2,29));
        excepciones.add(LocalDate.of(2008,2,29));
        ArrayList<String> omitidos = new ArrayList<>();
        omitidos.add("https://www.youtube.com/watch?v=ZoW9lAhLt3I");

        TablaCalendario programa_a_borrar = new TablaCalendario();
        programa_a_borrar.setId(1);
        programa_a_borrar.setMomento_inicial(LocalDateTime.of(2000,2,29,16,43));
        programa_a_borrar.setIdentificador_video("https://youtu.be/9xp1XWmJ_Wo");
        programa_a_borrar.setIdentificadores_de_lista(identificadores_de_lista);
        programa_a_borrar.setIdentificadores_de_canal(identificadores_de_canal);
        programa_a_borrar.setOrden("ASCENDENTE");
        programa_a_borrar.setRepetir("SEMANALMENTE");
        programa_a_borrar.setRepetir_cada(3);
        programa_a_borrar.setDias_de_la_semana(dias_de_la_semana.toString());
        programa_a_borrar.setCoincidencia("SEMANAL");
        programa_a_borrar.setDuracion("HASTA");
        programa_a_borrar.setNumero_de_repeticiones(9);
        programa_a_borrar.setCaducidad(LocalDate.of(2016,2,29));
        programa_a_borrar.setOmitidos(omitidos);
        programa_a_borrar.setExcepciones(excepciones);

        return args -> {
            base_de_datos.info("Cargando " + almacen_calendario.save(
                    programa_a_borrar
            ));
            programa_a_borrar.setId(2);
            programa_a_borrar.setIdentificador_video(null);
            programa_a_borrar.setRepetir("MENSUALMENTE");
            programa_a_borrar.setRepetir_cada(1);
            programa_a_borrar.setDias_de_la_semana(null);
            programa_a_borrar.setCoincidencia("NUMERICA");
            programa_a_borrar.setDuracion("REPETICION");
            programa_a_borrar.setNumero_de_repeticiones(15);
            programa_a_borrar.setCaducidad(null);
            base_de_datos.info("Cargando " + almacen_calendario.save(
                    programa_a_borrar
            ));
            programa_a_borrar.setId(3);
            programa_a_borrar.setIdentificadores_de_canal(null);
            programa_a_borrar.setRepetir("NUNCA");
            programa_a_borrar.setRepetir_cada(null);
            programa_a_borrar.setOrden("DESCENDENTE");
            programa_a_borrar.setCoincidencia(null);
            programa_a_borrar.setDuracion(null);
            programa_a_borrar.setNumero_de_repeticiones(null);
            programa_a_borrar.setExcepciones(null);
            programa_a_borrar.setOmitidos(null);
            base_de_datos.info("Cargando " + almacen_calendario.save(
                    programa_a_borrar
            ));

        };
    }
}
