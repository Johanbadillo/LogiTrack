package com.s1.LogiTrack.auth;

import com.s1.LogiTrack.config.JwtService;
import com.s1.LogiTrack.exception.BusinessRuleException;
import com.s1.LogiTrack.model.Empleado;
import com.s1.LogiTrack.repository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthControlle {

    private final EmpleadoRepository empleadoRepository;
    private final JwtService jwtService;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {

        Empleado empleado = empleadoRepository.findByUsuario(request.usuario());

        if (empleado == null) {
            throw new BusinessRuleException("Usuario no encontrado");
        }

        if (!empleado.getContrasena().equals(request.contrasena())) {
            throw new BusinessRuleException("Credenciales inválidas");
        }

        String token = jwtService.generateToken(empleado.getUsuario());

        return Map.of("token", token);
    }

}
