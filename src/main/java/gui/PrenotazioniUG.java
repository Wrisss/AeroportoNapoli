package gui;

import controller.Controller;
import model.Prenotazione;
import model.UtenteGenerico;
import model.StatoPrenotazione;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PrenotazioniUG extends JFrame {

    private Controller controller;
    private UtenteGenerico utenteCorrente;

    private JPanel panelPrenotazioni;
    private DefaultTableModel tableModel;
    private JTable tabellaPrenotazioni;
    private List<Prenotazione> elencoPrenotazioni;

    public PrenotazioniUG(Controller controller, PaginaUtenteGenerico paginaUtenteGenerico, UtenteGenerico utente) {
        this.controller = controller;
        this.utenteCorrente = utente;

        setTitle("Elenco Prenotazioni - PER PRENOTARE UN VOLO È NECESSARIO CONOSCERE IL PROPRIO ID");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        inizializzaComponenti();
        caricaPrenotazioniUtente();
        setVisible(true);
    }

    private void inizializzaComponenti() {
        setLayout(new BorderLayout(10, 10));

        // Definizione colonne tabella
        String[] colonne = {"ID Prenotazione", "ID Volo", "Passeggero",
                "Numero Biglietto", "Posto", "Stato"};
        tableModel = new DefaultTableModel(colonne, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabellaPrenotazioni = new JTable(tableModel);
        tabellaPrenotazioni.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabellaPrenotazioni.setRowHeight(25);

        // Imposta larghezze colonne
        tabellaPrenotazioni.getColumnModel().getColumn(0).setPreferredWidth(80);
        tabellaPrenotazioni.getColumnModel().getColumn(1).setPreferredWidth(60);
        tabellaPrenotazioni.getColumnModel().getColumn(2).setPreferredWidth(150);
        tabellaPrenotazioni.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabellaPrenotazioni.getColumnModel().getColumn(4).setPreferredWidth(50);
        tabellaPrenotazioni.getColumnModel().getColumn(5).setPreferredWidth(80);

        JScrollPane scrollPane = new JScrollPane(tabellaPrenotazioni);
        add(scrollPane, BorderLayout.CENTER);

        // Pannello per i pulsanti di ricerca
        JPanel panelRicerca = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JTextField txtCercaVolo = new JTextField(10);
        JTextField txtCercaPasseggero = new JTextField(15);
        JButton btnCercaVolo = new JButton("Cerca per ID Volo");
        JButton btnCercaPasseggero = new JButton("Cerca per Passeggero");
        JButton btnReset = new JButton("Mostra Tutte");

        panelRicerca.add(new JLabel("ID Volo:"));
        panelRicerca.add(txtCercaVolo);
        panelRicerca.add(btnCercaVolo);
        panelRicerca.add(new JLabel("Passeggero:"));
        panelRicerca.add(txtCercaPasseggero);
        panelRicerca.add(btnCercaPasseggero);
        panelRicerca.add(btnReset);

        // Pannello per i pulsanti operativi
        JPanel panelPulsanti = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnAggiornaStato = new JButton("Aggiorna Stato");
        JButton btnAggiorna = new JButton("Aggiorna Tabella");
        JButton btnAnnulla = new JButton("Annulla");

        panelPulsanti.add(btnAggiornaStato);
        panelPulsanti.add(btnAggiorna);
        panelPulsanti.add(btnAnnulla);

        // Aggiungi action listeners
        btnCercaVolo.addActionListener(e -> {
            try {
                int idVolo = Integer.parseInt(txtCercaVolo.getText().trim());
                cercaPerIdVolo(idVolo);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Inserire un ID volo valido");
            }
        });

        btnCercaPasseggero.addActionListener(e -> {
            String nomePasseggero = txtCercaPasseggero.getText().trim();
            if (!nomePasseggero.isEmpty()) {
                cercaPerPasseggero(nomePasseggero);
            } else {
                JOptionPane.showMessageDialog(this, "Inserire un nome passeggero");
            }
        });

        btnReset.addActionListener(e -> caricaPrenotazioniUtente());
        btnAggiorna.addActionListener(e -> caricaPrenotazioniUtente());
        btnAggiornaStato.addActionListener(e -> aggiornaStatoPrenotazione());
        btnAnnulla.addActionListener(e -> dispose());

        // Layout principale
        JPanel panelNord = new JPanel(new BorderLayout());
        panelNord.add(panelRicerca, BorderLayout.NORTH);

        JLabel lblInfo = new JLabel("Le tue prenotazioni - Usa i filtri per cercare specifiche prenotazioni");
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
        lblInfo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panelNord.add(lblInfo, BorderLayout.CENTER);

        add(panelNord, BorderLayout.NORTH);
        add(panelPulsanti, BorderLayout.SOUTH);
    }

    private void caricaPrenotazioniUtente() {
        aggiornaTabella(controller.getTuttePrenotazioni(utenteCorrente));
    }

    private void cercaPerIdVolo(int idVolo) {
        // Recupera l'ID utente dal database usando il metodo che hai implementato
        int idUtente = controller.getIdUtenteDaUsername(utenteCorrente.getUsername());
        if (idUtente != -1) {
            aggiornaTabella(controller.getTuttePrenotazioniByIdVolo(idVolo, idUtente));
        } else {
            JOptionPane.showMessageDialog(this, "Errore nel recupero ID utente");
        }
    }

    private void cercaPerPasseggero(String nomePasseggero) {
        // Recupera l'ID utente dal database
        int idUtente = controller.getIdUtenteDaUsername(utenteCorrente.getUsername());
        if (idUtente != -1) {
            aggiornaTabella(controller.getTuttePrenotazioniByPasseggero(nomePasseggero, idUtente));
        } else {
            JOptionPane.showMessageDialog(this, "Errore nel recupero ID utente");
        }
    }

    private void aggiornaTabella(List<Prenotazione> prenotazioni) {
        tableModel.setRowCount(0);
        this.elencoPrenotazioni = prenotazioni;

        if (prenotazioni.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nessuna prenotazione trovata");
            return;
        }

        for (Prenotazione prenotazione : prenotazioni) {
            // Usa il metodo getNomeCompleto() che hai aggiunto a Passeggero
            String nomePasseggero = prenotazione.getPasseggero().getNomeCompletoPasseggero();

            Object[] riga = {
                    prenotazione.getIdPrenotazione(),
                    prenotazione.getIdVolo(),
                    nomePasseggero,
                    prenotazione.getNumeroBiglietto(),
                    prenotazione.getPostoAssegnato(),
                    prenotazione.getStatoPrenotazione().toString().toLowerCase() // Converti in lowercase per il display
            };
            tableModel.addRow(riga);
        }
    }

    private void aggiornaStatoPrenotazione() {
        int rigaSelezionata = tabellaPrenotazioni.getSelectedRow();

        if (rigaSelezionata == -1) {
            JOptionPane.showMessageDialog(this, "Seleziona una prenotazione dalla tabella");
            return;
        }

        int idPrenotazione = (int) tableModel.getValueAt(rigaSelezionata, 0);
        String nomePasseggero = (String) tableModel.getValueAt(rigaSelezionata, 2);

        // Crea stati in lowercase per il database
        StatoPrenotazione[] stati = StatoPrenotazione.values();
        String[] statiLowerCase = new String[stati.length];
        for (int i = 0; i < stati.length; i++) {
            statiLowerCase[i] = stati[i].toString().toLowerCase();
        }

        String nuovoStato = (String) JOptionPane.showInputDialog(
                this,
                "Aggiorna stato per " + nomePasseggero + ":",
                "Aggiorna Stato",
                JOptionPane.QUESTION_MESSAGE,
                null,
                statiLowerCase,
                statiLowerCase[0]
        );

        if (nuovoStato != null) {
            // Converti la stringa lowercase back a StatoPrenotazione
            try {
                StatoPrenotazione statoEnum = StatoPrenotazione.valueOf(nuovoStato.toUpperCase());
                boolean successo = controller.aggiornaPrenotazioneController(idPrenotazione, statoEnum);

                if (successo) {
                    JOptionPane.showMessageDialog(this, "Stato aggiornato con successo!");
                    caricaPrenotazioniUtente(); // Ricarica per vedere le modifiche
                } else {
                    JOptionPane.showMessageDialog(this, "Errore durante l'aggiornamento");
                }
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, "Stato non valido");
            }
        }
    }
}