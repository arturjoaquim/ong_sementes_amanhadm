package br.ong.sementesamanha.erp.modules.education.application.services;

import br.ong.sementesamanha.erp.modules.education.application.dtos.CreateWorkshopDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Workshop;
import br.ong.sementesamanha.erp.modules.education.domain.entities.WorkshopParticipant;
import br.ong.sementesamanha.erp.modules.education.domain.ports.repository.EmployeeRepository;
import br.ong.sementesamanha.erp.modules.education.domain.ports.repository.StudentRepository;
import br.ong.sementesamanha.erp.modules.education.domain.ports.repository.WorkshopRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.enums.LookupTypeEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class WorkshopService {

    private final WorkshopRepository workshopRepository;
    private final EmployeeRepository employeeRepository;
    private final StudentRepository studentRepository;
    private final LookupService lookupService;

    public WorkshopService(WorkshopRepository workshopRepository, 
                           EmployeeRepository employeeRepository,
                           StudentRepository studentRepository,
                           LookupService lookupService) {
        this.workshopRepository = workshopRepository;
        this.employeeRepository = employeeRepository;
        this.studentRepository = studentRepository;
        this.lookupService = lookupService;
    }

    @Transactional
    public Workshop create(CreateWorkshopDTO dto) {
        // Validações
        employeeRepository.findById(dto.educatorId())
                .orElseThrow(() -> new IllegalArgumentException("Educator not found with id: " + dto.educatorId()));

        if (lookupService.getLookupAsMap(LookupTypeEnum.WORKSHOP_TYPE).get(dto.workshopTypeId()) == null) {
            throw new IllegalArgumentException("Workshop Type not found with id: " + dto.workshopTypeId());
        }

        // Cria a oficina
        Workshop workshop = new Workshop();
        workshop.setWorkshopTypeId(dto.workshopTypeId());
        workshop.setDescription(dto.description());
        workshop.setAttendanceListUrl(dto.attendanceListUrl());
        workshop.setEducatorId(dto.educatorId());

        // Adiciona os participantes
        Set<WorkshopParticipant> participants = new HashSet<>();
        if (dto.studentIds() != null) {
            for (Long studentId : dto.studentIds()) {
                studentRepository.findById(studentId)
                        .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + studentId));
                
                WorkshopParticipant participant = new WorkshopParticipant();
                participant.setStudentId(studentId);
                participants.add(participant);
            }
        }
        workshop.setParticipants(participants);

        return workshopRepository.save(workshop);
    }
}
