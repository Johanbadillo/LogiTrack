package com.s1.LogiTrack.controller;

import com.s1.LogiTrack.dto.request.InventarioRequestDTO;
import com.s1.LogiTrack.dto.response.InventarioResponseDTO;
import com.s1.LogiTrack.service.impl.InventarioServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
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

    @Operation(
            summary = "Crear registro de inventario",
            description = "Permite registrar un nuevo producto en el inventario de una bodega"
    )
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Registro de inventario creado correctamente"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos o body mal estructurado",
                    content = @Content
            )
    })
    public ResponseEntity<InventarioResponseDTO> guardar(@Valid @RequestBody InventarioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(inventarioService.crear(dto));
    }

    @Operation(
            summary = "Actualizar inventario",
            description = "Permite actualizar la información de un registro de inventario mediante su ID"
    )
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Inventario actualizado correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Registro de inventario no encontrado",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos",
                    content = @Content
            )
    })
    public ResponseEntity<InventarioResponseDTO> actualizar(
            @Valid @RequestBody InventarioRequestDTO dto,
            @PathVariable Long id) {

        return ResponseEntity.ok()
                .body(inventarioService.actualizar(id, dto));
    }

    @Operation(
            summary = "Listar inventario",
            description = "Obtiene la lista de todos los registros de inventario del sistema"
    )
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de inventario obtenida correctamente"
            )
    })
    public ResponseEntity<List<InventarioResponseDTO>> listarTodos() {
        return ResponseEntity.ok()
                .body(inventarioService.listar());
    }

    @Operation(
            summary = "Buscar inventario por ID",
            description = "Obtiene la información de un registro de inventario específico mediante su ID"
    )
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Registro de inventario encontrado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Registro de inventario no encontrado",
                    content = @Content
            )
    })
    public ResponseEntity<InventarioResponseDTO> buscarId(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(inventarioService.buscarPorId(id));
    }

    @Operation(
            summary = "Eliminar inventario",
            description = "Permite eliminar un registro de inventario del sistema mediante su ID"
    )
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Registro de inventario eliminado correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Registro de inventario no encontrado",
                    content = @Content
            )
    })
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        inventarioService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
