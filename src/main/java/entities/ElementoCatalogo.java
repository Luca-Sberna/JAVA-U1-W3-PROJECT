package entities;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SuppressWarnings("serial")
@NoArgsConstructor
@Getter
@Setter
public class ElementoCatalogo implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected UUID codiceIsbn;
	protected String titolo;
	protected Integer annoPubblicazione;
	protected Integer numeroPagine;
	private Prestito prestito;

	public void setPrestito(Prestito prestito) {
		this.prestito = prestito;
	}

	public Prestito getPrestito() {
		return prestito;
	}
}
