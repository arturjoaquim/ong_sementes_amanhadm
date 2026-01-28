package br.ong.sementesamanha.erp.modules.education.application.services;

import br.ong.sementesamanha.erp.modules.education.application.dtos.LoginDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.User;
import br.ong.sementesamanha.erp.modules.education.domain.ports.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final SecurityContextRepository securityContextRepository;

    public AuthService(AuthenticationManager authenticationManager, 
                       UserRepository userRepository,
                       SecurityContextRepository securityContextRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.securityContextRepository = securityContextRepository;
    }

    public User login(LoginDTO dto, HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.login(), dto.password())
        );

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        
        // Salva o contexto na sessão explicitamente
        securityContextRepository.saveContext(context, request, response);

        return userRepository.findByLogin(dto.login())
                .orElseThrow(() -> new RuntimeException("User not found after authentication"));
    }
}
