package gui;

import controller.Controller;
import model.Amministratore;
import model.Superutente;
import model.UtenteGenerico;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe GUI che permette all'utente di effettuare il login nel sistema se si è registrato correttamente.
 */
public class PaginaLogin extends JFrame {
    /**
     * Riferimento al controller{@link Controller} che gestisce la logica.
     */
    private Controller controller;
    /**
     * Riferimento alla Homepage per ritornare alla scherma di accesso.
     */
    private Homepage homepage;

    private JPanel LoginPanel;
    private JPanel panelTop;
    private JPanel panelBottom;

    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameTextField;
    private JPasswordField passwordField1;
    private JOptionPane jOptionPane;

    private JTextArea LoginMsg;
    private JButton TORNAINDIETROButton;
    private JButton LOGINButton;

    /**
     * Costruttore della classe PaginaLogin che istanzia la finestra di Login.
     * La pagina di Login viene creata con una serie di valori predefiniti che ne specificano la grafica.
     * @param controller riferimento al controller
     * @param homepage riferimento alla homepage
     */
    public PaginaLogin(Controller controller, Homepage homepage) {
        this.controller = controller;
        this.homepage = homepage;

        setTitle("Aeroporto Di Napoli - Login");
        setSize(700, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(LoginPanel);

        /**
         * Pulsante che permette all'utente di ritornare alla schermata precedente.
         */
        TORNAINDIETROButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                homepage.setVisible(true);
            }
        });

        /**
         * Pulsante che permette all'utente di effettuare il Login.
         * È presente qui solo la logica di correttezza di immissione (l'utente non può inserire campi vuoi).
         * A seconda delle credenziali inserite dall'utente esso verrà reindirizzato nella finestra corretta.
         * Se l'utente non è presente nel database viene mostrata una finestra di dialogo di errore.
         */
        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // input dall'utente
                String username = usernameTextField.getText().trim();
                String password = new String(passwordField1.getPassword()).trim();

                // verifica dell'input
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Inserisci username e password",
                            "Errore",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // variabile per verificare se l'utente è inserito nel database.
                Superutente utenteLoggato = controller.login(username, password);

                // verifica fallita
                if (utenteLoggato == null) {
                    JOptionPane.showMessageDialog(null,
                            "Errore di autenticazione: Username o password errati",
                            "Errore",
                            JOptionPane.ERROR_MESSAGE);
                }
                // verifica riuscita
                else {
                    if (utenteLoggato instanceof Amministratore) {
                        PaginaAmministratore paginaAmministratore = new PaginaAmministratore(controller,
                                PaginaLogin.this, username);
                        paginaAmministratore.setVisible(true);


                    } else if (utenteLoggato instanceof UtenteGenerico) {
                        PaginaUtenteGenerico paginaUtenteGenerico = new PaginaUtenteGenerico(controller,
                                PaginaLogin.this, username);
                        paginaUtenteGenerico.setVisible(true);
                        dispose();}
                }
            }
        });
    }
}
