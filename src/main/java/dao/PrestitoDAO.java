package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entities.Prestito;

public class PrestitoDAO {
	private EntityManager entityManager;

	public PrestitoDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void save(Prestito prestito) {
		entityManager.getTransaction().begin();
		entityManager.persist(prestito);
		entityManager.getTransaction().commit();
	}

	public Prestito getById(UUID uuid) {
		Prestito ele = entityManager.find(Prestito.class, uuid);
		return ele;
	}

	public void delete(Prestito prestito) {
		entityManager.getTransaction().begin();
		prestito = entityManager.merge(prestito);
		entityManager.remove(prestito);
		entityManager.getTransaction().commit();
	}

	public void refresh(Prestito prestito) {
		prestito = entityManager.merge(prestito);
		entityManager.refresh(prestito);
	}

	public List<Prestito> findAll() {
		TypedQuery<Prestito> query = entityManager.createQuery("SELECT p FROM Prestito p", Prestito.class);
		List<Prestito> prestiti = query.getResultList();
		return prestiti;
	}

}
