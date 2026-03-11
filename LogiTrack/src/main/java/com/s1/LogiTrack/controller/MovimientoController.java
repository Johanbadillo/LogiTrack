package com.s1.LogiTrack.controller;

import com.s1.LogiTrack.dto.request.MovimientoRequestDTO;
import com.s1.LogiTrack.dto.response.MovimientoResponseDTO;
import com.s1.LogiTrack.service.impl.MovimientoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimiento")
@RequiredArgsConstructor
public class MovimientoController {

    private final MovimientoServiceImpl movimientoService;

    @PostMapping
    public ResponseEntity<MovimientoResponseDTO> guardar(@RequestBody MovimientoRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(movimientoService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoResponseDTO> actualizar(
            @RequestBody MovimientoRequestDTO dto,
            @PathVariable Long id){

        return ResponseEntity.ok()
                .body(movimientoService.actualizar(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<MovimientoResponseDTO>> listarTodos(){
        return ResponseEntity.ok()
                .body(movimientoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoResponseDTO> buscarId(@PathVariable Long id){
        return ResponseEntity.ok()
                .body(movimientoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        movimientoService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
