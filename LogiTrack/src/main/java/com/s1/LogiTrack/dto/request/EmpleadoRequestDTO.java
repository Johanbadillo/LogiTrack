package com.s1.LogiTrack.dto.request;

import com.s1.LogiTrack.enums.Rol;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record EmpleadoRequestDTO(

        @Size(min = 2, max = 50, message = "El nombre debe estar en el rango de 2 a 50 caracteres")
        @NotBlank(message = "Este campo no puede estar vacio o solo espacios")
        @Schema(description = "Nombre completo de la persona", example = "Juan Perez")
        String nombre,

        @Size(min = 10, max = 20, message = "El documento debe estar en el rango de 10 a 20 caracteres")
        @Pattern(regexp = "^[0-9,.']{10,20}$", message = "El documento debe estar en el rango de 10 a 20 caracteres")
        @NotBlank(message = "Este campo no puede estar vacio o solo con espacios")
        @Schema(description = "Número de documento de identidad", example = "1234567890")
        String documento,

        @NotBlank(message = "Este campo no puede estar vacio o solo con espacios")
        @Email(message = "Esta mal ingresado el formato del correo")
        @Schema(description = "Correo electrónico de la persona", example = "juan.perez@email.com")
        String correo,

        @Size(min = 10, max = 20, message = "El telefono debe estar en el rango de 10 a 20 caracteres")
        @Pattern(regexp = "^[0-9]{10,20}$", message = "El telefono debe estar en el rango de 10 a 20 caracteres")
        @NotBlank(message = "Este campo no puede estar vacio o solo con espacios")
        @Schema(description = "Número de teléfono de contacto", example = "3001234567")
        String telefono,

        @NotNull(message = "Esta campo no puede estar vacio o solo con espacios")
        @Schema(description = "Rol del empleado dentro del sistema (ADMIN/EMPLEADO)", example = "ADMIN")
        Rol rol,

        @NotBlank(message = "Esta campo no puede estar vacio o solo con espacios")
        @Size(min = 5, max = 20, message = "El campo debe estar en el rango de 5 a 20 caracteres")
        @Schema(description = "Nombre de usuario del empleado", example = "juan_admin")
        String usuario,

        @NotBlank(message = "Esta campo no puede estar vacio o solo con espacios")
        @Size(min = 8, max = 50, message = "La contraseña debe tener un minimo de 8 a 50 caracteres")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,50}$",
                message = "La contraseña debe tener entre 8 y 50 caracteres, incluir al menos una mayuscula, una minuscula y un numero"
        )
        @Schema(description = "Contraseña del usuario con al menos una mayúscula, una minúscula y un número", example = "Admin1234")
        String contrasena

) {
}
