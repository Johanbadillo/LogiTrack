package com.s1.LogiTrack.repository;

import com.s1.LogiTrack.model.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodegaRepository extends JpaRepository<Bodega,Long> {

}
