package entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "elementi_catalogo")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Getter
@Setter

public abstract class ElementoCatalogo {
	@Id
//	@SequenceGenerator(name = "elemento_seq", sequenceName = "elemento_seq", allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "elemento_seq")
	protected UUID id;
	protected UUID codiceIsbn;
	protected String titolo;
	protected Integer annoPubblicazione;
	protected Integer numeroPagine;

	public ElementoCatalogo(UUID id, UUID codiceIsbn, String titolo, Integer annoPubblicazione, Integer numeroPagine) {
		this.id = id;
		this.codiceIsbn = codiceIsbn;
		this.annoPubblicazione = annoPubblicazione;
		this.numeroPagine = numeroPagine;
		this.titolo = titolo;
	}
}
