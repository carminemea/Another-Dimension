package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import model.ImmagineBean;

public class ImmagineDaoImpl implements ImmagineDao {
	
	private DataSource ds = null;
	
	public ImmagineDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public synchronized void doSave(ImmagineBean immagine) throws SQLException {
		String insertSQL = "INSERT INTO immagine (path, mimeType, idProdotto) VALUES (?, ?, ?)";
		
		try (Connection connection = ds.getConnection();
			 PreparedStatement ps = connection.prepareStatement(insertSQL)) {
			
			ps.setString(1, immagine.getPath());
			ps.setString(2, immagine.getMimeType());
			ps.setInt(3, immagine.getIdProdotto());
			
			ps.executeUpdate();
		}
	}

	@Override
	public synchronized boolean doDelete(int id) throws SQLException {
		String deleteSQL = "DELETE FROM immagine WHERE id = ?";
		int result = 0;
		
		try (Connection connection = ds.getConnection();
			 PreparedStatement ps = connection.prepareStatement(deleteSQL)) {
			
			ps.setInt(1, id);
			result = ps.executeUpdate();
		}
		return (result != 0);
	}

	@Override
	public synchronized Collection<ImmagineBean> doRetrieveByProdotto(int idProdotto) throws SQLException {
		List<ImmagineBean> immagini = new ArrayList<>();
		String selectSQL = "SELECT * FROM immagine WHERE idProdotto = ?";
		
		try (Connection connection = ds.getConnection();
			 PreparedStatement ps = connection.prepareStatement(selectSQL)) {
			
			ps.setInt(1, idProdotto);
			
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					ImmagineBean bean = new ImmagineBean();
					bean.setId(rs.getInt("id"));
					bean.setPath(rs.getString("path"));
					bean.setMimeType(rs.getString("mimeType"));
					bean.setIdProdotto(rs.getInt("idProdotto"));
					
					immagini.add(bean);
				}
			}
		}
		return immagini;
	}

	@Override
	public ImmagineBean doRetrieveByKey(int id) throws SQLException {
		String selectSQL = "SELECT * FROM immagine WHERE id = ?";
		ImmagineBean bean = null;
		
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(selectSQL)) {
			
			ps.setInt(1, id);
			
			try(ResultSet rs = ps.executeQuery()) {
				if(rs.next()) {
					bean = new ImmagineBean();
					bean.setId(rs.getInt("id"));
					bean.setPath(rs.getString("path"));
					bean.setMimeType(rs.getString("mimeType"));
					bean.setIdProdotto(rs.getInt("idProdotto"));
				}
			}
		}
		
		return bean;
	}
	
}

