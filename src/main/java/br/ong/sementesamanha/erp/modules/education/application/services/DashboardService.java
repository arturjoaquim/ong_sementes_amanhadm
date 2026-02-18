package br.ong.sementesamanha.erp.modules.education.application.services;

import br.ong.sementesamanha.erp.modules.education.application.dtos.dashboard.DashboardDistributionDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.dashboard.DashboardStatsDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.dashboard.RecentActivityDTO;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.EmployeeRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.StudentRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.WorkshopRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.WorkshopSessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final StudentRepository studentRepository;
    private final WorkshopRepository workshopRepository;
    private final EmployeeRepository employeeRepository;

    private final WorkshopSessionRepository workshopSessionRepository;

    public DashboardService(StudentRepository studentRepository,
                            WorkshopRepository workshopRepository,
                            EmployeeRepository employeeRepository,
                            WorkshopSessionRepository workshopSessionRepository) {
        this.studentRepository = studentRepository;
        this.workshopRepository = workshopRepository;
        this.employeeRepository = employeeRepository;
        this.workshopSessionRepository = workshopSessionRepository;
    }

    @Transactional(readOnly = true)
    public DashboardStatsDTO getStats() {
        long totalStudents = studentRepository.count();
        long totalWorkshops = workshopRepository.count();
        long totalEmployees = employeeRepository.count();

        return new DashboardStatsDTO(totalStudents, totalWorkshops, totalEmployees);
    }

    @Transactional(readOnly = true)
    public List<RecentActivityDTO> getRecentActivities() {
        List<RecentActivityDTO> activities = new ArrayList<>();

        studentRepository.findTop5ByOrderByRegistrationDateDesc().forEach(s -> {
            // Conversão segura de java.sql.Date para LocalDateTime
            LocalDateTime date;
            if (s.getRegistrationDate() instanceof java.sql.Date) {
                date = ((java.sql.Date) s.getRegistrationDate()).toLocalDate().atStartOfDay();
            } else {
                date = s.getRegistrationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            }
            activities.add(new RecentActivityDTO(s.getId(), "Novo Estudante", s.getPerson().getPersonName(), date, "STUDENT"));
        });

        workshopSessionRepository.findTop5ByOrderByCreatedAtDesc().forEach(ws -> {
            activities.add(new RecentActivityDTO(ws.getId(), "Nova Sessão de Oficina", ws.getWorkshop().getName(), ws.getCreatedAt(), "WORKSHOP"));
        });

        employeeRepository.findTop5ByOrderByCreatedAtDesc().forEach(e -> {
            activities.add(new RecentActivityDTO(e.getId(), "Novo Funcionário", e.getPerson().getPersonName(), e.getCreatedAt(), "EMPLOYEE"));
        });

        return activities.stream()
                .sorted(Comparator.comparing(RecentActivityDTO::timestamp).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DashboardDistributionDTO getDistribution() {
        long totalStudents = studentRepository.count();
        long activeStudents = studentRepository.countActiveStudents();
        double activeStudentsPercentage = totalStudents > 0 ? (double) activeStudents / totalStudents * 100 : 0;

        long totalWorkshops = workshopRepository.count();
        long fullWorkshops = workshopRepository.countFullWorkshops();
        double fullWorkshopsPercentage = totalWorkshops > 0 ? (double) fullWorkshops / totalWorkshops * 100 : 0;

        return new DashboardDistributionDTO(activeStudentsPercentage, fullWorkshopsPercentage);
    }
}
