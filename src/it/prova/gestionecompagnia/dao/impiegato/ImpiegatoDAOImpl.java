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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Impiegato input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Impiegato input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Impiegato> findByExample(Impiegato input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Impiegato> findAllByCompagnia(Compagnia compagniaInput) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Impiegato> countByDataFondazioneCompagniaGreaterThan(Date dataInput) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Impiegato> findAllByCompagniaConfatturatoMaggioreDi(int fatturatoInput) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Impiegato> findAllErroriAssunzione() {
		// TODO Auto-generated method stub
		return null;
	}

}
