package br.ong.sementesamanha.erp.modules.education.domain.entities;

import lombok.Data;

@Data
public class PersonContact {
    private Long id;
    private String telephone;
    private String mobilePhone;
    private boolean hasWhatsApp;
    private String email;
}
