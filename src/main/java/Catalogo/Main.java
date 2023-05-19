package Catalogo;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.LibroDAO;
import dao.PrestitoDAO;
import dao.RivistaDAO;
import dao.UtenteDAO;
import entities.Libro;
import entities.Prestito;
import entities.Rivista;
import entities.Utente;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JavaUnit1Week3Project");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {
			entityManager.getTransaction().begin();

			// Creo un utente
			Utente utente = new Utente();
			utente.setNome("Mario");
			utente.setCognome("Rossi");
			utente.setDataNascita(Date.valueOf("1990-01-01"));
			UtenteDAO utenteDAO = new UtenteDAO(entityManagerFactory);
			utenteDAO.save(utente);

			// Creo un libro
			Libro libro = new Libro();
			libro.setTitolo("Il signore degli anelli");
			libro.setAutore("J.R.R. Tolkien");
			libro.setAnnoPubblicazione(1954);
			LibroDAO libroDAO = new LibroDAO(entityManagerFactory);
			libroDAO.save(libro);

			// Creo una rivista
			Rivista rivista = new Rivista();
			rivista.setTitolo("National Geographic");
			rivista.setEditore("National Geographic Society");
			rivista.setMesePubblicazione("Maggio");
			RivistaDAO rivistaDAO = new RivistaDAO(entityManagerFactory);
			rivistaDAO.save(rivista);

			// Creo un prestito per il libro
			Prestito prestitoLibro = new Prestito();
			prestitoLibro.setUtente(utente);
			prestitoLibro.setMateriale(libro);
			prestitoLibro.setDataPrestito(new java.util.Date());
			PrestitoDAO prestitoDAO = new PrestitoDAO(entityManagerFactory);
			prestitoDAO.save(prestitoLibro);

			// Creo un prestito per la rivista
			Prestito prestitoRivista = new Prestito();
			prestitoRivista.setUtente(utente);
			prestitoRivista.setElemento(rivista);
			prestitoRivista.setDataPrestito(new java.util.Date());
			prestitoDAO.save(prestitoRivista);

			entityManager.getTransaction().commit();

			log.info("Utente: " + utente);
			log.info("Libro: " + libro);
			log.info("Rivista: " + rivista);
			log.info("Prestito Libro: " + prestitoLibro);
			log.info("Prestito Rivista: " + prestitoRivista);
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			log.error("Si è verificato un errore durante il salvataggio dei dati.", e);
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
	}
}
