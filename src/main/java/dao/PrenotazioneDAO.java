package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entities.Prenotazione;

public class PrenotazioneDAO {
	private EntityManager entityManager;

	public PrenotazioneDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void save(Prenotazione prenotazione) {
		entityManager.getTransaction().begin();
		entityManager.persist(prenotazione);
		entityManager.getTransaction().commit();
	}

	public Prenotazione getById(UUID uuid) {
		Prenotazione ele = entityManager.find(Prenotazione.class, uuid);
		return ele;
	}

	public void delete(Prenotazione prenotazione) {
		entityManager.getTransaction().begin();
		prenotazione = entityManager.merge(prenotazione);
		entityManager.remove(prenotazione);
		entityManager.getTransaction().commit();
	}

	public void refresh(Prenotazione prenotazione) {
		prenotazione = entityManager.merge(prenotazione);
		entityManager.refresh(prenotazione);
	}

	public List<Prenotazione> findAll() {
		TypedQuery<Prenotazione> query = entityManager.createQuery("SELECT pr FROM Prenotazione pr",
				Prenotazione.class);
		List<Prenotazione> elementi = query.getResultList();
		return elementi;
	}
}