package gui;

import controller.Controller;

import javax.swing.*;

public class PaginaRegistrazione extends JFrame {

    private Controller controller;
    private JPanel RegistrazionePanel;

    public PaginaRegistrazione(Controller controller){
        this.controller = controller;

        setTitle("Aeroporto Di Napoli - Registrazione");
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(RegistrazionePanel);
    }
}
