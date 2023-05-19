package entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "elementi_catalogo")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Getter
@Setter
public abstract class ElementoCatalogo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	protected UUID codiceIsbn;
	protected String titolo;
	protected int annoPubblicazione;
	protected int numeroPagine;

}
