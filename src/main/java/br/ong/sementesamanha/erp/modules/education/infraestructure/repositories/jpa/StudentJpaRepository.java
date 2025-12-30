package br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa;

import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentJpaRepository extends JpaRepository<StudentModel, Long>, JpaSpecificationExecutor<StudentModel> {
}
