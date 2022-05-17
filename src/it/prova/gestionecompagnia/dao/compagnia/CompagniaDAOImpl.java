package it.prova.gestionecompagnia.dao.compagnia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.prova.gestionecompagnia.connection.MyConnection;
import it.prova.gestionecompagnia.dao.AbstractMySQLDAO;
import it.prova.gestionecompagnia.model.Compagnia;

public class CompagniaDAOImpl extends AbstractMySQLDAO implements CompagniaDAO {
	
	public CompagniaDAOImpl(Connection connection) {
		super(connection);
	}

	@Override
	public List<Compagnia> list() throws Exception {
		
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		
		List<Compagnia> result = new ArrayList<>();
		Compagnia compagniaTemp = null;
		
		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from compagnia")){
			
			while (rs.next()) {
				compagniaTemp = new Compagnia();
				compagniaTemp.setId(rs.getLong("id"));
				compagniaTemp.setRagioneSociale(rs.getString("ragionesociale"));
				compagniaTemp.setFatturatoAnnuo(rs.getInt("fatturatoannuo"));
				compagniaTemp.setDataFondazione(rs.getDate("datafondazione"));
				
				result.add(compagniaTemp);
			}		
		}	catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	

	@Override
	public Compagnia get(Long idInput) throws Exception {
		
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		
		if(idInput == 0) {
			throw new Exception("id non puo essere 0.");
		}
		
		Compagnia result = null;
		try (PreparedStatement ps = connection.prepareStatement("select * from compagnia where id=?")) {
			
			ps.setLong(1, idInput);
			
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = new Compagnia();
					result.setId(rs.getLong("id"));
					result.setRagioneSociale(rs.getString("ragionesociale"));
					result.setFatturatoAnnuo(rs.getInt("fatturatoannuo"));
					result.setDataFondazione(rs.getDate("datafondazione"));
					
				}
			}
		}   	catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;	
	}

	@Override
	public int update(Compagnia input) throws Exception {
		
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		
		if(input == null || input.getId() < 1) {
			throw new Exception("Errore inserimento dati da parte del cliente");
		}
		
		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("update compagnia set ragionesociale = ?,fatturatoannuo = ?,datafondazione = ? where id = ?")) {
			
			
			ps.setString(1, input.getRagioneSociale());
			ps.setInt(2, input.getFatturatoAnnuo());
			ps.setDate(3, new java.sql.Date(input.getDataFondazione().getTime()));
			ps.setLong(4, input.getId());
			
			result = ps.executeUpdate();
		
		}	catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;	
	}

	@Override
	public int insert(Compagnia input) throws Exception {
		if(input == null) {
			throw new Exception("Errore inserimento dati da parte del cliente");
		}
		
		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("insert into compagnia (ragionesociale,fatturatoannuo,datafondazione) values(?,?,?)")){
			
			ps.setString(1, input.getRagioneSociale());
			ps.setInt(2, input.getFatturatoAnnuo());
			ps.setDate(3, new java.sql.Date(input.getDataFondazione().getTime()));
			
			result = ps.executeUpdate();
		}	catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;	
	}

	@Override
	public int delete(Compagnia input) throws Exception {
		
		if(input.getId() < 1) {
			throw new Exception("Errore inserimento dati da parte del cliente");
		}
		
		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("delete from compagnia where id = ?")){
			ps.setLong(1, input.getId());
			
			result = ps.executeUpdate();
		}	catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;	
	}
	
	
	@Override
	public List<Compagnia> findByExample(Compagnia input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		
		if(input == null)
			throw new Exception("l'input iniziale non puo essere null");
		
		List<Compagnia> result = new ArrayList<>();
		Compagnia compagniaTemp = null;
		
		String query = "select * from compagnia where 1 = 1  ";
		
		if(input.getRagioneSociale() != null || input.getRagioneSociale() != "") {
			query += " and ragionesociale like '" + input.getRagioneSociale() + "%'";
		}
		
		if(input.getFatturatoAnnuo() != 0) {
			query += " and fatturatoannuo = '" + input.getFatturatoAnnuo() + "'";
		}
		
		if(input.getDataFondazione() != null) {
			query += " and datafondazione = '" + new java.sql.Date(input.getDataFondazione().getTime()) + "' ";
		}
		
		//query += ";";
		
		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery(query);
		
			while (rs.next()) {
				compagniaTemp = new Compagnia();
				compagniaTemp.setId(rs.getLong("id"));
				compagniaTemp.setRagioneSociale(rs.getString("ragionesociale"));
				compagniaTemp.setFatturatoAnnuo(rs.getInt("fatturatoannuo"));
				compagniaTemp.setDataFondazione(rs.getDate("datafondazione"));
				
				result.add(compagniaTemp);
			}
		}	catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;	
	}

	@Override
	public List<Compagnia> findAllByDataAssunzioneMaggioreDi(Date dataInput) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compagnia> findAllByRagioneSocialeContiene(String fraseInput) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compagnia> findAllByCodiceFiscaleContiene(String fraseInput) {
		// TODO Auto-generated method stub
		return null;
	}

}
