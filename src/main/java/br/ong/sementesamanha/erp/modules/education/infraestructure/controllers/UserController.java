package br.ong.sementesamanha.erp.modules.education.infraestructure.controllers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.CreateUserDTO;
import br.ong.sementesamanha.erp.modules.education.application.services.UserService;
import br.ong.sementesamanha.erp.modules.education.domain.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> create(@RequestBody CreateUserDTO dto) {
        User createdUser = userService.create(dto);
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).build();
    }
}
