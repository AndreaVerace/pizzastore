package it.prova.pizzastore.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;

public class UtilityForm {

	public static Cliente createClienteFromParams(String nomeInputParam, String cognomeInputParam,
			String indirizzoInputParam, String attivoParam) {

		Cliente result = new Cliente(nomeInputParam, cognomeInputParam, indirizzoInputParam);
		result.setAttivo(Boolean.parseBoolean(attivoParam));
		return result;
	}

	public static boolean validateClienteBean(Cliente clienteToBeValidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(clienteToBeValidated.getNome())
				|| StringUtils.isBlank(clienteToBeValidated.getCognome())
				|| StringUtils.isBlank(clienteToBeValidated.getIndirizzo())) {
			return false;
		}
		return true;
	}
	
	public static Pizza createPizzaFromParams(String descrizioneInputparam, String ingredientiInputParam,
			String prezzoBaseInputParam, String attivoStringParam) {

		Pizza result = new Pizza(descrizioneInputparam, ingredientiInputParam);
		result.setAttivo(Boolean.parseBoolean(attivoStringParam));
		if (NumberUtils.isCreatable(prezzoBaseInputParam)) {
			result.setPrezzoBase(Integer.parseInt(prezzoBaseInputParam));
		}	
		return result;
	}

	public static boolean validatePizzaBean(Pizza pizzaToBeValidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(pizzaToBeValidated.getDescrizione())
				|| StringUtils.isBlank(pizzaToBeValidated.getIngredienti())
				|| pizzaToBeValidated.getPrezzoBase() == null 
				|| pizzaToBeValidated.getPrezzoBase() < 1) {
			return false;
		}
		return true;
	}
	
	public static Ordine createOrdineFromParams(String codiceInputparam, String dataOrdineInputParam,
			String closedInputParam) {

		Ordine result = new Ordine(codiceInputparam);
		result.setClosed(Boolean.parseBoolean(closedInputParam));
		result.setDataOrdine(parseDateArrivoFromString(dataOrdineInputParam));
		return result;
	}

	public static boolean validateOrdineBean(Ordine ordineToBeValidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(ordineToBeValidated.getCodice())
				|| ordineToBeValidated.getDataOrdine() == null) {
			return false;
		}
		return true;
	}

	public static Date parseDateArrivoFromString(String dataDiNascitaStringParam) {
		if (StringUtils.isBlank(dataDiNascitaStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dataDiNascitaStringParam);
		} catch (ParseException e) {
			return null;
		}
	}
}
