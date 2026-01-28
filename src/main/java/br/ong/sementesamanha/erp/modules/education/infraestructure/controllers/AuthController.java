package br.ong.sementesamanha.erp.modules.education.infraestructure.controllers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.LoginDTO;
import br.ong.sementesamanha.erp.modules.education.application.services.AuthService;
import br.ong.sementesamanha.erp.modules.education.domain.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginDTO dto, HttpServletRequest request, HttpServletResponse response) {
        User user = authService.login(dto, request, response);
        user.setPassword(null); // Remove a senha do retorno
        return ResponseEntity.ok(user);
    }
}
