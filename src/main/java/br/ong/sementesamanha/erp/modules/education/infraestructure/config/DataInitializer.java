package br.ong.sementesamanha.erp.modules.education.infraestructure.config;

import br.ong.sementesamanha.erp.modules.education.domain.entities.User;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("!test")
public class DataInitializer {

    @Bean
    public CommandLineRunner initAdminUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Verifica se o usuario admin ja existe para nao duplicar ou sobrescrever
            if (userRepository.findByLogin("admin").isEmpty()) {

                User admin = new User();
                admin.setLogin("admin");
                // A senha deve ser encodada (hash) antes de salvar
                admin.setPasswordHash(passwordEncoder.encode("admin"));
                admin.setCreatedBy(1L);
                admin.setActive(true);

                userRepository.save(admin);

                System.out.println("---------------------------------");
                System.out.println("USUARIO ADMIN CRIADO COM SUCESSO");
                System.out.println("User: admin");
                System.out.println("Pass: admin");
                System.out.println("---------------------------------");
            } else {
                System.out.println("Usuario admin ja existe.");
            }
        };
    }
}