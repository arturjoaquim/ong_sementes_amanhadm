package br.ong.sementesamanha.erp.modules.education.infraestructure.repositories;

import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IndividualPersonRepository extends JpaRepository<IndividualPerson, Long> {
    
    @Query("SELECT p FROM IndividualPerson p WHERE p.id = :id")
    Optional<IndividualPerson> findByIdForceLoad(@Param("id") Long id);
}
