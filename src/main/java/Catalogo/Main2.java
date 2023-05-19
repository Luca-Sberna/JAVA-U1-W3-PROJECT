package Catalogo;

import java.util.List;
import java.util.Scanner;

//QUESTO MAIN è PURAMENTE PER ESPERIENZA PERSONALE , RENDO UTILIZZABILE L'APP DA UN EVENTUALE ADMIN DI UN SITO EBOOK , CON QUALCHE ECCEZIONE PER L'INSERIMENTO E LA MODIFICA DEI CAMPI

public class Main2 {
	public static void main(String[] args) {
		Archivio archivio = new Archivio();

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		// MOSTRO UN MENU ALL'UTENTE
		while (true) {
			System.out.println("");
			System.out.println(
					"Scegli un'opzione:  (Digitare il numero corrispondente alla scelta voluta e successivamente premere invio per confermare)");
			System.out.println("");
			System.out.println("1. Aggiungi un libro");
			System.out.println("2. Aggiungi una rivista");
			System.out.println("3. Rimuovi un elemento");
			System.out.println("4. Cerca un elemento per ISBN (ID)");
			System.out.println("5. Cerca libri per autore");
			System.out.println("6. Mostra l'intero archivio");
			System.out.println("7. Modifica un elemento");
			System.out.println("");
			System.out.println("8. Esci");

			int scelta = scanner.nextInt();
			scanner.nextLine();

			// CASISTICHE CON POSSIBILI ECCEZIONI IN CASE 1(LIBRI) E 2(RIVISTE) , PER
			// L'INSERIMENTO
			// DELL'ANNO DI PUBB. E DEL NUM. DI PAGINE SIA DI LIBRI CHE DI RIVISTE.
			// PER IL CASO 5 DELLA RICERCA DEI LIBRI GRAZIE AL NOME DELL'AUTORE.
			// E L'AGGIUNTA DI UN CASO PER LA MODIFICA.
			switch (scelta) {
			// CASO 1
			case 1:
				System.out.print("Inserisci il codice ISBN: ");
				String isbnLibro = scanner.nextLine();

				System.out.print("Inserisci il titolo: ");
				String titoloLibro = scanner.nextLine();

				int annoPubblicazioneLibro = 0;
				while (true) {
					System.out.print("Inserisci l'anno di pubblicazione: ");
					try {
						annoPubblicazioneLibro = scanner.nextInt();
						scanner.nextLine();
						break;
					} catch (Exception e) {
						System.out
								.println("Anno di pubblicazione non valido , ripeti la modifica (solo numeri interi)");
						scanner.nextLine();
					}
				}

				int numeroPagineLibro = 0;
				while (true) {
					System.out.print("Inserisci il numero di pagine: ");
					try {
						numeroPagineLibro = scanner.nextInt();
						scanner.nextLine();
						break;
					} catch (Exception e) {
						System.out.println("Numero di pagine non valido , ripeti la modifica (solo numeri interi)");
						scanner.nextLine();
					}
				}

				System.out.print("Inserisci l'autore: ");
				String autoreLibro = scanner.nextLine();

				System.out.print("Inserisci il genere: ");
				String genereLibro = scanner.nextLine();

				Libro libro = new Libro(isbnLibro, titoloLibro, annoPubblicazioneLibro, numeroPagineLibro, autoreLibro,
						genereLibro);
				archivio.aggiungiElemento(libro);
				break;

			// CASO 2
			case 2:
				System.out.print("Inserisci l'ID personalizzabile dalla rivista: ");
				String isbnRivista = scanner.nextLine();

				System.out.print("Inserisci il titolo: ");
				String titoloRivista = scanner.nextLine();

				int annoPubblicazioneRivista = 0;
				while (true) {
					System.out.print("Inserisci l'anno di pubblicazione: ");
					try {
						annoPubblicazioneRivista = scanner.nextInt();
						scanner.nextLine();
						break;
					} catch (Exception e) {
						System.out
								.println("Anno di pubblicazione non valido , ripeti la modifica (solo numeri interi)");
						scanner.nextLine();
					}
				}

				int numeroPagineRivista = 0;
				while (true) {
					System.out.print("Inserisci il numero di pagine: ");
					try {
						numeroPagineRivista = scanner.nextInt();
						scanner.nextLine();
						break;
					} catch (Exception e) {
						System.out.println("Numero di pagine non valido , ripeti la modifica (solo numeri interi)");
						scanner.nextLine();
					}
				}

				System.out.print("Inserisci la periodicità [settimanale/mensile/semestrale]: ");
				String periodicitàRivista = scanner.nextLine();

				Rivista rivista = new Rivista(isbnRivista, titoloRivista, annoPubblicazioneRivista, numeroPagineRivista,
						periodicitàRivista);
				archivio.aggiungiElemento(rivista);
				break;

			// CASO 3
			case 3:
				System.out.print("Inserisci il codice ISBN dell'elemento da rimuovere: ");
				String isbnRimuovi = scanner.nextLine();
				ElementoCatalogo elementoRimosso = archivio.rimuoviElemento(isbnRimuovi);

				if (elementoRimosso != null) {
					System.out.println("elemento con id: " + "'" + elementoRimosso.codiceIsbn + "'" + " e titolo: "
							+ "'" + elementoRimosso.titolo + "'" + " rimosso con successo!");
				} else {
					System.out.println("Nessun elemento trovato con il codice ISBN specificato");
				}
				break;

			// CASO 4
			case 4:
				System.out.print("Inserisci il codice ISBN: ");
				String isbn = scanner.nextLine();
				ElementoCatalogo elementoTrovato = archivio.ricercaPerIsbn(isbn);

				if (elementoTrovato != null) {
					System.out.println("Elemento trovato: " + "'" + elementoTrovato.titolo + "' ," + " Id: " + "'"
							+ elementoTrovato.codiceIsbn + "' ," + " Anno: " + "'" + elementoTrovato.annoPubblicazione
							+ "'");
				} else {
					System.out.println("Nessun elemento trovato con il codice ISBN specificato");
				}
				break;

			// CASO 5
			case 5:
				System.out.print("Inserisci l'autore da cercare: ");
				String autoreCerca = scanner.nextLine();
				List<Libro> libriTrovati = archivio.ricercaPerAutore(autoreCerca);
				if (libriTrovati.isEmpty()) {
					System.out.println("Nessun autore trovato. Riprova.");
				} else {
					System.out.println("Libri trovati: ");
					for (Libro libroTrovato : libriTrovati) {
						System.out.println("- " + libroTrovato.titolo);
					}
				}
				break;

			// CASO 6
			case 6:
				System.out.println("Elementi del catalogo: ");
				for (ElementoCatalogo elemento : archivio.getElementiCatalogo()) {
					System.out.println(" - Titolo: " + "'" + elemento.titolo + "' ," + " Id: " + "'"
							+ elemento.codiceIsbn + "'" + " , Anno: " + "'" + elemento.annoPubblicazione + "'"
							+ " , Numero Pagine: " + "'" + elemento.numeroPagine + "'");
				}
				break;

			// CASO 7
			case 7:
				System.out.println("Vuoi modificare un libro o una rivista? (1 per libro, 2 per rivista)");
				int sceltaModifica = scanner.nextInt();
				scanner.nextLine();

				// MODIFICO I LIBRI
				if (sceltaModifica == 1) {
					System.out.print("Inserisci il codice ISBN (ID) del libro da modificare: ");
					String isbnLibroModifica = scanner.nextLine();
					ElementoCatalogo elementoModificare = archivio.ricercaPerIsbn(isbnLibroModifica);
					if (elementoModificare != null && elementoModificare instanceof Libro) {
						Libro libroModificare = (Libro) elementoModificare;

						System.out.print("Inserisci il nuovo titolo: ");
						String nuovoTitolo = scanner.nextLine();
						libroModificare.titolo = nuovoTitolo;

						while (true) {
							System.out.print("Inserisci il nuovo anno di pubblicazione: ");
							try {
								Integer nuovoAnnoDiPubblicazione = scanner.nextInt();
								scanner.nextLine();
								libroModificare.annoPubblicazione = nuovoAnnoDiPubblicazione;
								break;
							} catch (Exception e) {
								System.out.println(
										"Anno di pubblicazione non valido , ripeti la modifica (solo numeri interi)");
								scanner.nextLine();
							}

						}

						while (true) {
							System.out.print("Inserisci il nuovo numero delle pagine: ");
							try {
								Integer nuovoNumeroDellePagine = scanner.nextInt();
								scanner.nextLine();
								libroModificare.numeroPagine = nuovoNumeroDellePagine;
								break;
							} catch (Exception e) {
								System.out.println(
										"numero di pagine non valido , ripeti la modifica (solo numeri interi)");
								scanner.nextLine();
							}

						}

						System.out.print("Inserisci il nuovo nome dell'autore: ");
						String nuovoNomeAutore = scanner.nextLine();
						libroModificare.autore = nuovoNomeAutore;

						System.out.print("Inserisci il nuovo genere: ");
						String nuovoGenere = scanner.nextLine();
						libroModificare.genere = nuovoGenere;

						System.out.println("Libro modificato con successo!");
					} else {
						System.out.println("Nessun libro trovato con il codice ISBN (ID) specificato");
					}

					// MODIFICO LE RIVISTE
				} else if (sceltaModifica == 2) {
					System.out.print("Inserisci l'ID della rivista da modificare: ");
					String isbnRivistaModifica = scanner.nextLine();
					ElementoCatalogo elementoModificare = archivio.ricercaPerIsbn(isbnRivistaModifica);
					if (elementoModificare != null && elementoModificare instanceof Rivista) {
						Rivista rivistaModificare = (Rivista) elementoModificare;

						System.out.print("Inserisci il nuovo titolo: ");
						String nuovoTitolo = scanner.nextLine();
						rivistaModificare.titolo = nuovoTitolo;

						while (true) {
							System.out.print("Inserisci il nuovo anno di pubblicazione: ");
							try {
								Integer nuovoAnnoDiPubblicazione = scanner.nextInt();
								rivistaModificare.annoPubblicazione = nuovoAnnoDiPubblicazione;
								break;
							} catch (Exception e) {
								System.out.println(
										"numero di pagine non valido , ripeti la modifica (solo numeri interi)");
								scanner.nextLine();
							}
						}

						while (true) {
							System.out.print("Inserisci il nuovo numero delle pagine: ");
							try {
								Integer nuovoNumeroDellePagine = scanner.nextInt();
								rivistaModificare.numeroPagine = nuovoNumeroDellePagine;
								break;
							} catch (Exception e) {
								System.out.println(
										"numero di pagine non valido , ripeti la modifica (solo numeri interi)");
								scanner.nextLine();
							}
						}

						System.out.println("Rivista modificata con successo!");
					} else {
						System.out.println("Nessuna rivista trovata con con il codice ISBN (ID) specificato");
					}
				} else {
					System.out.println("Scelta non valida");
				}
				break;

			// CASO 8
			case 8:
				System.exit(0);
				System.out.println("Chiusura dell'app dei cataloghi...");
			default:
				System.out.println("Scelta non valida");

			}
		}
	}
}
