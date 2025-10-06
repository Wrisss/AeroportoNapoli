package model;

/**
 * La classe Prenotazione permette la gestione da parte degli utenti di prenotare i voli a cui sono interessati.
 * Ogni Utente può prenotare voli sia per sé che per altri.
 */
public class Prenotazione {
    /**
     * Il codice univoco di una prenotazione.
     */
    private final int idPrenotazione;

    /**
     * Il codice del volo collegato alla prenotazione.
     */
    private final int idVolo;

    /**
     * Il codice dell'utente che ha effettuato la prenotazione.
     */
    private final int idUtente;

    /**
     * Nome e cognome associato alla prenotazione.
     */
    private Passeggero passeggero;

    /**
     * Il codice identificato di ogni biglietto associato alla prenotazione.
     */
    private String numeroBiglietto;

    /**
     * Il posto assegnato sull'aereo. Capienza di ogni aereo: MAX 200 persone.
     */
    private int postoAssegnato;

    /**
     * Lo stato di una prenotazione. Ogni prenotazione appena creata presenta come valore di default: 'IN_ATTESA'.
     */
    private StatoPrenotazione statoPrenotazione;

    /**
     * Costruttore della classe Prenotazione. Richiede 7 argomenti.
     * @param idPrenotazione il codice univoco associato a una prenotazione
     * @param idVolo il codice del volo associato alla prenotazione
     * @param idUtente il codice dell'utente associato alla prenotazione
     * @param passeggero nome e cognome del passeggero.
     * @param numeroBiglietto il numero del biglietto associato alla prenotazione.
     * @param postoAssegnato il posto assegnato sull'aereo.
     * @param statoPrenotazione lo stato in cui si trova la prenotazione.
     */
    public Prenotazione(int idPrenotazione, int idVolo, int idUtente, Passeggero passeggero,
                        String numeroBiglietto, int postoAssegnato, StatoPrenotazione statoPrenotazione){
        this.idPrenotazione = idPrenotazione;
        this.idVolo = idVolo;
        this.idUtente = idUtente;
        this.passeggero = passeggero;
        this.numeroBiglietto = numeroBiglietto;
        this.postoAssegnato = postoAssegnato;
        this.statoPrenotazione = statoPrenotazione;
    }

    // GETTERS AND SETTERS

    /**
     * Metodo Getter per recuperare il codice univoco della prenotazione.
     * @return il codice della prenotazione
     */
    public int getIdPrenotazione() {
        return idPrenotazione;
    }

    /**
     * Metodo Getter per recuperare l'id del volo associato alla prenotazione.
     * @return l'id del volo
     */
    public int getIdVolo() {
        return idVolo;
    }

    /**
     * Metodo Getter per recuperare l'id dell'utente che ha effettuato la prenotazione.
     * @return l'id dell'utente
     */
    public int getIdUtente() {
        return idUtente;
    }

    /**
     * Metodo Setter per assegnare un oggetto passeggero a una prenotazione specifica.
     * @param passeggero il riferimento all'oggetto passeggero
     */
    public void setPasseggero(Passeggero passeggero) {
        this.passeggero = passeggero;
    }

    /**
     * Metodo Getter che permette di recuperare il riferimento di un oggetto passeggero assegnato alla prenotazione
     * @return il riferimento all'oggetto passeggero
     */
    public Passeggero getPasseggero() {
        return passeggero;
    }

    /**
     * Metodo Setter che permette di impostare il codice della prenotazione al biglietto.
     * @param numeroBiglietto il codice numerico del biglietto.
     */
    public void setNumeroBiglietto(String numeroBiglietto) {
        this.numeroBiglietto = numeroBiglietto;
    }

    /**
     * Metodo Getter che permette di recuperare il numero della prenotazione e del biglietto.
     * @return restituisce il codice numero del biglietto.
     */
    public String getNumeroBiglietto() {
        return numeroBiglietto;
    }

    /**
     * Metodo Setter che permette di assegnare un posto sull'aereo, se disponibile.
     * @param postoAssegnato il numero di posto assegnato.
     */
    public void setPostoAssegnato(int postoAssegnato) {
        this.postoAssegnato = postoAssegnato;
    }

    /**
     * Metodo Getter che permette di recuperare il numero di posto assegnato sull'aereo associato alla prenotazione.
     * @return restituisce il numero di posto assegnato.
     */
    public int getPostoAssegnato() {
        return postoAssegnato;
    }

    /**
     * Metodo Setter che permette di modificare lo stato di una prenotazione dopo la creazione.
     * @param statoPrenotazione lo stato della prenotazione.
     */
    public void setStatoPrenotazione(StatoPrenotazione statoPrenotazione) {
        this.statoPrenotazione = statoPrenotazione;
    }

    /**
     * Metodo Getter che permette di recuperare lo stato corrente della prenotazione.
     * @return restituisce lo stato della prenotazione.
     */
    public StatoPrenotazione getStatoPrenotazione() {
        return statoPrenotazione;
    }
}
