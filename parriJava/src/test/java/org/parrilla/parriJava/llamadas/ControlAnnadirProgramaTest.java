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
import org.parrilla.parriJava.validacion.FalloDTO;
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
public class ControlAnnadirProgramaTest {
    @Autowired
    private MockMvc llamadaInterna;
    @Autowired
    private AlmacenCalendario almacen_calendario;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {

    }

    @DisplayName("Llamando a /programa para crear un nuevo programa con datos mínimos devolviendo Creado.")
    @Test
    void llamandoAProgramaParaCrearUnNuevoProgramaConDatosMinimosDevolviendoCreado() throws Exception {
        Integer identificador_ultimo_programa_esperado = Long.signum(almacen_calendario.count()) + 1;
        ProgramaDTO solicitud = new ProgramaDTO(
                LocalDateTime.of(2000,2,29,16,43),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        llamadaInterna
                .perform(
                        MockMvcRequestBuilders
                                .put("/programa")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TratadorDTO.ObjectACadenaDeCaracteres(solicitud))
                )
                .andExpect(status().isCreated())
                .andReturn();


        TablaCalendario ultimo_programa_esperado = new TablaCalendario(
                identificador_ultimo_programa_esperado,
                LocalDateTime.of(2000,2,29,16,43),
                null,
                null,
                null,
                "DESCENDENTE",
                "NUNCA",
                null,
                null,
                "NUMERICA",
                "SIEMPRE",
                null,
                null,
                null,
                null
        );
        List<TablaCalendario> tabla_calendario = almacen_calendario.findAll();
        TablaCalendario ultimo_programa = tabla_calendario.get(tabla_calendario.size() - 1);
        assertEquals(ultimo_programa_esperado, ultimo_programa);
    }

    @DisplayName("Llamando a /programa para intentar crear con la falta de campos obligatorios devolviendo Mala solicitud.")
    @Test
    void llamandoAProgramaParaIntentarCrearConLaFaltaDeCamposObligatoriosDevolviendoMalaSolicitud() throws Exception {
        ProgramaDTO solicitud = new ProgramaDTO(
                null,
                null,
                null,
                null,
                null,
                null,
                1,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        llamadaInterna
                .perform(
                        MockMvcRequestBuilders
                                .put("/programa")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TratadorDTO.ObjectACadenaDeCaracteres(solicitud))
                )
                .andExpect(status().isBadRequest())
                .andExpect(
                        TratadorDTO.ObjectAResultMatcher(
                                new FalloDTO(
                                        400,
                                        "Solicitud incorrecta: [momento_inicial es obligatorio]"
                                )
                        )
                )
                .andReturn();
    }

    @DisplayName("Llamando a /programa para intentar crear con falta de mensaje devolviendo Mala solicitud.")
    @Test
    void llamandoAProgramaParaIntentarCrearConFaltaDeMensajeDevolviendoMalaSolicitud() throws Exception {
        llamadaInterna
                .perform(
                        MockMvcRequestBuilders
                                .put("/programa")
                )
                .andExpect(status().isBadRequest())
                .andExpect(
                        TratadorDTO.ObjectAResultMatcher(
                                new FalloDTO(
                                        400,
                                        "Solicitud incorrecta: no se recibió el cuerpo."
                                )
                        )
                )
                .andReturn();
    }

    @DisplayName("Llamando a /programa para crear un nuevo programa con datos completos devolviendo Creado.")
    @Test
    void llamandoAProgramaParaCrearUnNuevoProgramaConDatosCompletosDevolviendoCreado() throws Exception {
        Integer identificador_ultimo_programa_esperado = Long.signum(almacen_calendario.count()) + 1;

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

        ProgramaDTO solicitud = new ProgramaDTO(
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

        llamadaInterna
                .perform(
                        MockMvcRequestBuilders
                                .put("/programa")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TratadorDTO.ObjectACadenaDeCaracteres(solicitud))
                )
                .andExpect(status().isCreated())
                .andReturn();


        TablaCalendario ultimo_programa_esperado = new TablaCalendario(
                identificador_ultimo_programa_esperado,
                LocalDateTime.of(2000,2,29,16,43),
                "https://youtu.be/9xp1XWmJ_Wo",
                identificadores_de_lista,
                identificadores_de_canal,
                "ASCENDENTE",
                "SEMANALMENTE",
                3,
                dias_de_la_semana.toString(),
                "SEMANAL",
                "HASTA",
                9,
                LocalDate.of(2016,2,29),
                omitidos,
                excepciones
        );
        List<TablaCalendario> tabla_calendario = almacen_calendario.findAll();
        TablaCalendario ultimo_programa = tabla_calendario.get(tabla_calendario.size() - 1);
        assertEquals(ultimo_programa_esperado, ultimo_programa);
    }
}
