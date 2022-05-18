package it.prova.gestionecompagnia.dao.compagnia;

import java.util.Date;
import java.util.List;

import it.prova.gestionecompagnia.dao.IBaseDAO;
import it.prova.gestionecompagnia.model.Compagnia;
import it.prova.gestionecompagnia.model.Impiegato;

public interface CompagniaDAO extends IBaseDAO<Compagnia> {

	public List<Compagnia> findAllByDataAssunzioneMaggioreDi(Date dataInput) throws Exception;
	
	public List<Compagnia> findAllByRagioneSocialeContiene(String fraseInput) throws Exception;
	
	public List<Compagnia> findAllByCodiceFiscaleContiene(String fraseInput) throws Exception;
	
	public boolean verificaSeCompagniaPossiedeimpiegati(long idInput) throws Exception;
	
}
