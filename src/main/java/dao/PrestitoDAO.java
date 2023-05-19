package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import entities.Prestito;

public class PrestitoDAO {
	private EntityManagerFactory entityManagerFactory;

	public PrestitoDAO(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public void save(Prestito prestito) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(prestito);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public Prestito getById(UUID uuid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Prestito ele = entityManager.find(Prestito.class, uuid);
		entityManager.close();
		return ele;
	}

	public void delete(Prestito prestito) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		prestito = entityManager.merge(prestito);
		entityManager.remove(prestito);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void refresh(Prestito prestito) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		prestito = entityManager.merge(prestito);
		entityManager.refresh(prestito);
		entityManager.close();
	}

	public List<Prestito> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		TypedQuery<Prestito> query = entityManager.createQuery("SELECT p FROM Prestito p", Prestito.class);
		List<Prestito> prestiti = query.getResultList();
		entityManager.close();
		return prestiti;
	}

}
