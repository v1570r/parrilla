package org.parrilla.parriJava.llamadas;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.parrilla.parriJava.basededatos.AlmacenCalendario;
import org.parrilla.parriJava.basededatos.TablaCalendario;
import org.parrilla.parriJava.modulador.BaseDatosADTO;
import org.parrilla.parriJava.validacion.ProgramaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ControlPrograma {
    private final AlmacenCalendario almacen_calendario;

    public ControlPrograma(AlmacenCalendario almacen_calendario) {
        this.almacen_calendario = almacen_calendario;
    }

    @PutMapping("/programa")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void guardarPrograma(
            @Valid
            @NotNull
            @RequestBody
            ProgramaDTO programaDTO
    ){
        TablaCalendario nuevo_programa = new TablaCalendario();
        nuevo_programa.setMomento_inicial(programaDTO.momento_inicial());
        nuevo_programa.setIdentificador_video(programaDTO.identificador_video());
        nuevo_programa.setIdentificadores_de_lista(programaDTO.identificadores_de_lista());
        nuevo_programa.setIdentificadores_de_canal(programaDTO.identificadores_de_canal());
        if( null != programaDTO.orden() ) {
            nuevo_programa.setOrden(programaDTO.orden().getOrden());
        }
        if( null != programaDTO.repetir() ){
            nuevo_programa.setRepetir(programaDTO.repetir().getRepeticion());
        }
        nuevo_programa.setRepetir_cada(programaDTO.repetir_cada());
        if( null != programaDTO.dias_de_la_semana() ){
            nuevo_programa.setDias_de_la_semana(programaDTO.dias_de_la_semana().toString());
        }
        if( null != programaDTO.coincidencia() ){
            nuevo_programa.setCoincidencia(programaDTO.coincidencia().getCoincidencia());
        }
        if( null != programaDTO.duracion() ){
            nuevo_programa.setDuracion(programaDTO.duracion().getDuracion());
        }
        nuevo_programa.setNumero_de_repeticiones(programaDTO.numero_de_repeticiones());
        nuevo_programa.setCaducidad(programaDTO.caducidad());
        nuevo_programa.setExcepciones(programaDTO.excepciones());
        nuevo_programa.setOmitidos(programaDTO.omitidos());
        almacen_calendario.save(nuevo_programa);
    }

    @GetMapping("/programa/{identificador}")
    public ProgramaDTO obtenerPrograma(
            @Valid
            @NotNull
            @PathVariable
            @Positive
            Long identificador
    ){
        return BaseDatosADTO.transformarAProgramaDTODesdeTablaCalendario(
                almacen_calendario
                        .findById(identificador)
                        .get()
        );
    }
}
