package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Prestito implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID id;

	@ManyToOne
	private Utente utente;

	@ManyToOne
	private ElementoCatalogo elemento;
	private LocalDate dataInizio;
	private LocalDate dataPrevista;
	private LocalDate dataEffettiva;

	public Prestito(Utente utente, ElementoCatalogo elemento, LocalDate dataInizio, LocalDate dataPrevista,
			LocalDate dataEffettiva) {
		this.utente = utente;
		this.elemento = elemento;
		this.dataInizio = dataInizio;
		this.dataPrevista = dataPrevista.plusDays(30);
		this.dataEffettiva = dataEffettiva;
	}

	public void setMateriale(Libro libro) {
		// TODO Auto-generated method stub

	}

	public void setDataPrestito(Date date) {
		// TODO Auto-generated method stub

	}

}
