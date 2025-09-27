package controller;

import dao.PostgresDAO;
import implementazionePostgresDAO.ImplementazionePostgresDAO;
import model.*;

import java.util.List;

/**
 * La classe Controller gestisce la logica aeroportuale dell'applicazione.
 * Essa permette la comunicazione tra l'interfaccia grafica e il database.
 */
public class Controller {

    /**
     * Riferimento all'oggetto DAO. La classe DAO permette l'accesso al database.
     */
    private final PostgresDAO dao;

    /**
     * Costruttore della classe controller viene inizializzato con zero parametri.
     * Viene tuttavia costruito creando una istanza della classe ImplementazionePostgresDAO che contiene tutti i metodi
     * per accedere al database.
     */
    public Controller(){
        this.dao = new ImplementazionePostgresDAO();
    }

    // METODI DISPONIBILI SIA AGLI AMMINISTRATORI CHE AGLI UTENTI GENERICI
    /**
     * Metodo di login che permette di effettuare il login nel sistema utilizzando le credenziali.
     * @param username l'username con il quale si è effettuata la registrazione
     * @param password la password associata all'username
     * @return il login dell'utente che può essere amministratore o utente generico.
     * La logica che permette la distinzione dei ruoli è implementata nel metodo: {@link ImplementazionePostgresDAO#getUtenteByCredentials(String, String)}
     */
    public Superutente login(String username, String password){
        Superutente utenteLoggato = dao.getUtenteByCredentials(username, password);
        return utenteLoggato;
    }

    /**
     * Metodo che permette di recuperare tutti i voli presenti nel database.
     * @return la lista dei voli.
     * La logica viene gestita dal metodo: {@link implementazionePostgresDAO.ImplementazionePostgresDAO#getElencoVoli()}
     */
    public List<Volo> getTuttiVoli(){
        return dao.getElencoVoli();
    }

    // METODO DISPONIBILI SOLO AGLI AMMINISTRATORI
    /**
     * Metodo che permette di inserire un nuovo volo nel database.
     * @param volo il volo da aggiungere al database con le sue specifiche
     * @return true se l'inserimento è andato a buon fine, altrimenti false.
     * La logica viene gestita dal metodo: {@link implementazionePostgresDAO.ImplementazionePostgresDAO#insertVolo(Volo)} 
     */
    public boolean voloInserito(Volo volo){
        return dao.insertVolo(volo);
    }

    /**
     * Metodo che permette di modificare un volo già presente nel database.
     * @param volo il volo che si intende modificare
     * @return true se la modifica è andata a buon fine, false altrimenti.
     * La logica viene gestita dal metodo: {@link implementazionePostgresDAO.ImplementazionePostgresDAO#updateVolo(Volo)}
     */
    public boolean voloModificato(Volo volo){
        return dao.updateVolo(volo);
    }

    /**
     * Metodo che permette di modificare il gate a un volo in partenza da Napoli.
     * @param nuovogate il numero del gate che si vuole assegnare al volo
     * @param codicevolo il codice del volo permette di identificare univocamente il volo
     * @return true se la modifica è andata a buon fine, false altrimenti.
     * La logica viene gestita dal metodo: {@link implementazionePostgresDAO.ImplementazionePostgresDAO#modificaGate(int, int)} 
     */
    public boolean gateModificato(int nuovogate, int codicevolo){
        return dao.modificaGate(nuovogate, codicevolo);
    }

    // METODI DISPONIBILI SOLO AGLI UTENTI GENERICI
    /**
     * Metodo che permette a utente di registrarsi nel sistema.
     * @param utenteGenerico l'oggetto utente che si vuole aggiungere al database
     * @return true se la registrazione è andata a buon fine, false altrimenti.
     * La logica viene gestita dal metodo: {@link implementazionePostgresDAO.ImplementazionePostgresDAO#insertUtenteGenerico(UtenteGenerico)}
     */
    public boolean utenteRegistrato(UtenteGenerico utenteGenerico){
        return dao.insertUtenteGenerico(utenteGenerico);
    }

    /**
     * Metodo che permette di prenotare un volo. Ciò consente di creare una prenotazione univoca associata al volo desiderato.
     * @param codiceVolo il codice del volo identifica il volo che si intende prenotare
     * @param nomePasseggero nome e cognome del passeggero associati alla prenotazione
     * @param postoAssegnato il posto che si intende occupare sull'aereo
     * @return true se il volo è stato prenotato con successo, false altrimenti.
     * La logica viene gestita dal metodo: {@link implementazionePostgresDAO.ImplementazionePostgresDAO#prenotaVolo(int, String, int)}
     */
    public boolean voloPrenotato(int codiceVolo, String nomePasseggero, int postoAssegnato){
        return dao.prenotaVolo(codiceVolo, nomePasseggero, postoAssegnato);
    }

    /**
     * Metodo che permette di recuperare le prenotazioni associati a un utente registrato nel sistema.
     * @param utenteGenerico l'oggetto utente generico di cui si voglioni recuperare le informazioni
     * @return la lista di tutte le prenotazioni collegate all'utente passato come argomento.
     * La logica viene gestita dal metodo: {@link implementazionePostgresDAO.ImplementazionePostgresDAO#getPrenotazioniByUtente(UtenteGenerico)} 
     */
    public List<Prenotazione> getTuttePrenotazioni(UtenteGenerico utenteGenerico){
        return dao.getPrenotazioniByUtente(utenteGenerico);

    }
    /**
     * Metodo che permette di modifica lo stato di una prenotazione.
     * @param idPrenotazione l'id permette di recuperare la prenotazione che gli è associata
     * @param nuovostato confermare o cancellare una prenotazione.
     * @return true se l'aggiornamento è andato a buon fine, false altrimenti.
     * La logica viene gestita dal metodo: {@link implementazionePostgresDAO.ImplementazionePostgresDAO#aggiornaPrenotazione(int, StatoPrenotazione)} 
     */
    public boolean prenotazioneAggiornata(int idPrenotazione, StatoPrenotazione nuovostato){
        return dao.aggiornaPrenotazione(idPrenotazione, nuovostato);
    }

    /**
     * Metodo che permette di recuperare tutte le prenotazioni di utente specifico associate a un volo.
     * @param codicevolo il codice del volo per filtrare la ricerca
     * @param idUtente l'identificato dell'utente che ha effettua la prenotazione
     * @return la lista delle prenotazioni filtrate tramite il codice del volo passato come argomento.
     * La logica viene gestita dal metodo: {@link implementazionePostgresDAO.ImplementazionePostgresDAO#getPrenotazioniByIdVolo(int, int)} 
     */
    public List<Prenotazione> getTuttePrenotazioniByIdVolo(int codicevolo, int idUtente){
        return dao.getPrenotazioniByIdVolo(codicevolo, idUtente);
    }

    /**
     * Metodo che permette di recuperare tutte le prenotazioni di un utente specifico associate a un passeggero.
     * @param nomeCognPasseggero nome e cognome del passeggero per filtrare la ricerca
     * @param idUtente l'identificato dell'utente che ha effettuato la prenotazione
     * @return la liste delle prenotazioni filtrate tramite nome e cognome del passeggero.
     * La logica viene gestita dal metodo: {@link implementazionePostgresDAO.ImplementazionePostgresDAO#getPrenotazioniByPasseggero(String, int)} 
     */
    public List<Prenotazione> getTuttePrenotazioniByPasseggero(String nomeCognPasseggero, int idUtente){
        return dao.getPrenotazioniByPasseggero(nomeCognPasseggero, idUtente);
    }

}
