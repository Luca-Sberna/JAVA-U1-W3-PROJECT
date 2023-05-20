package entities;

import java.util.UUID;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("riviste")
public class Rivista extends ElementoCatalogo {

	public Rivista(UUID id, UUID codiceIsbn, String titolo, Integer annoPubblicazione, Integer numeroPagine,
			TipoEvento periodicità) {
		super(id, codiceIsbn, titolo, annoPubblicazione, numeroPagine);
		this.periodicità = periodicità;
	}

	@Enumerated(EnumType.STRING)
	private TipoEvento periodicità;

	public enum TipoEvento {
		SETTIMANALE, MENSILE, SEMESTRALE
	}

	@Override
	public String toString() {
		return "Rivista [periodicità=" + periodicità + ", id=" + id + ", codiceIsbn=" + codiceIsbn + ", titolo="
				+ titolo + ", annoPubblicazione=" + annoPubblicazione + ", numeroPagine=" + numeroPagine
				+ ", getPeriodicità()=" + getPeriodicità() + ", getId()=" + getId() + ", getCodiceIsbn()="
				+ getCodiceIsbn() + ", getTitolo()=" + getTitolo() + ", getAnnoPubblicazione()="
				+ getAnnoPubblicazione() + ", getNumeroPagine()=" + getNumeroPagine() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}
