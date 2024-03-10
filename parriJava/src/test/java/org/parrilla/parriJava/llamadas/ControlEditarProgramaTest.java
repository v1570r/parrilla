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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControlEditarProgramaTest {
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

    @DisplayName("Llamando a /programa para editar un programa completando todos los datos devolviendo los datos antiguos.")
    @Test
    void llamandoAProgramaParaEditarUnProgramaCompletandoTodosLosDatosDevolviendoLosDatosAntiguos() throws Exception {
        Long identificador_programa_a_editar = 3L;

        ArrayList<String> identificadores_de_lista = new ArrayList<>();
        identificadores_de_lista.add("https://www.youtube.com/watch?list=PLBgogxgQVM9tQKj3tmNRZXg0JHtk-GLf5&pp=iAQB");
        ArrayList<String> identificadores_de_canal = new ArrayList<>();
        identificadores_de_canal.add("https://www.youtube.com/watch?list=UCTMRxtyHoE3LPcrl-kT4AQQ");
        ArrayList<DiaDeLaSemanaDTO> dias_de_la_semana = new ArrayList<>();
        dias_de_la_semana.add(DiaDeLaSemanaDTO.LUNES);
        dias_de_la_semana.add(DiaDeLaSemanaDTO.MARTES);
        ArrayList<LocalDate> excepciones = new ArrayList<>();
        excepciones.add(LocalDate.of(2034,2,2));
        ArrayList<String> omitidos = new ArrayList<>();
        omitidos.add("https://youtu.be/tmHXGbro8NY");

        ProgramaDTO solicitud = new ProgramaDTO(
                LocalDateTime.of(2025,10,29,11,26),
                "https://www.youtube.com/watch?v=tmHXGbro8NY",
                identificadores_de_lista,
                identificadores_de_canal,
                null,
                RepetirDTO.SEMANALMENTE,
                3,
                dias_de_la_semana,
                null,
                DuracionDTO.HASTA,
                null,
                LocalDate.of(2036,6,29),
                excepciones,
                omitidos
        );

        ProgramaDTO expectativa = new ProgramaDTO(
                LocalDateTime.of(2000,2,29,16,43),
                null,
                null,
                null,
                OrdenDTO.DESCENDENTE,
                RepetirDTO.NUNCA,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );

        llamada_interna
                .perform(
                        MockMvcRequestBuilders
                                .post("/programa/" + identificador_programa_a_editar)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TratadorDTO.ObjectACadenaDeCaracteres(solicitud))
                )
                .andExpect(status().isOk())
                .andExpect(
                        TratadorDTO.ObjectAResultMatcher(
                                expectativa
                        )
                )
                .andReturn();


        TablaCalendario programa_editado_esperado = new TablaCalendario(
                identificador_programa_a_editar.intValue(),
                LocalDateTime.of(2025,10,29,11,26),
                "https://www.youtube.com/watch?v=tmHXGbro8NY",
                identificadores_de_lista,
                identificadores_de_canal,
                "DESCENDENTE",
                "SEMANALMENTE",
                3,
                dias_de_la_semana.toString(),
                "NUMERICA",
                "HASTA",
                null,
                LocalDate.of(2036,6,29),
                excepciones,
                omitidos
        );

        TablaCalendario programa_editado = almacen_calendario.findById(identificador_programa_a_editar).get();
        assertEquals(programa_editado_esperado, programa_editado);
    }
}
