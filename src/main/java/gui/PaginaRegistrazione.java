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

    /**
     * Pannello principale.
     */
    private JPanel RegistrazionePanel;

    /**
     * Pannello di inserimenti.
     */
    private JPanel InserimentoField;

    /**
     * Campo per inserire l'username.
     */
    private JTextField usernameTextField;

    /**
     * Campo per inserire la password.
     */
    private JPasswordField passwordField1;

    /**
     * Label per indicare un errore di inserimento di un username.
     */
    private JLabel erroreLabelUsername;

    /**
     * Label per indicare un errore di inserimento della password.
     */
    private JLabel erroreLabelPassword;

    /**
     * Label di username.
     */
    private JLabel usernameLabel;

    /**
     * Label di password.
     */
    private JLabel passwordLabel;

    /**
     * Pulsante ´TORNA INDIETRO´. Ritorna alla schermata precedente.
     */
    private JButton TORNAINDIETROButton;

    /**
     * Pulsante 'REGISTRATI'. Permette all'utente di registrarsi.
     */
    private JButton REGISTRATIButton;

    /**
     * Costruttore della classe PaginaRegistrazione. Permette la creazione della finestra di registrazione.
     * Il costruttore crea una finestra con una serie di valori predefiniti che ne specificano la grafica.
     * Vengono inizializzati inoltre i pulsanti 'TORNAINDIETRO' e 'REGISTRATI'.
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


        TORNAINDIETROButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                homepage.setVisible(true);
            }
        });

        REGISTRATIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText().trim();
                String password = new String(passwordField1.getPassword()).trim();

                // Logica di controllo dell'input.
                if (username.length() < 6) {
                    erroreLabelUsername.setText("Username deve contenere almeno 6 caratteri");
                    erroreLabelUsername.setForeground(Color.RED);
                    return;
                }
                if (password.length() < 6) {
                    erroreLabelPassword.setText("Password deve contenere almeno 6 caratteri");
                    erroreLabelPassword.setForeground(Color.RED);
                    return;
                }

                usernameTextField.setText("");
                passwordField1.setText("");

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
        });
    }
}