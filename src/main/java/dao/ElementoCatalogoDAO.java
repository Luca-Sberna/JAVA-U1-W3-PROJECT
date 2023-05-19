package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import entities.ElementoCatalogo;

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
}
