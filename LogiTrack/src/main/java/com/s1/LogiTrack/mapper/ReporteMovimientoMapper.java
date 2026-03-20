package com.s1.LogiTrack.mapper;

import com.s1.LogiTrack.dto.response.ReporteMovimientoResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ReporteMovimientoMapper {

    public ReporteMovimientoResponseDTO entidadADTO(Long cantidadTotal, Long cantidadMovimientoEntrada,
                                                    Long cantidadMovimientoSalida,
                                                    Long cantidadMovimientoTransferencia){
        return new ReporteMovimientoResponseDTO(
                cantidadTotal,
                cantidadMovimientoEntrada,
                cantidadMovimientoSalida,
                cantidadMovimientoTransferencia
        );
    }
}
