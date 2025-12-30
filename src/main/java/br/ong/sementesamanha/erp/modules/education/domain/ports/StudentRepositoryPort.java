package br.ong.sementesamanha.erp.modules.education.domain.ports;

import br.ong.sementesamanha.erp.modules.education.domain.entities.Student;
import br.ong.sementesamanha.erp.modules.education.domain.projections.StudentPreview;
import br.ong.sementesamanha.erp.modules.education.domain.filters.StudentFilter;
import java.util.List;
import java.util.Optional;

public interface StudentRepositoryPort {
    List<Student> findAll(StudentFilter filter);
    List<StudentPreview> findAllPreviews(StudentFilter filter);
    Student save(Student student);
    Optional<Student> findById(Long id);
}
