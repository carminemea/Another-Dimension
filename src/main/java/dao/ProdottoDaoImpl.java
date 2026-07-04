package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import model.ProdottoBean;

public class ProdottoDaoImpl implements ProdottoDao {
	
	private DataSource ds = null;
	
	public ProdottoDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public synchronized void doSave(ProdottoBean prodotto) throws SQLException {
		String insertSQL = "INSERT INTO prodotto (nome, descrizione, prezzo, disponibile, testoPersonalizzabile) VALUES (?, ?, ?, ?, ?)";
		
		try (Connection connection = ds.getConnection();
			 PreparedStatement ps = connection.prepareStatement(insertSQL, java.sql.Statement.RETURN_GENERATED_KEYS)) {
			
			ps.setString(1, prodotto.getNome());
			ps.setString(2, prodotto.getDescrizione());
			ps.setDouble(3, prodotto.getPrezzo());
			ps.setBoolean(4, prodotto.isDisponibile());
			ps.setBoolean(5, prodotto.isTestoPersonalizzabile());
			
			ps.executeUpdate();
			
			// Recuperiamo l'ID autogenerato dal Database e lo impostiamo nel Bean (per usarlo nella chiave esterna delle immagini)
			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					prodotto.setId(rs.getInt(1));
				}
			}
		}
	}

	@Override
	public synchronized boolean doDelete(int id) throws SQLException {
		//Soft Delete per fare in modo che se un prodotto viene eliminato, questo non scompare dagli storici
		String deleteSQL = "UPDATE prodotto SET disponibile = false WHERE id = ?";
		int result = 0;
		
		try (Connection connection = ds.getConnection();
			 PreparedStatement ps = connection.prepareStatement(deleteSQL)) {
			
			ps.setInt(1, id);
			ps.executeUpdate();
		}
		return (result != 0);
	}

	@Override
	public synchronized ProdottoBean doRetrieveByKey(int id) throws SQLException {
		String selectSQL = "SELECT * FROM prodotto WHERE id = ?";
		ProdottoBean bean = null;
		
		try (Connection connection = ds.getConnection();
			 PreparedStatement ps = connection.prepareStatement(selectSQL)) {
			
			ps.setInt(1, id);
			
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					bean = new ProdottoBean();
					bean.setId(rs.getInt("id"));
					bean.setNome(rs.getString("nome"));
					bean.setDescrizione(rs.getString("descrizione"));
					bean.setPrezzo(rs.getDouble("prezzo"));
					bean.setDisponibile(rs.getBoolean("disponibile"));
					bean.setTestoPersonalizzabile(rs.getBoolean("testoPersonalizzabile"));
				}
			}
		}
		return bean;
	}

	@Override
	public synchronized Collection<ProdottoBean> doRetrieveAll(String order) throws SQLException {
		List<ProdottoBean> products = new ArrayList<>();
		String selectSQL = "SELECT * FROM prodotto";
		
		// WHITELIST per evitare SQL Injection nell'ORDER BY
		if (order != null && !order.isEmpty()) {
			if (order.equals("nome") || order.equals("prezzo") || order.equals("id")) {
				selectSQL += " ORDER BY " + order;
			}
		}
		
		try (Connection connection = ds.getConnection();
			 PreparedStatement ps = connection.prepareStatement(selectSQL);
			 ResultSet rs = ps.executeQuery()) {
			
			while (rs.next()) {
				ProdottoBean bean = new ProdottoBean();
				bean.setId(rs.getInt("id"));
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setDisponibile(rs.getBoolean("disponibile"));
				bean.setTestoPersonalizzabile(rs.getBoolean("testoPersonalizzabile"));
				
				products.add(bean);
			}
		}
		return products;
	}

	@Override
	public synchronized void doUpdate(ProdottoBean prodotto) throws SQLException {
		String updateSQL = "UPDATE prodotto SET nome = ?, descrizione = ?, prezzo = ?, disponibile = ?, testoPersonalizzabile = ? WHERE id = ?";
		
		try (Connection connection = ds.getConnection();
			 PreparedStatement ps = connection.prepareStatement(updateSQL)) {
			
			ps.setString(1, prodotto.getNome());
			ps.setString(2, prodotto.getDescrizione());
			ps.setDouble(3, prodotto.getPrezzo());
			ps.setBoolean(4, prodotto.isDisponibile());
			ps.setBoolean(5, prodotto.isTestoPersonalizzabile());
			
			ps.setInt(6, prodotto.getId());
			
			ps.executeUpdate();
		}
	}
}