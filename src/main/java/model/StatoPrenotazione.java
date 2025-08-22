package model;

/**
 * Lo stato della prenotazione visibile nell'area riservata di ogni utente generico.
 */
public enum StatoPrenotazione {
    /**
     * Valore di default alla creazione della prenotazione. Indica che la prenotazione è stata creata ma deve essere
     * confermata per effettuare il check-in.
     */
    IN_ATTESA,

    /**
     * La prenotazione è stata confermata. Vale come check-in per l'imbarco.
     */
    CONFERMATA,

    /**
     * La prenotazione è stata cancellata dall'utente.
     */
    CANCELLATA;
}
