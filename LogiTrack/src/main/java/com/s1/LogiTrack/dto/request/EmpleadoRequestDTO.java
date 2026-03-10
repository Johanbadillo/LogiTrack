package com.s1.LogiTrack.dto.request;

import com.s1.LogiTrack.enums.Rol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EmpleadoRequestDTO(

    @NotBlank(message = "Esta campo no puede estar vacio o solo con espacios")
    Rol rol,
    @NotBlank(message = "Esta campo no puede estar vacio o solo con espacios")
    @Size(min = 5,max = 20, message = "El campo debe estar en el rango de 5 a 20 caracteres")
    String usuario,
    @NotBlank(message = "Esta campo no puede estar vacio o solo con espacios")
    @Size(min = 8,max = 50,message = "La contraseña debe tener un minimo de 8 a 50 caracteres")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,50}$",
            message = "La contraseña debe tener entre 8 y 50 caracteres, incluir al menos una mayuscula, una minuscula y un numero"
    )
    String contrasena
) {
}
