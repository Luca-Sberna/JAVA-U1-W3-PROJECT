package entities;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Libro extends ElementoCatalogo {
	String autore;
	String genere;

	public Libro(String titolo, Integer annoPubblicazione, Integer numeroPagine, String autore, String genere) {

		this.setAutore(autore);
		this.setGenere(genere);
	}

}
