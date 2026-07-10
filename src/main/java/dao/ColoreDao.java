package dao;

import java.sql.SQLException;
import java.util.List;
import model.ColoreBean;

public interface ColoreDao {

    /**
     * Inserisce un nuovo colore nel catalogo.
     * Tabella coinvolta: colore
     * Utilizzo: Chiamato dalla Servlet dell'Admin quando crea un nuovo colore.
     */
    public void doSave(ColoreBean colore) throws SQLException;

    /**
     * Aggiorna un colore esistente (es. modifica del nome o del codice hex).
     * Tabella coinvolta: colore
     * Utilizzo: Chiamato dalla Servlet dell'Admin per correggere un colore.
     */
    public boolean doUpdate(ColoreBean colore) throws SQLException;

    /**
     * Elimina un colore dal database.
     * Tabella coinvolta: colore
     * N.B.: Grazie al vincolo ON DELETE CASCADE che hai saggiamente inserito in creaDatabase.sql 
     * sulla tabella prodottoColore, eliminando il colore spariranno in automatico anche 
     * le associazioni con i prodotti!
     * Utilizzo: Chiamato dalla Servlet dell'Admin.
     */
    public boolean doDelete(int id) throws SQLException;

    /**
     * Recupera un singolo colore tramite il suo ID.
     * Tabella coinvolta: colore
     * Utilizzo: Utile se l'Admin vuole modificare un colore e devi riempire i campi del form.
     */
    public ColoreBean doRetrieveByKey(int id) throws SQLException;

    /**
     * Recupera tutti i colori salvati nel database.
     * Tabella coinvolta: colore
     * Utilizzo: 
     * 1. Nella pagina Admin per mostrare la tabella di tutti i colori.
     * 2. Nel form di inserimento/modifica Prodotto per generare dinamicamente le checkbox dei colori selezionabili.
     */
    public List<ColoreBean> doRetrieveAll() throws SQLException;

    /**
     * Recupera SOLO i colori associati a uno specifico prodotto.
     * Tabelle coinvolte: colore JOIN prodottoColore
     * Utilizzo: Fondamentale per la pagina del Prodotto lato utente (Product Page). 
     * Quando un utente apre un prodotto, la Servlet chiamerà questo metodo passando 
     * l'ID del prodotto per mostrare solo le opzioni di colore effettivamente disponibili.
     */
    public List<ColoreBean> doRetrieveByProdotto(int idProdotto) throws SQLException;

}
