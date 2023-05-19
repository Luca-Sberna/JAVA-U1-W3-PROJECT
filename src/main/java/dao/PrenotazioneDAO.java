package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import entities.Prenotazione;

public class PrenotazioneDAO {
	private EntityManagerFactory entityManagerFactory;

	public PrenotazioneDAO(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public void save(Prenotazione prenotazione) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(prenotazione);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public Prenotazione getById(UUID uuid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Prenotazione ele = entityManager.find(Prenotazione.class, uuid);
		entityManager.close();
		return ele;
	}

	public void delete(Prenotazione prenotazione) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		prenotazione = entityManager.merge(prenotazione);
		entityManager.remove(prenotazione);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void refresh(Prenotazione prenotazione) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		prenotazione = entityManager.merge(prenotazione);
		entityManager.refresh(prenotazione);
		entityManager.close();
	}

	public List<Prenotazione> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		TypedQuery<Prenotazione> query = entityManager.createQuery("SELECT pr FROM Prenotazione pr",
				Prenotazione.class);
		List<Prenotazione> elementi = query.getResultList();
		entityManager.close();
		return elementi;
	}
}