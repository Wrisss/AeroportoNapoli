package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe GUI che inizializza una finestra 'Pagina Amministratore'.
 * Questa pagina si apre solo se a effettuare l'accesso è un amministratore (consultare il manuale).
 */
public class PaginaAmministratore extends JFrame {

    /**
     * Riferimento al controller che gestisce la logica il passaggio di dati tra GUI e database.
     */
    private Controller controller;

    /**
     * L'username dell'amministratore che effettua il login
     */
    private String username;

    /**
     * Pannello principale.
     */
    private JPanel AmministratorePanel;

    /**
     * Pannello di benvenuto.
     */
    private JPanel panelMessaggio;
    /**
     * Label per inserire il messaggio di benvenuto.
     */
    private JLabel labelBenvenuto;

    /**
     * Pulsante per recuperare l'elenco dei voli.
     */
    private JButton ELENCOVOLIButton;

    /**
     * Pulsante che permette l'inserimento di un nuovo volo nel database.
     */
    private JButton INSERISCIVOLOButton;

    /**
     * Label collegata al pulsante 'INSERISCI VOLO'.
     */
    private JLabel inserisciVoloLabel;

    /**
     * Label collegata al pulsante 'ELENCO VOLI'.
     */
    private JLabel elencoVoliLabel;

    /**
     * Costruttore della classe GUI 'Pagina Amministratore'. Inizializza la creazione della finestra impostando
     * una grafica con valori predefiniti.
     * Il costruttore inizializza anchei pulsanti 'ELENCO VOLI' e 'INSERISCI VOLO'.
     * @param controller il controller per gestire la logica di controllo dell'esecuzione
     * @param username l'username dell'Amministratore che ha effettua il login.
     */
    public PaginaAmministratore(Controller controller,
                                String username) {
        this.controller = controller;
        this.username = username;

        setTitle("Aeroporto Di Napoli - Amministratore");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(AmministratorePanel);


        labelBenvenuto.setText("Benvenuto, " + username + "!");
        labelBenvenuto.setFont(new Font("Arial", Font.BOLD, 22));

        ELENCOVOLIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ElencoVoliAmm elencoVoliAmm = new ElencoVoliAmm(controller, PaginaAmministratore.this);
                elencoVoliAmm.setVisible(true);
            }
        });

        INSERISCIVOLOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog dialogInserisciVolo = new JDialog(PaginaAmministratore.this,
                        "Inserisci Nuovo Volo", true);
                dialogInserisciVolo.setSize(700, 600);
                dialogInserisciVolo.setLocationRelativeTo(PaginaAmministratore.this);
                dialogInserisciVolo.setLayout(new GridLayout(10, 2, 10, 10));

                JLabel labelCodice = new JLabel("Codice Volo:");
                JTextField textCodiceVolo = new JTextField();

                JLabel labelIdUtente = new JLabel("ID Amministratore:");
                JTextField textIdUtente = new JTextField();

                JLabel labelCompagnia = new JLabel("Compagnia Aerea:");
                JTextField textCompagniaAerea = new JTextField();

                JLabel labelOrigine = new JLabel("Aeroporto Origine:");
                JTextField textAeroportoOrigine = new JTextField();

                JLabel labelDestinazione = new JLabel("Aeroporto Destinazione:");
                JTextField textAeroportoDestinazione = new JTextField();

                JLabel labelDataPartenza = new JLabel("Data e ora Partenza (GG-MM-AAAA HH:MM):");
                JTextField textDataPartenza = new JTextField();

                JLabel labelDataArrivo = new JLabel("Data e ora Arrivo (GG-MM-AAAA HH:MM):");
                JTextField textDataArrivo = new JTextField();


                JButton buttonConferma = new JButton("Conferma");
                JButton buttonAnnulla = new JButton("Annulla");

                dialogInserisciVolo.add(labelCodice);
                dialogInserisciVolo.add(textCodiceVolo);

                dialogInserisciVolo.add(labelIdUtente);
                dialogInserisciVolo.add(textIdUtente);

                dialogInserisciVolo.add(labelCompagnia);
                dialogInserisciVolo.add(textCompagniaAerea);

                dialogInserisciVolo.add(labelOrigine);
                dialogInserisciVolo.add(textAeroportoOrigine);

                dialogInserisciVolo.add(labelDestinazione);
                dialogInserisciVolo.add(textAeroportoDestinazione);

                dialogInserisciVolo.add(labelDataPartenza);
                dialogInserisciVolo.add(textDataPartenza);

                dialogInserisciVolo.add(labelDataArrivo);
                dialogInserisciVolo.add(textDataArrivo);

                dialogInserisciVolo.add(buttonAnnulla);
                dialogInserisciVolo.add(buttonConferma);

                buttonConferma.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            int codiceVolo = Integer.parseInt(textCodiceVolo.getText());
                            int idUtenteInsert = Integer.parseInt(textIdUtente.getText());
                            String compagnia = textCompagniaAerea.getText();
                            String origine = textAeroportoOrigine.getText();
                            String destinazione = textAeroportoDestinazione.getText();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                            LocalDateTime dataOraPartenza = LocalDateTime.parse(textDataPartenza.getText(), formatter);
                            LocalDateTime dataOraArrivo = LocalDateTime.parse(textDataArrivo.getText(), formatter);

                            boolean inserito = controller.inserisciNuovoVolo(codiceVolo, idUtenteInsert, compagnia, origine,
                                    destinazione, dataOraPartenza, dataOraArrivo);

                            if(inserito){
                            JOptionPane.showMessageDialog(dialogInserisciVolo,
                                    "Volo inserito con successo!");
                            dialogInserisciVolo.dispose();}
                            else {
                                JOptionPane.showMessageDialog(dialogInserisciVolo,
                                        "Errore durante l'inserimento del volo nel database!",
                                        "Errore",
                                        JOptionPane.ERROR_MESSAGE);
                            }

                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(dialogInserisciVolo,
                                    "Errore: Il codice volo deve essere un numero!",
                                    "Errore", JOptionPane.ERROR_MESSAGE);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(dialogInserisciVolo,
                                    "Errore: Controlla i dati inseriti!\nFormato data: GG-MM-AAAA HH:MM",
                                    "Errore", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                buttonAnnulla.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialogInserisciVolo.dispose();}
                });
                dialogInserisciVolo.setVisible(true);
            }
        });

    }
}
