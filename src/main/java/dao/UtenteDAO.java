package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entities.Utente;

public class UtenteDAO {
	private EntityManager entityManager;

	public UtenteDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void save(Utente utente) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(utente);
		transaction.commit();
	}

	public Utente getById(UUID uuid) {
		Utente ele = entityManager.find(Utente.class, uuid);
		return ele;
	}

	public void delete(Utente utente) {
		entityManager.getTransaction().begin();
		utente = entityManager.merge(utente);
		entityManager.remove(utente);
		entityManager.getTransaction().commit();
	}

	public void refresh(Utente utente) {
		utente = entityManager.merge(utente);
		entityManager.refresh(utente);
	}

	public List<Utente> findAll() {
		TypedQuery<Utente> query = entityManager.createQuery("SELECT u FROM Utente u", Utente.class);
		List<Utente> utenti = query.getResultList();
		return utenti;
	}
}
