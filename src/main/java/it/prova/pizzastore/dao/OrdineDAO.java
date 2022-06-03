package it.prova.pizzastore.dao;

import java.util.List;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Utente;

public interface OrdineDAO extends IBaseDAO<Ordine> {
	public List<Ordine> findByExample(Ordine example) throws Exception;
	
	public List<Ordine> listaOrdiniAperti(String fattorino) throws Exception;
	
	public void chiudiOrdine(Long idOrdine) throws Exception;

}
