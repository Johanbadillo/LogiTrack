package com.s1.LogiTrack.repository;

import com.s1.LogiTrack.model.DetalleMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleMovimientoRepository extends JpaRepository<DetalleMovimiento,Long> {

    List<DetalleMovimiento> findByIdMovimientoId(Long idMovimiento);

}
