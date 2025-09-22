package dao;

import model.*;
import java.util.List;

/**
 * L'interfaccia PostgresDAO contiene tutte le firme ai metodi che consentono di recuperare i dati dal database PostegreSQL.
 */
public interface PostgresDAO {

    // INTERFACCE SUPERUTENTE
    /**
     *  Interfaccia per recuperare un utente conoscendone le credenziali.
     *
     * @param username l'username utilizzato per accedere
     * @param password la password utilizzata per accedere
     * @return l'utente richiesto se le credenziali sono valide, altrimenti null.
     */
    Superutente getUtenteByCredentials(String username, String password);

    /**
     * Interfaccia che permette di inserire nel database un nuovo utente.
     * @param utenteGenerico il nuovo utente
     * @return true se l'utente è stato inserito, false altrimenti.
     */
    boolean insertUtenteGenerico (UtenteGenerico utenteGenerico);

    /**
     * Interfaccia per recuperare tutti i voli presenti nel database.
     * @return l'elenco dei voli
     */
    List<Volo> getElencoVoli();


    // INTERFACCE AMMINISTRATORE
    /**
     * Interfaccia che permette di inserire nuovi voli al database.
     * @return true se l'inserimento è avvenuto con successo, false altrimenti.
     */
    boolean insertVolo(Volo volo);

    /**
     * Interfaccia che permette di aggiornare le informazioni di un volo.
     * @return true se la modifica è avvenuta con successo, false altrimenti.
     */
    boolean updateVolo(Volo volo);

    /**
     * Interfaccia che permette di assegnare un nuovo gate, solo per i voli in partenza
     * @return true se l'assegnazione ha avuto successo, false altrimenti.
     */
    boolean modificaGate(int nuovogate, int codiceVolo);


    // INTERFACCE UTENTE GENERICO

    /**
     * Metodo che permette di aggiungere una prenotazione al database tramite l'id del volo.
     * @param CodiceVolo il codice del volo che si intende prenotare
     * @param nomePasseggero il nome del passeggero associato alla prenotazione
     * @param postoScelto il posto scelto sull'aereo
     * @return true se l'operazione è andata a buon fine, false altrimenti
     */
    boolean prenotaVolo(int CodiceVolo, String nomePasseggero, int postoScelto);

    /**
     * Interfaccia che permette di recuperare tutte le prenotazioni associate a un utente.
     * @param utenteGenerico l'utente generico di cui si voglio recuperare le prenotazioni.
     * @return l'elenco delle prenotazioni
     */
    List<Prenotazione> getPrenotazioniByUtente(UtenteGenerico utenteGenerico);

    /**
     * Interfaccia che permette di modificare una prenotazione.
     * @param idPrenotazione la prenotazione che si vuole modificare
     * @return true se la modifica è avvenuta con successo, false altrimenti
     */
    boolean aggiornaPrenotazione(int idPrenotazione, StatoPrenotazione nuovoStato);

    /**
     * Interfaccia che permette di recuperare la prenotazione conoscendo l'id del volo.
     * @param codiceVolo l'id del volo richiesta
     * @return la prenotazione associata al volo.
     */
    List<Prenotazione> getPrenotazioniByIdVolo(int codiceVolo, int idUtente);

    /**
     * Interfaccia che permette di recuperare una prenotazione tramite i dati di un passeggero.
     * @param nomePasseggero il nome del passeggero
     * @return la prenotazione associata al passeggero.
     */
    List<Prenotazione> getPrenotazioniByPasseggero(String nomePasseggero, int idUtente);

}