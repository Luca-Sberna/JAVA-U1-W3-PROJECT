package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import entities.Rivista;

public class RivistaDAO {
	private EntityManagerFactory entityManagerFactory;

	public RivistaDAO(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public void create(Rivista rivista) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(rivista);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void save(Rivista rivista) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(rivista);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public Rivista getById(UUID uuid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Rivista ele = entityManager.find(Rivista.class, uuid);
		entityManager.close();
		return ele;
	}

	public void delete(UUID uuid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		uuid = entityManager.merge(uuid);
		entityManager.remove(uuid);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void refresh(Rivista rivista) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		rivista = entityManager.merge(rivista);
		entityManager.refresh(rivista);
		entityManager.close();
	}

	public List<Rivista> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		TypedQuery<Rivista> query = entityManager.createQuery("SELECT r FROM Rivista r", Rivista.class);
		List<Rivista> riviste = query.getResultList();
		entityManager.close();
		return riviste;
	}
}
