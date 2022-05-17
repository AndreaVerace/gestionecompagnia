package it.prova.gestionecompagnia.model;

import java.util.Date;
import java.util.List;

public class Compagnia {

	private long id;
	private String ragioneSociale;
	private int fatturatoAnnuo;
	private Date dataFondazione;
	private List<Impiegato> impiegati;
	
	public Compagnia() {
		
	}
	
	public Compagnia(String ragioneSociale,int fatturatoAnnuo,Date dataFondazione) {
		this.ragioneSociale=ragioneSociale;
		this.fatturatoAnnuo=fatturatoAnnuo;
		this.dataFondazione=dataFondazione;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public int getFatturatoAnnuo() {
		return fatturatoAnnuo;
	}

	public void setFatturatoAnnuo(int fatturatoAnnuo) {
		this.fatturatoAnnuo = fatturatoAnnuo;
	}

	public Date getDataFondazione() {
		return dataFondazione;
	}

	public void setDataFondazione(Date dataFondazione) {
		this.dataFondazione = dataFondazione;
	}

	public List<Impiegato> getImpiegati() {
		return impiegati;
	}

	public void setImpiegati(List<Impiegato> impiegati) {
		this.impiegati = impiegati;
	}

	@Override
	public String toString() {
		return "Compagnia [id=" + id + ", ragioneSociale=" + ragioneSociale + ", fatturatoAnnuo=" + fatturatoAnnuo
				+ ", dataFondazione=" + dataFondazione + ", impiegati=" + impiegati + "]";
	}
	
	
	
	
	
}
