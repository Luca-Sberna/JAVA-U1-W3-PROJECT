package Catalogo;

import java.time.LocalDate;
import java.util.List;

import entities.Archivio;
import entities.ElementoCatalogo;
import entities.Libro;
import entities.Prestito;
import entities.Rivista;
import entities.Rivista.TipoEvento;
import entities.Utente;

public class Main {
	public static void main(String[] args) {
		Archivio archivio = new Archivio();

		// CREO ELEMENTI DA POI AGG. ALL'ARCHIVIO
		Libro libro1 = new Libro("Il Signore degli Anelli - La Compagnia Dell'Anello", 1954, 1178, "J.R.R. Tolkien",
				"Fantasy");
		Libro libro2 = new Libro("Harry Potter - E Lo Sbarco In Normandia", 1983, 1500, "J.K.Rowling", "Fantasy");
		Libro libro3 = new Libro("Il Signore degli Anelli - Le Due Torri", 1990, 1378, "J.R.R. Tolkien", "Fantasy");
		Libro libro4 = new Libro("La Storia e La Morte Di Pop Smoke - King Of New York", 2022, 200, "Crips",
				"Biografia");
		Rivista rivista = new Rivista("Bleach", 2022, 101, "Un Grande Autore", "manga", TipoEvento.MENSILE);
		Rivista rivista2 = new Rivista("Mushle", 2023, 100, "Il Secondo Grande Autore", "manga",
				TipoEvento.SETTIMANALE);

		Utente utente1 = new Utente("John Doe", "123456");
		Utente utente2 = new Utente("Jane Smith", "789012");
		Utente utente3 = new Utente("Mark Johnson", "345678");

		// Esempio di prestito con data di restituzione effettiva
		LocalDate dataInizio1 = LocalDate.of(2023, 4, 1);
		LocalDate dataPrevista1 = LocalDate.of(2023, 4, 8);
		LocalDate dataEffettiva1 = LocalDate.of(2023, 4, 10);
		archivio.aggiungiPrestito(utente1, libro1, dataInizio1, dataPrevista1, dataEffettiva1);

		// Esempio di prestito in corso senza data di restituzione effettiva
		LocalDate dataInizio2 = LocalDate.of(2023, 4, 15);
		LocalDate dataPrevista2 = LocalDate.of(2023, 4, 22);
		archivio.aggiungiPrestito(utente2, libro2, dataInizio2, dataPrevista2, null);

		// Esempio di prestito scaduto e non restituito
		LocalDate dataInizio3 = LocalDate.of(2023, 4, 5);
		LocalDate dataPrevista3 = LocalDate.of(2023, 4, 12);
		archivio.aggiungiPrestito(utente3, libro1, dataInizio3, dataPrevista3, null);

		ElementoCatalogo elementoPrestito = archivio.ricercaPerIsbn("codiceISBN");
		if (elementoPrestito != null) {
			LocalDate dataInizio = LocalDate.now();
			LocalDate dataPrevista = LocalDate.now().plusDays(7);
			LocalDate dataEffettiva = null; // Non ancora restituito
			archivio.aggiungiPrestito(utente1, elementoPrestito, dataInizio, dataPrevista, dataEffettiva);
		} else {
			System.out.println("Elemento non trovato.");
		}

		// AGGIUNGO ALL'ARCHIVIO DEGLI ELEMENTI
		archivio.aggiungiElemento(libro1);
		archivio.aggiungiElemento(libro2);
		archivio.aggiungiElemento(libro3);
		archivio.aggiungiElemento(libro4);
		archivio.aggiungiElemento(rivista);
		archivio.aggiungiElemento(rivista2);

		// CERCO ELEMENTO PER CODICE ISBN (ID)
		ElementoCatalogo elementoTrovato = archivio.ricercaPerIsbn("");
		if (elementoTrovato != null) {
			System.out.println("Elemento trovato: " + "'" + elementoTrovato.getTitolo() + "' ," + " Id: " + "'"
					+ elementoTrovato.getCodiceIsbn() + "' ," + " Anno: " + "'" + elementoTrovato.getAnnoPubblicazione()
					+ "'");
		} else {
			System.out.println("Elemento non trovato.");
		}

		// CERCO I LIBRI DI UN DETERMINATO AUTORE
		List<Libro> libriTrovati = archivio.ricercaPerAutore("J.R.R. Tolkien");
		System.out.println("");
		System.out.println("Libri trovati: ");
		for (Libro libro : libriTrovati) {
			System.out.println("- '" + libro.getTitolo() + "' ," + " Id: " + "'" + libro.getCodiceIsbn() + "' ,"
					+ " Anno: " + "'" + libro.getAnnoPubblicazione() + "'");
		}

		// RIMUOVO DALL'ARCHIVIO
		System.out.println("");
		ElementoCatalogo elementoRimosso = archivio.rimuoviElemento("");
		if (elementoRimosso != null) {
			System.out.println("Elemento rimosso con successo - ID: " + elementoRimosso.getCodiceIsbn() + ", Titolo: "
					+ elementoRimosso.getTitolo());
		} else {
			System.out.println("Elemento non trovato o impossibile rimuovere.");
		}

		// SALVO ARCHIVIO SU DISCO
		archivio.salvaSuDisco("archivio.dat");

		// CARICO SU DISCO
		Archivio archivioCaricato = new Archivio();
		archivioCaricato.caricaDaDisco("archivio.dat");

		// MOSTRO L'INTERO ARCHIVIO
		System.out.println("");
		System.out.println("Elementi presenti in archivio: ");
		for (ElementoCatalogo elemento : archivioCaricato.getElementiCatalogo()) {
			System.out.println("- Titolo: " + "'" + elemento.getTitolo() + "' ," + " Id: " + "'"
					+ elemento.getCodiceIsbn() + "'" + " , Anno: " + "'" + elemento.getAnnoPubblicazione() + "'");
		}

		// Ricerca dei prestiti per un utente
		List<Prestito> prestitiUtente1 = archivio.ricercaPrestitiPerUtente("123456");
		System.out.println("Prestiti per l'utente John Doe:");
		for (Prestito prestito : prestitiUtente1) {
			System.out.println("Elemento: " + prestito.getElemento().getTitolo() + ", Data di inizio: "
					+ prestito.getDataInizio() + ", Data di prevista di restituzione: " + prestito.getDataPrevista()
					+ ", Data di restituzione effettiva: " + prestito.getDataEffettiva());
		}

		// Ricerca dei prestiti scaduti e non restituiti
		List<Prestito> prestitiScadutiNonRestituiti = archivio.ricercaPrestitiScadutiNonRestituiti();
		System.out.println("Prestiti scaduti e non restituiti:");
		for (Prestito prestito : prestitiScadutiNonRestituiti) {
			System.out.println("Elemento: " + prestito.getElemento().getTitolo() + ", Data di inizio: "
					+ prestito.getDataInizio() + ", Data di prevista di restituzione: " + prestito.getDataPrevista());
		}
	}
}
