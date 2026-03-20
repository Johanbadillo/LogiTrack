package com.s1.LogiTrack.service.impl;

import com.s1.LogiTrack.dto.response.ReporteMovimientoResponseDTO;
import com.s1.LogiTrack.mapper.ReporteMovimientoMapper;
import com.s1.LogiTrack.repository.ReporteRepository;
import com.s1.LogiTrack.service.ReportesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.s1.LogiTrack.enums.TipoMovimiento.*;

@Service
@RequiredArgsConstructor
public class ReportesServiceImpl implements ReportesService {

    private final ReporteRepository reporteRepository;
    private final ReporteMovimientoMapper reporteMovimientoMapper;

    @Override
    public ReporteMovimientoResponseDTO clasificacionMovimientos() {

        Long cantidadTotal = reporteRepository.count();

        Long cantidadMovimientoEntrada = reporteRepository.countBytipo_movimiento(ENTRADA);

        Long cantidadMovimientoSalida = reporteRepository.countBytipo_movimiento(SALIDA);

        Long cantidadMovimientoTransferencia = reporteRepository.countBytipo_movimiento(TRANSFERENCIA);

        return reporteMovimientoMapper.entidadADTO(cantidadTotal,
                cantidadMovimientoEntrada,cantidadMovimientoSalida,cantidadMovimientoTransferencia);
    }

}
