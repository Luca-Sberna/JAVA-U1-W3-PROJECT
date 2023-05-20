package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import entities.ElementoCatalogo;
import entities.Libro;
import entities.Prestito;

public class ElementoCatalogoDAO {
	private EntityManager entityManager;

	public ElementoCatalogoDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void save(ElementoCatalogo ele) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(ele);
		transaction.commit();
		System.out.println("Elemento salvato " + ele.toString());
	}

	public ElementoCatalogo getById(UUID uuid) {
		ElementoCatalogo found = entityManager.find(ElementoCatalogo.class, uuid);
		return found;
	}

	public void delete(ElementoCatalogo elementoCatalogo) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(elementoCatalogo);
		transaction.commit();
	}

	public void refresh(ElementoCatalogo elementoCatalogo) {
		entityManager.refresh(elementoCatalogo);
	}

	public List<ElementoCatalogo> findAll() {
		TypedQuery<ElementoCatalogo> query = entityManager.createQuery("SELECT l FROM Libro l", ElementoCatalogo.class);
		List<ElementoCatalogo> elementi = query.getResultList();
		return elementi;
	}

	public ElementoCatalogo cercaPerIsbn(UUID isbn) {
		TypedQuery<ElementoCatalogo> query = entityManager
				.createQuery("SELECT e FROM ElementoCatalogo e WHERE e.codiceIsbn = :isbn", ElementoCatalogo.class);
		query.setParameter("isbn", isbn);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<ElementoCatalogo> cercaPerAnnoPubblicazione(int anno) {
		TypedQuery<ElementoCatalogo> query = entityManager.createQuery(
				"SELECT e FROM ElementoCatalogo e WHERE e.annoPubblicazione = :anno", ElementoCatalogo.class);
		query.setParameter("anno", anno);
		return query.getResultList();
	}

	public List<Libro> cercaPerAutore(String autore) {
		TypedQuery<Libro> query = entityManager.createQuery("SELECT l FROM Libro l WHERE l.autore = :autore",
				Libro.class);
		query.setParameter("autore", autore);
		return query.getResultList();
	}

	public List<ElementoCatalogo> cercaPerTitolo(String titolo) {
		TypedQuery<ElementoCatalogo> query = entityManager
				.createQuery("SELECT e FROM ElementoCatalogo e WHERE e.titolo LIKE :titolo", ElementoCatalogo.class);
		query.setParameter("titolo", "%" + titolo + "%");
		return query.getResultList();
	}

	public List<ElementoCatalogo> cercaElementiInPrestito(String Tessera) {
		TypedQuery<ElementoCatalogo> query = entityManager.createQuery(
				"SELECT p.elementoPrestato FROM Prestito p WHERE p.utente.numeroTessera = :numeroTessera AND p.dataRestituzioneEffettiva IS NULL",
				ElementoCatalogo.class);
		query.setParameter("numeroTessera", Tessera);
		return query.getResultList();
	}

	public List<Prestito> cercaPrestitiScaduti() {
		TypedQuery<Prestito> query = entityManager.createQuery(
				"SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < CURRENT_DATE AND p.dataRestituzioneEffettiva IS NULL",
				Prestito.class);
		return query.getResultList();
	}

}
