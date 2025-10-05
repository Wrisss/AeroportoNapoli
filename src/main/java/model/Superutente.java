package model;

/**
 * La classe che rappresenta un utente generico che accede al sistema viene definita come classe astratta.
 * Ogni utente deve accedere con le proprie credenziali.
 * Questa classe è una generalizzazione delle classi, rispettivamente, di UTENTE_GENERICO e AMMINISTRATORE.
 */

public abstract class Superutente {
    // ATTRIBUTI

    /**
     * L'username per accedere al sistema.
     */
    protected String username;
    /**
     * La password per accedere al sistema.
     */
    protected String password;

    // COSTRUTTORE
    /**
     * Costruttore che permette la creazione di un oggetto della classe UTENTE con gli argomenti passatigli.
     * @param username username per l'autenticazione.
     * @param password password per l'autenticazione.
     */
    protected Superutente(String username, String password){
        this.username = username;
        this.password = password;
    }

    // GETTERS AND SETTERS


    /**
     * Metodo setter per impostare il proprio username.
     * @param username l'username scelto dall'utente.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Metodo getter per ricevere il nome dell'utente.
     * @return l'username scelto dall'utente.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Metodo setter per impostare la password.
     * @param password password scelta per accedere al sistema.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Metodo getter per recuperare la password.
     * @return recupera la password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Interfaccia astratta che permette di recuperare il ruolo di utente navigando le sottoclassi specifiche di SUPERUTENTE.
     * @return il ruolo dell'utente
     */
    public abstract String getRuolo();
}


