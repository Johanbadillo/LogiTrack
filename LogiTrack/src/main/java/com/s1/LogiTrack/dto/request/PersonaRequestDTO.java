package com.s1.LogiTrack.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record PersonaRequestDTO(
        @Size(min = 2,max = 50, message = "El nombre debe estar en el rango de 2 a 50 caracteres")
        @NotBlank(message = "Este campo no puede estar vacio o solo espacios")
        @Schema(description = "Nombre completo de la persona", example = "Juan Perez")
        String nombre,
        @Size(min = 10,max = 20, message = "El documento debe estar en el rango de 10 a 20 caracteres")
        @Pattern(regexp = "^[0-9]{10,}$")
        @NotBlank(message = "Este campo no puede estar vacio o solo con espacios")
        @Schema(description = "Número de documento de identidad", example = "1234567890")
        String documento,
        @NotBlank(message = "Este campo no puede estar vacio o solo con espacios")
        @Email(message = "Esta mal ingresado el formato del correo")
        @Schema(description = "Correo electrónico de la persona", example = "juan.perez@email.com")
        String correo,
        @Size(min = 10,max = 20, message = "El telefono debe estar en el rango de 10 a 20 caracteres")
        @Pattern(regexp = "^[0-9]{10,20}$")
        @NotBlank(message = "Este campo no puede estar vacio o solo con espacios")
        @Schema(description = "Número de teléfono de contacto", example = "3001234567")
        String telefono
) {
}
