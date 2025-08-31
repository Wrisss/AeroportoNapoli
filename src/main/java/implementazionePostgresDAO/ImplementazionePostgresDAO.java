package implementazionePostgresDAO;

import dao.PostgresDAO;
import database.ConnessioneDatabase;
import java.sql.*;

import model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe ImplementazionePostgresDAO contiene tutte le implementazioni logiche delle interfacce PostgresDAO.
 * In particolare questa classe permette l'interazione con il database PostegresSQL:
 * Aggiunta, modifica e lettura dei dati presenti nel database.
 */
public class ImplementazionePostgresDAO implements PostgresDAO {

    //COSTRUTTORE

    /**
     * Costruttore privato. Non è possibile istanziare oggetti della classe.
     */
    private ImplementazionePostgresDAO() {
    }

    /**
     * Metodo che permette di recuperare dal database l'utente richiesto tramite username e password.
     * Questo metodo è concettualmente composto da tre parti:
     * 1. la preparazione della query da inviare al database.
     * 2. la query che viene "inviata" tramite l'oggetto connessione al database.
     * 3. La processazione dei risultati restituiti dal database.
     * L'intero metodo viene racchiuso in un "try-catch" poiché se la connessione al database dovesse fallire
     * a tempo di esecuzione il programma non andrebbe in crash
     *
     * @param username l'username utilizzato per accedere
     * @param password la password utilizzata per accedere
     * @return l'utente richiesto.
     */
    @Override
    public Superutente getUtenteByCredentials(String username, String password) {
        // Preparazione della query
        String query = "SELECT idutente, nomeutente, ruolo, password " +
                "FROM superutente WHERE nomeutente = ? AND password = ?";

        try (Connection conn = ConnessioneDatabase.getInstance().connection;
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            // Invio della query al database
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String ruoloDatabase = rs.getString("ruolo");
                    String usernameDb = rs.getString("nomeutente");
                    String passwordDb = rs.getString("password");

                    // Processazione dei risultati.
                    if ("Amministratore".equalsIgnoreCase(ruoloDatabase)) {
                        return new Amministratore(usernameDb, passwordDb);
                    } else if ("utente".equalsIgnoreCase(ruoloDatabase)) {
                        return new UtenteGenerico(usernameDb, passwordDb);
                    } else {
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Errore durante il recupero dei dati dell'utente: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo che permette la registrazione e quindi l'inserimento di un nuovo utente nel database
     * è possibile registrarsi solo come utenti generici e non come amministratori. Questa logica viene gestita
     * attraverso la "forzatura" del valore del ruolo che non può essere diverso da 'utente'
     *
     * @param utenteGenerico l'utente che si vuole registrare nel database
     * @return true se l'operazione è andata a buon fine, altrimenti false
     */
    @Override
    public boolean insertUtenteGenerico(UtenteGenerico utenteGenerico) {
        String query = "INSERT INTO Superutente (nomeutente, ruolo, password) VALUES (?, ?, ?)";

        try (Connection conn = ConnessioneDatabase.getInstance().connection;
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, utenteGenerico.getUsername());
            stmt.setString(2, "utente");
            stmt.setString(3, utenteGenerico.getPassword());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Errore durante la registrazione dell'utente: " +
                    e.getMessage());
            return false;
        }
    }

    /**
     * Metodo che interroga il database per restituire l'intero elenco dei voli.
     *
     * @return l'elenco dei voli presenti attualmente nel database.
     */
    @Override
    public List<Volo> getElencoVoli() {
        List<Volo> elencoVoli = new ArrayList<>();
        String query = "SELECT v.codicevolo, v.compaerea, v.appart, v.apdest," +
                "v.dataorapart, v.dataoraarrivo, v.ritardo, v.statovolo"
                + " FROM Volo v order by dataorapart DESC";

        try (Connection conn = ConnessioneDatabase.getInstance().connection;
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int codiceVolo = rs.getInt("codicevolo");
                String compagniaAerea = rs.getString("compaerea");
                String aeroportoOrigine = rs.getString("appart");
                String aeroportoDestinazione = rs.getString("apdest");
                LocalDate dataOraPartenza = rs.getDate("dataorapart").toLocalDate();
                LocalDate dataOraArrivo = rs.getDate("dataoraarrivo").toLocalDate();
                int ritardo = rs.getInt("ritardo");
                StatoVolo statoVolo = StatoVolo.valueOf(rs.getString("statovolo"));

                Volo volo = new Volo(codiceVolo, compagniaAerea, aeroportoOrigine, aeroportoDestinazione, dataOraPartenza,
                        dataOraArrivo, statoVolo, ritardo);
                elencoVoli.add(volo);
            }
        } catch (SQLException e) {
            System.err.println("Errore durante il recupero dell'elenco dei voli: " + e.getMessage());
            e.printStackTrace();
        }
        return elencoVoli;
    }

    /**
     * Metodo che permette l'inserimento di un volo nel database. Il gate assegnato viene impostato a '0'
     * come valore di default. Viene assegnato successivamente se il volo risulta in partenza da Napoli.
     * @param volo il volo che va inserito nel database.
     * @return true se il volo è stato aggiunto con successo al database, altrimenti false
     */
    @Override
    public boolean insertVolo(Volo volo) {
        String query = "INSERT INTO Volo (codicevolo, compaerea, appart, apdest, dataorapart, dataoraarrivo, gateassegnato, ritardo, statovolo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnessioneDatabase.getInstance().connection) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, volo.getCodiceVolo());
                stmt.setString(2, volo.getCompagniaAerea());
                stmt.setString(3, volo.getAeroportoOrigine());
                stmt.setString(4, volo.getAeroportoDestinazione());
                stmt.setDate(5, Date.valueOf(volo.getOraDataPartenza()));
                stmt.setDate(6, Date.valueOf(volo.getOraDataArrivo()));
                stmt.setInt(7, 0);
                stmt.setInt(8, volo.getRitardo());
                stmt.setString(9, volo.getStatoVolo().toString());

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected == 0) {
                    conn.rollback();
                    return false;}
                conn.commit();
                return true;

            } catch (SQLException e) {
                conn.rollback();
                System.err.println("Errore nell'inserimento del volo: " + e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Errore di connessione al database: " + e.getMessage());
            return false;
        }
    }

    /**
     * Metodo che permette l'aggiornamento di un volo presente nel database.
     * @param volo il volo che si desidera aggiornare
     * @return true se il volo è stato aggiornato correttamente, false altrimenti.
     */
    @Override
    public boolean updateVolo(Volo volo){
        String query = "UPDATE volo SET compaerea = ?, appart = ?, apdest = ?, " +
                "dataorapart = ?, dataoraarrivo = ?, ritardo= ?, statovolo = ? WHERE codicevolo = ?";
    try( Connection conn = ConnessioneDatabase.getInstance().connection;
         PreparedStatement stmt = conn.prepareStatement(query)){
             stmt.setString(1, volo.getCompagniaAerea());
             stmt.setString(2, volo.getAeroportoOrigine());
             stmt.setString(3, volo.getAeroportoDestinazione());
             stmt.setDate(4, Date.valueOf(volo.getOraDataPartenza()));
             stmt.setDate(5, Date.valueOf(volo.getOraDataArrivo()));
             stmt.setInt(6, volo.getRitardo());
             stmt.setString(7, volo.getStatoVolo().toString());
             stmt.setInt(8, volo.getCodiceVolo());
             return stmt.executeUpdate() > 0;
    }
    catch(SQLException e){
    System.err.println("Errore durante l'aggiornamento del volo: " + e.getMessage());
    e.printStackTrace();}
    return false;}

    /**
     * Metodo che permette di assegnare il gate ai voli in partenza da Napoli. Il controllo del volo viene affidato al database.
     * La query infatti presenta la condizione esplicita. Questa implementazione rispetta l'eredità tra le classi SUPERUTENTE
     * e UTENTE GENERICO e la responsabilità singola dei metodi.
     * @param nuovogate il nuovo gate che si vuole assegnare al volo
     * @param codicevolo il codice per recuperare il volo che si vuole modificare
     * @return true se la modifica è andata a buon fine, false altrimenti.
     */
    public boolean modificaGate(int nuovogate, int codicevolo){
        String query = "UPDATE volo SET gate = ? WHERE codicevolo = ? AND aeroporto_partenza = 'Napoli'";

        try(Connection conn = ConnessioneDatabase.getInstance().connection;
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, nuovogate);
            stmt.setInt(2, codicevolo);

            int righeAggiornate = stmt.executeUpdate();
            if (righeAggiornate > 0) {return true;}
        }
        catch(SQLException e){
            System.err.println("Errore SQL durante l'assegnazione del gate: " + e.getMessage());
            e.printStackTrace();
        }
        return false;}
























}



