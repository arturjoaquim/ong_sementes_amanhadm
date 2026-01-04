package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.CreatePersonDTO;
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
        
        PersonModel personModel = new PersonModel();
        personModel.setPersonType(1);
        model.setPerson(personModel);

        model.setPersonName(domain.getPersonName());
        model.setNaturalnessId(domain.getNaturalnessId());
        model.setRaceId(domain.getRaceId());
        model.setBirthDate(domain.getBirthDate());
        model.setSexId(domain.getSexId());
        model.setMotherName(domain.getMotherName());
        model.setFatherName(domain.getFatherName());

        model.setAddress(addressMapper.toModel(domain.getAddress()));
        if (model.getAddress() != null) {
            model.getAddress().setPerson(model);
        }

        model.setContact(contactMapper.toModel(domain.getContact()));
        if (model.getContact() != null) {
            model.getContact().setPerson(model);
        }

        model.setEducation(educationMapper.toModel(domain.getEducation()));
        if (model.getEducation() != null) {
            model.getEducation().setPerson(model);
        }

        if (domain.getDocuments() != null) {
            model.setDocuments(domain.getDocuments().stream()
                    .map(doc -> {
                        var docModel = documentMapper.toModel(doc);
                        docModel.setPerson(model);
                        return docModel;
                    })
                    .collect(Collectors.toList()));
        }

        return model;
    }

    public IndividualPerson toDomain(CreatePersonDTO dto) {
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
            person.setAddress(this.addressMapper.toDomain(dto.address()));
        }
        if (dto.contact() != null) {
            person.setContact(this.contactMapper.toDomain(dto.contact()));
        }
        if (dto.education() != null) {
            person.setEducation(this.educationMapper.toDomain(dto.education()));
        }
        if (dto.documents() != null) {
            person.setDocuments(dto.documents().stream()
                    .map(this.documentMapper::toDomain)
                    .collect(Collectors.toList()));
        }

        return person;
    }

}
