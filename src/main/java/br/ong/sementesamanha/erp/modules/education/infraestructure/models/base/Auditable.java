package br.ong.sementesamanha.erp.modules.education.infraestructure.models.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

    @CreatedDate
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(name = "criado_por_id", updatable = false) // Removido nullable=false para flexibilidade
    private Long createdBy;

    @LastModifiedDate
    @Column(name = "atualizado_em")
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(name = "atualizado_por_id")
    private Long updatedBy;
}
