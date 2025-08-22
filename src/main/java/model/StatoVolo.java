package model;

public enum StatoVolo {
    /**
     * Volo Programmato. Questo è il valore di default assegnato a ogni nuovo volo appena creato,
     */
    PROGRAMMATO,

    /**
     * Volo decollato e attualmente in volo.
     */
    IN_VOLO,

    /**
     * Volo che è arrivato a destinazione.
     *
     */
    ARRIVATO,

    /**
     * Volo cancellato.
     */
    CANCELLATO;
}
