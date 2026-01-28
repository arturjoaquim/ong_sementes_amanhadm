package br.ong.sementesamanha.erp.modules.education.domain.ports.repository;

import br.ong.sementesamanha.erp.modules.education.domain.entities.Student;
import br.ong.sementesamanha.erp.modules.education.application.dtos.StudentDetailsViewDTO;
import br.ong.sementesamanha.erp.modules.education.domain.projections.StudentPreview;
import br.ong.sementesamanha.erp.modules.education.domain.filters.StudentFilter;
import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    List<Student> findAll(StudentFilter filter);
    List<StudentPreview> findAllPreviews(StudentFilter filter);
    Student save(Student student);
    Optional<Student> findById(Long id);
    Optional<StudentDetailsViewDTO> findDetailsById(Long id);
}
