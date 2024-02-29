package org.parrilla.parriJava.llamadas;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.parrilla.parriJava.llamadas.respuestas.ListaVideosValidada;
import org.parrilla.parriJava.llamadas.respuestas.TratadorDTO;
import org.parrilla.parriJava.validacion.ParrillaDTO;
import org.parrilla.parriJava.validacion.VideoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ControlEmisionTest {
    private MockedStatic<LocalDateTime> falsearLocalDateTime;
    private LocalDate ultimo_dia_de_la_semana_pasada;
    @Autowired
    private MockMvc llamadaInterna;

    @BeforeEach
    void setUp() {
        DayOfWeek dia_de_la_semana = LocalDate.now().getDayOfWeek();
        ultimo_dia_de_la_semana_pasada = LocalDate.now().minusDays(
                dia_de_la_semana.getValue()
        );
    }

    @AfterEach
    void tearDown() {
        falsearLocalDateTime.closeOnDemand();
    }

    @DisplayName("Llamando a /emision cuando es miércoles a las 11h devuelve 12 videos.")
    @Test
    void llamandoAEmisionCuandoEsMiercolesALas11hDevuelve12Videos() throws Exception {
        falsearLocalDateTime = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS);
        LocalDateTime now = LocalDateTime.of(
                ultimo_dia_de_la_semana_pasada.plusDays(DayOfWeek.WEDNESDAY.getValue()),
                LocalTime.of(11, 0)
        );
        falsearLocalDateTime.when(LocalDateTime::now).thenReturn(now);

        int videos_a_borrar = 3;
        ArrayList<VideoDTO> expectativa_lista = new ListaVideosValidada().obtenerLista();
        for (int i = 0; i < videos_a_borrar; i++) {
            expectativa_lista.remove(0);
        }

        llamadaInterna.perform(MockMvcRequestBuilders.get("/emision"))
                .andExpect(status().isOk())
                .andExpect(
                        TratadorDTO.ObjectAResultMatcher(
                                new ParrillaDTO(expectativa_lista)
                        )
                )
                .andReturn();
    }

    @DisplayName("Llamando a /emision cuando es miércoles a las 12h devuelve 11 videos.")
    @Test
    void llamandoAEmisionCuandoEsMiercolesALas12hDevuelve11Videos() throws Exception {
        falsearLocalDateTime = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS);
        LocalDateTime now = LocalDateTime.of(
                ultimo_dia_de_la_semana_pasada.plusDays(DayOfWeek.WEDNESDAY.getValue()),
                LocalTime.of( 12, 0)
        );
        falsearLocalDateTime.when(LocalDateTime::now).thenReturn(now);

        int videos_a_borrar = 4;
        ArrayList<VideoDTO> expectativa_lista = new ListaVideosValidada().obtenerLista();
        for (int i = 0; i < videos_a_borrar; i++) {
            expectativa_lista.remove(0);
        }

        llamadaInterna.perform(MockMvcRequestBuilders.get("/emision"))
                .andExpect(status().isOk())
                .andExpect(
                        TratadorDTO.ObjectAResultMatcher(
                                new ParrillaDTO(expectativa_lista)
                        )
                )
                .andReturn();
    }
}