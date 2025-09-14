package model;

import java.time.LocalDate;

/**
 * La Classe VoloPartenza è una specializzazione della classe Volo. La peculiarità di questa classe è che
 * per ogni volo in partenza viene assegnato un gate per le operazioni di imbarco.
 */
public class VoloPartenza extends Volo{
    /**
     * Il numero di gate di imbarco assegnato per accedere ai controlli di imbarco.
     */
    private int gateAssegnato;

    /**
     * Costruttore identico alla classe Volo. Tutti i parametri richiesti rimangono uguali eccetto che qui bisogna specificare
     * un numero di gate di imbarco e inoltre 'NAPOLI' rappresenta il valore fisso dell'attributo "AeroportoOrigine".
     * @param codiceVolo            il codice univoco del volo
     * @param idUtenteInsert        il codice dell'utente che inserito il volo
     * @param compagniaAerea        la compagnia che eroga il volo
     * @param aeroportoOrigine      valore costante 'NAPOLI'
     * @param aeroportoDestinazione l'aeroporto di atterraggio del volo
     * @param dataOraPartenza       data e ora di partenza del volo
     * @param dataOraArrivo         data e ora di arrivo del volo
     * @param statoVolo             lo stato attuale del volo
     * @param ritardo               eventuale ritardo del volo
     * @param gateAssegnato         gate assegnato per i voli in partenza soltanto
     */
    public VoloPartenza(int codiceVolo, int idUtenteInsert, String compagniaAerea,
                        String aeroportoOrigine, String aeroportoDestinazione,
                        LocalDate dataOraPartenza, LocalDate dataOraArrivo, StatoVolo statoVolo,
                        int ritardo, int gateAssegnato){
        super(codiceVolo, idUtenteInsert, compagniaAerea, "NAPOLI", aeroportoDestinazione, dataOraPartenza, dataOraArrivo,
                statoVolo, ritardo);
        this.gateAssegnato = gateAssegnato;
    }

}
