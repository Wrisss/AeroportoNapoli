package implementazionePostgresDAO;

import dao.PostgresDAO;
import database.ConnessioneDatabase;
import java.sql.*;

import model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
                    if ("amministratore".equalsIgnoreCase(ruoloDatabase)) {
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
        String query = "SELECT v.codicevolo, v.idutenteinsert, v.compaerea, v.appart, v.apdest," +
                "v.dataorapart, v.dataoraarrivo, v.ritardo, v.statovolo"
                + " FROM Volo v order by dataorapart DESC";

        try (Connection conn = ConnessioneDatabase.getInstance().connection;
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int codiceVolo = rs.getInt("codicevolo");
                int idUtenteInsert = rs.getInt("idutenteinsert");
                String compagniaAerea = rs.getString("compaerea");
                String aeroportoOrigine = rs.getString("appart");
                String aeroportoDestinazione = rs.getString("apdest");
                LocalDate dataOraPartenza = rs.getDate("dataorapart").toLocalDate();
                LocalDate dataOraArrivo = rs.getDate("dataoraarrivo").toLocalDate();
                int ritardo = rs.getInt("ritardo");
                StatoVolo statoVolo = StatoVolo.valueOf(rs.getString("statovolo"));

                Volo volo = new Volo(codiceVolo, idUtenteInsert, compagniaAerea,
                        aeroportoOrigine, aeroportoDestinazione, dataOraPartenza,
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
        String query = "INSERT INTO Volo (codicevolo, idutenteinsert, compaerea,  appart," +
                "apdest, dataorapart, dataoraarrivo, gateassegnato, ritardo, statovolo)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnessioneDatabase.getInstance().connection) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, volo.getCodiceVolo());
                stmt.setInt(2, volo.getIdUtenteInsert());
                stmt.setString(3, volo.getCompagniaAerea());
                stmt.setString(4, volo.getAeroportoOrigine());
                stmt.setString(5, volo.getAeroportoDestinazione());
                stmt.setDate(6, Date.valueOf(volo.getOraDataPartenza()));
                stmt.setDate(7, Date.valueOf(volo.getOraDataArrivo()));
                stmt.setInt(8, 0);
                stmt.setInt(9, volo.getRitardo());
                stmt.setString(10, volo.getStatoVolo().toString());

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
        String query = "UPDATE volo SET v.compaerea = ?, v.appart = ?, v.apdest = ?, " +
                "v.dataorapart = ?, v.dataoraarrivo = ?, v.gateassegnato = ?, v.ritardo= ?," +
                "v.statovolo = ? WHERE codicevolo = ?";
    try (Connection conn = ConnessioneDatabase.getInstance().connection;
         PreparedStatement stmt = conn.prepareStatement(query)){
             stmt.setString(1, volo.getCompagniaAerea());
             stmt.setString(2, volo.getAeroportoOrigine());
             stmt.setString(3, volo.getAeroportoDestinazione());
             stmt.setDate(4, Date.valueOf(volo.getOraDataPartenza()));
             stmt.setDate(5, Date.valueOf(volo.getOraDataArrivo()));
             stmt.setInt(6,0);
             stmt.setInt(7, volo.getRitardo());
             stmt.setString(8, volo.getStatoVolo().toString());
             stmt.setInt(9, volo.getCodiceVolo());
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
    @Override
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

    /**
     * Metodo che permette di selezionare un volo e creare una prenotazione legata a quel volo.
     * @param codiceVolo il codice del volo che si vuole prenotare
     * @return true se la prenotazione è stata creata con successo, altrimenti false.
     */
    @Override
    public boolean prenotaVolo(int codiceVolo, String nomePasseggero, int postoScelto){
        String query = "INSERT INTO PRENOTAZIONE (idVolo, NomePasseggero, NumeroBiglietto," +
                "PostoAssegnato, StatoPren)" +
                "VALUES (?, ?, ?, ?, ?)";

    try(Connection conn = ConnessioneDatabase.getInstance().connection;
        PreparedStatement stmt = conn.prepareStatement(query)){

        Random random = new Random();
        int numeroBiglietto = 100000 + random.nextInt(900000);
        String numBiglietto = "BGL" + numeroBiglietto;

        stmt.setInt(1, codiceVolo);
        stmt.setString(2, nomePasseggero);
        stmt.setString(3, numBiglietto);
        stmt.setInt(4, postoScelto);
        stmt.setString(5, "IN_ATTESA");

        int righeInserite = stmt.executeUpdate();

        return righeInserite > 0;

    }
    catch(SQLException e){System.out.println("Errore durante la creazione della prenotazione: " + e.getMessage());
        e.printStackTrace();
        return false;}
    }

    /**
     * Metodo che permette di recuperare tutte le prenotazioni associate a utente.
     * Questo metodo richiama il metodo di utilità 'creaPasseggeroDaNomeCompleto'.
     * L'implementazione di tale metodo viene documentata più avanti.
     * @param utenteGenerico l'utente generico di cui si voglio recuperare le prenotazioni
     * @return l'elenco completo delle prenotazioni associate a un utente.
     */
    @Override
    public List<Prenotazione> getPrenotazioniByUtente(UtenteGenerico utenteGenerico){
        List<Prenotazione> elencoPrenotazioni = new ArrayList<>();

        String query = "SELECT p.idPrenotazione, p.idVolo, p.idUtente, p.NomePasseggero, " +
                "p.NumeroBiglietto, p.PostoAssegnato, p.StatoPren " +
                "FROM PRENOTAZIONE p " +
                "JOIN SUPERUTENTE su ON p.idutente = su.idutente WHERE su.NomeUtente = ? " +
                "ORDER BY idprenotazione DESC";

        try(Connection conn = ConnessioneDatabase.getInstance().connection;
            PreparedStatement stmt = conn.prepareStatement(query);) {

            stmt.setString(1, utenteGenerico.getUsername());

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idPrenotazione = rs.getInt("idPrenotazione");
                int idVolo = rs.getInt("idVolo");
                int idUtente = rs.getInt("idUtente");

                // Metodo per dividere il dato presente nel database e convertirlo in un oggetto.
                String nomeCompleto = rs.getString("NomePasseggero");
                Passeggero passeggero = creaPasseggeroDaNomeCompleto(nomeCompleto);
                //

                int numeroBiglietto = rs.getInt("NumeroBiglietto");
                int postoAssegnato = rs.getInt("PostoAssegnato");
                String statoString = rs.getString("StatoPren");
                StatoPrenotazione statoPren = StatoPrenotazione.valueOf(statoString);


                Prenotazione prenotazione = new Prenotazione(idPrenotazione, idVolo, idUtente, passeggero,
                        numeroBiglietto, postoAssegnato, statoPren);
                elencoPrenotazioni.add(prenotazione);
            }
        }
        catch (SQLException e){System.out.println("Errore durante il recupero della prenotazione: " + e.getMessage());
            e.printStackTrace();}
        return elencoPrenotazioni;
    }

    @Override
    public boolean aggiornaPrenotazione(Prenotazione prenotazione) {

        String query = ""

        try (Connection conn = ConnessioneDatabase.getInstance().connection;
             PreparedStatement stmt = conn.prepareStatement(query)) {
        } catch (SQLException e) {
            System.out.println("Errore durante la creazione della prenotazione: " + e.getMessage());
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Prenotazione getPrenotazioneByIdVolo(int codiceVolo){

        String query = ""

            try(Connection conn = ConnessioneDatabase.getInstance().connection;
                PreparedStatement stmt = conn.prepareStatement(query)){}
            catch (SQLException e){System.out.println("Errore durante la creazione della prenotazione: " + e.getMessage());
                e.printStackTrace();
        }

    }

    @Override
    public Prenotazione GetPrenotazioneByPasseggero(Passeggero passeggero){

        String query = ""
            try(Connection conn = ConnessioneDatabase.getInstance().connection;
                PreparedStatement stmt = conn.prepareStatement(query)){}
            catch (SQLException e){System.out.println("Errore durante la creazione della prenotazione: " + e.getMessage());
                e.printStackTrace();
        }

    }


    /**
     * Metodo che permette di trasformare una stringa contenente nome e cognome estratta dal database, in un oggetto
     * passeggero definito nella classe Passeggero.
     * @param nomeCompleto il nome e cognome così come sono salvati sul database
     * @return l'oggetto passeggero.
     */
    private Passeggero creaPasseggeroDaNomeCompleto(String nomeCompleto) {

        String[] partiNome = nomeCompleto.trim().split("\\s+", 2);
        String nomePasseggero;
        String cognomePasseggero;

        nomePasseggero = partiNome[0];
        cognomePasseggero = partiNome[1];

        String documentoIdentita = "";
        return new Passeggero(nomePasseggero, cognomePasseggero, documentoIdentita);
    }

}



