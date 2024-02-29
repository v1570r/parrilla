package org.parrilla.parriJava.modulador;

import org.parrilla.parriJava.basededatos.TablaParrilla;
import org.parrilla.parriJava.validacion.ParrillaDTO;
import org.parrilla.parriJava.validacion.VideoDTO;

import java.util.List;

public class BaseDatosADTO {
    public static ParrillaDTO annadirMensajeAParrillaDTODesdeTablaParrilla(ParrillaDTO parrillaDTO, List<TablaParrilla> tablaParrilla){
        for (int i = 0; i < tablaParrilla.size(); i++){
            TablaParrilla fila = tablaParrilla.get(i);
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
}
