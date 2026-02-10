package br.ong.sementesamanha.erp.modules.education.application.services;

import br.ong.sementesamanha.erp.modules.education.application.dtos.workshop.CreateWorkshopDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Employee;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Student;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Workshop;
import br.ong.sementesamanha.erp.modules.education.domain.entities.WorkshopParticipant;
import br.ong.sementesamanha.erp.modules.education.infraestructure.enums.LookupTypeEnum;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.EmployeeRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.StudentRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.WorkshopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
        Employee educator = employeeRepository.findById(dto.educatorId())
                .orElseThrow(() -> new IllegalArgumentException("Educator not found with id: " + dto.educatorId()));

        if (lookupService.getLookupAsMap(LookupTypeEnum.WORKSHOP_TYPE).get(dto.workshopTypeId()) == null) {
            throw new IllegalArgumentException("Workshop Type not found with id: " + dto.workshopTypeId());
        }

        Workshop workshop = new Workshop();
        workshop.setWorkshopTypeId(dto.workshopTypeId());
        workshop.setDescription(dto.description());
        workshop.setAttendanceListLink(dto.attendanceListUrl());
        workshop.setResponsibleEducator(educator);

        List<WorkshopParticipant> participants = new ArrayList<>();
        if (dto.studentIds() != null) {
            for (Long studentId : dto.studentIds()) {
                Student student = studentRepository.findById(studentId)
                        .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + studentId));
                
                WorkshopParticipant participant = new WorkshopParticipant();
                participant.setStudent(student);
                participant.setWorkshop(workshop);
                participants.add(participant);
            }
        }
        workshop.setParticipants(participants);

        return workshopRepository.save(workshop);
    }
}
