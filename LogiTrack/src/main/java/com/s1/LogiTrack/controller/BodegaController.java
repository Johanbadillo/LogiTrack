package com.s1.LogiTrack.controller;

import com.s1.LogiTrack.dto.request.BodegaRequestDTO;
import com.s1.LogiTrack.dto.response.BodegaResponseDTO;
import com.s1.LogiTrack.service.impl.BodegaServiceImpl;
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
@RequestMapping("/api/bodega")
@RequiredArgsConstructor
public class BodegaController {

    private final BodegaServiceImpl bodegaService;

    @Operation(
            summary = "Crear bodega",
            description = "Permite registrar una nueva bodega en el sistema"
    )
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Bodega creada exitosamente"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos o body mal estructurado",
                    content = @Content
            )
    })
    public ResponseEntity<BodegaResponseDTO> guardar(@Valid @RequestBody BodegaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bodegaService.crear(dto));
    }

    @Operation(
            summary = "Actualizar bodega",
            description = "Permite actualizar la información de una bodega existente mediante su ID"
    )
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Bodega actualizada correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Bodega no encontrada",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos",
                    content = @Content
            )
    })
    public ResponseEntity<BodegaResponseDTO> actualizar(@Valid @RequestBody BodegaRequestDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok().body(bodegaService.actualizar(id, dto));
    }

    @Operation(
            summary = "Listar bodegas",
            description = "Obtiene la lista de todas las bodegas registradas en el sistema"
    )
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de bodegas obtenida correctamente"
            )
    })
    public ResponseEntity<List<BodegaResponseDTO>> listarTodos() {
        return ResponseEntity.ok().body(bodegaService.listar());
    }

    @Operation(
            summary = "Buscar bodega por ID",
            description = "Obtiene la información de una bodega específica mediante su ID"
    )
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Bodega encontrada"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Bodega no encontrada",
                    content = @Content

            )
    })
    public ResponseEntity<BodegaResponseDTO> buscarId(@PathVariable Long id) {
        return ResponseEntity.ok().body(bodegaService.buscarPorId(id));
    }

    @Operation(
            summary = "Eliminar bodega",
            description = "Permite eliminar una bodega del sistema mediante su ID"
    )
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Bodega eliminada correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Bodega no encontrada",
                    content = @Content
            )
    })
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        bodegaService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
