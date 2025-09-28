package gui;

import controller.Controller;

import javax.swing.*;

public class PaginaAmministratore extends JFrame {

    private Controller controller;
    private JPanel AmministratorePanel;

    public PaginaAmministratore(Controller controller){
        this.controller = controller;

        setTitle("Aeroporto Di Napoli - Amministratore");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(AmministratorePanel);
    }

}
