package br.ong.sementesamanha.erp.modules.education.infraestructure.controllers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.dashboard.DashboardDistributionDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.dashboard.DashboardStatsDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.dashboard.RecentActivityDTO;
import br.ong.sementesamanha.erp.modules.education.application.services.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/stats")
    public ResponseEntity<DashboardStatsDTO> getStats() {
        return ResponseEntity.ok(service.getStats());
    }

    @GetMapping("/activities")
    public ResponseEntity<List<RecentActivityDTO>> getRecentActivities() {
        return ResponseEntity.ok(service.getRecentActivities());
    }

    @GetMapping("/distribution")
    public ResponseEntity<DashboardDistributionDTO> getDistribution() {
        return ResponseEntity.ok(service.getDistribution());
    }
}
