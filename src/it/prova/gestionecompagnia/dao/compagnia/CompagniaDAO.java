package it.prova.gestionecompagnia.dao.compagnia;

import java.util.Date;
import java.util.List;

import it.prova.gestionecompagnia.dao.IBaseDAO;
import it.prova.gestionecompagnia.model.Compagnia;

public interface CompagniaDAO extends IBaseDAO<Compagnia> {

	public List<Compagnia> findAllByDataAssunzioneMaggioreDi(Date dataInput);
	
	public List<Compagnia> findAllByRagioneSocialeContiene(String fraseInput);
	
	public List<Compagnia> findAllByCodiceFiscaleContiene(String fraseInput);
	
}