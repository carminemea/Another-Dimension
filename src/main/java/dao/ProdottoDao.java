package dao;

import java.sql.SQLException;
import java.util.Collection;
import model.ProdottoBean;

public interface ProdottoDao {
	
	public void doSave(ProdottoBean prodotto) throws SQLException;

	public boolean doDelete(int id) throws SQLException;
	
	public ProdottoBean doRetrieveByKey(int id) throws SQLException;
	
	public Collection<ProdottoBean> doRetrieveAll(String order) throws SQLException;
	
	public void doUpdate(ProdottoBean prodotto) throws SQLException;
	
	public Collection<ProdottoBean> doRetrieveByText(String text) throws SQLException;
	
}