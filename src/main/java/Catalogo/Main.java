package Catalogo;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		Archivio archivio = new Archivio();

		// CREO ELEMENTI DA POI AGG. ALL'ARCHIVIO
		Libro libro1 = new Libro("123456789", "Il Signore degli Anelli - La Compagnia Dell'Anello", 1954, 1178,
				"J.R.R. Tolkien", "Fantasy");
		Libro libro2 = new Libro("987654321", "Harry Potter - E Lo Sbarco In Normandia", 1983, 1500, "J.K.Rowling",
				"Fantasy");
		Libro libro3 = new Libro("123456890", "Il Signore degli Anelli - Le Due Torri", 1990, 1378, "J.R.R. Tolkien",
				"Fantasy");
		Libro libro4 = new Libro("123321123", "La Storia e La Morte Di Pop Smoke - King Of New York", 2022, 200,
				"Crips", "Biografia");
		Rivista rivista = new Rivista("479042", "Bleach", 2022, 101, "Manga");
		Rivista rivista2 = new Rivista("246890", "Mushle", 2023, 100, "Manga");

		// AGGIUNGO ALL'ARCHIVIO DEGLI ELEMENTI
		archivio.aggiungiElemento(libro1);
		archivio.aggiungiElemento(libro2);
		archivio.aggiungiElemento(libro3);
		archivio.aggiungiElemento(libro4);
		archivio.aggiungiElemento(rivista);
		archivio.aggiungiElemento(rivista2);

		// CERCO ELEMENTO PER CODICE ISBN (ID)
		ElementoCatalogo elementoTrovato = archivio.ricercaPerIsbn("123321123");
		System.out.println("Elemento trovato: " + "'" + elementoTrovato.titolo + "' ," + " Id: " + "'"
				+ elementoTrovato.codiceIsbn + "' ," + " Anno: " + "'" + elementoTrovato.annoPubblicazione + "'");

		// CERCO I LIBRI DI UN DETERMINATO AUTORE
		List<Libro> libriTrovati = archivio.ricercaPerAutore("J.R.R. Tolkien");
		System.out.println("");
		System.out.println("Libri trovati: ");
		for (Libro libro : libriTrovati) {
			System.out.println("- '" + libro.titolo + "' ," + " Id: " + "'" + libro.codiceIsbn + "' ," + " Anno: " + "'"
					+ libro.annoPubblicazione + "'");
		}

		// RIMUOVO DALL'ARCHIVIO
		System.out.println("");
		ElementoCatalogo elementoRimosso = archivio.rimuoviElemento("987654321");
		System.out.println("elemento con id: " + "'" + elementoRimosso.codiceIsbn + "'" + " e titolo: " + "'"
				+ elementoRimosso.titolo + "'" + " rimosso con successo!");

		// SALVO ARCHIVIO SU DISCO
		archivio.salvaSuDisco("archivio.dat");

		// CARICO SU DISCO
		Archivio archivioCaricato = new Archivio();
		archivioCaricato.caricaDaDisco("archivio.dat");

		// MOSTRO L'INTERO ARCHIVIO
		System.out.println("");
		System.out.println("Elementi presenti in archivio: ");
		for (ElementoCatalogo elemento : archivioCaricato.getElementiCatalogo()) {
			System.out.println("- Titolo: " + "'" + elemento.titolo + "' ," + " Id: " + "'" + elemento.codiceIsbn + "'"
					+ " , Anno: " + "'" + elemento.annoPubblicazione + "'");
		}
	}
}
