package br.ong.sementesamanha.erp.modules.education.application.services;

import br.ong.sementesamanha.erp.modules.education.application.dtos.social.CreateFamilyMemberDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.social.UpdateFamilyMemberDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Family;
import br.ong.sementesamanha.erp.modules.education.domain.entities.FamilyMember;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.FamilyMemberMapper;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.FamilyMemberRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.FamilyRepository; // Assumindo que existe ou vou criar
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FamilyMemberService {

    private final FamilyMemberRepository memberRepository;
    private final FamilyRepository familyRepository; // Preciso criar se não existir
    private final FamilyMemberMapper mapper;

    public FamilyMemberService(FamilyMemberRepository memberRepository,
                               FamilyRepository familyRepository,
                               FamilyMemberMapper mapper) {
        this.memberRepository = memberRepository;
        this.familyRepository = familyRepository;
        this.mapper = mapper;
    }

    @Transactional
    public void addMember(Long familyId, CreateFamilyMemberDTO dto) {
        Family family = familyRepository.findById(familyId)
                .orElseThrow(() -> new IllegalArgumentException("Family not found with id: " + familyId));

        FamilyMember member = mapper.toEntity(dto);
        member.setFamily(family);
        memberRepository.save(member);
    }

    @Transactional
    public void updateMember(Long memberId, UpdateFamilyMemberDTO dto) {
        FamilyMember member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberId));

        if (dto.name() != null) member.setName(dto.name());
        if (dto.profession() != null) member.setProfession(dto.profession());
        if (dto.monthlyIncome() != null) member.setMonthlyIncome(dto.monthlyIncome());
        if (dto.kinshipId() != null) member.setKinshipId(dto.kinshipId());

        memberRepository.save(member);
    }

    @Transactional
    public void removeMember(Long memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new IllegalArgumentException("Member not found with id: " + memberId);
        }
        memberRepository.deleteById(memberId);
    }
}
