package com.s1.LogiTrack.controller;

import com.s1.LogiTrack.dto.request.MovimientoRequestDTO;
import com.s1.LogiTrack.dto.response.MovimientoResponseDTO;
import com.s1.LogiTrack.service.impl.MovimientoServiceImpl;
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
@RequestMapping("/api/movimiento")
@RequiredArgsConstructor
public class MovimientoController {

    private final MovimientoServiceImpl movimientoService;

    @Operation(
            summary = "Crear movimiento",
            description = "Permite registrar un nuevo movimiento de productos entre bodegas"
    )
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Movimiento creado correctamente"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos o body mal estructurado",
                    content = @Content
            )
    })
    public ResponseEntity<MovimientoResponseDTO> guardar(@RequestBody MovimientoRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(movimientoService.crear(dto));
    }

    @Operation(
            summary = "Actualizar movimiento",
            description = "Permite actualizar la información de un movimiento mediante su ID"
    )
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Movimiento actualizado correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Movimiento no encontrado",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos",
                    content = @Content
            )
    })
    public ResponseEntity<MovimientoResponseDTO> actualizar(
            @RequestBody MovimientoRequestDTO dto,
            @PathVariable Long id){

        return ResponseEntity.ok()
                .body(movimientoService.actualizar(id, dto));
    }

    @Operation(
            summary = "Listar movimientos",
            description = "Obtiene la lista de todos los movimientos registrados en el sistema"
    )
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de movimientos obtenida correctamente"
            )
    })
    public ResponseEntity<List<MovimientoResponseDTO>> listarTodos(){
        return ResponseEntity.ok()
                .body(movimientoService.listar());
    }

    @Operation(
            summary = "Buscar movimiento por ID",
            description = "Obtiene la información de un movimiento específico mediante su ID"
    )
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Movimiento encontrado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Movimiento no encontrado",
                    content = @Content
            )
    })
    public ResponseEntity<MovimientoResponseDTO> buscarId(@PathVariable Long id){
        return ResponseEntity.ok()
                .body(movimientoService.buscarPorId(id));
    }

    @Operation(
            summary = "Eliminar movimiento",
            description = "Permite eliminar un movimiento del sistema mediante su ID"
    )
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Movimiento eliminado correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Movimiento no encontrado",
                    content = @Content
            )
    })
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        movimientoService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
