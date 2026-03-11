package com.s1.LogiTrack.repository;

import com.s1.LogiTrack.model.DetalleMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleMovimientoRepository extends JpaRepository<DetalleMovimiento,Long> {

}
