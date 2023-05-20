package entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "prenotazioni")
@Getter
@Setter
@NoArgsConstructor
public class Prenotazione {

	@Id
//	@SequenceGenerator(name = "prenotazione_seq", sequenceName = "prenotazione_seq", allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prenotazione_seq")
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "utente_id")
	private Utente utente;

	@ManyToOne
	@JoinColumn(name = "elemento_catalogo_id")
	private ElementoCatalogo elementoPrenotato;

	private LocalDate dataPrenotazione;

	public Prenotazione(UUID id, Utente utente, ElementoCatalogo elementoPrenotato, LocalDate dataPrenotazione) {
		this.id = id;
		this.utente = utente;
		this.elementoPrenotato = elementoPrenotato;
		this.dataPrenotazione = dataPrenotazione;
	}
}