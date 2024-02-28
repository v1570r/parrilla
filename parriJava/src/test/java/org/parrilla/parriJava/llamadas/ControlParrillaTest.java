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
class ControlParrillaTest {
    private MockedStatic<LocalDate> falsear_LocalDate;
    private MockedStatic<LocalDateTime> falsear_LocalDateTime;
    private LocalDate ultimo_dia_de_la_semana_pasada;
    @Autowired
    private MockMvc llamada_interna;

    @BeforeEach
    void setUp() {
        DayOfWeek dia_de_la_semana = LocalDate.now().getDayOfWeek();
        ultimo_dia_de_la_semana_pasada = LocalDate.now().minusDays(
                dia_de_la_semana.getValue()
        );
    }

    @AfterEach
    void tearDown() {
        falsear_LocalDate.closeOnDemand();
        falsear_LocalDateTime.closeOnDemand();
    }

    @DisplayName("Llamando a /parrilla cuando es jueves devuelve 7 videos.")
    @Test
    void llamandoAParrillaCuandoEsJuevesDevuelve7Videos() throws Exception {
        falsear_LocalDate = Mockito.mockStatic(LocalDate.class, Mockito.CALLS_REAL_METHODS);
        LocalDate hoy = ultimo_dia_de_la_semana_pasada.plusDays(DayOfWeek.THURSDAY.getValue());
        falsear_LocalDate.when(LocalDate::now).thenReturn(hoy);

        falsear_LocalDateTime = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS);
        LocalDateTime now = LocalDateTime.of(
                ultimo_dia_de_la_semana_pasada.plusDays(DayOfWeek.TUESDAY.getValue()),
                LocalTime.of( 12, 0)
        );
        falsear_LocalDateTime.when(LocalDateTime::now).thenReturn(now);

        int videos_a_borrar = 7;
        ArrayList<VideoDTO> expectativa_lista = new ListaVideosValidada().obtenerLista();
        expectativa_lista.remove(0);
        for (int i = 0; i < expectativa_lista.size(); i++) {
            expectativa_lista.remove(videos_a_borrar);
        }

        llamada_interna.perform(MockMvcRequestBuilders.get("/parrilla"))
                .andExpect(status().isOk())
                .andExpect(
                        TratadorDTO.ObjectAResultMatcher(
                               new ParrillaDTO(expectativa_lista)
                        )
                )
                .andReturn();
    }

    @DisplayName("Llamando a /parrilla cuando es sábado devuelve 14 videos.")
    @Test
    void llamandoAParrillaCuandoEsSabadoDevuelve14Videos() throws Exception {
        falsear_LocalDate = Mockito.mockStatic(LocalDate.class, Mockito.CALLS_REAL_METHODS);
        LocalDate hoy = ultimo_dia_de_la_semana_pasada.plusDays(DayOfWeek.SATURDAY.getValue());
        falsear_LocalDate.when(LocalDate::now).thenReturn(hoy);

        falsear_LocalDateTime = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS);
        LocalDateTime now = LocalDateTime.of(
                ultimo_dia_de_la_semana_pasada.plusDays(DayOfWeek.SATURDAY.getValue()),
                LocalTime.of( 12, 0)
        );
        falsear_LocalDateTime.when(LocalDateTime::now).thenReturn(now);

        ArrayList<VideoDTO> expectativa_lista = new ListaVideosValidada().obtenerLista();
        expectativa_lista.remove(expectativa_lista.size()-1);
        expectativa_lista.remove(0);

        llamada_interna.perform(MockMvcRequestBuilders.get("/parrilla"))
                .andExpect(status().isOk())
                .andExpect(
                        TratadorDTO.ObjectAResultMatcher(
                                new ParrillaDTO(expectativa_lista)
                        )
                )
                .andReturn();
    }
}