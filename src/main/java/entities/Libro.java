package entities;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@SuppressWarnings("serial")
public class Libro extends ElementoCatalogo implements Serializable {
	String autore;
	String genere;

	public Libro(String titolo, int annoPubblicazione, int numeroPagine, String autore, String genere) {

		this.setAutore(autore);
		this.setGenere(genere);
	}

}
