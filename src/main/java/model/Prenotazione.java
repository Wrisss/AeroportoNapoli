package model;

/**
 * La classe Prenotazione permette la gestione da parte degli utenti di prenotare i voli a cui sono interessati.
 * Ogni Utente può prenotare voli sia per sé che per altri.
 */
public class Prenotazione {
    /**
     * Il codice identificato di ogni biglietto associato alla prenotazione.
     */
    private int numeroBiglietto;

    /**
     * Il posto assegnato sull'aereo. Capienza di ogni aereo: MAX 200 persone.
     */
    private int postoAssegnato;

    /**
     * Lo stato di una prenotazione. Ogni prenotazione appena creata presenta come valore di default: 'IN_ATTESA'.
     */
    private StatoPrenotazione statoPrenotazione;

    /**
     * Costruttore della classe Prenotazione. Richiede tre argomenti.
     * @param numeroBiglietto il numero del biglietto associato alla prenotazione.
     * @param postoAssegnato il posto assegnato sull'aereo.
     * @param statoPrenotazione lo stato in cui si trova la prenotazione.
     */
    public Prenotazione(int numeroBiglietto, int postoAssegnato, StatoPrenotazione statoPrenotazione){
        this.numeroBiglietto = numeroBiglietto;
        this.postoAssegnato = postoAssegnato;
        this.statoPrenotazione = statoPrenotazione.IN_ATTESA;
    }

    // GETTERS AND SETTERS

    /**
     * Metodo Setter che permette il codice della prenotazione al biglietto.
     * @param numeroBiglietto il codice numerico del biglietto.
     */
    public void setNumeroBiglietto(int numeroBiglietto) {
        this.numeroBiglietto = numeroBiglietto;
    }

    /**
     * Metodo Getter che permette di recuperare il numero della prenotazione e del biglietto.
     * @return restituisce il codice numero del biglietto.
     */
    public int getNumeroBiglietto() {
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
