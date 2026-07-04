package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import model.ComposizioneOrdineBean;
import model.OrdineBean;

public class OrdineDaoImpl implements OrdineDao {
	
	private DataSource ds;
	
	public OrdineDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public synchronized void doSave(OrdineBean ordine) throws SQLException {
		Connection connection = null;
		
		try {
			connection = ds.getConnection();
			// per rendere l'aggiunta a ordine e composizioneOrdine atomica
			connection.setAutoCommit(false); 
			
			String insertOrdineSQL = "INSERT INTO ordine (data, totale, indirizzo, idUtente) VALUES (?, ?, ?, ?)";
			
			//RETURN_GENERATED_KEYS per l'ID appena creato
			try (PreparedStatement psOrdine = connection.prepareStatement(insertOrdineSQL, Statement.RETURN_GENERATED_KEYS)) {
				psOrdine.setDate(1, ordine.getData());
				psOrdine.setDouble(2, ordine.getTotale());
				psOrdine.setString(3, ordine.getIndirizzo());
				psOrdine.setInt(4, ordine.getIdUtente());
				
				psOrdine.executeUpdate();
				
				// Imposto l'ID dell'ordine appena salvato
				try (ResultSet rs = psOrdine.getGeneratedKeys()) {
					if (rs.next()) {
						ordine.setId(rs.getInt(1));
					}
				}
			}
			
			// sezione composizione ordine. Prodotti veri e propri
			String insertComposizioneSQL = "INSERT INTO composizioneOrdine (idOrdine, idProdotto, quantita, prezzoAcquisto, coloreScelto, testoPersonalizzato) VALUES (?, ?, ?, ?, ?, ?)";
			
			try (PreparedStatement psComp = connection.prepareStatement(insertComposizioneSQL)) {
				for (ComposizioneOrdineBean comp : ordine.getComposizioni()) {
					psComp.setInt(1, ordine.getId()); // ID dell'ordine appena generato
					psComp.setInt(2, comp.getIdProdotto());
					psComp.setInt(3, comp.getQuantita());
					psComp.setDouble(4, comp.getPrezzoAcquisto());
					psComp.setString(5, comp.getColoreScelto());
					psComp.setString(6, comp.getTestoPersonalizzato());
					
					psComp.executeUpdate();
				}
			}
			
			//eseguo (atomicamente)
			connection.commit(); 
			
		} catch (SQLException e) {
			if (connection != null) {
				connection.rollback(); 
			}
			throw e;
		} finally {
			if (connection != null) {
				// ripristino impostazioni base
				connection.setAutoCommit(true); 
				connection.close();
			}
		}
	}

	@Override
	public synchronized OrdineBean doRetrieveByKey(int id) throws SQLException {
		OrdineBean ordine = null;
		String selectOrdineSQL = "SELECT * FROM ordine WHERE id = ?";
		String selectComposizioniSQL = "SELECT * FROM composizioneOrdine WHERE idOrdine = ?";
		
		try (Connection connection = ds.getConnection()) {
			
			try (PreparedStatement ps = connection.prepareStatement(selectOrdineSQL)) {
				ps.setInt(1, id);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						ordine = new OrdineBean();
						ordine.setId(rs.getInt("id"));
						ordine.setData(rs.getDate("data"));
						ordine.setTotale(rs.getDouble("totale"));
						ordine.setIndirizzo(rs.getString("indirizzo"));
						ordine.setIdUtente(rs.getInt("idUtente"));
						ordine.setComposizioni(new ArrayList<>());
					}
				}
			}
			
			// composizioni associate
			if (ordine != null) {
				try (PreparedStatement ps = connection.prepareStatement(selectComposizioniSQL)) {
					ps.setInt(1, id);
					try (ResultSet rs = ps.executeQuery()) {
						while (rs.next()) {
							ComposizioneOrdineBean comp = new ComposizioneOrdineBean();
							comp.setId(rs.getInt("id"));
							comp.setIdOrdine(rs.getInt("idOrdine"));
							comp.setIdProdotto(rs.getInt("idProdotto"));
							comp.setQuantita(rs.getInt("quantita"));
							comp.setPrezzoAcquisto(rs.getDouble("prezzoAcquisto"));
							comp.setColoreScelto(rs.getString("coloreScelto"));
							comp.setTestoPersonalizzato(rs.getString("testoPersonalizzato"));
							
							ordine.addComposizione(comp);
						}
					}
				}
			}
		}
		return ordine;
	}

	@Override
	public synchronized Collection<OrdineBean> doRetrieveByUser(int idUtente) throws SQLException {
		List<OrdineBean> ordini = new ArrayList<>();
		String selectSQL = "SELECT * FROM ordine WHERE idUtente = ? ORDER BY data DESC";
		
		try (Connection connection = ds.getConnection();
			 PreparedStatement ps = connection.prepareStatement(selectSQL)) {
			
			ps.setInt(1, idUtente);
			
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					OrdineBean ordine = new OrdineBean();
					ordine.setId(rs.getInt("id"));
					ordine.setData(rs.getDate("data"));
					ordine.setTotale(rs.getDouble("totale"));
					ordine.setIndirizzo(rs.getString("indirizzo"));
					ordine.setIdUtente(rs.getInt("idUtente"));
					
					ordini.add(ordine);
				}
			}
		}
		return ordini;
	}

	@Override
	public synchronized Collection<OrdineBean> doRetrieveAllFiltered(String startDate, String endDate, String utenteId) throws SQLException {
		List<OrdineBean> ordini = new ArrayList<>();
		
		StringBuilder query = new StringBuilder("SELECT * FROM ordine WHERE true");
		
		if (startDate != null && !startDate.isEmpty()) query.append(" AND data >= ?");
		if (endDate != null && !endDate.isEmpty()) query.append(" AND data <= ?");
		if (utenteId != null && !utenteId.isEmpty()) query.append(" AND idUtente = ?");
		
		query.append(" ORDER BY data DESC");
		
		try (Connection connection = ds.getConnection();
			 PreparedStatement ps = connection.prepareStatement(query.toString())) {
			
			int paramIndex = 1;
			
			if (startDate != null && !startDate.isEmpty()) ps.setDate(paramIndex++, java.sql.Date.valueOf(startDate));
			if (endDate != null && !endDate.isEmpty()) ps.setDate(paramIndex++, java.sql.Date.valueOf(endDate));
			if (utenteId != null && !utenteId.isEmpty()) ps.setInt(paramIndex++, Integer.parseInt(utenteId));
			
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					OrdineBean ordine = new OrdineBean();
					ordine.setId(rs.getInt("id"));
					ordine.setData(rs.getDate("data"));
					ordine.setTotale(rs.getDouble("totale"));
					ordine.setIndirizzo(rs.getString("indirizzo"));
					ordine.setIdUtente(rs.getInt("idUtente"));
					
					ordini.add(ordine);
				}
			}
		}
		return ordini;
	}
}