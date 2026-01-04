package br.ong.sementesamanha.erp.modules.education.infraestructure.config;

import br.ong.sementesamanha.erp.modules.education.infraestructure.security.UserDetailsImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditConfig {

    @Bean
    public AuditorAware<Long> auditorProvider() {
        return new AuditorAwareImpl();
    }

    public static class AuditorAwareImpl implements AuditorAware<Long> {
        @Override
        public Optional<Long> getCurrentAuditor() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
                // Em cenários de seed ou sem usuário, pode retornar um ID padrão ou vazio
                return Optional.empty(); 
            }
            UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
            return Optional.of(userPrincipal.getId());
        }
    }
}
