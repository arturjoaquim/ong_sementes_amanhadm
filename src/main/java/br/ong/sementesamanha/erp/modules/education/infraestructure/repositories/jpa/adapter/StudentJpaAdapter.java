package br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa.adapter;

import br.ong.sementesamanha.erp.modules.education.domain.entities.Student;
import br.ong.sementesamanha.erp.modules.education.domain.projections.StudentPreview;
import br.ong.sementesamanha.erp.modules.education.domain.filters.StudentFilter;
import br.ong.sementesamanha.erp.modules.education.domain.ports.StudentRepositoryPort;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.StudentMapper;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.StudentModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa.StudentJpaRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.specifications.StudentSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StudentJpaAdapter implements StudentRepositoryPort {

    private final StudentJpaRepository studentJpaRepository;
    private final StudentMapper studentMapper;

    public StudentJpaAdapter(StudentJpaRepository studentJpaRepository, StudentMapper studentMapper) {
        this.studentJpaRepository = studentJpaRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public List<Student> findAll(StudentFilter filter) {
        List<StudentModel> models = studentJpaRepository.findAll(StudentSpecification.withFilter(filter));
        return models.stream()
                .map(studentMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentPreview> findAllPreviews(StudentFilter filter) {
        // Combina a especificação de filtro com a especificação de fetch
        Specification<StudentModel> spec = StudentSpecification.withFilter(filter)
                .and(StudentSpecification.fetchForPreview());

        List<StudentModel> models = studentJpaRepository.findAll(spec);

        return models.stream()
                .map(studentMapper::toPreview)
                .collect(Collectors.toList());
    }

    @Override
    public Student save(Student student) {
        StudentModel model = studentMapper.toModel(student);
        StudentModel savedModel = studentJpaRepository.save(model);
        return studentMapper.toDomain(savedModel);
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentJpaRepository.findById(id)
                .map(studentMapper::toDomain);
    }
}
