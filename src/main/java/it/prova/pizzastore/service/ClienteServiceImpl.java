package it.prova.pizzastore.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.pizzastore.dao.ClienteDAO;
import it.prova.pizzastore.exceptions.ElementNotFoundException;
import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.web.listener.LocalEntityManagerFactoryListener;



public class ClienteServiceImpl implements ClienteService {
	
	private ClienteDAO clienteDAO;
	
	@Override
	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO=clienteDAO;
		
	}

	@Override
	public List<Cliente> listAllElements() throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			clienteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return clienteDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Cliente caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			clienteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return clienteDAO.findOne(id).get();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Cliente caricaSingoloElementoConOrdini(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(Cliente clienteInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			clienteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			clienteDAO.update(clienteInstance);

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
	public void inserisciNuovo(Cliente clienteInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			clienteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			clienteDAO.insert(clienteInstance);

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
	public void rimuovi(Long idCliente) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			clienteDAO.setEntityManager(entityManager);
			Cliente registaToRemove = clienteDAO.findOne(idCliente).orElse(null);
			if (registaToRemove == null)
				throw new ElementNotFoundException("Regista con id: " + idCliente + " non trovato.");

			clienteDAO.delete(registaToRemove);
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
	public List<Cliente> findByExample(Cliente example) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			clienteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return clienteDAO.findByExample(example);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

}
