package br.ong.sementesamanha.erp.modules.education.infraestructure.repositories;

import br.ong.sementesamanha.erp.modules.education.domain.entities.PersonContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonContactRepository extends JpaRepository<PersonContact, Long> {
}
