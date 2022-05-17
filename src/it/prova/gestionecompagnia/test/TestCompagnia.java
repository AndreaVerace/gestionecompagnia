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

public class TestCompagnia {

	public static void main(String[] args) {
		
		CompagniaDAO compagniaDAOInstance = null;
		ImpiegatoDAO impiegatoDAOInstance = null;
		
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			
			compagniaDAOInstance = new CompagniaDAOImpl(connection);
			impiegatoDAOInstance = new ImpiegatoDAOImpl(connection);
			
			testListCompagnia(compagniaDAOInstance);
			
			
		}	catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void testListCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception{
		List<Compagnia> listaCompagnie = compagniaDAOInstance.list();
		
		int result = listaCompagnie.size();
		
		System.out.println("al momento sono presenti : " + result + " compagnìe.");
	}
	
}
