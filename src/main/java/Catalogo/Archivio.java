package Catalogo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Archivio {
	private List<ElementoCatalogo> elementiCatalogo;

	public Archivio() {
		this.elementiCatalogo = new ArrayList<>();
	}

	public void aggiungiElemento(ElementoCatalogo elemento) {
		elementiCatalogo.add(elemento);
	}

	public ElementoCatalogo rimuoviElemento(String codiceIsbn) {
		ElementoCatalogo elementoRimosso = ricercaPerIsbn(codiceIsbn);
		elementiCatalogo.removeIf(e -> e.codiceIsbn.equals(codiceIsbn));
		return elementoRimosso;
	}

	public ElementoCatalogo ricercaPerIsbn(String codiceIsbn) {
		return elementiCatalogo.stream().filter(e -> e.codiceIsbn.equals(codiceIsbn)).findFirst().orElse(null);
	}

	public List<ElementoCatalogo> getElementiCatalogo() {
		return elementiCatalogo;
	}

	public List<ElementoCatalogo> ricercaPerAnnoPubblicazione(int annoPubblicazione) {
		return elementiCatalogo.stream().filter(e -> e.annoPubblicazione == annoPubblicazione).toList();
	}

	public List<Libro> ricercaPerAutore(String autore) {
		return elementiCatalogo.stream().filter(e -> e instanceof Libro).map(e -> (Libro) e)
				.filter(l -> autore.equals(l.getAutore())).toList();
	}

//	NEL CORSO DELLO SVILUPPO DEL PROGETTO, HO CERCATO SOLUZIONI ONLINE PER
//	MIGLIORARE IL MIO CODICE. HO SCOPERTO CHE L’UTILIZZO DELLE
//	CLASSI FILEINPUTSTREAM, FILEOUTPUTSTREAM, OBJECTINPUTSTREAM E
//	OBJECTOUTPUTSTREAM PER LEGGERE E SCRIVERE DATI SU E DA FILE È UN MODO
//	SEMPLECE E DIRETTO PER LAVORARE CON I FILE. QUESTE CLASSI FANNO PARTE DELLA
//	LIBRERIA STANDARD DI JAVA E SONO AMPIAMENTE UTILIZZATE PER LEGGERE E SCRIVERE
//	DATI SU E DA FILE.
//
//	-FILEINPUTSTREAM VIENE UTILIZZATO PER LEGGERE I DATI DA UN FILE
//	-FILEOUTPUTSTREAM VIENE UTILIZZATO PER SCRIVERE DATI SU UN FILE
//	-OBJECTINPUTSTREAM VIENE UTILIZZATO PER LEGGERE OGGETTI DA UNO STREAM DI
//	INPUT
//	-OBJECTOUTPUTSTREAM VIENE UTILIZZATO PER SCRIVERE OGGETTI SU UNO STREAM DI
//	OUTPUT

	public void salvaSuDisco(String nomeFile) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomeFile))) {
			out.writeObject(elementiCatalogo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void caricaDaDisco(String nomeFile) {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomeFile))) {
			elementiCatalogo = (List<ElementoCatalogo>) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
