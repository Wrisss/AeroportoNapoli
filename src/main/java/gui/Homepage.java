package gui;

import controller.Controller;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class Homepage extends JFrame {

    private Controller controller;

    private JPanel HomepagePanel;

    private JTextArea BenvenutoMsg;
    private JButton ACCEDIButton;
    private JButton REGISTRATIButton;

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
                PaginaLogin paginaLogin = new PaginaLogin(controller);
                paginaLogin.setVisible(true);



            }
        });

        REGISTRATIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                PaginaRegistrazione paginaRegistrazione = new PaginaRegistrazione(controller);
                paginaRegistrazione.setVisible(true);


            }
        });

    }


}
