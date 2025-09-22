package model;

/**
 * Sottoclasse AMMINISTRATORE della superclasse SUPERUTENTE.
 * Gli amministratori sono gli unici che possono modificare il database dei voli presenti.
 */
public class Amministratore extends Superutente {
    //ATTRIBUTI
    /**
     * L'attributo "Amministratore" in questa classe è costante (non può essere modificato) ed è inoltre sempre definito
     * per questo l'uso della parola chiave 'final' e l'inizializzazione già dichiarata.
     */
    private final String ruolo = "Amministratore";

    /**
     * Costruttore della classe AMMINISTRATORE. Stessi argomenti presi in ingresso della superclasse a cui fa riferimento.
     * @param username l'username per accedere al sistema.
     * @param password la password per accedere al sistema.
     */
    public Amministratore(String username, String password){
        super(username, password);
    }

    public String getRuolo() {
        return ruolo;
    }
}
