package org.parrilla.parriJava.llamadas;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.parrilla.parriJava.basededatos.AlmacenCalendario;
import org.parrilla.parriJava.basededatos.TablaCalendario;
import org.parrilla.parriJava.llamadas.respuestas.TratadorDTO;
import org.parrilla.parriJava.validacion.ProgramaDTO;
import org.parrilla.parriJava.validacion.programaDTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControlBorrarPromgramaTest {
    @Autowired
    private MockMvc llamada_interna;
    @Autowired
    private AlmacenCalendario almacen_calendario;

    private ProgramaDTO expectativa;

    @BeforeEach
    void setUp() {
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

        expectativa = new ProgramaDTO(
                LocalDateTime.of(2000,2,29,16,43),
                null,
                identificadores_de_lista,
                identificadores_de_canal,
                OrdenDTO.ASCENDENTE,
                RepetirDTO.MENSUALMENTE,
                1,
                null,
                CoincidenciaDTO.NUMERICA,
                DuracionDTO.REPETICION,
                15,
                null,
                excepciones,
                omitidos
        );
    }

    @AfterEach
    void tearDown() {

    }

    @DisplayName("Llamando a /programa/{id} para eliminarlo, devolviendo un programa.")
    @Test
    void llamandoAProgramaIdParaEliminarloDevolviendoUnPrograma() throws Exception {
        Long identificador = Long.valueOf(2);
        llamada_interna.perform(
                MockMvcRequestBuilders.delete("/programa/" + identificador)
            ).andExpect(status().isOk())
            .andExpect(
                    TratadorDTO.ObjectAResultMatcher(
                            expectativa
                    )
            )
            .andReturn();
        assertTrue(almacen_calendario.findById(identificador).isEmpty());
    }
}
