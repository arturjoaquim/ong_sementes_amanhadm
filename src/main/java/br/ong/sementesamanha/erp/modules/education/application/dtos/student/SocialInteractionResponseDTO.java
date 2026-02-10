package br.ong.sementesamanha.erp.modules.education.application.dtos.student;

public record SocialInteractionResponseDTO(
    Long id,
    Integer interactionLevel,
    String socialGroupDescription
) {}
