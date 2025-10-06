package gui;

import controller.Controller;

import model.Volo;
import model.VoloPartenza;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ElencoVoliUG extends JFrame{

    private Controller controller;
    private List<Volo> elencoVoli;
    private PaginaUtenteGenerico paginaUtenteGenerico;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private JTable tabellaVoli;
    private DefaultTableModel tableModel;
    private JPanel panelElencoVoli;

    public ElencoVoliUG(Controller controller, PaginaUtenteGenerico paginaUtenteGenerico){
        this.controller = controller;
        this.paginaUtenteGenerico = paginaUtenteGenerico;

        setTitle("Elenco dei voli");
        setSize(1300,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
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
        JButton btnPrenota = new JButton("Prenota");


        btnAnnulla.addActionListener(e -> tornaIndietro());
        btnPrenota.addActionListener(e -> prenotaVolo());


        panelPulsanti.add(btnAnnulla);
        panelPulsanti.add(btnPrenota);

        add(panelPulsanti, BorderLayout.SOUTH);

        JLabel lblInfo = new JLabel("Seleziona un volo dalla tabella per effettuare una prenotazione.");
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
        lblInfo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(lblInfo, BorderLayout.NORTH);
    }

    private void tornaIndietro(){
        dispose();
        paginaUtenteGenerico.setVisible(true);
    }

    private void prenotaVolo() {
        int rigaSelezionata = tabellaVoli.getSelectedRow();

        if (rigaSelezionata == -1) {
            JOptionPane.showMessageDialog(this,
                    "Seleziona un volo dalla tabella",
                    "Nessuna Selezione",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        int codiceVolo = (int) tableModel.getValueAt(rigaSelezionata, 0);

        DialogPrenotazione dialog = new DialogPrenotazione(this, controller, codiceVolo);
        dialog.setVisible(true);
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
                    volo.getStatoVolo().toString().toLowerCase()
            };
            tableModel.addRow(riga);
        }
    }

    public class DialogPrenotazione extends JDialog {
        private boolean prenotazioneEffettuata = false;
        private Controller controller;
        private int codiceVolo;

        private JTextField txtUsername;
        private JTextField txtNomePasseggero;
        private JTextField txtCognomePasseggero;
        private JSpinner spinnerPosto;

        public DialogPrenotazione(JFrame parent, Controller controller, int codiceVolo) {
            super(parent, "Prenota Volo", true);
            this.controller = controller;
            this.codiceVolo = codiceVolo;

            setSize(500, 400);
            setLocationRelativeTo(parent);
            setLayout(new BorderLayout(10, 10));

            inizializzaComponenti();
        }

        private void inizializzaComponenti() {
            JPanel panelCampi = new JPanel(new GridLayout(5, 2, 10, 10));
            panelCampi.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            panelCampi.add(new JLabel("Username:"));
            txtUsername = new JTextField();
            panelCampi.add(txtUsername);

            panelCampi.add(new JLabel("Nome Passeggero:"));
            txtNomePasseggero = new JTextField();
            panelCampi.add(txtNomePasseggero);

            panelCampi.add(new JLabel("Cognome Passeggero:"));
            txtCognomePasseggero = new JTextField();
            panelCampi.add(txtCognomePasseggero);

            panelCampi.add(new JLabel("Posto Scelto:"));
            spinnerPosto = new JSpinner(new SpinnerNumberModel(1, 1, 300, 1));
            panelCampi.add(spinnerPosto);

            panelCampi.add(new JLabel("Codice Volo:"));
            JLabel lblCodiceVolo = new JLabel(String.valueOf(codiceVolo));
            panelCampi.add(lblCodiceVolo);

            add(panelCampi, BorderLayout.CENTER);

            JPanel panelPulsanti = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            JButton btnPrenota = new JButton("Conferma Prenotazione");
            JButton btnAnnulla = new JButton("Annulla");

            btnPrenota.addActionListener(e -> confermaPrenotazione());
            btnAnnulla.addActionListener(e -> dispose());

            panelPulsanti.add(btnPrenota);
            panelPulsanti.add(btnAnnulla);

            add(panelPulsanti, BorderLayout.SOUTH);

            JLabel lblTitolo = new JLabel("Prenotazione Volo " + codiceVolo);
            lblTitolo.setFont(new Font("Arial", Font.BOLD, 16));
            lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
            lblTitolo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            add(lblTitolo, BorderLayout.NORTH);
        }

        private void confermaPrenotazione() {
            String usernameTxt = txtUsername.getText().trim();
            String nomePasseggero = txtNomePasseggero.getText().trim();
            String cognomePassegero = txtCognomePasseggero.getText().trim();
            int postoScelto = (Integer) spinnerPosto.getValue();

            if (nomePasseggero.isEmpty() || cognomePassegero.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Compila tutti i campi.",
                        "Campo Obbligatorio",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            int idUtente = controller.getIdUtenteDaUsername(usernameTxt);

            if (idUtente == -1) {
                JOptionPane.showMessageDialog(this,
                        "Username non trovato nel sistema.",
                        "Errore",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String nomeCompleto = nomePasseggero + " " + cognomePassegero;

            boolean successo = controller.prenotaVoloController(codiceVolo, idUtente, nomeCompleto, postoScelto);

            if (successo) {
                prenotazioneEffettuata = true;
                JOptionPane.showMessageDialog(this,
                        "Prenotazione effettuata con successo per " + nomeCompleto + "!",
                        "Successo",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Errore durante la prenotazione",
                        "Errore",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        public boolean isPrenotazioneEffettuata() {
            return prenotazioneEffettuata;
        }
    }
}
