package entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Utente {
	@Id
//	@SequenceGenerator(name = "utente_seq", sequenceName = "utente_seq", allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utente_seq")
	private UUID id;
	private String nome;
	private String cognome;
	private LocalDate dataNascita;
	private String tessera;

	public Utente(String nome, String tessera, LocalDate dataNascita, String cognome) {
		this.nome = nome;
		this.tessera = tessera;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
	}

	@Override
	public String toString() {
		return "Utente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", dataNascita=" + dataNascita
				+ ", tessera=" + tessera + ", getId()=" + getId() + ", getNome()=" + getNome() + ", getCognome()="
				+ getCognome() + ", getDataNascita()=" + getDataNascita() + ", getTessera()=" + getTessera()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
}
