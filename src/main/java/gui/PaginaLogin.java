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

    /**
     * Pannello principale.
     */
    private JPanel LoginPanel;

    /**
     * Pannello Top.
     */
    private JPanel panelTop;

    /**
     * Pannello Bottom.
     */
    private JPanel panelBottom;

    /**
     * Label Username.
     */
    private JLabel usernameLabel;

    /**
     * Label Password.
     */
    private JLabel passwordLabel;

    /**
     * Campo dove inserire l'username.
     */
    private JTextField usernameTextField;

    /**
     * Campo dove inserire la password.
     */
    private JPasswordField passwordField1;

    /**
     * OptionPane per avvisare l'utente in caso di riuscita o errore nel login.
     */
    private JOptionPane jOptionPane;

    /**
     * Messaggio di benvenuto al login.
     */
    private JTextArea LoginMsg;

    /**
     * Pulsante 'TORNA INDIETRO'. Ritorna alla schermata precedente.
     */
    private JButton TORNAINDIETROButton;

    /**
     * Pulsante 'LOGIN'. Permette l'accesso alla propria area riservata.
     */
    private JButton LOGINButton;

    /**
     * Costruttore della classe PaginaLogin che istanzia la finestra di Login.
     * La pagina di Login viene creata con una serie di valori predefiniti che ne specificano la grafica.
     * Vengono inoltre inizializzati i pulsanti 'LOGIN' e 'TORNA INDIETRO'.
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


        TORNAINDIETROButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                homepage.setVisible(true);
            }
        });


        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = usernameTextField.getText().trim();
                String password = new String(passwordField1.getPassword()).trim();

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Inserisci username e password",
                            "Errore",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Superutente utenteLoggato = controller.login(username, password);

                if (utenteLoggato == null) {
                    JOptionPane.showMessageDialog(null,
                            "Errore di autenticazione: Username o password errati",
                            "Errore",
                            JOptionPane.ERROR_MESSAGE);
                }

                else {
                    if (utenteLoggato instanceof Amministratore) {
                        PaginaAmministratore paginaAmministratore = new PaginaAmministratore(controller, username);
                        paginaAmministratore.setVisible(true);
                    }
                    else if (utenteLoggato instanceof UtenteGenerico) {
                        PaginaUtenteGenerico paginaUtenteGenerico = new PaginaUtenteGenerico(controller,
                                (UtenteGenerico) utenteLoggato);
                        paginaUtenteGenerico.setVisible(true);
                        dispose();}
                }
            }
        });
    }
}
