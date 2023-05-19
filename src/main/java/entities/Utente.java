package entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@NoArgsConstructor
public class Utente {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private UUID idUtente;
	private String nome;
	private String cognome;
	private LocalDate dataNascita;
	private String tessera;

	public Utente(String nome, String numeroTessera, LocalDate dataNascita, String tessera) {
		this.nome = nome;
		this.tessera = tessera;
	}
}
