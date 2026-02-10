package br.ong.sementesamanha.erp.modules.education.application.services;

import br.ong.sementesamanha.erp.modules.education.application.dtos.auth.LoginDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.user.UserDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.User;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.UserMapper;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.UserRepository;
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

    private final UserMapper userMapper;

    public AuthService(AuthenticationManager authenticationManager, 
                       UserRepository userRepository,
                       SecurityContextRepository securityContextRepository,
                       UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.securityContextRepository = securityContextRepository;
        this.userMapper = userMapper;
    }

    public UserDTO login(LoginDTO dto, HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.login(), dto.password())
        );

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        
        securityContextRepository.saveContext(context, request, response);

        User loggedUser = userRepository.findByLogin(dto.login())
                .orElseThrow(() -> new RuntimeException("User not found after authentication"));
        loggedUser.setPasswordHash(null);
        return userMapper.toDTO(loggedUser);
    }
}
