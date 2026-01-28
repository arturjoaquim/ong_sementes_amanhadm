package br.ong.sementesamanha.erp.modules.education.domain.entities;

import lombok.Data;

@Data
public class SocialInteraction {
    private Long id;
    private Integer interactionLevel;
    private String socialGroupDescription;
}
