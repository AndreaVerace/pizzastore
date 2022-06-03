package it.prova.pizzastore.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.pizzastore.dao.PizzaDAO;
import it.prova.pizzastore.exceptions.ElementNotFoundException;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.web.listener.LocalEntityManagerFactoryListener;

public class PizzaServiceImpl implements PizzaService {

	private PizzaDAO pizzaDAO;
	
	@Override
	public void setPizzaDAO(PizzaDAO pizzaDAO) {
		this.pizzaDAO=pizzaDAO;
		
	}

	@Override
	public List<Pizza> listAllElements() throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			pizzaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return pizzaDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Pizza caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			pizzaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return pizzaDAO.findOne(id).get();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Pizza pizzaInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			pizzaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			pizzaDAO.update(pizzaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
		
	}

	@Override
	public void inserisciNuovo(Pizza pizzaInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			pizzaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			pizzaDAO.insert(pizzaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
		
	}

	@Override
	public void rimuovi(Long idPizza) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			pizzaDAO.setEntityManager(entityManager);
			Pizza pizzaToRemove = pizzaDAO.findOne(idPizza).orElse(null);
			if (pizzaToRemove == null)
				throw new ElementNotFoundException("Regista con id: " + idPizza + " non trovato.");

			pizzaDAO.delete(pizzaToRemove);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
		
	}

	@Override
	public List<Pizza> findByExample(Pizza example) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			pizzaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return pizzaDAO.findByExample(example);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

}
