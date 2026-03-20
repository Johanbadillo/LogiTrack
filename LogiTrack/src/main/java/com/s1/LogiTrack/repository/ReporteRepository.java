package com.s1.LogiTrack.repository;

import com.s1.LogiTrack.enums.TipoMovimiento;
import com.s1.LogiTrack.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteRepository extends JpaRepository<Movimiento,Long> {


    Long countBytipo_movimiento(TipoMovimiento tipo);
}
