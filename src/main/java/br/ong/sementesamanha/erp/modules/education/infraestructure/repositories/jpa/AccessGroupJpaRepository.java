package br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa;

import br.ong.sementesamanha.erp.modules.education.infraestructure.models.sistema.AccessGroupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessGroupJpaRepository extends JpaRepository<AccessGroupModel, Long> {
    Optional<AccessGroupModel> findByName(String name);
}
