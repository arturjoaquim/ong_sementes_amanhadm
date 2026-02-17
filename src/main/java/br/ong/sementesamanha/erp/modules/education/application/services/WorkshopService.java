package br.ong.sementesamanha.erp.modules.education.application.services;

import br.ong.sementesamanha.erp.modules.education.application.dtos.workshop.CreateWorkshopDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.workshop.CreateWorkshopSessionDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.workshop.WorkshopDetailsResponseDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.workshop.WorkshopPreviewResponseDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.*;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.WorkshopMapper;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkshopService {

    private final WorkshopRepository workshopRepository;
    private final WorkshopSessionRepository sessionRepository;
    private final EmployeeRepository employeeRepository;
    private final StudentRepository studentRepository;
    private final WorkshopEnrollmentRepository enrollmentRepository;
    private final WorkshopMapper workshopMapper;

    public WorkshopService(WorkshopRepository workshopRepository,
                           WorkshopSessionRepository sessionRepository,
                           EmployeeRepository employeeRepository,
                           StudentRepository studentRepository,
                           WorkshopEnrollmentRepository enrollmentRepository,
                           WorkshopMapper workshopMapper) {
        this.workshopRepository = workshopRepository;
        this.sessionRepository = sessionRepository;
        this.employeeRepository = employeeRepository;
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.workshopMapper = workshopMapper;
    }

    @Transactional(readOnly = true)
    public List<WorkshopPreviewResponseDTO> getAllPreviews() {
        return workshopRepository.findAll().stream()
                .map(workshopMapper::toPreview)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public WorkshopDetailsResponseDTO findById(Long id) {
        return workshopRepository.findById(id)
                .map(workshopMapper::toDetails)
                .orElseThrow(() -> new IllegalArgumentException("Workshop not found with id: " + id));
    }

    @Transactional
    public Workshop create(CreateWorkshopDTO dto) {
        Workshop workshop = new Workshop();
        workshop.setName(dto.name());
        workshop.setEnrollmentLimit(dto.enrollmentLimit());
        workshop.setActive(dto.active());
        return workshopRepository.save(workshop);
    }

    @Transactional
    public WorkshopSession createSession(CreateWorkshopSessionDTO dto) {
        Workshop workshop = workshopRepository.findById(dto.workshopId())
                .orElseThrow(() -> new IllegalArgumentException("Workshop not found with id: " + dto.workshopId()));

        Employee educator = employeeRepository.findById(dto.responsibleEducatorId())
                .orElseThrow(() -> new IllegalArgumentException("Educator not found with id: " + dto.responsibleEducatorId()));

        WorkshopSession session = new WorkshopSession();
        session.setWorkshop(workshop);
        session.setDescription(dto.description());
        session.setAttendanceListLink(dto.attendanceListLink());
        session.setResponsibleEducator(educator);

        List<WorkshopPresence> participants = new ArrayList<>();
        if (dto.studentIds() != null) {
            for (Long studentId : dto.studentIds()) {
                // Busca matrícula existente ou cria nova
                WorkshopEnrollment enrollment = enrollmentRepository.findByStudentIdAndWorkshopId(studentId, dto.workshopId())
                        .orElseGet(() -> {
                            Student student = studentRepository.findById(studentId)
                                    .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + studentId));
                            
                            WorkshopEnrollment newEnrollment = new WorkshopEnrollment();
                            newEnrollment.setStudent(student);
                            newEnrollment.setWorkshop(workshop);
                            return enrollmentRepository.save(newEnrollment);
                        });
                
                WorkshopPresence presence = new WorkshopPresence();
                presence.setWorkshopEnrollment(enrollment); // Associa a matrícula, não o estudante direto
                presence.setWorkshopSession(session);
                participants.add(presence);
            }
        }
        session.setParticipants(participants);

        return sessionRepository.save(session);
    }

    @Transactional
    public Workshop update(Long id, Workshop workshop) {
        return workshopRepository.findById(id)
                .map(existing -> {
                    existing.setName(workshop.getName());
                    existing.setEnrollmentLimit(workshop.getEnrollmentLimit());
                    existing.setActive(workshop.getActive());
                    return workshopRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Workshop not found with id " + id));
    }

    @Transactional
    public void delete(Long id) {
        workshopRepository.deleteById(id);
    }
}
