package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import entities.ElementoCatalogo;
import entities.Libro;
import entities.Prestito;

public class ElementoCatalogoDAO {
	private EntityManagerFactory entityManagerFactory;

	public ElementoCatalogoDAO(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public void save(ElementoCatalogo elementoCatalogo) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(elementoCatalogo);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public ElementoCatalogo getById(UUID uuid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		ElementoCatalogo ele = entityManager.find(ElementoCatalogo.class, uuid);
		entityManager.close();
		return ele;
	}

	public void delete(ElementoCatalogo elementoCatalogo) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		elementoCatalogo = entityManager.merge(elementoCatalogo);
		entityManager.remove(elementoCatalogo);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void refresh(ElementoCatalogo elementoCatalogo) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		elementoCatalogo = entityManager.merge(elementoCatalogo);
		entityManager.refresh(elementoCatalogo);
		entityManager.close();
	}

	public List<ElementoCatalogo> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		TypedQuery<ElementoCatalogo> query = entityManager.createQuery("SELECT l FROM Libro l", ElementoCatalogo.class);
		List<ElementoCatalogo> elementi = query.getResultList();
		entityManager.close();
		return elementi;
	}

	public void aggiungiElemento(ElementoCatalogo elemento) {
		((EntityManager) entityManagerFactory).getTransaction().begin();
		((EntityManager) entityManagerFactory).persist(elemento);
		((EntityManager) entityManagerFactory).getTransaction().commit();
	}

	public void rimuoviElemento(UUID isbn) {
		ElementoCatalogo elemento = cercaPerIsbn(isbn);
		if (elemento != null) {
			((EntityManager) entityManagerFactory).getTransaction().begin();
			((EntityManager) entityManagerFactory).remove(elemento);
			((EntityManager) entityManagerFactory).getTransaction().commit();
		}
	}

	public ElementoCatalogo cercaPerIsbn(UUID isbn) {
		TypedQuery<ElementoCatalogo> query = ((EntityManager) entityManagerFactory)
				.createQuery("SELECT e FROM ElementoCatalogo e WHERE e.isbn = :isbn", ElementoCatalogo.class);
		query.setParameter("isbn", isbn);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<ElementoCatalogo> cercaPerAnnoPubblicazione(int anno) {
		TypedQuery<ElementoCatalogo> query = ((EntityManager) entityManagerFactory).createQuery(
				"SELECT e FROM ElementoCatalogo e WHERE e.annoPubblicazione = :anno", ElementoCatalogo.class);
		query.setParameter("anno", anno);
		return query.getResultList();
	}

	public List<Libro> cercaPerAutore(String autore) {
		TypedQuery<Libro> query = ((EntityManager) entityManagerFactory)
				.createQuery("SELECT l FROM Libro l WHERE l.autore = :autore", Libro.class);
		query.setParameter("autore", autore);
		return query.getResultList();
	}

	public List<ElementoCatalogo> cercaPerTitolo(String titolo) {
		TypedQuery<ElementoCatalogo> query = ((EntityManager) entityManagerFactory)
				.createQuery("SELECT e FROM ElementoCatalogo e WHERE e.titolo LIKE :titolo", ElementoCatalogo.class);
		query.setParameter("titolo", "%" + titolo + "%");
		return query.getResultList();
	}

	public List<ElementoCatalogo> cercaElementiInPrestito(String Tessera) {
		TypedQuery<ElementoCatalogo> query = ((EntityManager) entityManagerFactory).createQuery(
				"SELECT p.elementoPrestato FROM Prestito p WHERE p.utente.numeroTessera = :numeroTessera AND p.dataRestituzioneEffettiva IS NULL",
				ElementoCatalogo.class);
		query.setParameter("numeroTessera", Tessera);
		return query.getResultList();
	}

	public List<Prestito> cercaPrestitiScaduti() {
		TypedQuery<Prestito> query = ((EntityManager) entityManagerFactory).createQuery(
				"SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < CURRENT_DATE AND p.dataRestituzioneEffettiva IS NULL",
				Prestito.class);
		return query.getResultList();
	}

}
