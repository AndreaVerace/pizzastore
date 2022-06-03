package it.prova.pizzastore.service;

import java.util.List;

import it.prova.pizzastore.dao.OrdineDAO;
import it.prova.pizzastore.dao.PizzaDAO;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Utente;


public interface OrdineService {
	
	public void setOrdineDAO(OrdineDAO ordineDAO);
	
	public List<Ordine> listAllElements() throws Exception;

	public Ordine caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Ordine ordineInstance) throws Exception;

	public void inserisciNuovo(Ordine ordineInstance) throws Exception;

	public void rimuovi(Long idOrdine) throws Exception;
	
	public List<Ordine> findByExample(Ordine example) throws Exception;
	
	public List<Ordine> listaOrdiniAperti(String fattorino) throws Exception;
	
	public void chiudiOrdine(Long idOrdine) throws Exception;
}
