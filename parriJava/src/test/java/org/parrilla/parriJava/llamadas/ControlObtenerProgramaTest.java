package org.parrilla.parriJava.llamadas;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.parrilla.parriJava.basededatos.AlmacenCalendario;
import org.parrilla.parriJava.llamadas.respuestas.TratadorDTO;
import org.parrilla.parriJava.validacion.FalloDTO;
import org.parrilla.parriJava.validacion.ProgramaDTO;
import org.parrilla.parriJava.validacion.programaDTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControlObtenerProgramaTest {
    @Autowired
    private MockMvc llamada_interna;
    @Autowired
    private AlmacenCalendario almacen_calendario;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {

    }

    @DisplayName("Llamando a /programa/{id} devolviendo un programa.")
    @Test
    void llamandoAProgramaIdDevolviendoUnPrograma() throws Exception {
        ArrayList<String> identificadores_de_lista = new ArrayList<>();
        identificadores_de_lista.add("https://www.youtube.com/watch?list=PL9hW1uS6HUfva3BDhnfnGBOBkvl3Q1WGw");
        ArrayList<String> identificadores_de_canal = new ArrayList<>();
        identificadores_de_canal.add("https://www.youtube.com/watch?list=UCMDQxm7cUx3yXkfeHa5zJIQ");
        ArrayList<DiaDeLaSemanaDTO> dias_de_la_semana = new ArrayList<>();
        dias_de_la_semana.add(DiaDeLaSemanaDTO.MARTES);
        dias_de_la_semana.add(DiaDeLaSemanaDTO.VIERNES);
        dias_de_la_semana.add(DiaDeLaSemanaDTO.SABADO);
        ArrayList<String> excepciones = new ArrayList<>();
        excepciones.add("https://www.youtube.com/watch?v=ZoW9lAhLt3I");
        ArrayList<LocalDate> omitidos = new ArrayList<>();
        omitidos.add(LocalDate.of(2004,2,29));
        omitidos.add(LocalDate.of(2008,2,29));

        ProgramaDTO expectativa = new ProgramaDTO(
                LocalDateTime.of(2000,2,29,16,43),
                "https://youtu.be/9xp1XWmJ_Wo",
                identificadores_de_lista,
                identificadores_de_canal,
                OrdenDTO.ASCENDENTE,
                RepetirDTO.SEMANALMENTE,
                3,
                dias_de_la_semana,
                CoincidenciaDTO.SEMANAL,
                DuracionDTO.HASTA,
                9,
                LocalDate.of(2016,2,29),
                omitidos,
                excepciones
        );

        llamada_interna.perform(MockMvcRequestBuilders.get("/programa/1"))
                .andExpect(status().isOk())
                .andExpect(
                        TratadorDTO.ObjectAResultMatcher(
                                expectativa
                        )
                )
                .andReturn();
    }

    @DisplayName("Llamando a /programa/{id} con identificador inexistente devolviendo Mala solicitud.")
    @Test
    void llamandoAProgramaIdConIdentificadorInexistenteDevolviendoMalaSolicitud() throws Exception {
        llamada_interna.perform(MockMvcRequestBuilders.get("/programa/9999"))
                .andExpect(status().isNotFound())
                .andExpect(
                        TratadorDTO.ObjectAResultMatcher(
                                new FalloDTO(
                                        404,
                                        "Solicitud incorrecta: Identificador inexistente."
                                )
                        )
                )
                .andReturn();
    }
}
