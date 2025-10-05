package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe GUI che implementa la finestra per permettere all'utente di registrarsi nel sistema.
 */
public class PaginaRegistrazione extends JFrame {
    /**
     * Riferimento al controller{@link Controller} che gestisce la logica.
     */
    private Controller controller;
    /**
     * Riferimento alla Homepage per ritornare alla scherma iniziale.
     */
    private Homepage homepage;

    private JPanel RegistrazionePanel;

    private JTextField usernameTextField;
    private JPasswordField passwordField1;

    private JLabel erroreLabelUsername;
    private JLabel erroreLabelPassword;
    private JLabel usernameLabel;

    private JPanel InserimentoField;
    private JLabel passwordLabel;

    private JButton TORNAINDIETROButton;
    private JButton REGISTRATIButton;

    /**
     * Costruttore della classe PaginaRegistrazione. Permette la creazione della finestra di registrazione.
     * Il costruttore crea una finestra con una serie di valori predefiniti che ne specificano la grafica.
     * @param controller viene passato il riferimento a controller {@link Controller}
     * @param homepage viene passato il riferimento a homepage {@link Homepage}
     */
    public PaginaRegistrazione(Controller controller, Homepage homepage) {
        this.controller = controller;
        this.homepage = homepage;
        setTitle("Aeroporto Di Napoli - Registrazione");
        setSize(800
                , 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(RegistrazionePanel);

        /**
         * Pulsante che permette all'utente di ritornare alla schermata della Homepage.
         */
        TORNAINDIETROButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                homepage.setVisible(true);
            }
        });

        /**
         * Pulsante che permette all'utente registrare la propria utenza nel database.
         * È presente un controllo in immissione sia per il campo password che per il campo username.
         * In caso di errore di immissione da parte dell'utente viene mostrata una finestra di dialogo di errore.
         * In caso di successo viene mostrata una finestra di dialogo di successo. Dopodiché l'utente viene riportato
         * automaticamente alla Homepage.
         */
        REGISTRATIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText().trim();
                String password = new String(passwordField1.getPassword()).trim();

                boolean isValid = true;

                if (username.length() < 6) {
                    erroreLabelUsername.setText("Username deve contenere almeno 6 caratteri");
                    erroreLabelUsername.setForeground(Color.RED);
                    isValid = false;
                }

                if (password.length() < 6) {
                    erroreLabelPassword.setText("Password deve contenere almeno 6 caratteri");
                    erroreLabelPassword.setForeground(Color.RED);
                    isValid = false;
                }

                usernameTextField.setText("");
                passwordField1.setText("");

                if (isValid) {

                    boolean success = controller.registraUtente(username, password);

                    if (success) {
                        JOptionPane.showMessageDialog(
                                PaginaRegistrazione.this,
                                "Registrazione completata con successo!",
                                "Successo",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        dispose();
                        homepage.setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(
                                PaginaRegistrazione.this,
                                "Errore durante la registrazione. Riprova.",
                                "Errore",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}