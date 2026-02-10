package br.ong.sementesamanha.erp.modules.education.application.dtos.user;

import java.util.List;

public record UserDTO (
        Long id,
        String username,
        String password,
        List<AccessGroupDTO> accessGroups
) {}
