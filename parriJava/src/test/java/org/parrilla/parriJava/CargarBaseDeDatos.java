package org.parrilla.parriJava;

import org.parrilla.parriJava.basededatos.AlmacenCalendario;
import org.parrilla.parriJava.basededatos.AlmacenParrilla;
import org.parrilla.parriJava.basededatos.BaseDeDatosCalendario;
import org.parrilla.parriJava.basededatos.BaseDeDatosParrilla;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CargarBaseDeDatos {
    private static final Logger base_de_datos = LoggerFactory.getLogger(CargarBaseDeDatos.class);

    @Bean
    CommandLineRunner iniciarBaseDeDatosParrilla(AlmacenParrilla almacenParrilla){
        BaseDeDatosParrilla base_de_datos_de_la_tabla_parrilla = new BaseDeDatosParrilla(base_de_datos);
        return base_de_datos_de_la_tabla_parrilla.descargarBaseDeDatosParrilla(almacenParrilla);
    }
    @Bean
    CommandLineRunner iniciarBaseDeDatosCalendario(AlmacenCalendario almacenCalendario){
        BaseDeDatosCalendario base_de_datos_de_la_tabla_calendario = new BaseDeDatosCalendario(base_de_datos);
        return base_de_datos_de_la_tabla_calendario.descargarBaseDeDatosCalendario(almacenCalendario);
    }
}
