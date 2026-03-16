package com.s1.LogiTrack.auth;

import com.s1.LogiTrack.config.JwtService;
import com.s1.LogiTrack.exception.BusinessRuleException;
import com.s1.LogiTrack.model.Empleado;
import com.s1.LogiTrack.repository.EmpleadoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final EmpleadoRepository empleadoRepository;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {

        Empleado empleado = empleadoRepository.findByUsuario(request.usuario());

        if (empleado == null) {
            throw new BusinessRuleException("Usuario no encontrado");
        }

        if (!empleado.getContrasena().equals(request.contrasena())) {
            throw new BusinessRuleException("Credenciales inválidas");
        }

        String token = jwtService.generateToken(empleado.getUsuario(), empleado.getRol().name());

        return ResponseEntity.ok(new LoginResponse(token, empleado.getRol().name()));
    }

}
