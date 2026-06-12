package dao;

import java.sql.SQLException;
import java.util.Collection;
import model.ImmagineBean;

public interface ImmagineDao {
	
	public void doSave(ImmagineBean immagine) throws SQLException;
	
	public boolean doDelete(int id) throws SQLException;
	
	public Collection<ImmagineBean> doRetrieveByProdotto(int idProdotto) throws SQLException;
	
}