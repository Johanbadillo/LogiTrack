package com.s1.LogiTrack.controller;

import com.s1.LogiTrack.dto.request.DetalleMovimientoRequestDTO;
import com.s1.LogiTrack.dto.response.DetalleMovimientoResponseDTO;
import com.s1.LogiTrack.service.impl.DetalleMovimientoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalleMovimiento")
@RequiredArgsConstructor
public class DetalleMovimientoController {

    private final DetalleMovimientoServiceImpl detalleMovimientoService;

    @PostMapping
    public ResponseEntity<DetalleMovimientoResponseDTO> guardar(@RequestBody DetalleMovimientoRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(detalleMovimientoService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleMovimientoResponseDTO> actualizar(@RequestBody DetalleMovimientoRequestDTO dto, @PathVariable Long id){
        return ResponseEntity.ok().body(detalleMovimientoService.actualizar(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<DetalleMovimientoResponseDTO>> listarTodos(){
        return ResponseEntity.ok().body(detalleMovimientoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleMovimientoResponseDTO> buscarId(@PathVariable Long id){
        return ResponseEntity.ok().body(detalleMovimientoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        detalleMovimientoService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
