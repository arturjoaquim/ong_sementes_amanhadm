package br.ong.sementesamanha.erp.modules.education.domain.filters;

import lombok.Data;

@Data
public class StudentFilter {
    private String studentName;
    private String guardianName;
    private Integer minAge;
    private Integer maxAge;
    private String status;
}
