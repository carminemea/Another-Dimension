package dao;

import java.sql.SQLException;
import java.util.Collection;
import model.UtenteBean;

public interface UtenteDao {
	
    public void doSave(UtenteBean utente) throws SQLException;
    
    public UtenteBean doRetrieveByKey(int id) throws SQLException;
    
    public boolean doDelete(int id) throws SQLException;
    
    public UtenteBean doRetrieveByEmailAndPassword(String email, String password) throws SQLException;
    
    public void doUpdate(UtenteBean utente) throws SQLException;
    
    public Collection<UtenteBean> doRetrieveAll(String order) throws SQLException;
}
