package br.ong.sementesamanha.erp.modules.education.application.services;

import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPerson;
import br.ong.sementesamanha.erp.modules.education.domain.entities.LegalGuardian;
import br.ong.sementesamanha.erp.modules.education.domain.ports.repository.LegalGuardianRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LegalGuardianService {

    private final LegalGuardianRepository repository;
    private final IndividualPersonService personService;

    public LegalGuardianService(LegalGuardianRepository repository, IndividualPersonService personService) {
        this.repository = repository;
        this.personService = personService;
    }

    @Transactional
    public LegalGuardian create(IndividualPerson person) {
        IndividualPerson savedPerson = personService.create(person);
        
        LegalGuardian guardian = new LegalGuardian();
        guardian.setPersonId(savedPerson.getId());
        guardian.setPerson(savedPerson);
        
        return repository.save(guardian);
    }
    
    public LegalGuardian findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guardian not found"));
    }
}
