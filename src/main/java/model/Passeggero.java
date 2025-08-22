package model;

/**
 * La classe Passeggero è un'aggregazione della classe Prenotazione. Specifica i riferimenti generati
 * dei passeggeri a cui è stata assegnata la prenotazione di un volo.
 */
public class Passeggero {
    /**
     * Il nome del passeggero.
     */
    private String nomePasseggero;

    /**
     * Il cognome dell passeggero.
     */
    private String cognomePasseggero;

    /**
     * Il tipo e il numero di documento.
     */
    private String documentoIdentità;

    /**
     * Costruttore della classe Passeggero. Accetta tre parametri.
     * @param nomePasseggero il nome del passeggero.
     * @param cognomePasseggero il cognome del passeggero.
     * @param documentoIdentità il tipo di documento e il numero del documento.
     */
    public Passeggero(String nomePasseggero, String cognomePasseggero, String documentoIdentità){
        this.nomePasseggero = nomePasseggero;
        this.cognomePasseggero = cognomePasseggero;
        this.documentoIdentità = documentoIdentità;
    }

    /**
     * Metodo Setter che permette di assegnare, alla prenotazione, il nome di un passeggero,
     * anche diverso da chi ha creato la prenotazione.
     * @param nomePasseggero il nome del passeggero.
     */
    public void setNomePasseggero(String nomePasseggero) {
        this.nomePasseggero = nomePasseggero;
    }

    /**
     * Metodo Getter che permette di recuperare il nome del passeggero.
     * @return restituisce il nome del passeggero.
     */
    public String getNomePasseggero() {
        return nomePasseggero;
    }

    /**
     * Metodo Setter che permette di assegnare, alla prenotazione, il cognome di un passeggero,
     * anche diverso da chi ha creato la prenotazione.
     * @param cognomePasseggero il cognome del passeggero.
     */
    public void setCognomePasseggero(String cognomePasseggero) {
        this.cognomePasseggero = cognomePasseggero;

    }
    /**
     * Metodo Getter che permette di recuperare il cognome del passeggero.
     * @return restituisce il cognome del passeggero.
     */
    public String getCognomePasseggero() {
        return cognomePasseggero;
    }

    /**
     * Metodo Setter che permette di assegnare a ogni passeggero il documento anagrafico con cui si effettua la prenotazione.
     * @param documentoIdentità il tipo e il numero del documento.
     */
    public void setDocumentoIdentità(String documentoIdentità) {
        this.documentoIdentità = documentoIdentità;
    }

    /**
     * Metodo Getter che permette di recuperare il documento anagrafico del passeggero.
     * @return restituisce le specifiche del documento d'identità del passeggero.
     */
    public String getDocumentoIdentità() {
        return documentoIdentità;
    }
}
