package Catalogo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ElementoCatalogo implements Serializable {
	protected  String codiceIsbn;
	protected String titolo;
	protected int annoPubblicazione;
	protected int numeroPagine;

	public ElementoCatalogo(String codiceIsbn, String titolo, int annoPubblicazione, int numeroPagine) {
		this.codiceIsbn = codiceIsbn;
		this.titolo = titolo;
		this.annoPubblicazione = annoPubblicazione;
		this.numeroPagine = numeroPagine;
	}

}
