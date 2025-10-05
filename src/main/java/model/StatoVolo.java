package model;

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
