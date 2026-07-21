package dao;

import java.sql.SQLException;
import java.util.List;
import model.ColoreBean;

public interface ColoreDao {

    public void doSave(ColoreBean colore) throws SQLException;

    public boolean doUpdate(ColoreBean colore) throws SQLException;

    public boolean doDelete(int id) throws SQLException;

    public ColoreBean doRetrieveByKey(int id) throws SQLException;

    public List<ColoreBean> doRetrieveAll() throws SQLException;

    public List<ColoreBean> doRetrieveByProdotto(int idProdotto) throws SQLException;

}
