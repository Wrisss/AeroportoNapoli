package model;

import java.time.LocalDate;

/**
 * La classe VOLO è il centro logico dell'intero sistema.
 * Presenta una serie di attributi documentati successivamente.
 */

public class Volo {

    /**
     * Il codice univoco per ogni singolo volo.
     */
    protected int codiceVolo;

    /**
     * La compagnia aerea che predispone il volo.
     */
    private String compagniaAerea;

    /**
     * L'aeroporto da cui il volo è partito / deve partire.
     */
    private String aeroportoOrigine;

    /**
     * L'aeroporto in cui volo è atterrato / deve atterrare.
     */
    private String aeoroportoDestinazione;

    /**
     * Il giorno e l'orario di partenza.
     */
    private LocalDate oraDataPartenza;

    /**
     * Il giorno e l'orario di arrivo.
     */
    private LocalDate oraDataArrivo;

    /**
     * Lo stato del Volo definito come un valore disponibile prefissato.
     */
    private StatoVolo statoVolo;

    /**
     * L'eventuale ritardo segnato in minuti.
     *
     */
    private int ritardo;

    /**
     * Costruttore della classe volo.
     *
     * @param codiceVolo             il codice univoco per ogni volo.
     * @param compagniaAerea         la compagnia che eroga il volo.
     * @param aeroportoOrigine       l'aeroporto da cui parte il volo.
     * @param aeoroportoDestinazione l'aeroporto in cui atterra il volo.
     * @param oraDataArrivo          data e ora di partenza del volo.
     * @param oraDataPartenza        data e ora di arrivo del volo.
     * @param statoVolo              stato del volo. All'inizio della creazione il volo risulta sempre 'PROGRAMMATO'
     * @param ritardo                l'eventuale ritardo. All'inizio il valore è sempre '0'
     */
    public Volo(int codiceVolo, String compagniaAerea, String aeroportoOrigine, String aeoroportoDestinazione,
                LocalDate oraDataPartenza, LocalDate oraDataArrivo, StatoVolo statoVolo, int ritardo) {
        this.codiceVolo = codiceVolo;
        this.compagniaAerea = compagniaAerea;
        this.aeroportoOrigine = aeroportoOrigine;
        this.aeoroportoDestinazione = aeoroportoDestinazione;
        this.oraDataPartenza = oraDataPartenza;
        this.oraDataArrivo = oraDataArrivo;
        this.statoVolo = StatoVolo.PROGRAMMATO;
        this.ritardo = 0;
    }

    // GETTERS AND SETTERS

    /**
     * Metodo Setter per assegnare un codice univoco a ogni volo.
     * @param codiceVolo il codice per identificare univocamente ogni volo.
     */
    public void setCodiceVolo(int codiceVolo) {
        this.codiceVolo = codiceVolo;
    }

    /**
     * Metodo Getter per recuperare il codice univoco di ogni volo.
     * @return recupera il codice del volo.
     */
    public int getCodiceVolo() {
        return codiceVolo;
    }

    /**
     * Metodo Setter per specificare la compagnia che eroga il volo.
     * @param compagniaAerea la compagnia che eroga il volo.
     */
    public void setCompagniaAerea(String compagniaAerea) {
        this.compagniaAerea = compagniaAerea;
    }

    /**
     * Metodo getter per recuperare la compagnia erogatrice.
     * @return restituisce la compagnia che eroga il volo.
     */
    public String getCompagniaAerea() {
        return compagniaAerea;
    }

    /**
     * Metodo Setter per assegnare l'aeroporto di origine.
     * @param aeroportoOrigine l'aeroporto di origine del volo.
     */
    public void setAeroportoOrigine(String aeroportoOrigine) {
        this.aeroportoOrigine = aeroportoOrigine;
    }

    /**
     * Metodo getter per recuperare l'aeroporto di partenza.
     * @return restituisce l'aeroporto da cui è partito il volo
     */
    public String getAeroportoOrigine() {
        return aeroportoOrigine;
    }

    /**
     * Metodo setter per assegnare l'aeroporto di destinazione.
     * @param aeoroportoDestinazione l'aeroporto di atterraggio del volo.
     */
    public void setAeoroportoDestinazione(String aeoroportoDestinazione) {
        this.aeoroportoDestinazione = aeoroportoDestinazione;
    }

    /**
     * Metodo getter per conoscere l'aeroporto di atterraggio.
     * @return restituisce l'aeroporto di atterraggio di un volo.
     */
    public String getAeoroportoDestinazione() {
        return aeoroportoDestinazione;
    }

    /**
     * Metodo Setter per assegnare data e ora di partenza.
     * @param oraDataPartenza data e ora di partenza di un volo.
     */
    public void setOraDataPartenza(LocalDate oraDataPartenza) {
        this.oraDataPartenza = oraDataPartenza;
    }

    /**
     * Metodo Getter per recuperare la data e l'orario di partenza.
     * @return restituisce la data e l'ora di partenza del volo.
     */
    public LocalDate getOraDataPartenza() {
        return oraDataPartenza;
    }

    /**
     * Metodo Setter per specificare la data e l'orario di arrivo.
     * @param oraDataArrivo data e ora di arrivo del volo.
     */
    public void setOraDataArrivo(LocalDate oraDataArrivo) {
        this.oraDataArrivo = oraDataArrivo;
    }

    /**
     * Metodo Getter per recuperare l'orario e la data di arrivo.
     * @return data e ora di atterraggio del volo.
     */
    public LocalDate getOraDataArrivo() {
        return oraDataArrivo;
    }

    /**
     * Metodo Setter per stabilire lo stato in cui si trova il volo. Disponibili solo 4 stati possibili.
     * @param statoVolo definisce lo stato del volo.
     */
    public void setStatoVolo(StatoVolo statoVolo) {
        this.statoVolo = statoVolo;
    }

    /**
     * Metodo Getter che restituisce le informazioni sullo stato.
     * @return lo stato attuale del volo.
     */
    public StatoVolo getStatoVolo() {
        return statoVolo;
    }

    /**
     * Metodo Setter per aggiornare il ritardo del volo, se presente.
     * @param ritardo il ritardo, in minuti, del volo.
     */
    public void setRitardo(int ritardo) {
        this.ritardo = ritardo;
    }

    /**
     * Metodo getter per recuperare l'eventuale ritardo.
     * @return il ritardo del volo, se presente.
     */
    public int getRitardo() {
        return ritardo;
    }
}
