package it.prova.pizzastore.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import it.prova.pizzastore.model.Pizza;

public class PizzaDAOImpl implements PizzaDAO {

	@Override
	public List<Pizza> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Pizza> findOne(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Pizza input) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(Pizza input) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Pizza input) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		// TODO Auto-generated method stub

	}

}
