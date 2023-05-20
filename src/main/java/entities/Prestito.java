package entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "prestiti")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prestito {
	@Id
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "utente_id")
	private Utente utente;

	@ManyToOne
	@JoinColumn(name = "elemento_catalogo_id")
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

}
