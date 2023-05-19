package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Utente implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID id;
	private String nome;
	private String cognome;
	@Temporal(TemporalType.DATE)
	private Date dataNascita;
	private String tessera;

	public Utente(String nome, String numeroTessera) {
		this.nome = nome;
		this.tessera = numeroTessera;
	}
}
