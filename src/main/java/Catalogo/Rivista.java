package Catalogo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Rivista extends ElementoCatalogo implements Serializable {
	private String periodicità;

	public Rivista(String codiceIsbn, String titolo, int annoPubblicazione, int numeroPagine, String periodicità) {
		super(codiceIsbn, titolo, annoPubblicazione, numeroPagine);
		this.periodicità = periodicità;
	}

	public String getPeriodicità() {
		return periodicità;
	}

	public void setPeriodicità(String periodicità) {
		this.periodicità = periodicità;
	}
}
