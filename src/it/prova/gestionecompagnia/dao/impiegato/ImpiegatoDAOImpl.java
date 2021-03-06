package it.prova.gestionecompagnia.dao.impiegato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.prova.gestionecompagnia.dao.AbstractMySQLDAO;
import it.prova.gestionecompagnia.model.Compagnia;
import it.prova.gestionecompagnia.model.Impiegato;

public class ImpiegatoDAOImpl extends AbstractMySQLDAO implements ImpiegatoDAO {
	
	public ImpiegatoDAOImpl(Connection connection) {
		super(connection);
	}

	@Override
	public List<Impiegato> list() throws Exception {
		
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		
		List<Impiegato> result = new ArrayList<>();
		Impiegato impiegatoTemp = null;
		
		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from impiegato")){
			
			while (rs.next()) {
				impiegatoTemp = new Impiegato();
				impiegatoTemp.setId(rs.getLong("id"));
				impiegatoTemp.setNome(rs.getString("nome"));
				impiegatoTemp.setCognome(rs.getString("cognome"));
				impiegatoTemp.setCodiceFiscale(rs.getString("codicefiscale"));
				impiegatoTemp.setDataNascita(rs.getDate("datanascita"));
				impiegatoTemp.setDataAssunzione(rs.getDate("dataassunzione"));
				
				
				Compagnia compagniaTemp = new Compagnia();
				compagniaTemp.setId(rs.getLong("id"));
				
				
				impiegatoTemp.setCompagnia(compagniaTemp);
				
				
				result.add(impiegatoTemp);
			}		
		}	catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
		
	}

	@Override
	public Impiegato get(Long idInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		
		if(idInput == 0) {
			throw new Exception("id non puo essere 0.");
		}
		
		Impiegato result = null;
		try (PreparedStatement ps = connection.prepareStatement("select * from impiegato where id=?")) {
			
			ps.setLong(1, idInput);
			
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = new Impiegato();
					result.setId(rs.getLong("id"));
					result.setNome(rs.getString("nome"));
					result.setCognome(rs.getString("cognome"));
					result.setCodiceFiscale(rs.getString("codicefiscale"));
					result.setDataNascita(rs.getDate("datanascita"));
					result.setDataAssunzione(rs.getDate("dataassunzione"));
					
					Compagnia compagniaTemp = new Compagnia();
					compagniaTemp.setId(rs.getLong("id"));
					
					result.setCompagnia(compagniaTemp);
				}
			}
		}   	catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;	
	}

	@Override
	public int update(Impiegato input) throws Exception {
		
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		
		if(input == null || input.getId() < 1) {
			throw new Exception("Errore inserimento dati da parte del cliente");
		}
		
		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("update impiegato set nome = ?,cognome = ?,codicefiscale = ? where id = ?")) {
			
			
			ps.setString(1, input.getNome());
			ps.setString(2, input.getCognome());
			ps.setString(3, input.getCodiceFiscale());
			ps.setLong(4, input.getId());
			
			result = ps.executeUpdate();
		
		}	catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;	
		
	}

	@Override
	public int insert(Impiegato input) throws Exception {
		if(input == null) {
			throw new Exception("Errore inserimento dati da parte del cliente");
		}
		
		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("insert into impiegato (nome,cognome,codicefiscale,datanascita,dataassunzione,compagnia_id) values(?,?,?,?,?,?)")){
			
			ps.setString(1, input.getNome());
			ps.setString(2, input.getCognome());
			ps.setString(3, input.getCodiceFiscale());
			ps.setDate(4, new java.sql.Date(input.getDataNascita().getTime()));
			ps.setDate(5, new java.sql.Date(input.getDataAssunzione().getTime()));
			ps.setLong(6, input.getCompagnia().getId());
			
			result = ps.executeUpdate();
		}	catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;	
	}

	@Override
	public int delete(Impiegato input) throws Exception {
		if(input.getId() < 1) {
			throw new Exception("Errore inserimento dati da parte del cliente");
		}
		
		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("delete from impiegato where id = ?")){
			ps.setLong(1, input.getId());
			
			result = ps.executeUpdate();
		}	catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;	
	}

	@Override
	public List<Impiegato> findByExample(Impiegato input) throws Exception {
		
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		
		if(input == null)
			throw new Exception("l'input iniziale non puo essere null");
		
		List<Impiegato> result = new ArrayList<>();
		Impiegato impiegatoTemp = null;
		
		String query = "select * from impiegato where 1 = 1  ";
		
		if(input.getNome() != null || input.getNome() != "") {
			query += " and nome like '" + input.getNome() + "%'";
		}
		
		if(input.getCognome() != null) {
			query += " and cognome like '" + input.getCognome() + "%'";
		}
		
		if(input.getCodiceFiscale() != null) {
			query += " and codicefiscale like '" + input.getCodiceFiscale() + "%'";
		}
		
		if(input.getDataNascita() != null) {
			query += " and datanascita = '" + new java.sql.Date(input.getDataNascita().getTime()) + "' ";
		}
		
		if(input.getDataAssunzione() != null) {
			query += " and dataassunzione = '" + new java.sql.Date(input.getDataAssunzione().getTime()) + "' ";
		}
		
		if(input.getCompagnia() != null) {
			query += " and compagnia_id = '" + input.getCompagnia().getId() + "' ";
		}
		
		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery(query);
		
			while (rs.next()) {
				impiegatoTemp = new Impiegato();
				impiegatoTemp.setId(rs.getLong("id"));
				impiegatoTemp.setNome(rs.getString("nome"));
				impiegatoTemp.setCognome(rs.getString("cognome"));
				impiegatoTemp.setCodiceFiscale(rs.getString("codicefiscale"));
				impiegatoTemp.setDataNascita(rs.getDate("datanascita"));
				impiegatoTemp.setDataAssunzione(rs.getDate("dataassunzione"));
				
				
				Compagnia compagniaTemp = new Compagnia();
				compagniaTemp.setId(rs.getLong("id"));
				
				
				impiegatoTemp.setCompagnia(compagniaTemp);
				
				
				result.add(impiegatoTemp);
			}
		}	catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;	
		
	}

	@Override
	public List<Impiegato> findAllByCompagnia(Compagnia compagniaInput) throws Exception {
		
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		
		if(compagniaInput == null || compagniaInput.getId() < 1) {
			throw new Exception("Compagnia non pu?? essere null.");
		}
		
		List<Impiegato> result = new ArrayList<>();
		Impiegato impiegatoTemp = null;
		Compagnia compagniaTemp = null;
		
		try (PreparedStatement ps = connection.prepareStatement("select * from impiegato i where compagnia_id = ?")){
			
			ps.setLong(1, compagniaInput.getId());
			
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					impiegatoTemp = new Impiegato();
					impiegatoTemp.setId(rs.getLong("id"));
					impiegatoTemp.setNome(rs.getString("nome"));
					impiegatoTemp.setCognome(rs.getString("cognome"));
					impiegatoTemp.setCodiceFiscale(rs.getString("codicefiscale"));
					impiegatoTemp.setDataNascita(rs.getDate("datanascita"));
					impiegatoTemp.setDataAssunzione(rs.getDate("dataassunzione"));
					
					compagniaTemp = new Compagnia();
					compagniaTemp.setId(rs.getLong("id"));
					
					
					impiegatoTemp.setCompagnia(compagniaTemp);
					
					result.add(impiegatoTemp);
				}
			}
		}	catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;	
	}

	@Override
	public int countByDataFondazioneCompagniaGreaterThan(Date dataInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		
		if(dataInput == null) {
			throw new Exception("DATA non pu?? essere null.");
		}
		
		int result = 0;
		Impiegato impiegatoTemp = null;
		Compagnia compagniaTemp = null;
		
		try (PreparedStatement ps = connection.prepareStatement("select i.* from impiegato i inner join compagnia c on i.compagnia_id = c.id where datafondazione > ?")){
			
			ps.setDate(1, new java.sql.Date(dataInput.getTime()));
			
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					
					result++;
				}
			}
		}	catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Impiegato> findAllByCompagniaConfatturatoMaggioreDi(int fatturatoInput) throws Exception {
		
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		
		if(fatturatoInput < 1) {
			throw new Exception("Compagnia non pu?? essere null.");
		}
		
		List<Impiegato> result = new ArrayList<>();
		Impiegato impiegatoTemp = null;
		Compagnia compagniaTemp = null;
		
		try (PreparedStatement ps = connection.prepareStatement("select i.* from impiegato i inner join compagnia c on i.compagnia_id = c.id where fatturatoannuo > ?")){
			
			ps.setInt(1, fatturatoInput);
			
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					impiegatoTemp = new Impiegato();
					impiegatoTemp.setId(rs.getLong("id"));
					impiegatoTemp.setNome(rs.getString("nome"));
					impiegatoTemp.setCognome(rs.getString("cognome"));
					impiegatoTemp.setCodiceFiscale(rs.getString("codicefiscale"));
					impiegatoTemp.setDataNascita(rs.getDate("datanascita"));
					impiegatoTemp.setDataAssunzione(rs.getDate("dataassunzione"));
					
					compagniaTemp = new Compagnia();
					compagniaTemp.setId(rs.getLong("id"));
					
					
					impiegatoTemp.setCompagnia(compagniaTemp);
					
					result.add(impiegatoTemp);
				}
			}
		}	catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;	
		
		
	}

	@Override
	public List<Impiegato> findAllErroriAssunzione() throws Exception {
		
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		
		List<Impiegato> result = new ArrayList<>();
		Impiegato impiegatoTemp = null;
		Compagnia compagniaTemp = null;
		
		try (PreparedStatement ps = connection.prepareStatement("select i.* from impiegato i inner join compagnia c on i.compagnia_id = c.id where dataassunzione < datafondazione")){
			
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					impiegatoTemp = new Impiegato();
					impiegatoTemp.setId(rs.getLong("id"));
					impiegatoTemp.setNome(rs.getString("nome"));
					impiegatoTemp.setCognome(rs.getString("cognome"));
					impiegatoTemp.setCodiceFiscale(rs.getString("codicefiscale"));
					impiegatoTemp.setDataNascita(rs.getDate("datanascita"));
					impiegatoTemp.setDataAssunzione(rs.getDate("dataassunzione"));
					
					compagniaTemp = new Compagnia();
					compagniaTemp.setId(rs.getLong("id"));
					
					
					impiegatoTemp.setCompagnia(compagniaTemp);
					
					result.add(impiegatoTemp);
				}
			}	
		}	catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	
	
}
