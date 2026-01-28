package br.ong.sementesamanha.erp.modules.education.application.services;

import br.ong.sementesamanha.erp.modules.education.application.dtos.IndividualPersonDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPerson;
import br.ong.sementesamanha.erp.modules.education.domain.ports.repository.IndividualPersonRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.IndividualPersonMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IndividualPersonService {

    private final IndividualPersonRepository repository;
    private final IndividualPersonMapper mapper;

    public IndividualPersonService(IndividualPersonRepository repository, IndividualPersonMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public IndividualPerson create(IndividualPerson person) {
        return repository.save(person);
    }

    @Transactional
    public IndividualPerson update(Long id, IndividualPersonDTO dto) {
        IndividualPerson existingPerson = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Person not found with id: " + id));

        // Preservar IDs dos filhos antes de atualizar
        Long addressId = existingPerson.getAddress() != null ? existingPerson.getAddress().getId() : null;
        Long contactId = existingPerson.getContact() != null ? existingPerson.getContact().getId() : null;
        Long educationId = existingPerson.getEducation() != null ? existingPerson.getEducation().getId() : null;

        mapper.updateDomainFromDto(existingPerson, dto);

        // Restaurar IDs nos novos objetos criados pelo mapper
        if (existingPerson.getAddress() != null && addressId != null) {
            existingPerson.getAddress().setId(addressId);
        }
        if (existingPerson.getContact() != null && contactId != null) {
            existingPerson.getContact().setId(contactId);
        }
        if (existingPerson.getEducation() != null && educationId != null) {
            existingPerson.getEducation().setId(educationId);
        }

        return repository.save(existingPerson);
    }
}
