package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.person.IndividualPersonDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPerson;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Person;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class IndividualPersonMapper {

    private final PersonAddressMapper addressMapper;
    private final PersonContactMapper contactMapper;
    private final IndividualPersonEducationMapper educationMapper;
    private final PersonDocumentMapper documentMapper;

    public IndividualPersonMapper(PersonAddressMapper addressMapper,
                                  PersonContactMapper contactMapper,
                                  IndividualPersonEducationMapper educationMapper,
                                  PersonDocumentMapper documentMapper) {
        this.addressMapper = addressMapper;
        this.contactMapper = contactMapper;
        this.educationMapper = educationMapper;
        this.documentMapper = documentMapper;
    }

    public IndividualPerson toDomain(IndividualPersonDTO dto) {
        if (dto == null) return null;
        IndividualPerson person = new IndividualPerson();
        
        if (dto.id() != null) {
            person.setId(dto.id());
        }

        Person basePerson = new Person();
        basePerson.setId(dto.id()); // ID da base é o mesmo
        basePerson.setPersonType(1);
        person.setBasePerson(basePerson);
        
        person.setPersonName(dto.personName());
        person.setBirthDate(dto.birthDate());
        person.setMotherName(dto.motherName());
        person.setFatherName(dto.fatherName());
        person.setNaturalnessId(dto.naturalnessId());
        person.setRaceId(dto.raceId());
        person.setSexId(dto.sexId());

        if (dto.address() != null) {
            basePerson.setAddress(addressMapper.toDomain(dto.address(), basePerson));
        }
        if (dto.contact() != null) {
            basePerson.setContact(contactMapper.toDomain(dto.contact(), basePerson));
        }
        if (dto.education() != null) {
            person.setEducation(educationMapper.toDomain(dto.education(), person));
        }
        if (dto.documents() != null) {
            basePerson.setDocuments(dto.documents().stream()
                    .map(doc -> documentMapper.toDomain(doc, basePerson))
                    .collect(Collectors.toList()));
        }

        return person;
    }

    public void updateDomainFromDto(IndividualPerson person, IndividualPersonDTO dto) {
        if (dto == null) return;

        if (dto.personName() != null) person.setPersonName(dto.personName());
        if (dto.birthDate() != null) person.setBirthDate(dto.birthDate());
        if (dto.motherName() != null) person.setMotherName(dto.motherName());
        if (dto.fatherName() != null) person.setFatherName(dto.fatherName());
        if (dto.naturalnessId() != null) person.setNaturalnessId(dto.naturalnessId());
        if (dto.raceId() != null) person.setRaceId(dto.raceId());
        if (dto.sexId() != null) person.setSexId(dto.sexId());

        if (dto.address() != null) {
            if (person.getBasePerson().getAddress() == null) {
                person.getBasePerson().setAddress(addressMapper.toDomain(dto.address(), person.getBasePerson()));
            } else {
                addressMapper.updateDomainFromDto(person.getBasePerson().getAddress(), dto.address());
            }
        }
        
        if (dto.contact() != null) {
            if (person.getBasePerson().getContact() == null) {
                person.getBasePerson().setContact(contactMapper.toDomain(dto.contact(), person.getBasePerson()));
            } else {
                contactMapper.updateDomainFromDto(person.getBasePerson().getContact(), dto.contact());
            }
        }
        
        if (dto.education() != null) {
            if (person.getEducation() == null) {
                person.setEducation(educationMapper.toDomain(dto.education(), person));
            } else {
                educationMapper.updateDomainFromDto(person.getEducation(), dto.education());
            }
        }
    }

    public IndividualPersonDTO toDTO(IndividualPerson domain) {
        if (domain == null) return null;

        return new IndividualPersonDTO(
            domain.getId(),
            domain.getPersonName(),
            domain.getBirthDate(),
            domain.getMotherName(),
            domain.getFatherName(),
            domain.getNaturalnessId(),
            domain.getRaceId(),
            domain.getSexId(),
            addressMapper.toDTO(domain.getBasePerson().getAddress()),
            contactMapper.toDTO(domain.getBasePerson().getContact()),
            educationMapper.toDTO(domain.getEducation()),
            domain.getBasePerson().getDocuments() != null ? domain.getBasePerson().getDocuments().stream()
                    .map(documentMapper::toDTO)
                    .collect(Collectors.toList()) : null
        );
    }
}
