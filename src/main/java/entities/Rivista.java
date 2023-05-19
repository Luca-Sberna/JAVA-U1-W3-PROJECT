package entities;

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
public class Rivista extends ElementoCatalogo {

	@Enumerated(EnumType.STRING)
	private TipoEvento periodicità;

	public enum TipoEvento {
		SETTIMANALE, MENSILE, SEMESTRALE
	}

}
