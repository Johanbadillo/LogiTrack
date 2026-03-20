package com.s1.LogiTrack.controller;

import com.s1.LogiTrack.dto.response.ReporteMovimientoResponseDTO;
import com.s1.LogiTrack.service.impl.ReportesServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReportesController {

    private final ReportesServiceImpl reportesService;

    @GetMapping("/movimientos")
    public ResponseEntity<ReporteMovimientoResponseDTO> clasificacionMovimientos(){
        return ResponseEntity.ok()
                .body(reportesService.clasificacionMovimientos());
    }
}
