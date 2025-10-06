package model;

import java.time.LocalDateTime;

/**
 * La Classe VoloDestinazione è una specializzazione della classe Volo. Gli oggetti che fanno riferimento specifico a questa classe
 * hanno come destinazione sempre Napoli.
 */
public class VoloDestinazione extends Volo {

    /**
     * Costruttore identico alla classe Volo. Tutti i parametri richiesti rimangono uguali eccetto che qui bisogna specificare
     * che il valore dell'attributo "AeroportoDestinazione" è un valore costante 'NAPOLI'
     *
     * @param codiceVolo            il codice univoco del volo
     * @param idUtenteInsert        il codice dell'amministratore
     * @param compagniaAerea        la compagnia che eroga il volo
     * @param aeroportoOrigine      l'aeroporto di partenza di un volo
     * @param aeroportoDestinazione 'NAPOLI' valore costante
     * @param dataOraPartenza       data e ora di partenza del volo
     * @param dataOraArrivo         data e ora di arrivo del volo
     * @param statoVolo             lo stato attuale del volo
     * @param ritardo               eventuale ritardo del volo
     */
    public VoloDestinazione(int codiceVolo, int idUtenteInsert, String compagniaAerea,
                            String aeroportoOrigine, String aeroportoDestinazione,
                            LocalDateTime dataOraPartenza, LocalDateTime dataOraArrivo, StatoVolo statoVolo,
                            int ritardo) {
        super(codiceVolo, idUtenteInsert, compagniaAerea, aeroportoOrigine, "Napoli", dataOraPartenza, dataOraArrivo,
                statoVolo, ritardo);
    }
}