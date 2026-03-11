package com.s1.LogiTrack.controller;

import com.s1.LogiTrack.dto.request.DetalleMovimientoRequestDTO;
import com.s1.LogiTrack.dto.response.DetalleMovimientoResponseDTO;
import com.s1.LogiTrack.service.DetalleMovimientoService;
import com.s1.LogiTrack.service.impl.DetalleMovimientoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Crear detalle de movimiento",
            description = "Permite registrar el detalle de un movimiento de productos entre bodegas"
    )
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Detalle de movimiento creado exitosamente"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos o body mal estructurado",
                    content = @Content
            )
    })
    public ResponseEntity<DetalleMovimientoResponseDTO> guardar(@RequestBody DetalleMovimientoRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(detalleMovimientoService.crear(dto));
    }

    @Operation(
            summary = "Actualizar detalle de movimiento",
            description = "Permite actualizar la información de un detalle de movimiento existente mediante su ID"
    )
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Detalle de movimiento actualizado correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Detalle de movimiento no encontrado",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos",
                    content = @Content
            )
    })
    public ResponseEntity<DetalleMovimientoResponseDTO> actualizar(@RequestBody DetalleMovimientoRequestDTO dto, @PathVariable Long id){
        return ResponseEntity.ok().body(detalleMovimientoService.actualizar(id, dto));
    }

    @Operation(
            summary = "Buscar detalle de movimiento por ID",
            description = "Obtiene la información de un detalle de movimiento específico mediante su ID"
    )
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Detalle de movimiento encontrado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Detalle de movimiento no encontrado",
                    content = @Content
            )
    })
    public ResponseEntity<DetalleMovimientoResponseDTO> buscarId(@PathVariable Long id){
        return ResponseEntity.ok().body(detalleMovimientoService.buscarPorId(id));
    }

    @Operation(
            summary = "Eliminar detalle de movimiento",
            description = "Permite eliminar un detalle de movimiento del sistema mediante su ID"
    )
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Detalle de movimiento eliminado correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Detalle de movimiento no encontrado",
                    content = @Content
            )
    })
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        detalleMovimientoService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Buscar detalles por movimiento",
            description = "Obtiene todos los detalles asociados a un movimiento específico mediante el ID del movimiento"
    )
    @GetMapping("/movimiento/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Detalles de movimiento encontrados"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Movimiento no encontrado o sin detalles",
                    content = @Content
            )
    })
    public ResponseEntity<List<DetalleMovimientoResponseDTO>> buscarPorMovimientoId(@PathVariable Long id){
        return ResponseEntity.ok().body(detalleMovimientoService.buscarPorMovimientoId(id));
    }
}
