package entities;

import java.util.UUID;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DiscriminatorValue("libri")
public class Libro extends ElementoCatalogo {
	String autore;
	String genere;

	public Libro(UUID id, UUID codiceIsbn, String titolo, Integer annoPubblicazione, Integer numeroPagine,
			String autore, String genere) {
		super(id, codiceIsbn, titolo, annoPubblicazione, numeroPagine);
		this.setAutore(autore);
		this.setGenere(genere);
	}

	@Override
	public String toString() {
		return "Libro [autore=" + autore + ", genere=" + genere + ", codiceIsbn=" + codiceIsbn + ", titolo=" + titolo
				+ ", annoPubblicazione=" + annoPubblicazione + ", numeroPagine=" + numeroPagine + ", getAutore()="
				+ getAutore() + ", getGenere()=" + getGenere() + ", getCodiceIsbn()=" + getCodiceIsbn()
				+ ", getTitolo()=" + getTitolo() + ", getAnnoPubblicazione()=" + getAnnoPubblicazione()
				+ ", getNumeroPagine()=" + getNumeroPagine() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
