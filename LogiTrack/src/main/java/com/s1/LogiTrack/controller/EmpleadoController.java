package com.s1.LogiTrack.controller;

import com.s1.LogiTrack.dto.request.EmpleadoRequestDTO;
import com.s1.LogiTrack.dto.request.PersonaRequestDTO;
import com.s1.LogiTrack.dto.response.EmpleadoResponseDTO;
import com.s1.LogiTrack.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleado")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @PostMapping
    public ResponseEntity<EmpleadoResponseDTO> guardar(
            @RequestBody EmpleadoRequestDTO empleadoDTO,
            @RequestParam PersonaRequestDTO personaDTO){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(empleadoService.crear(empleadoDTO, personaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody EmpleadoRequestDTO empleadoDTO,
            @RequestParam PersonaRequestDTO personaDTO){

        return ResponseEntity.ok(
                empleadoService.actualizar(id, empleadoDTO, personaDTO)
        );
    }

    @GetMapping
    public ResponseEntity<List<EmpleadoResponseDTO>> listar(){
        return ResponseEntity.ok(empleadoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoResponseDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(empleadoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        empleadoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
