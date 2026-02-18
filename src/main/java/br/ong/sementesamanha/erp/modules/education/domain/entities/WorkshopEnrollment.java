package br.ong.sementesamanha.erp.modules.education.domain.entities;

import br.ong.sementesamanha.erp.modules.education.domain.entities.base.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "oficina_matriculas", schema = "academico")
@Getter
@Setter
public class WorkshopEnrollment extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "oficina_id")
	private Workshop workshop;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "aluno_id")
	private Student student;

	@Column(name = "ativo")
	private Boolean active = true;

	@OneToMany(mappedBy = "workshopEnrollment", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<WorkshopPresence> presences;
}
