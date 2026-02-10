package br.ong.sementesamanha.erp.modules.education.application.services;

import br.ong.sementesamanha.erp.modules.education.application.dtos.guardian.CreateLegalGuardianDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.guardian.LegalGuardianResponseDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.guardian.UpdateLegalGuardianDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPerson;
import br.ong.sementesamanha.erp.modules.education.domain.entities.LegalGuardian;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.IndividualPersonMapper;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.LegalGuardianMapper;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.LegalGuardianRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LegalGuardianService {

    private final LegalGuardianRepository repository;
    private final IndividualPersonService personService;
    private final IndividualPersonMapper personMapper;
    private final LegalGuardianMapper guardianMapper;

    public LegalGuardianService(LegalGuardianRepository repository, 
                                IndividualPersonService personService,
                                IndividualPersonMapper personMapper,
                                LegalGuardianMapper guardianMapper) {
        this.repository = repository;
        this.personService = personService;
        this.personMapper = personMapper;
        this.guardianMapper = guardianMapper;
    }

    @Transactional
    public LegalGuardianResponseDTO create(CreateLegalGuardianDTO dto) {
        IndividualPerson person = personMapper.toDomain(dto.person());
        IndividualPerson savedPerson = personService.create(person);
        
        LegalGuardian guardian = new LegalGuardian();
        guardian.setPerson(savedPerson);
        
        LegalGuardian savedGuardian = repository.save(guardian);
        return guardianMapper.toDTO(savedGuardian);
    }

    @Transactional
    public LegalGuardian update(Long id, UpdateLegalGuardianDTO dto) {
        LegalGuardian existingGuardian = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guardian not found with id: " + id));

        if (dto.person() != null && existingGuardian.getPerson() != null) {
            personService.update(existingGuardian.getPerson().getId(), dto.person());
        }

        return repository.save(existingGuardian);
    }
    
    public LegalGuardian findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guardian not found with id: " + id));
    }
}
