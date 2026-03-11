package com.s1.LogiTrack.service;

import com.s1.LogiTrack.dto.request.EmpleadoRequestDTO;
import com.s1.LogiTrack.dto.request.PersonaRequestDTO;
import com.s1.LogiTrack.dto.response.EmpleadoResponseDTO;

import java.util.List;

public interface EmpleadoService {

    EmpleadoResponseDTO crear(EmpleadoRequestDTO dto, PersonaRequestDTO pdto);

    List<EmpleadoResponseDTO> listar();

    EmpleadoResponseDTO buscarPorId(Long id);

    EmpleadoResponseDTO actualizar(Long id, EmpleadoRequestDTO dto, PersonaRequestDTO pdto);

    void eliminar(Long id);
}
