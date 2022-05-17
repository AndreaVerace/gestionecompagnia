package it.prova.gestionecompagnia.dao.impiegato;

import java.util.Date;
import java.util.List;

import it.prova.gestionecompagnia.dao.IBaseDAO;
import it.prova.gestionecompagnia.model.Compagnia;
import it.prova.gestionecompagnia.model.Impiegato;

public interface ImpiegatoDAO extends IBaseDAO<Impiegato> {

	public List<Impiegato> findAllByCompagnia(Compagnia compagniaInput);
	
	public List<Impiegato> countByDataFondazioneCompagniaGreaterThan(Date dataInput);
	
	public List<Impiegato> findAllByCompagniaConfatturatoMaggioreDi(int fatturatoInput);
	
	public List<Impiegato> findAllErroriAssunzione();
	
}