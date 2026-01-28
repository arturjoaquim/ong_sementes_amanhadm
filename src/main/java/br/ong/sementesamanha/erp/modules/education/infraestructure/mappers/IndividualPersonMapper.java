package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.IndividualPersonDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPerson;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas.IndividualPersonModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas.PersonModel;
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

    public IndividualPerson toDomain(IndividualPersonModel model) {
        if (model == null) return null;

        IndividualPerson domain = new IndividualPerson();
        domain.setId(model.getId());
        domain.setPersonName(model.getPersonName());
        domain.setNaturalnessId(model.getNaturalnessId());
        domain.setRaceId(model.getRaceId());
        domain.setBirthDate(model.getBirthDate());
        domain.setSexId(model.getSexId());
        domain.setMotherName(model.getMotherName());
        domain.setFatherName(model.getFatherName());

        domain.setAddress(addressMapper.toDomain(model.getAddress()));
        domain.setContact(contactMapper.toDomain(model.getContact()));
        domain.setEducation(educationMapper.toDomain(model.getEducation()));
        
        if (model.getDocuments() != null) {
            domain.setDocuments(model.getDocuments().stream()
                    .map(documentMapper::toDomain)
                    .collect(Collectors.toList()));
        }

        return domain;
    }

    public IndividualPersonModel toModel(IndividualPerson domain) {
        if (domain == null) return null;

        IndividualPersonModel model = new IndividualPersonModel();
        updateModelFromDomain(model, domain);

        // Para criação, precisamos garantir que o PersonModel base seja criado
        if (model.getPerson() == null) {
            PersonModel personModel = new PersonModel();
            personModel.setPersonType(1);
            if (domain.getId() != null) {
                personModel.setId(domain.getId());
            }
            model.setPerson(personModel);
        }

        return model;
    }

    public void updateModelFromDomain(IndividualPersonModel model, IndividualPerson domain) {
        if (domain == null || model == null) return;

        model.setId(domain.getId());
        model.setPersonName(domain.getPersonName());
        model.setNaturalnessId(domain.getNaturalnessId());
        model.setRaceId(domain.getRaceId());
        model.setBirthDate(domain.getBirthDate());
        model.setSexId(domain.getSexId());
        model.setMotherName(domain.getMotherName());
        model.setFatherName(domain.getFatherName());

        if (domain.getAddress() != null) {
            if (model.getAddress() == null) {
                model.setAddress(addressMapper.toModel(domain.getAddress()));
                model.getAddress().setPerson(model);
            } else {
                addressMapper.updateModelFromDomain(model.getAddress(), domain.getAddress());
            }
        }

        if (domain.getContact() != null) {
            if (model.getContact() == null) {
                model.setContact(contactMapper.toModel(domain.getContact()));
                model.getContact().setPerson(model);
            } else {
                contactMapper.updateModelFromDomain(model.getContact(), domain.getContact());
            }
        }

        if (domain.getEducation() != null) {
            if (model.getEducation() == null) {
                model.setEducation(educationMapper.toModel(domain.getEducation()));
                model.getEducation().setPerson(model);
            } else {
                educationMapper.updateModelFromDomain(model.getEducation(), domain.getEducation());
            }
        }

        if (model.getDocuments() == null) {
            model.setDocuments(domain.getDocuments().stream()
                    .map(doc -> {
                        var docModel = documentMapper.toModel(doc);
                        docModel.setPerson(model);
                        return docModel;
                    })
                    .collect(Collectors.toList()));
        }
    }

    public IndividualPerson toDomain(IndividualPersonDTO dto) {
        if (dto == null) return null;
        IndividualPerson person = new IndividualPerson();
        person.setPersonName(dto.personName());
        person.setBirthDate(dto.birthDate());
        person.setMotherName(dto.motherName());
        person.setFatherName(dto.fatherName());
        person.setNaturalnessId(dto.naturalnessId());
        person.setRaceId(dto.raceId());
        person.setSexId(dto.sexId());

        if (dto.address() != null) {
            person.setAddress(addressMapper.toDomain(dto.address()));
        }
        if (dto.contact() != null) {
            person.setContact(contactMapper.toDomain(dto.contact()));
        }
        if (dto.education() != null) {
            person.setEducation(educationMapper.toDomain(dto.education()));
        }
        if (dto.documents() != null) {
            person.setDocuments(dto.documents().stream()
                    .map(documentMapper::toDomain)
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
            if (person.getAddress() == null) {
                person.setAddress(addressMapper.toDomain(dto.address()));
            } else {
                addressMapper.updateDomainFromDto(person.getAddress(), dto.address());
            }
        }
        
        if (dto.contact() != null) {
            if (person.getContact() == null) {
                person.setContact(contactMapper.toDomain(dto.contact()));
            } else {
                contactMapper.updateDomainFromDto(person.getContact(), dto.contact());
            }
        }
        
        if (dto.education() != null) {
            if (person.getEducation() == null) {
                person.setEducation(educationMapper.toDomain(dto.education()));
            } else {
                educationMapper.updateDomainFromDto(person.getEducation(), dto.education());
            }
        }
    }
}
