package dao;

import java.sql.SQLException;
import java.util.Collection;

import model.OrdineBean;

public interface OrdineDao {
	
	public void doSave(OrdineBean ordine) throws SQLException;
	
	public OrdineBean doRetrieveByKey(int id) throws SQLException;
	
	public Collection<OrdineBean> doRetrieveByUser(int idUtente) throws SQLException;
	
	public Collection<OrdineBean> doRetrieveAllFiltered(String startDate, String endDate, String utenteId) throws SQLException;

}