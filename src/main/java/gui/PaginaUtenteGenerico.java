package gui;

import controller.Controller;
import model.UtenteGenerico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaginaUtenteGenerico extends JFrame {

    private Controller controller;
    private UtenteGenerico utenteCorrente;
    private String username;
    private ElencoVoliUG elencoVoliUG;
    private PrenotazioniUG leMiePrenotazioni;

    private JPanel UtenteGenericoPanel;
    private JLabel labelBenvenuto;
    private JButton ELENCOVOLIButton;
    private JButton LEMIEPRENOTAZIONIButton;

    public PaginaUtenteGenerico(Controller controller, PaginaLogin paginaLogin, UtenteGenerico utenteCorrente){
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
                        PaginaUtenteGenerico.this, utenteCorrente);
                leMiePrenotazioni.setVisible(true);


            }
        });
    }
}
