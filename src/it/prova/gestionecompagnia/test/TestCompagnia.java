package it.prova.gestionecompagnia.test;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
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
			
			// testUpdateCompagnia(compagniaDAOInstance);
			
			// testUpdateImpiegato(impiegatoDAOInstance);
			
			// testInsertCompagnia(compagniaDAOInstance);
			
			// testInsertImpiegato(impiegatoDAOInstance);
			
			//testDeleteImpiegato(impiegatoDAOInstance);
			
			//  ------------------------------testDeleteCompagnia(compagniaDAOInstance); 
			
			// testFindByExampleCompagnia(compagniaDAOInstance);
			
			// testFindByExampleImpiegato(impiegatoDAOInstance);
			
			//testFindAllByDataAssunzioneMaggioreDi(compagniaDAOInstance);
			
			
			// testFindAllByRagioneSocialeContiene(compagniaDAOInstance);
			
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
	
	
	public static void testUpdateCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception {
		List<Compagnia> elencoVociPresenti = compagniaDAOInstance.list();
		if(elencoVociPresenti.size() < 1) {
			throw new RuntimeException("testUpdateCompagnia : FAILED, non ci sono voci sul DB");
		}
		
		Date dataFondazione = new SimpleDateFormat("dd-MM-yyyy").parse("03-01-2000");
		
		Compagnia daModificare = new Compagnia("AGUGLIA",700000,dataFondazione);
		daModificare.setId((long) 2);
		
		int result = compagniaDAOInstance.update(daModificare);
		
		if(result < 1) {
			throw new Exception("testUpdateCompagnia : FAILED");
		}
		System.out.println(compagniaDAOInstance.list().get(1));
	}
	
	
	public static void testUpdateImpiegato(ImpiegatoDAO impiegatoDAOInstance) throws Exception {
		List<Impiegato> elencoVociPresenti = impiegatoDAOInstance.list();
		if(elencoVociPresenti.size() < 1) {
			throw new RuntimeException("testUpdateImpiegato : FAILED, non ci sono voci sul DB");
		}
		
		Date dataNascita = new SimpleDateFormat("dd-MM-yyyy").parse("03-01-2000");
		Date dataAssunzione = new SimpleDateFormat("dd-MM-yyyy").parse("03-01-2022");
		
		Impiegato daModificare = new Impiegato("GIO","NERI","GVNNNR00",dataNascita,dataAssunzione);
		daModificare.setId((long) 4);
		
		int result = impiegatoDAOInstance.update(daModificare);
		
		if(result < 1) {
			throw new Exception("testUpdateImpiegato : FAILED");
		}
		System.out.println(impiegatoDAOInstance.list().get(3));
	}
	
	public static void testInsertCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception{
		
		Date dataFondazione = new SimpleDateFormat("dd-MM-yyyy").parse("13-05-1965");
		
		Compagnia daInserire = new Compagnia("AQUA",850000,dataFondazione);
		
		int result = compagniaDAOInstance.insert(daInserire);
		
		if(result < 1) {
			throw new Exception("testInsertCompagnia : FAILED");
		}
		
		for(Compagnia comp : compagniaDAOInstance.list()) {
			System.out.println(comp);
		}
	}
	
	public static void testInsertImpiegato(ImpiegatoDAO impiegatoDAOInstance) throws Exception{
		
		Date dataNascita = new SimpleDateFormat("dd-MM-yyyy").parse("03-01-1970");
		Date dataAssunzione = new SimpleDateFormat("dd-MM-yyyy").parse("13-09-1999");
		
		Impiegato daInserire = new Impiegato("FABIO","BELLINI","FBBLLN70",dataNascita,dataAssunzione);
		daInserire.setCompagnia(impiegatoDAOInstance.list().get(0).getCompagnia());
		
		int result = impiegatoDAOInstance.insert(daInserire);
		
		if(result < 1) {
			throw new Exception("testInsertCompagnia : FAILED");
		}
		
		for(Impiegato imp : impiegatoDAOInstance.list()) {
			System.out.println(imp);
		}
	}
	
	
	public static void testDeleteCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception {
		List<Compagnia> elencoVociPresenti = compagniaDAOInstance.list();
		if(elencoVociPresenti.size() < 1) {
			throw new RuntimeException("testDeleteImpiegato : FAILED, non ci sono voci sul DB");
		}
		
		Compagnia daEliminare = compagniaDAOInstance.list().get(0);
		
		if(compagniaDAOInstance.verificaSeCompagniaPossiedeimpiegati(daEliminare)  != true) {
			throw new Exception("IMPOSSIBILE ELIMINARE COMPAGNIA CON IMPIEGATI A CARICO.");
		}
		
		int result = compagniaDAOInstance.delete(daEliminare);
		
		if(result < 1) {
			throw new Exception("testDeleteImpiegato : FAILED");
		}
		
	}
	
	
	public static void testDeleteImpiegato(ImpiegatoDAO impiegatoDAOInstance) throws Exception {
		List<Impiegato> elencoVociPresenti = impiegatoDAOInstance.list();
		if(elencoVociPresenti.size() < 1) {
			throw new RuntimeException("testDeleteImpiegato : FAILED, non ci sono voci sul DB");
		}
		
		Impiegato daEliminare = impiegatoDAOInstance.list().get(5);
		
		int result = impiegatoDAOInstance.delete(daEliminare);
		
		if(result < 1) {
			throw new Exception("testDeleteImpiegato : FAILED");
		}
	}
	
	public static void testFindByExampleCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception {
		List<Compagnia> elencoVociPresenti = compagniaDAOInstance.list();
		if(elencoVociPresenti.size() < 1) {
			throw new RuntimeException("testDeleteImpiegato : FAILED, non ci sono voci sul DB");
		}
		
		Date dataFondazione = new SimpleDateFormat("dd-MM-yyyy").parse("06-06-1980");
		
		Compagnia daRicercare = new Compagnia("OB",250000,dataFondazione);
		
		
		List<Compagnia> listaResult = compagniaDAOInstance.findByExample(daRicercare);
		
		if( listaResult.size() < 1) {
			throw new Exception("testFindByExampleCompagnia : FAILED");
		}
		
		for(Compagnia comp : listaResult) {
			System.out.println(comp);
		}
	}
	
	
	public static void testFindByExampleImpiegato(ImpiegatoDAO impiegatoDAOInstance) throws Exception {
		List<Impiegato> elencoVociPresenti = impiegatoDAOInstance.list();
		if(elencoVociPresenti.size() < 1) {
			throw new RuntimeException("testFindByExampleImpiegato : FAILED, non ci sono voci sul DB");
		}
		
		Date dataNascita = new SimpleDateFormat("dd-MM-yyyy").parse("02-01-1970");
		Date dataAssunzione = new SimpleDateFormat("dd-MM-yyyy").parse("12-09-1999");
		
		Impiegato daRicercare = new Impiegato("FA","BE","FB",dataNascita,dataAssunzione);
		
		
		
		List<Impiegato> listaResult = impiegatoDAOInstance.findByExample(daRicercare);
		
		if( listaResult.size() < 1) {
			throw new Exception("testFindByExampleCompagnia : FAILED");
		}
		
		for(Impiegato comp : listaResult) {
			System.out.println(comp);
		}
	}
	
	public static void testFindAllByDataAssunzioneMaggioreDi(CompagniaDAO compagniaDAOInstance) throws Exception {
		
		List<Compagnia> elencoVociPresenti = compagniaDAOInstance.list();
		if(elencoVociPresenti.size() < 1) {
			throw new RuntimeException("testDeleteImpiegato : FAILED, non ci sono voci sul DB");
		}
		
		Date dataInput = new SimpleDateFormat("dd-MM-yyyy").parse("06-06-2008");
		
		List<Compagnia> result = compagniaDAOInstance.findAllByDataAssunzioneMaggioreDi(dataInput);
		
		if(result.size() < 1) {
			throw new Exception("SBAGLIATO.");
		}
		
		for(Compagnia c : result) {
			System.out.println(c);
		}
	}
	
	public static void testFindAllByRagioneSocialeContiene(CompagniaDAO compagniaDAOInstance) throws Exception {
		
		List<Compagnia> elencoVociPresenti = compagniaDAOInstance.list();
		if(elencoVociPresenti.size() < 1) {
			throw new RuntimeException("testDeleteImpiegato : FAILED, non ci sono voci sul DB");
		}
		
		String iniziale = "BL";
		
		List<Compagnia> result = compagniaDAOInstance.findAllByRagioneSocialeContiene(iniziale);
		
		if(result.size() < 1) {
			throw new Exception("SBAGLIATO.");
		}
		
		for(Compagnia c : result) {
			System.out.println(c);
		}
		
	}
}
