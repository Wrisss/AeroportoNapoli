package model;

/**
 * Stato Volo è una classe ENUM utilizzata per esprimere valori costanti. I valori degli attributi della classe vengono qui
 * esplicitamente inizializzati con lettere minuscole per evitare conflitti con il database PostegreSQL che è case-sensitive.
 */
public enum StatoVolo {
    /**
     * Volo Programmato. Questo è il valore di default assegnato a ogni nuovo volo appena creato,
     */
    programmato,

    /**
     * Volo decollato e attualmente in volo.
     */
    in_volo,

    /**
     * Volo che è arrivato a destinazione.
     *
     */
    arrivato,

    /**
     * Volo cancellato.
     */
    cancellato
}
