package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import entities.Utente;

public class UtenteDAO {
	private EntityManagerFactory entityManagerFactory;

	public UtenteDAO(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public void save(Utente utente) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(utente);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public Utente getById(UUID uuid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Utente ele = entityManager.find(Utente.class, uuid);
		entityManager.close();
		return ele;
	}

	public void delete(Utente utente) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		utente = entityManager.merge(utente);
		entityManager.remove(utente);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void refresh(Utente utente) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		utente = entityManager.merge(utente);
		entityManager.refresh(utente);
		entityManager.close();
	}

	public List<Utente> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		TypedQuery<Utente> query = entityManager.createQuery("SELECT u FROM Utente u", Utente.class);
		List<Utente> utenti = query.getResultList();
		entityManager.close();
		return utenti;
	}
}
