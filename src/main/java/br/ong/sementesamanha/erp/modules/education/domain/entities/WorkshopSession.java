package br.ong.sementesamanha.erp.modules.education.domain.entities;

import br.ong.sementesamanha.erp.modules.education.domain.entities.base.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "oficina_sessoes", schema = "academico")
@Getter
@Setter
public class WorkshopSession extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "educador_responsavel_id")
	private Employee responsibleEducator;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "oficina_id")
	private Workshop workshop;

	@Column(name = "link_lista_presenca")
	private String attendanceListLink;

	@Column(name = "descricao")
	private String description;

	@OneToMany(mappedBy = "workshopSession", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<WorkshopPresence> participants;
}
