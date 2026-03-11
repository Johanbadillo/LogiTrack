package com.s1.LogiTrack.repository;

import com.s1.LogiTrack.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento,Long> {

}
