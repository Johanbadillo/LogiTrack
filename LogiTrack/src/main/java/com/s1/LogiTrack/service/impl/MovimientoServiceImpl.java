package com.s1.LogiTrack.service.impl;

import com.s1.LogiTrack.dto.request.MovimientoRequestDTO;
import com.s1.LogiTrack.dto.response.BodegaResponseDTO;
import com.s1.LogiTrack.dto.response.EmpleadoResponseDTO;
import com.s1.LogiTrack.dto.response.MovimientoResponseDTO;
import com.s1.LogiTrack.exception.BusinessRuleException;
import com.s1.LogiTrack.mapper.BodegaMapper;
import com.s1.LogiTrack.mapper.EmpleadoMapper;
import com.s1.LogiTrack.mapper.MovimientoMapper;
import com.s1.LogiTrack.model.Bodega;
import com.s1.LogiTrack.model.Empleado;
import com.s1.LogiTrack.model.Movimiento;
import com.s1.LogiTrack.repository.BodegaRepository;
import com.s1.LogiTrack.repository.EmpleadoRepository;
import com.s1.LogiTrack.repository.MovimientoRepository;
import com.s1.LogiTrack.service.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final MovimientoMapper movimientoMapper;

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;

    private final BodegaRepository bodegaRepository;
    private final BodegaMapper bodegaMapper;


    @Override
    public MovimientoResponseDTO crear(@NonNull MovimientoRequestDTO dto) {

        Empleado e = empleadoRepository.findById(dto.idEmpleado())
                .orElseThrow(() -> new BusinessRuleException("NO EXISTE DICHO EMPLEADO"));

        Bodega bo = bodegaRepository.findById(dto.idBodegaOrigen())
                .orElseThrow(() -> new BusinessRuleException("NO EXISTE DICHA BODEGA ORIGEN"));

        Bodega bd = bodegaRepository.findById(dto.idBodegaDestino())
                .orElseThrow(() -> new BusinessRuleException("NO EXISTE DICHA BODEGA DESTINO"));

        Movimiento m = movimientoMapper.DTOAentidad(dto, e, bo, bd);
        Movimiento m_insertado = movimientoRepository.save(m);

        EmpleadoResponseDTO dtoE = empleadoMapper.entidadADTO(e);
        BodegaResponseDTO dtoBO = bodegaMapper.entidadADTO(bo, dtoE);
        BodegaResponseDTO dtoBD = bodegaMapper.entidadADTO(bd, dtoE);

        return movimientoMapper.entidadADTO(m_insertado, dtoE, dtoBO, dtoBD);
    }

    @Override
    public List<MovimientoResponseDTO> listar() {

        return movimientoRepository.findAll().stream()
                .map(dato -> {

                    Empleado e = dato.getIdEmpleado();
                    Bodega bo = dato.getIdBodegaOrigen();
                    Bodega bd = dato.getIdBodegaDestino();

                    return movimientoMapper.entidadADTO(dato, empleadoMapper.entidadADTO(e), bodegaMapper.entidadADTO(bo, empleadoMapper.entidadADTO(e)), bodegaMapper.entidadADTO(bd, empleadoMapper.entidadADTO(e)));
                })
                .toList();
    }

    @Override
    public MovimientoResponseDTO buscarPorId(Long id) {

        Movimiento m = movimientoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("NO EXISTE DICHO MOVIMIENTO"));

        Empleado e = m.getIdEmpleado();
        Bodega bo = m.getIdBodegaOrigen();
        Bodega bd = m.getIdBodegaDestino();

        EmpleadoResponseDTO dtoE = empleadoMapper.entidadADTO(e);
        BodegaResponseDTO dtoBO = bodegaMapper.entidadADTO(bo, dtoE);
        BodegaResponseDTO dtoBD = bodegaMapper.entidadADTO(bd, dtoE);

        return movimientoMapper.entidadADTO(m, dtoE, dtoBO, dtoBD);
    }

    @Override
    public MovimientoResponseDTO actualizar(Long id, @NonNull MovimientoRequestDTO dto) {

        Movimiento m = movimientoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("NO EXISTE MOVIMIENTO A ACTUALIZAR"));

        Empleado e = empleadoRepository.findById(dto.idEmpleado())
                .orElseThrow(() -> new BusinessRuleException("NO EXISTE DICHO EMPLEADO"));

        Bodega bo = bodegaRepository.findById(dto.idBodegaOrigen())
                .orElseThrow(() -> new BusinessRuleException("NO EXISTE BODEGA ORIGEN"));

        Bodega bd = bodegaRepository.findById(dto.idBodegaDestino())
                .orElseThrow(() -> new BusinessRuleException("NO EXISTE BODEGA DESTINO"));

        movimientoMapper.actualizarEntidadDesdeDTO(m, dto, e, bo, bd);

        Movimiento m_actualizado = movimientoRepository.save(m);

        EmpleadoResponseDTO dtoE = empleadoMapper.entidadADTO(e);
        BodegaResponseDTO dtoBO = bodegaMapper.entidadADTO(bo, dtoE);
        BodegaResponseDTO dtoBD = bodegaMapper.entidadADTO(bd, dtoE);

        return movimientoMapper.entidadADTO(m_actualizado, dtoE, dtoBO, dtoBD);
    }

    @Override
    public void eliminar(Long id) {

        Movimiento m = movimientoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("NO EXISTE MOVIMIENTO A ELIMINAR"));

        movimientoRepository.delete(m);
    }

}
