package it.prova.pizzastore.web.listener;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import it.prova.pizzastore.model.Ruolo;
import it.prova.pizzastore.model.StatoUtente;
import it.prova.pizzastore.model.Utente;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.service.RuoloService;
import it.prova.pizzastore.service.UtenteService;



@WebListener
public class LocalEntityManagerFactoryListener implements ServletContextListener {

	private static EntityManagerFactory entityManagerFactory;

	public void contextDestroyed(ServletContextEvent sce) {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}

	public void contextInitialized(ServletContextEvent sce) {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("pizzastore_unit");
			// questa chiamata viene fatta qui per semplicit√† ma in genere √® meglio trovare
			// altri modi per fare init
			initAdminUserAndRuoli();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManager getEntityManager() {
		if (entityManagerFactory == null) {
			throw new IllegalStateException("Context is not initialized yet.");
		}
		return entityManagerFactory.createEntityManager();

	}

	public static void closeEntityManager(EntityManager em) {
		if (em != null) {
			try {
				if (em.isOpen()) {
					em.close();
				}
			} catch (PersistenceException ex) {
				System.err.println("Could not close JPA EntityManager" + ex);
			} catch (Throwable ex) {
				System.err.println("Unexpected exception on closing JPA EntityManager" + ex);
			}
		}
	}

	private void initAdminUserAndRuoli() throws Exception {
		RuoloService ruoloServiceInstance = MyServiceFactory.getRuoloServiceInstance();
		UtenteService utenteServiceInstance = MyServiceFactory.getUtenteServiceInstance();

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", "ROLE_ADMIN"));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo", "ROLE_PIZZAIOLO") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Pizzaiolo", "ROLE_PIZZAIOLO"));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino", "ROLE_FATTORINO") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Fattorino", "ROLE_FATTORINO"));
		}
		
		if (utenteServiceInstance.findByUsernameAndPassword("admin", "admin") == null) {
			Utente admin = new Utente("admin", "admin", "Mario", "Rossi", new Date());
			admin.setStato(StatoUtente.ATTIVO);
			utenteServiceInstance.inserisciNuovo(admin);
			utenteServiceInstance.aggiungiRuolo(admin,
					ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN"));
		}
		
		if (utenteServiceInstance.findByUsernameAndPassword("pizzaiolo", "pizzaiolo") == null) {
			Utente pizzaiolo = new Utente("pizzaiolo", "pizzaiolo", "Giovanni", "Bianchi", new Date());
			pizzaiolo.setStato(StatoUtente.ATTIVO);
			utenteServiceInstance.inserisciNuovo(pizzaiolo);
			utenteServiceInstance.aggiungiRuolo(pizzaiolo,
					ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo", "ROLE_PIZZAIOLO"));
		}
		
		if (utenteServiceInstance.findByUsernameAndPassword("fattorino", "fattorino") == null) {
			Utente fattorino = new Utente("fattorino", "fattorino", "Aldo", "Neri", new Date());
			fattorino.setStato(StatoUtente.ATTIVO);
			utenteServiceInstance.inserisciNuovo(fattorino);
			utenteServiceInstance.aggiungiRuolo(fattorino,
					ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino", "ROLE_FATTORINO"));
		}
	}
	

}
