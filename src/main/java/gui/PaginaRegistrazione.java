package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaginaRegistrazione extends JFrame {

    private Controller controller;
    private Homepage homepage;
    private JPanel RegistrazionePanel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JLabel usernameLabel;
    private JPanel InserimentoField;
    private JLabel passwordLabel;
    private JButton TORNAINDIETROButton;
    private JButton REGISTRATIButton;

    public PaginaRegistrazione(Controller controller, Homepage homepage){
        this.controller = controller;
        this.homepage = homepage;

        setTitle("Aeroporto Di Napoli - Registrazione");
        setSize(800
                ,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(RegistrazionePanel);


        TORNAINDIETROButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                homepage.setVisible(true);
            }
        });
    }
}
