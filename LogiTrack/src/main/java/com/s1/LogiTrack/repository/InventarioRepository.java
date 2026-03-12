package com.s1.LogiTrack.repository;

import com.s1.LogiTrack.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario,Long> {

    boolean existsByIdBodega_IdAndIdProducto_Id(Long idBodega, Long idProducto);

}
