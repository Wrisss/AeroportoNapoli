package gui;

import controller.Controller;

import javax.swing.*;

public class PaginaLogin extends JFrame {

    private Controller controller;
    private JPanel LoginPanel;

    public PaginaLogin(Controller controller){
        this.controller = controller;

        setTitle("Aeroporto Di Napoli - Login");
        setSize(300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(LoginPanel);
    }
}
