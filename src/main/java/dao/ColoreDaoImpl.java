package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.ColoreBean;

public class ColoreDaoImpl implements ColoreDao {
	
	private DataSource ds = null;
	
	public ColoreDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public void doSave(ColoreBean colore) throws SQLException {
		String insertSQL = "INSERT INTO colore (nome, codiceHex) VALUES (?, ?)";
		
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(insertSQL, java.sql.Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, colore.getNome());
			ps.setString(2, colore.getCodiceHex());
			
			ps.executeUpdate();
			
			try(ResultSet rs = ps.getGeneratedKeys()) {
				if(rs.next())
					colore.setId(rs.getInt(1));
			}
		}
	}

	@Override
	public boolean doUpdate(ColoreBean colore) throws SQLException {
		String updateSQL = "UPDATE colore SET nome = ?, codiceHex = ? WHERE id = ?";
		
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(updateSQL)) {
			ps.setString(1, colore.getNome());
			ps.setString(2, colore.getCodiceHex());
			ps.setInt(3, colore.getId());
			
			return (ps.executeUpdate() > 0);
		}
	}

	@Override
	public boolean doDelete(int id) throws SQLException {
		String deleteSQL = "DELETE FROM colore WHERE id = ?"; //il resto gestito da ON DELETE CASCADE
		
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(deleteSQL)) {
			ps.setInt(1, id);
			
			return (ps.executeUpdate() > 0);
		}
	}

	@Override
	public ColoreBean doRetrieveByKey(int id) throws SQLException {
		String selectSQL = "SELECT id, nome, codiceHex FROM colore WHERE id = ?";
		ColoreBean colore = null;
		
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(selectSQL)) {
			
			ps.setInt(1, id);
			
			try(ResultSet rs = ps.executeQuery()) {
				if(rs.next()) {
					colore = new ColoreBean();
					colore.setId(rs.getInt("id"));
					colore.setNome(rs.getString("nome"));
					colore.setCodiceHex(rs.getString("codiceHex"));
				}
			}
		}
		return colore;
	}

	@Override
	public List<ColoreBean> doRetrieveAll() throws SQLException {
		String selectSQL = "SELECT id, nome, codiceHex FROM colore";
		List<ColoreBean> colori = new ArrayList<>();

		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(selectSQL);
				ResultSet rs = ps.executeQuery()) {
			while(rs.next()) {
				ColoreBean colore = new ColoreBean();
				colore.setId(rs.getInt("id"));
				colore.setNome(rs.getString("nome"));
				colore.setCodiceHex(rs.getString("codiceHex"));
				colori.add(colore);
			}
		}
		return colori;
	}

	@Override
	public List<ColoreBean> doRetrieveByProdotto(int idProdotto) throws SQLException {
		String selectSQL = "SELECT c.id, c.nome, c.codiceHex " +
				"FROM colore c JOIN prodottoColore pc ON c.id = pc.idColore " +
				"WHERE pc.idProdotto = ?";
		List<ColoreBean> colori = new ArrayList<>();
		
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(selectSQL)) {
				
				ps.setInt(1, idProdotto);
				
				try(ResultSet rs = ps.executeQuery()) {
					while(rs.next()) {
						ColoreBean colore = new ColoreBean();
						colore.setId(rs.getInt("id"));
						colore.setNome(rs.getString("nome"));
						colore.setCodiceHex(rs.getString("codiceHex"));
						colori.add(colore);
					}
				}
			}
			return colori;
	}
	
}
