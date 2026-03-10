package com.s1.LogiTrack.dto.request;

import jakarta.validation.constraints.*;

public record PersonaRequestDTO(
        @Size(min = 2,max = 50, message = "El nombre debe estar en el rango de 2 a 50 caracteres")
        @NotBlank(message = "Este campo no puede estar vacio o solo espacios")
        String nombre,
        @Size(min = 10,max = 20, message = "El documento debe estar en el rango de 10 a 20 caracteres")
        @Pattern(regexp = "^[0-9]{10,}$")
        @NotBlank(message = "Este campo no puede estar vacio o solo con espacios")
        String documento,
        @NotBlank(message = "Este campo no puede estar vacio o solo con espacios")
        @Email(message = "Esta mal ingresado el formato del correo")
        String correo,
        @Size(min = 10,max = 20, message = "El telefono debe estar en el rango de 10 a 20 caracteres")
        @Pattern(regexp = "^[0-9]{10,20}$")
        @NotBlank(message = "Este campo no puede estar vacio o solo con espacios")
        String telefono
) {
}
