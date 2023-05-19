package entities;

import java.io.Serializable;

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
@SuppressWarnings("serial")
public class Rivista extends ElementoCatalogo implements Serializable {
	@Enumerated(EnumType.STRING)
	private TipoEvento periodicità;

	public enum TipoEvento {
		SETTIMANALE, MENSILE, SEMESTRALE
	}

	public Rivista(String titolo, Integer annoPubblicazione, Integer numeroPagine, String autore, String genere,
			TipoEvento periodicità) {
		this.periodicità = periodicità;
	}

	public void setMesePubblicazione(String string) {
		// TODO Auto-generated method stub

	}

	public void setEditore(String string) {
		// TODO Auto-generated method stub

	}

}
