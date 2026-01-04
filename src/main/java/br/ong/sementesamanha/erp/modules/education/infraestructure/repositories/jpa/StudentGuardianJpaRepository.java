package br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa;

import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.StudentGuardianId;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.StudentGuardianModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentGuardianJpaRepository extends JpaRepository<StudentGuardianModel, StudentGuardianId> {
}
