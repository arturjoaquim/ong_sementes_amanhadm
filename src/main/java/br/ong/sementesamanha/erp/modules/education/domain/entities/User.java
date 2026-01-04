package br.ong.sementesamanha.erp.modules.education.domain.entities;

import lombok.Data;
import java.util.Set;

@Data
public class User {
    private Long id;
    private String login;
    private String password;
    private boolean active;
    private Set<String> roles;
}
