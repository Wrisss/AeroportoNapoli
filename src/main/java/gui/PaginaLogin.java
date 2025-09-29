package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaginaLogin extends JFrame {

    private Controller controller;
    private Homepage homepage;
    private JPanel LoginPanel;
    private JPanel panelTop;
    private JPanel panelBottom;
    private JTextField textField1;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordField1;
    private JTextArea LoginMsg;
    private JButton TORNAINDIETROButton;
    private JButton button2;

    public PaginaLogin(Controller controller, Homepage homepage){
        this.controller = controller;
        this.homepage = homepage;

        setTitle("Aeroporto Di Napoli - Login");
        setSize(700,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(LoginPanel);

        TORNAINDIETROButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                homepage.setVisible(true);
            }
        });
    }
}
