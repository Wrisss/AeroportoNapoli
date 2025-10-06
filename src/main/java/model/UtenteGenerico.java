package model;

/**
 * Sottoclasse UTENTE GENERICO della superclasse SUPERUTENTE.
 * Gli utenti generici utilizzano il servizio per gestire le prenotazioni dei voli scelti.
 */
public class UtenteGenerico extends Superutente {
    // ATTRIBUTI
    /**
     * L'attributo "Utente Generico" in questa classe è costante (non può essere modificato) ed è inoltre sempre definito
     * per questo l'uso della parola chiave 'final' e l'inizializzazione già dichiarata.
     */
    private final String ruolo = "utente";

    //COSTRUTTORE

    /**
     * Costruttore della classe UtenteGenerico. Stessi argomenti richiesti in ingresso della superclasse a cui fa riferimento.
     * @param username l'username per accedere al sistema.
     * @param password la password per accedere al sistema.
     */
    public UtenteGenerico(String username, String password){
        super(username, password);
    }

}
