package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import model.UtenteBean;

public class UtenteDaoImpl implements UtenteDao {
	
	DataSource ds = null;
	
	public UtenteDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public synchronized void doSave(UtenteBean utente) throws SQLException {
		String insertSQL = "INSERT INTO Utente (nome, cognome, email, password, ruolo)"
				+ "VALUES (?,?,?,?,?)";
		
		try(Connection c = ds.getConnection();
				PreparedStatement ps = c.prepareStatement(insertSQL)) {
			ps.setString(1, utente.getNome());
			ps.setString(2, utente.getCognome());
			ps.setString(3, utente.getEmail());
			ps.setString(4, utente.getPassword());
			ps.setString(5, utente.getRuolo());
			ps.executeUpdate();
		}
		
	}

	@Override
	public synchronized UtenteBean doRetrieveByKey(int id) throws SQLException {
		String retrieveSQL = "SELECT * FROM utente WHERE id = ?";
		
		UtenteBean utente = null; 
		
		try(Connection c = ds.getConnection();
			PreparedStatement ps = c.prepareStatement(retrieveSQL)) {
			
			ps.setInt(1, id);
			
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) { 
					utente = new UtenteBean();
					utente.setId(rs.getInt("id"));
					utente.setNome(rs.getString("nome"));
					utente.setCognome(rs.getString("cognome"));
					utente.setEmail(rs.getString("email"));
					utente.setPassword(rs.getString("password"));
					utente.setRuolo(rs.getString("ruolo"));
				}
			}
		}
		
		return utente;
	}

	@Override
	public synchronized boolean doDelete(int id) throws SQLException {
		String deleteSQL = "DELETE FROM utente WHERE id = ?";
		int result = 0;
		
		try(Connection c = ds.getConnection();
				PreparedStatement ps = c.prepareStatement(deleteSQL)) {
			
			ps.setInt(1, id);
			ps.executeUpdate();
		}
		return (result != 0);
	}

	@Override
	public synchronized UtenteBean doRetrieveByEmailAndPassword(String email, String password) throws SQLException {
		String retrieveSQL = "SELECT * FROM utente WHERE (email = ? AND password = ?)";
		
		UtenteBean utente = null; 
		
		try(Connection c = ds.getConnection();
			PreparedStatement ps = c.prepareStatement(retrieveSQL)) {
			
			ps.setString(1, email);
			ps.setString(2, password);
			
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) { 
					utente = new UtenteBean();
					utente.setId(rs.getInt("id"));
					utente.setNome(rs.getString("nome"));
					utente.setCognome(rs.getString("cognome"));
					utente.setEmail(rs.getString("email"));
					utente.setPassword(rs.getString("password"));
					utente.setRuolo(rs.getString("ruolo"));
				}
			}
		}
		
		return utente;
	}

	@Override
	public synchronized void doUpdate(UtenteBean utente) throws SQLException {
		String updateSQL = "UPDATE utente SET nome = ?, cognome = ?, email = ?, password = ?, ruolo = ? WHERE id = ?";
		
		try (Connection c = ds.getConnection();
			 PreparedStatement ps = c.prepareStatement(updateSQL)) {
			
			ps.setString(1, utente.getNome());
			ps.setString(2, utente.getCognome());
			ps.setString(3, utente.getEmail());
			ps.setString(4, utente.getPassword());
			ps.setString(5, utente.getRuolo());
			
			ps.setInt(6, utente.getId());
			
			ps.executeUpdate();
		}
	}

	@Override
	public Collection<UtenteBean> doRetrieveAll(String order) throws SQLException {
		List<UtenteBean> utenti = new ArrayList<>();
		String retrieveAllSQL = "SELECT * FROM utente";
		
		if (order != null && !order.isEmpty()) {
			if (order.equals("id") || order.equals("nome") || order.equals("cognome") || order.equals("email") || order.equals("ruolo")) {
				retrieveAllSQL += " ORDER BY " + order;
			}
		}
		
		try(Connection c = ds.getConnection();
				PreparedStatement ps = c.prepareStatement(retrieveAllSQL);
				ResultSet rs = ps.executeQuery()) {
			
					while (rs.next()) { 
						UtenteBean utente = new UtenteBean();
						utente.setId(rs.getInt("id"));
						utente.setNome(rs.getString("nome"));
						utente.setCognome(rs.getString("cognome"));
						utente.setEmail(rs.getString("email"));
						utente.setPassword(rs.getString("password"));
						utente.setRuolo(rs.getString("ruolo"));
						
						utenti.add(utente);
					}
				}
			
			return utenti;
	}
	
}
