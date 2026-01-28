package br.ong.sementesamanha.erp.modules.education.domain.entities;

import lombok.Data;
import java.util.Date;

@Data
public class StudentNote {
    private Long id;
   // private Long noteTypeId;
    private Boolean positive;
    private String shortDescription;
    private String fullDescription;
    private Date noteDate;
    private Long creatorUserId;
} 
