package com.s1.LogiTrack.controller;

import com.s1.LogiTrack.dto.request.InventarioRequestDTO;
import com.s1.LogiTrack.dto.response.InventarioResponseDTO;
import com.s1.LogiTrack.service.impl.InventarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioServiceImpl inventarioService;

    @PostMapping
    public ResponseEntity<InventarioResponseDTO> guardar(@RequestBody InventarioRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(inventarioService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioResponseDTO> actualizar(
            @RequestBody InventarioRequestDTO dto,
            @PathVariable Long id){

        return ResponseEntity.ok()
                .body(inventarioService.actualizar(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<InventarioResponseDTO>> listarTodos(){
        return ResponseEntity.ok()
                .body(inventarioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioResponseDTO> buscarId(@PathVariable Long id){
        return ResponseEntity.ok()
                .body(inventarioService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        inventarioService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
