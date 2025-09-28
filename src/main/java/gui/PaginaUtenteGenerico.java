package gui;

import controller.Controller;

import javax.swing.*;

public class PaginaUtenteGenerico extends JFrame {

    private Controller controller;
    private JPanel UtenteGenericoPanel;

    public PaginaUtenteGenerico(Controller controller){
        this.controller = controller;

        setTitle("Aeroporto Di Napoli - Utente Generico");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(UtenteGenericoPanel);
    }
}
