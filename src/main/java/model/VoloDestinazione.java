package model;

import java.time.LocalDate;

/**
 * La Classe VoloDestinazione è una specializzazione della classe Volo. Gli oggetti che fanno riferimneto specifico a questa classe
 * hanno come destinazione sempre Napoli.
 */
public class VoloDestinazione extends Volo {

    /**
     * Costruttore identico alla classe Volo. Tutti i parametri richiesti rimangono uguali eccetto che qui bisogna specificare
     * che il valore dell'attributo "AeroportoDestinazione" è un valore costante 'NAPOLI'
     *
     * @param codiceVolo            il codice univoco del volo.
     * @param compagniaAerea        la compagnia che eroga il volo.
     * @param aeroportoOrigine      l'aeroporto di partenza di un volo
     * @param aeroportoDestinazione 'NAPOLI' valore costante.
     * @param dataOraPartenza       data e ora di partenza del volo.
     * @param dataOraArrivo         data e ora di arrivo del volo.
     * @param statoVolo             lo stato attuale del volo.
     * @param ritardo               eventuale ritardo del volo.
     */
    public VoloDestinazione(int codiceVolo, String compagniaAerea,
                            String aeroportoOrigine, String aeroportoDestinazione,
                            LocalDate dataOraPartenza, LocalDate dataOraArrivo, StatoVolo statoVolo,
                            int ritardo, int gateAssegnato) {
        super(codiceVolo, compagniaAerea, aeroportoOrigine, "NAPOLI", dataOraPartenza, dataOraArrivo,
                statoVolo, ritardo);
    }
}