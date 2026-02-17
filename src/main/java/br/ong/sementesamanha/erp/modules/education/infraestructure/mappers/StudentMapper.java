package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.person.PersonContactDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.student.CreateStudentDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.student.StudentDetailsResponseDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.student.StudentPreviewResponseDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.student.UpdateStudentDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.HomeCondition;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Student;
import br.ong.sementesamanha.erp.modules.education.domain.entities.StudentHealth;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

@Component
public class StudentMapper {

    private final StudentGuardianMapper studentGuardianMapper;
    private final IndividualPersonMapper individualPersonMapper;
    private final PersonContactMapper personContactMapper;
    private final StudentNoteMapper studentNoteMapper;
    private final StudentHealthMapper studentHealthMapper;
    private final HomeConditionMapper homeConditionMapper;
    private final SocialInteractionMapper socialInteractionMapper;
    private final LegalGuardianMapper legalGuardianMapper;

    public StudentMapper(StudentGuardianMapper studentGuardianMapper,
                         IndividualPersonMapper individualPersonMapper,
                         PersonContactMapper personContactMapper,
                         StudentNoteMapper studentNoteMapper,
                         StudentHealthMapper studentHealthMapper,
                         HomeConditionMapper homeConditionMapper,
                         SocialInteractionMapper socialInteractionMapper,
                         LegalGuardianMapper legalGuardianMapper) {
        this.studentGuardianMapper = studentGuardianMapper;
        this.individualPersonMapper = individualPersonMapper;
        this.personContactMapper = personContactMapper;
        this.studentNoteMapper = studentNoteMapper;
        this.studentHealthMapper = studentHealthMapper;
        this.homeConditionMapper = homeConditionMapper;
        this.socialInteractionMapper = socialInteractionMapper;
        this.legalGuardianMapper = legalGuardianMapper;
    }

    public StudentPreviewResponseDTO toPreview(Student model) {
        if (model == null) return null;

        String studentName = (model.getPerson() != null) ? model.getPerson().getPersonName() : "N/A";
        String guardianName = model.getGuardians().iterator().next().getGuardian().getPerson().getPersonName();
        String guardianPhone = model.getGuardians().iterator().next().getGuardian().getPerson().getBasePerson().getContact().getMobilePhone();
        Long gradeId = model.getPerson().getEducation().getEducationLevelId();

        Integer age = null;
        if (model.getPerson() != null && model.getPerson().getBirthDate() != null) {
            LocalDate birthDate = convertToLocalDate(model.getPerson().getBirthDate());
            age = Period.between(birthDate, LocalDate.now()).getYears();
        }

        return new StudentPreviewResponseDTO(
                model.getId(),
                studentName,
                guardianName,
                guardianPhone,
                model.isActive() ? "active" : "inactive",
                0.0f,
                gradeId,
                age
        );
    }

    public StudentDetailsResponseDTO toDetails(Student model) {
        if (model == null) return null;

        PersonContactDTO emergencyContact = personContactMapper.toDTO(
            model.getGuardians().iterator().next().getGuardian().getPerson().getBasePerson().getContact()
        );

        return new StudentDetailsResponseDTO(
                model.getId(),
                individualPersonMapper.toDTO(model.getPerson()),
                model.getRegistrationDate(),
                model.isActive() ? "active" : "inactive",
                0.0,
                model.getPeriodId(),
                model.getRegistrationOriginId(),
                model.isHasTransportAutonomy(),
                model.getTransportResponsibleName(),
                (model.getGuardians() != null && !model.getGuardians().isEmpty()) ? emergencyContact : null,
                model.getOccurrences() != null ?
                        model.getOccurrences().stream().map(studentNoteMapper::toDTO).toList() :
                        Collections.emptyList(),
                studentHealthMapper.toDTO(model.getHealthRecord()),
                homeConditionMapper.toDTO(model.getHomeCondition()),
                model.getSocialInteractions() != null ?
                        model.getSocialInteractions().stream().map(socialInteractionMapper::toDTO).toList() :
                        Collections.emptyList(),
                model.getGuardians() != null ? model.getGuardians().stream().map(studentGuardianMapper::toDTO).toList() :
                        Collections.emptyList(),
                new ArrayList<>()
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
        student.setHealthRecord(studentHealthMapper.toEntity(dto.healthData(), student));
        student.setHomeCondition(homeConditionMapper.toEntity(dto.dwellingCondition(), student));
        student.setRegistrationDate(dto.registrationDate());
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

    public void updateDomainFromDTO(Student domain, UpdateStudentDTO dto) {
        if (dto == null || domain == null || dto.studentData() == null)
            return;

        if (dto.studentData().periodId() != null) {
            domain.setPeriodId(dto.studentData().periodId());
        }

        if (dto.studentData().hasTransportAutonomy() != null) {
            domain.setHasTransportAutonomy(dto.studentData().hasTransportAutonomy());
        }

        if (dto.studentData().registrationOriginId() != null) {
            domain.setRegistrationOriginId(dto.studentData().registrationOriginId());
        }

        if (dto.studentData().transportResponsibleName() != null) {
            domain.setTransportResponsibleName(dto.studentData().transportResponsibleName());
        }

        if (dto.dwellingCondition() != null) {
            if (domain.getHomeCondition() == null) {
                HomeCondition newHome = new HomeCondition();
                newHome.setStudent(domain);
                domain.setHomeCondition(newHome);
            }
            homeConditionMapper.updateEntityFromDto(domain.getHomeCondition(), dto.dwellingCondition());
        }

        if (dto.healthData() != null) {
            if (domain.getHealthRecord() == null) {
                StudentHealth newHealth = new StudentHealth();
                newHealth.setStudent(domain);
                domain.setHealthRecord(newHealth);
            }
            studentHealthMapper.updateEntityFromDto(domain.getHealthRecord(), dto.healthData());
        }
    }
}
