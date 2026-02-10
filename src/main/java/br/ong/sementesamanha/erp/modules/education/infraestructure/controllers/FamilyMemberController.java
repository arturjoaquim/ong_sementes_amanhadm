package br.ong.sementesamanha.erp.modules.education.infraestructure.controllers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.social.CreateFamilyMemberDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.social.UpdateFamilyMemberDTO;
import br.ong.sementesamanha.erp.modules.education.application.services.FamilyMemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/families/{familyId}/members")
public class FamilyMemberController {

    private final FamilyMemberService service;

    public FamilyMemberController(FamilyMemberService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> addMember(@PathVariable Long familyId, @RequestBody CreateFamilyMemberDTO dto) {
        service.addMember(familyId, dto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<Void> updateMember(@PathVariable Long familyId, @PathVariable Long memberId, @RequestBody UpdateFamilyMemberDTO dto) {
        service.updateMember(memberId, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> removeMember(@PathVariable Long familyId, @PathVariable Long memberId) {
        service.removeMember(memberId);
        return ResponseEntity.noContent().build();
    }
}
