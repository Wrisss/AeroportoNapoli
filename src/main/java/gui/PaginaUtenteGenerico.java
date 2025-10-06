package gui;

import controller.Controller;
import model.UtenteGenerico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe GUI che imposta la finestra di visualizzazione di un utente generico dopo che ha effettuato il login.
 */
public class PaginaUtenteGenerico extends JFrame {

    /**
     * Riferimento al controller che si occupa della logica.
     */
    private Controller controller;

    /**
     * Riferimento all'utente specifico che effettua il login.
     */
    private UtenteGenerico utenteCorrente;

    /**
     * Pannello principale.
     */
    private JPanel UtenteGenericoPanel;

    /**
     * Label che mostra un messaggio di benvenuto.
     */
    private JLabel labelBenvenuto;

    /**
     * Pulsante 'ELENCO VOLI'. Permette all'utente di mostrare una nuova finestra con tutti i voli presenti nel database.
     */
    private JButton ELENCOVOLIButton;

    /**
     * Pulsante 'LE MIE PRENOTAZIONI'. Permette all'utente di gestire le proprie prenotazioni.
     */
    private JButton LEMIEPRENOTAZIONIButton;

    /**
     * Costruttore della classe PaginaUtenteGenerico che istanzia la finestra di gestione per l'utente generico.
     * Il costruttore inizializza la finestra con una serie di valori predefiniti che ne specificano la grafica.
     * Vengono inoltre creati i pulsanti 'ELENCO VOLI' e 'LE MIE PRENOTAZIONI'.
     * @param controller riferimento a controller che ne gestisce la logica.
     * @param utenteCorrente riferimento all'utente che ha effettua il login.
     */
    public PaginaUtenteGenerico(Controller controller, UtenteGenerico utenteCorrente){
        this.controller = controller;
        this.utenteCorrente = utenteCorrente;

        setTitle("Aeroporto Di Napoli - Utente Generico");
        setSize(700,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(UtenteGenericoPanel);

        labelBenvenuto.setText("Benvenuto, " + utenteCorrente.getUsername() + "!");
        labelBenvenuto.setFont(new Font("Arial", Font.BOLD, 22));

        ELENCOVOLIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ElencoVoliUG elencoVoliUG = new ElencoVoliUG(controller, PaginaUtenteGenerico.this);
                elencoVoliUG.setVisible(true);

            }
        });

        LEMIEPRENOTAZIONIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrenotazioniUG leMiePrenotazioni = new PrenotazioniUG(controller,
                         utenteCorrente);
                leMiePrenotazioni.setVisible(true);
            }
        });
    }
}
