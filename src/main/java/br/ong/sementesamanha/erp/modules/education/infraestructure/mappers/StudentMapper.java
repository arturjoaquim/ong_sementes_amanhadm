package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.CreateStudentDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.StudentPreviewDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.UpdateStudentDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Student;
import br.ong.sementesamanha.erp.modules.education.domain.projections.StudentPreview;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.LegalGuardianModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.StudentModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas.IndividualPersonModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

@Component
public class StudentMapper {

    private final IndividualPersonMapper individualPersonMapper;

    public StudentMapper(IndividualPersonMapper individualPersonMapper) {
        this.individualPersonMapper = individualPersonMapper;
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
        student.setNotes(new ArrayList<>()); 
        
        return student;
    }

    public StudentModel toModel(Student domain) {
        if (domain == null) return null;
        
        StudentModel model = new StudentModel();
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
        
        return model;
    }

    public StudentPreview toPreview(StudentModel model) {
        if (model == null) return null;

        String studentName = (model.getPerson() != null) ? model.getPerson().getPersonName() : "N/A";
        LegalGuardianModel firstGuardian = model.getGuardians().stream().findFirst().orElse(null);

        Integer age = null;
        if (model.getPerson() != null && model.getPerson().getBirthDate() != null) {
            LocalDate birthDate = convertToLocalDate(model.getPerson().getBirthDate());
            age = Period.between(birthDate, LocalDate.now()).getYears();
        }

        return new StudentPreview(
                model.getId(),
                studentName,
                firstGuardian.getPerson().getPersonName(),
                firstGuardian.getPerson().getContact().getMobilePhone(),
                model.isActive(),
                model.getPerson().getEducation().getEducationLevelId().toString(),
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

    public Student toDomain(CreateStudentDTO dto) {
        if (dto == null) return null;
        Student student = new Student();
        
        if (dto.studentData() != null) {
            student.setRegistrationOriginId(dto.studentData().registrationOriginId());
            student.setPeriodId(dto.studentData().periodId());
            student.setHasTransportAutonomy(dto.studentData().hasTransportAutonomy());
            student.setTransportResponsibleName(dto.studentData().transportResponsibleName());
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
