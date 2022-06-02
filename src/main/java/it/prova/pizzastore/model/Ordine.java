package it.prova.pizzastore.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ordine")
public class Ordine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "codice")
	private String codice;
	
	@Column(name = "dataordine")
	private Date dataOrdine;
	
	@Column(name = "closed")
	private boolean closed;
	
	@Column(name = "costo_totale_ordine")
	private int costoTotaleOrdine = costoTotaleOrdini();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "utente_id",nullable = false)
	private Utente utente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id",nullable = false)
	private Cliente cliente;
	
	@ManyToMany
	@JoinTable(name = "pizza_ordine", joinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ordine_id", referencedColumnName = "ID"))
	private Set<Pizza> pizze = new HashSet<>(0);
	
	public Ordine() {
		
	}
	
	public Ordine(String codice) {
		this.codice=codice;
	}

	

	public int costoTotaleOrdini() {
		if(this.pizze == null || this.pizze.isEmpty()) {
			this.costoTotaleOrdine = 0;	
		}
		else {
			for(Pizza p : this.pizze) {
				this.costoTotaleOrdine += p.getPrezzoBase();
			}
		}
		return this.costoTotaleOrdine;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Date getDataOrdine() {
		return dataOrdine;
	}

	public void setDataOrdine(Date dataOrdine) {
		this.dataOrdine = dataOrdine;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public int getCostoTotaleOrdine() {
		return costoTotaleOrdine;
	}

	public void setCostoTotaleOrdine(int costoTotaleOrdine) {
		this.costoTotaleOrdine = costoTotaleOrdine;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<Pizza> getPizze() {
		return pizze;
	}

	public void setPizze(Set<Pizza> pizze) {
		this.pizze = pizze;
	}
	
	
	
	
	
}
