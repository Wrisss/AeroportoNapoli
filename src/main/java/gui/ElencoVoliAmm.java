package gui;

import controller.Controller;
import model.Volo;
import model.VoloPartenza;
import model.StatoVolo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Classe GUI che implementa la finestra di gestione dei voli.
 */
public class ElencoVoliAmm extends JFrame {

    /**
     * Riferimento al controller che gestisce la logica.
     */
    private Controller controller;

    /**
     * Riferimento alla pagina Amministratore per tornare alla schermata precedente.
     */
    private PaginaAmministratore paginaAmministratore;

    private List<Volo> elencoVoli;

    /**
     * Pannello principale.
     */
    private JPanel panelElencoVoli;

    /**
     * Tabella che mostra i voli presenti nel sistema.
     */
    private JTable tabellaVoli;

    /**
     * Specifiche per modificare la tabella.
     */
    private DefaultTableModel tableModel;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Costruttore della classe ElencoVoliAmm. Inizilizza la finestra che mostra l'elenco dei voli solo per amministratori.
     * Il costruttore utilizza una serie di valori predefiniti che ne specificano la grafica.
     * Inizializza, inoltre, una serie di componenti per la gestione dei voli.
     * @param controller il riferimento al controller che gestisce la logica.
     * @param paginaAmministratore riferimento alla pagina amministratore per tornare alla schermata precedente.
     */
    public ElencoVoliAmm(Controller controller,
                         PaginaAmministratore paginaAmministratore){
        this.controller = controller;
        this.paginaAmministratore = paginaAmministratore;

        setTitle("Gestore dei voli");
        setSize(1300, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        inizializzaComponenti();
        caricaVoli();
    }

    private void inizializzaComponenti() {
        setLayout(new BorderLayout(10, 10));

        String[] colonne = {"Codice", "Compagnia", "Origine", "Destinazione",
                "Partenza", "Arrivo", "Gate", "Ritardo (min)", "Stato"};
        tableModel = new DefaultTableModel(colonne, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabellaVoli = new JTable(tableModel);
        tabellaVoli.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabellaVoli.setRowHeight(25);
        tabellaVoli.getTableHeader().setReorderingAllowed(false);

        tabellaVoli.getColumnModel().getColumn(0).setPreferredWidth(80);
        tabellaVoli.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabellaVoli.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabellaVoli.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabellaVoli.getColumnModel().getColumn(4).setPreferredWidth(150);
        tabellaVoli.getColumnModel().getColumn(5).setPreferredWidth(150);
        tabellaVoli.getColumnModel().getColumn(6).setPreferredWidth(80);
        tabellaVoli.getColumnModel().getColumn(7).setPreferredWidth(100);
        tabellaVoli.getColumnModel().getColumn(8).setPreferredWidth(120);

        JScrollPane scrollPane = new JScrollPane(tabellaVoli);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelPulsanti = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnAnnulla = new JButton("Annulla");
        JButton btnModificaDettagli = new JButton("Modifica Dettagli");
        JButton btnModificaGate = new JButton("Modifica Gate");
        JButton btnAggiorna = new JButton("Aggiorna Tabella");

        btnAnnulla.addActionListener(e -> tornaIndietro());
        btnModificaDettagli.addActionListener(e -> modificaDettagliVolo());
        btnModificaGate.addActionListener(e -> modificaGateVolo());
        btnAggiorna.addActionListener(e -> caricaVoli());

        panelPulsanti.add(btnAnnulla);
        panelPulsanti.add(btnModificaDettagli);
        panelPulsanti.add(btnModificaGate);
        panelPulsanti.add(btnAggiorna);

        add(panelPulsanti, BorderLayout.SOUTH);

        JLabel lblInfo = new JLabel("Seleziona un volo dalla tabella - Il gate può essere modificato solo per i voli in partenza");
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
        lblInfo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(lblInfo, BorderLayout.NORTH);
    }

    private void tornaIndietro(){
        dispose();
        paginaAmministratore.setVisible(true);
    }

    private void caricaVoli() {

        tableModel.setRowCount(0);

        elencoVoli = controller.getTuttiVoli();

        for (Volo volo : elencoVoli) {
            String gate = "-";

            if (volo instanceof VoloPartenza) {
                VoloPartenza voloPartenza = (VoloPartenza) volo;
                gate = String.valueOf(voloPartenza.getGateAssegnato());
            }

            Object[] riga = {
                    volo.getCodiceVolo(),
                    volo.getCompagniaAerea(),
                    volo.getAeroportoOrigine(),
                    volo.getAeroportoDestinazione(),
                    volo.getOraDataPartenza().format(formatter),
                    volo.getOraDataArrivo().format(formatter),
                    gate,
                    volo.getRitardo(),
                    volo.getStatoVolo().toString()
            };
            tableModel.addRow(riga);
        }
    }

    private void modificaDettagliVolo() {
        int rigaSelezionata = tabellaVoli.getSelectedRow();

        if (rigaSelezionata == -1) {
            JOptionPane.showMessageDialog(this,
                    "Seleziona un volo dalla tabella",
                    "Nessuna selezione",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Volo voloSelezionato = elencoVoli.get(rigaSelezionata);

        DialogModificaVolo dialog = new DialogModificaVolo(this, voloSelezionato, controller);
        dialog.setVisible(true);

        if (dialog.isModificaEffettuata()) {
            caricaVoli();
        }
    }

    private void modificaGateVolo() {
        int rigaSelezionata = tabellaVoli.getSelectedRow();

        if (rigaSelezionata == -1) {
            JOptionPane.showMessageDialog(this,
                    "Seleziona un volo dalla tabella",
                    "Nessuna selezione",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Volo voloSelezionato = elencoVoli.get(rigaSelezionata);

        if (!(voloSelezionato instanceof VoloPartenza)) {
            JOptionPane.showMessageDialog(this,
                    "Il gate può essere modificato solo per i voli in partenza",
                    "Operazione non consentita",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        VoloPartenza voloPartenza = (VoloPartenza) voloSelezionato;

        String gateAttuale = String.valueOf(voloPartenza.getGateAssegnato());
        String nuovoGate = (String) JOptionPane.showInputDialog(
                this,
                "Inserisci il nuovo gate per il volo " + voloPartenza.getCodiceVolo() + ":",
                "Modifica Gate",
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                gateAttuale
        );

        if (nuovoGate != null && !nuovoGate.trim().isEmpty()) {
            try {
                int nuovoGateInt = Integer.parseInt(nuovoGate.trim());

                boolean successo = controller.modificaGate(nuovoGateInt, voloPartenza.getCodiceVolo());

                if (successo) {
                    JOptionPane.showMessageDialog(this,
                            "Gate modificato con successo!",
                            "Successo",
                            JOptionPane.INFORMATION_MESSAGE);
                    caricaVoli();
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Errore durante la modifica del gate",
                            "Errore",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Il gate deve essere un numero valido",
                        "Errore di Input",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

class DialogModificaVolo extends JDialog {
    private boolean modificaEffettuata = false;
    private Volo volo;
    private Controller controller;

    private JTextField txtCompagnia;
    private JTextField txtOrigine;
    private JTextField txtDestinazione;
    private JTextField txtRitardo;
    private JComboBox<StatoVolo> comboStato;

    /**
     * Classe GUI inserita in ElencoVoliAmm. Gestisce la logica che permette all'amministratore di gestire i voli.
     * @param parent la classe Amministratore in cui è implementata la classe.
     * @param volo il volo che si intende aggiungere o modifcare.
     * @param controller riferimento al controller che gestisce la logica.
     */
    public DialogModificaVolo(JFrame parent, Volo volo, Controller controller) {
        super(parent, "Modifica Dettagli Volo", true);
        this.volo = volo;
        this.controller = controller;

        setSize(400, 350);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        JPanel panelCampi = new JPanel(new GridLayout(5, 2, 10, 10));
        panelCampi.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelCampi.add(new JLabel("Compagnia Aerea:"));
        txtCompagnia = new JTextField(volo.getCompagniaAerea());
        panelCampi.add(txtCompagnia);

        panelCampi.add(new JLabel("Aeroporto Origine:"));
        txtOrigine = new JTextField(volo.getAeroportoOrigine());
        panelCampi.add(txtOrigine);

        panelCampi.add(new JLabel("Aeroporto Destinazione:"));
        txtDestinazione = new JTextField(volo.getAeroportoDestinazione());
        panelCampi.add(txtDestinazione);

        panelCampi.add(new JLabel("Ritardo (minuti):"));
        txtRitardo = new JTextField(String.valueOf(volo.getRitardo()));
        panelCampi.add(txtRitardo);

        panelCampi.add(new JLabel("Stato Volo:"));
        comboStato = new JComboBox<>(StatoVolo.values());
        comboStato.setSelectedItem(volo.getStatoVolo());
        panelCampi.add(comboStato);

        add(panelCampi, BorderLayout.CENTER);

        JPanel panelPulsanti = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnSalva = new JButton("Salva");
        JButton btnAnnulla = new JButton("Annulla");

        btnSalva.addActionListener(e -> salvaModifiche());
        btnAnnulla.addActionListener(e -> dispose());

        panelPulsanti.add(btnSalva);
        panelPulsanti.add(btnAnnulla);

        add(panelPulsanti, BorderLayout.SOUTH);

        JLabel lblTitolo = new JLabel("Volo: " + volo.getCodiceVolo());
        lblTitolo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitolo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblTitolo, BorderLayout.NORTH);
    }

    private void salvaModifiche() {
        try {
            String compagnia = txtCompagnia.getText().trim();
            String origine = txtOrigine.getText().trim();
            String destinazione = txtDestinazione.getText().trim();
            int ritardo = Integer.parseInt(txtRitardo.getText().trim());
            StatoVolo stato = (StatoVolo) comboStato.getSelectedItem();

            if (compagnia.isEmpty() || origine.isEmpty() || destinazione.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Tutti i campi devono essere compilati",
                        "Campi Vuoti",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            volo.setCompagniaAerea(compagnia);
            volo.setAeroportoOrigine(origine);
            volo.setAeroportoDestinazione(destinazione);
            volo.setRitardo(ritardo);
            volo.setStatoVolo(stato);

            boolean successo = controller.modificaVolo(volo);

            if (successo) {
                modificaEffettuata = true;
                JOptionPane.showMessageDialog(this,
                        "Volo modificato con successo!",
                        "Successo",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Errore durante la modifica del volo",
                        "Errore",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Il ritardo deve essere un numero valido",
                    "Errore di Input",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isModificaEffettuata() {
        return modificaEffettuata;
    }
}