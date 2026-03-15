package com.s1.LogiTrack.controller;

import com.s1.LogiTrack.dto.request.EmpleadoRequestDTO;
import com.s1.LogiTrack.dto.response.EmpleadoResponseDTO;
import com.s1.LogiTrack.service.EmpleadoService;
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
@RequestMapping("/api/empleado")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @Operation(
            summary = "Crear empleado",
            description = "Permite registrar un nuevo empleado junto con la información de la persona asociada"
    )
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Empleado creado correctamente"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos o estructura incorrecta",
                    content = @Content
            )
    })
    public ResponseEntity<EmpleadoResponseDTO> guardar(
            @Valid @RequestBody EmpleadoRequestDTO empleadoDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(empleadoService.crear(empleadoDTO));
    }

    @Operation(
            summary = "Actualizar empleado",
            description = "Permite actualizar la información de un empleado y su persona asociada mediante el ID"
    )
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Empleado actualizado correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Empleado no encontrado",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos",
                    content = @Content
            )
    })
    public ResponseEntity<EmpleadoResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody EmpleadoRequestDTO empleadoDTO) {

        return ResponseEntity.ok(
                empleadoService.actualizar(id, empleadoDTO)
        );
    }

    @Operation(
            summary = "Listar empleados",
            description = "Obtiene la lista de todos los empleados registrados en el sistema"
    )
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de empleados obtenida correctamente"
            )
    })
    public ResponseEntity<List<EmpleadoResponseDTO>> listar() {
        return ResponseEntity.ok(empleadoService.listar());
    }

    @Operation(
            summary = "Buscar empleado por ID",
            description = "Obtiene la información de un empleado específico mediante su ID"
    )
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Empleado encontrado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Empleado no encontrado",
                    content = @Content
            )
    })
    public ResponseEntity<EmpleadoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(empleadoService.buscarPorId(id));
    }

    @Operation(
            summary = "Eliminar empleado",
            description = "Permite eliminar un empleado del sistema mediante su ID"
    )
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Empleado eliminado correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Empleado no encontrado",
                    content = @Content
            )
    })
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        empleadoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
