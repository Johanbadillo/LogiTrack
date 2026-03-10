package com.s1.LogiTrack.mapper;

import com.s1.LogiTrack.dto.request.EmpleadoRequestDTO;
import com.s1.LogiTrack.dto.request.PersonaRequestDTO;
import com.s1.LogiTrack.dto.response.EmpleadoResponseDTO;
import com.s1.LogiTrack.model.Empleado;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoMapper {

    public EmpleadoResponseDTO entidadADTO(Empleado empleado) {
        if (empleado == null) return null;

        return new EmpleadoResponseDTO(
                empleado.getId(),
                empleado.getNombre(),
                empleado.getDocumento(),
                empleado.getCorreo(),
                empleado.getTelefono(),
                empleado.getRol()
        );
    }

    public Empleado DTOAentidad(EmpleadoRequestDTO dto, PersonaRequestDTO pdto) {
        if (dto == null) return null;

        Empleado e = new Empleado();
        e.setNombre(pdto.nombre());
        e.setDocumento(pdto.documento());
        e.setCorreo(pdto.correo());
        e.setTelefono(pdto.telefono());
        e.setRol(dto.rol());
        e.setUsuario(dto.usuario());
        e.setContrasena(dto.contrasena());

        return e;
    }

    public void actualizarEntidadDesdeDTO(Empleado empleado, EmpleadoRequestDTO dto, PersonaRequestDTO pdto) {
        if (empleado == null || dto == null || pdto == null) return;

        empleado.setNombre(pdto.nombre());
        empleado.setDocumento(pdto.documento());
        empleado.setCorreo(pdto.correo());
        empleado.setTelefono(pdto.telefono());

        empleado.setRol(dto.rol());
        empleado.setUsuario(dto.usuario());
        empleado.setContrasena(dto.contrasena());
    }

}
