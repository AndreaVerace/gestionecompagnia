package it.prova.gestionecompagnia.test;

import java.sql.Connection;
import java.util.List;

import it.prova.gestionecompagnia.connection.MyConnection;
import it.prova.gestionecompagnia.dao.Constants;
import it.prova.gestionecompagnia.dao.compagnia.CompagniaDAO;
import it.prova.gestionecompagnia.dao.compagnia.CompagniaDAOImpl;
import it.prova.gestionecompagnia.dao.impiegato.ImpiegatoDAO;
import it.prova.gestionecompagnia.dao.impiegato.ImpiegatoDAOImpl;
import it.prova.gestionecompagnia.model.Compagnia;
import it.prova.gestionecompagnia.model.Impiegato;

public class TestCompagnia {

	public static void main(String[] args) {
		
		CompagniaDAO compagniaDAOInstance = null;
		ImpiegatoDAO impiegatoDAOInstance = null;
		
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			
			compagniaDAOInstance = new CompagniaDAOImpl(connection);
			impiegatoDAOInstance = new ImpiegatoDAOImpl(connection);
			
			testListCompagnia(compagniaDAOInstance);
			
			testListImpiegato(impiegatoDAOInstance);
			
			testGetCompagnia(compagniaDAOInstance);
			
			testGetImpiegato(impiegatoDAOInstance);
			
		}	catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void testListCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception{
		List<Compagnia> listaCompagnie = compagniaDAOInstance.list();
		
		int result = listaCompagnie.size();
		
		System.out.println("al momento sono presenti : " + result + " compagnìe.");
	}
	
	public static void testListImpiegato(ImpiegatoDAO impiegatoDAOInstance) throws Exception {
		List<Impiegato> listaImpiegati = impiegatoDAOInstance.list();
		
		int result = listaImpiegati.size();
		
		System.out.println("al momento sono presenti : " + result + " impiegati.");
	}
	
	
	public static void testGetCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception {
		List<Compagnia> elencoVociPresenti = compagniaDAOInstance.list();
		if(elencoVociPresenti.size() < 1) {
			throw new RuntimeException("testGetCompagnia : FAILED, non ci sono voci sul DB");
		}
		long id = compagniaDAOInstance.list().get(2).getId();
		
		Compagnia daPrendere = compagniaDAOInstance.get(id);
		
		System.out.println(daPrendere);
	}
	
	public static void testGetImpiegato(ImpiegatoDAO impiegatoDAOInstance) throws Exception {
		List<Impiegato> elencoVociPresenti = impiegatoDAOInstance.list();
		if(elencoVociPresenti.size() < 1) {
			throw new RuntimeException("testGetCompagnia : FAILED, non ci sono voci sul DB");
		}
		long id = impiegatoDAOInstance.list().get(2).getId();
		
		Impiegato daPrendere = impiegatoDAOInstance.get(id);
		
		System.out.println(daPrendere);
	}
	
}
