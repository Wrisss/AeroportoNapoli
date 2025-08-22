package model;

/**
 * La classe GateImbarco specifica il numero di gate a cui si accede per finalizzare le procedure di imbarco.
 */
public class GateImbarco {
    /**
     * L'unico attributo della classe. Rappresenta il numero di gate assegnato per i voli in partenza.
     * I gate gestiti dall'aeroporto di Napoli sono 20.
     */
    private int gateAssegnato;

    /**
     * Costruttore della classe GateImbarco. Accetta come parametro solo il numero del gate.
     * @param gateAssegnato il numero univoco del gate di imbarco.
     */
    public GateImbarco(int gateAssegnato){
        this.gateAssegnato = gateAssegnato;
    }

    // GETTERS AND SETTERS
    /**
     * Metodo Setter che permette di assegnare il numero di gate di imbarco di un volo.
     * @param gateAssegnato il numero di gate assegnato.
     */
    public void setGateAssegnato(int gateAssegnato) {
        this.gateAssegnato = gateAssegnato;
    }

    /**
     * Metodo Getter che permette di recuperare il gate assegnato di un volo.
     * @return restituisce il numero di gate di imbarco.
     */
    public int getGateAssegnato() {
        return gateAssegnato;
    }
}
