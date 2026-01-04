package br.ong.sementesamanha.erp.modules.education.application.dtos;

import lombok.Data;

@Data
public class LookupDTO {
    private Long id;
    private String name;
    private String description;

    public LookupDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
