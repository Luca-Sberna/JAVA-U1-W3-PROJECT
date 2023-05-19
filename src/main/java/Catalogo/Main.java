package Catalogo;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.ElementoCatalogoDAO;
import dao.PrenotazioneDAO;
import dao.PrestitoDAO;
import dao.UtenteDAO;
import entities.ElementoCatalogo;
import entities.Libro;
import entities.Prenotazione;
import entities.Prestito;
import entities.Rivista;
import entities.Utente;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaUnit1Week3Project");
		EntityManager entityManager = emf.createEntityManager();

		ElementoCatalogoDAO elementoDao = new ElementoCatalogoDAO(emf);
		PrestitoDAO prestitoDao = new PrestitoDAO(emf);
		PrenotazioneDAO prenotazioneDao = new PrenotazioneDAO(emf);
		UtenteDAO utenteDao = new UtenteDAO(emf);

		// Crea un nuovo libro
		Libro libro1 = new Libro("Il Signore Mi Aiuti", 1954, 1178, "Tolkien", "Fantasy");
		libro1.setCodiceIsbn(UUID.randomUUID());
		elementoDao.save(libro1);

		// Crea una nuova rivista
		Rivista rivista = new Rivista();
		rivista.setCodiceIsbn(UUID.randomUUID());
		rivista.setTitolo("National Geographic");
		rivista.setAnnoPubblicazione(2022);
		rivista.setNumeroPagine(100);
		rivista.setPeriodicità(Rivista.TipoEvento.MENSILE);
		elementoDao.save(rivista);

		// Crea un nuovo utente
		Utente utente = new Utente();
		utente.setNome("Mario");
		utente.setCognome("Rossi");
		utente.setDataNascita(LocalDate.of(1990, 1, 1));
		utente.setTessera("12345");
		utenteDao.save(utente);

		// Crea un nuovo prestito
		Prestito prestito = new Prestito();
		prestito.setUtente(utente);
		prestito.setElemento(libro1);
		prestito.setDataInizio(LocalDate.now());
		prestito.setDataPrevista(LocalDate.now().plusDays(30));
		prestitoDao.save(prestito);

		// Crea una nuova prenotazione
		Prenotazione prenotazione = new Prenotazione();
		prenotazione.setUtente(utente);
		prenotazione.setElementoPrenotato(rivista);
		prenotazione.setDataPrenotazione(LocalDate.now());
		prenotazioneDao.save(prenotazione);

		// Cerca elementi del catalogo per ISBN
		ElementoCatalogo elementoTrovato = elementoDao.getById(libro1.getCodiceIsbn());
		log.info("Elemento trovato: " + elementoTrovato.getTitolo());

		// Cerca elementi del catalogo per anno di pubblicazione
		List<ElementoCatalogo> elementiAnno = elementoDao.cercaPerAnnoPubblicazione(2022);
		log.info("Elementi trovati per anno di pubblicazione: " + elementiAnno.size());

		// Cerca libri per autore
		List<Libro> libriAutore = elementoDao.cercaPerAutore("J.R.R. Tolkien");
		log.info("Libri trovati per autore: " + libriAutore.size());

		// Cerca elementi del catalogo per titolo
		List<ElementoCatalogo> elementiTitolo = elementoDao.cercaPerTitolo("National Geographic");
		log.info(elementiTitolo.toString());

		entityManager.close();
		emf.close();
	}
}
