package gui;

import controller.Controller;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La Homepage è la prima pagina che viene caricata all'avvio dell applicazione.
 */
public class Homepage extends JFrame {

    /**
     * Viene specificato tra gli attributi un oggetto controller della classe Controller che permette
     * la gestione della logica dell'applicazione aeroportuale.
     */
    private Controller controller;

    /**
     * Il pannello principale dell'applicazione.
     */
    private  JPanel HomepagePanel;

    /**
     * JTextArea mostra il messaggio di Benvenuto.
     */
    private JTextArea BenvenutoMsg;

    /**
     * Bottone che serve per accedere ai servizi dell'applicazione.
     */
    private JButton ACCEDIButton;

    /**
     * Bottone che serve a registrarsi al sistema. Gli amministratori non possono registrarsi per motivi di sicurezza.
     */
    private JButton REGISTRATIButton;

    /**
     * Costruttore della classe Homepage che inizializza una serie di interfacce basilari come titolo e layout generale.
     * @param controller il controller passato come riferimento al costruttore
     */
    public Homepage(Controller controller) {
        this.controller = controller;

        setTitle("Aeroporto di Napoli - Homepage");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(HomepagePanel);


        ACCEDIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                PaginaLogin paginaLogin = new PaginaLogin(controller, Homepage.this);
                paginaLogin.setVisible(true);

            }
        });

        REGISTRATIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                PaginaRegistrazione paginaRegistrazione = new PaginaRegistrazione(controller, Homepage.this);
                paginaRegistrazione.setVisible(true);

            }
        });
    }


}
