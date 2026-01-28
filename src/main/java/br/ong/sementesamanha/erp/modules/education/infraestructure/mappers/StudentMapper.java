package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.CreateStudentDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.StudentPreviewDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.UpdateStudentDTO;
import br.ong.sementesamanha.erp.modules.education.application.services.LookupService;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Student;
import br.ong.sementesamanha.erp.modules.education.domain.entities.StudentGuardian;
import br.ong.sementesamanha.erp.modules.education.application.dtos.StudentDetailsViewDTO;
import br.ong.sementesamanha.erp.modules.education.domain.projections.StudentPreview;
import br.ong.sementesamanha.erp.modules.education.infraestructure.enums.LookupTypeEnum;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.StudentModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas.IndividualPersonModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class StudentMapper {

    private final IndividualPersonMapper individualPersonMapper;
    private final StudentGuardianMapper studentGuardianMapper;
    private final StudentNoteMapper studentNoteMapper;
    private final StudentHealthMapper studentHealthMapper;
    private final HomeConditionMapper homeConditionMapper;
    private final SocialInteractionMapper socialInteractionMapper;
    private final WorkshopParticipantMapper workshopParticipantMapper;
    private final LookupService lookupService;

    public StudentMapper(IndividualPersonMapper individualPersonMapper, 
                         StudentGuardianMapper studentGuardianMapper,
                         StudentNoteMapper studentNoteMapper,
                         StudentHealthMapper studentHealthMapper,
                         HomeConditionMapper homeConditionMapper,
                         SocialInteractionMapper socialInteractionMapper,
                         WorkshopParticipantMapper workshopParticipantMapper,
                         LookupService lookupService) {
        this.individualPersonMapper = individualPersonMapper;
        this.studentGuardianMapper = studentGuardianMapper;
        this.studentNoteMapper = studentNoteMapper;
        this.studentHealthMapper = studentHealthMapper;
        this.homeConditionMapper = homeConditionMapper;
        this.socialInteractionMapper = socialInteractionMapper;
        this.workshopParticipantMapper = workshopParticipantMapper;
        this.lookupService = lookupService;
    }

    public Student toDomain(StudentModel model) {
        if (model == null) return null;
        
        Student student = new Student();
        student.setId(model.getId());
        if (model.getPerson() != null) {
            student.setPersonId(model.getPerson().getId());
        }
        student.setRegistrationOriginId(model.getRegistrationOriginId());
        student.setPeriodId(model.getPeriodId());
        student.setHasTransportAutonomy(model.isHasTransportAutonomy());
        student.setTransportResponsibleName(model.getTransportResponsibleName());
        student.setRegistrationDate(model.getRegistrationDate());
        student.setActive(model.isActive());

        if (model.getGuardians() != null) {
            student.setGuardians(model.getGuardians().stream()
                    .map(studentGuardianMapper::toDomain)
                    .collect(Collectors.toList()));
        } else {
            student.setGuardians(new ArrayList<>());
        }

        if (model.getOccurrences() != null) {
            student.setNotes(model.getOccurrences().stream()
                    .map(studentNoteMapper::toDomain)
                    .collect(Collectors.toList()));
        } else {
            student.setNotes(new ArrayList<>());
        }
        
        return student;
    }

    public StudentModel toModel(Student domain) {
        if (domain == null) return null;
        
        StudentModel model = new StudentModel();
        updateModelFromDomain(model, domain);
        
        return model;
    }

    public void updateModelFromDomain(StudentModel model, Student domain) {
        if (domain == null || model == null) return;

        model.setId(domain.getId());

        if (domain.getPersonId() != null) {
            IndividualPersonModel person = new IndividualPersonModel();
            person.setId(domain.getPersonId());
            model.setPerson(person);
        }

        model.setRegistrationOriginId(domain.getRegistrationOriginId());
        model.setPeriodId(domain.getPeriodId());
        model.setHasTransportAutonomy(domain.isHasTransportAutonomy());
        model.setTransportResponsibleName(domain.getTransportResponsibleName());
        model.setRegistrationDate(domain.getRegistrationDate());
        model.setActive(domain.isActive());

        if (domain.getGuardians() != null) {
            if (model.getGuardians() == null) {
                model.setGuardians(domain.getGuardians().stream()
                        .map(sg -> studentGuardianMapper.toModel(sg, model))
                        .collect(Collectors.toSet()));
            }
        }

        if (domain.getNotes() != null) {
            if (model.getOccurrences() == null) {
                model.setOccurrences(domain.getNotes().stream()
                        .map(note -> studentNoteMapper.toModel(note, model))
                        .collect(Collectors.toSet()));
            }
        }
    }

    public StudentPreview toPreview(StudentModel model) {
        if (model == null) return null;

        String studentName = (model.getPerson() != null) ? model.getPerson().getPersonName() : "N/A";
        String guardianName = model.getGuardians().iterator().next().getGuardian().getPerson().getPersonName();
        String guardianPhone = model.getGuardians().iterator().next().getGuardian().getPerson().getContact().getMobilePhone();
        Long gradeId = model.getPerson().getEducation().getEducationLevelId();

        Integer age = null;
        if (model.getPerson() != null && model.getPerson().getBirthDate() != null) {
            LocalDate birthDate = convertToLocalDate(model.getPerson().getBirthDate());
            age = Period.between(birthDate, LocalDate.now()).getYears();
        }

        return new StudentPreview(
                model.getId(),
                studentName,
                guardianName,
                guardianPhone,
                model.isActive(),
                lookupService.getLookupAsMap(LookupTypeEnum.EDUCATION_LEVEL).get(gradeId).getName(),
                age
        );
    }

    public StudentPreviewDTO toDTO(StudentPreview preview) {
        if (preview == null) return null;

        return new StudentPreviewDTO(
                preview.id(),
                preview.studentName(),
                preview.guardianName(),
                preview.guardianPhone(),
                preview.active() ? "Ativo" : "Inativo",
                0.0f,
                preview.grade(),
                preview.age()
        );
    }

    public StudentDetailsViewDTO toDetails(StudentModel model) {
        if (model == null) return null;

        return new StudentDetailsViewDTO(
            model.getId(),
            individualPersonMapper.toDomain(model.getPerson()),
            model.getRegistrationDate(),
            model.isActive() ? "active" : "inactive",
            0.0,
            model.getPeriodId(),
            String.valueOf(model.getRegistrationOriginId()),
            model.isHasTransportAutonomy(),
            model.getTransportResponsibleName(),
            (model.getGuardians() != null && !model.getGuardians().isEmpty()) ? 
                individualPersonMapper.toDomain(model.getGuardians().iterator().next().getGuardian().getPerson()).getContact() : null,
            model.getOccurrences() != null ? model.getOccurrences().stream().map(studentNoteMapper::toDomain).collect(Collectors.toList()) : Collections.emptyList(),
            model.getHealthRecord() != null ? studentHealthMapper.toDomain(model.getHealthRecord()) : null,
            model.getHomeCondition() != null ? homeConditionMapper.toDomain(model.getHomeCondition()) : null,
            model.getSocialInteractions() != null ? model.getSocialInteractions().stream().map(socialInteractionMapper::toDomain).collect(Collectors.toList()) : Collections.emptyList(),
            model.getGuardians() != null ? model.getGuardians().stream().map(studentGuardianMapper::toDomain).collect(Collectors.toList()) : Collections.emptyList(),
            model.getWorkshopParticipations() != null ? model.getWorkshopParticipations().stream().map(workshopParticipantMapper::toDomain).collect(Collectors.toList()) : Collections.emptyList()
        );
    }

    public Student toDomain(CreateStudentDTO dto) {
        if (dto == null) return null;
        Student student = new Student();
        
        if (dto.studentData() != null) {
            student.setRegistrationOriginId(dto.studentData().registrationOriginId());
            student.setPeriodId(dto.studentData().periodId());
            student.setHasTransportAutonomy(dto.studentData().hasTransportAutonomy());
            student.setTransportResponsibleName(dto.studentData().transportResponsibleName());
        }
        
        if (dto.guardians() != null) {
            student.setGuardians(dto.guardians().stream().map(g -> {
                StudentGuardian sg = new StudentGuardian();
                sg.setGuardianId(g.guardianId());
                sg.setKinshipId(g.kinshipId());
                return sg;
            }).collect(Collectors.toList()));
        }

        student.setRegistrationDate(dto.registrationDate());
        return student;
    }

    public Student toDomain(UpdateStudentDTO dto) {
        if (dto == null) return null;
        Student student = new Student();

        if (dto.studentData() != null) {
            student.setRegistrationOriginId(dto.studentData().registrationOriginId());
            student.setPeriodId(dto.studentData().periodId());
            student.setHasTransportAutonomy(dto.studentData().hasTransportAutonomy());
            student.setTransportResponsibleName(dto.studentData().transportResponsibleName());
        }

        if (dto.active() != null) {
            student.setActive(dto.active());
        }
        return student;
    }

    private LocalDate convertToLocalDate(Date dateToConvert) {
        if (dateToConvert instanceof java.sql.Date) {
            return ((java.sql.Date) dateToConvert).toLocalDate();
        }
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
