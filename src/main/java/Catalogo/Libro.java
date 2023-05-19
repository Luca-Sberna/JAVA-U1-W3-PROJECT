package Catalogo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Libro extends ElementoCatalogo implements Serializable {
	String autore;
	String genere;

	public Libro(String codiceIsbn, String titolo, int annoPubblicazione, int numeroPagine, String autore,
			String genere) {
		super(codiceIsbn, titolo, annoPubblicazione, numeroPagine);
		this.setAutore(autore);
		this.setGenere(genere);
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}
}
