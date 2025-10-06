
package main;

import gui.Homepage;
import controller.Controller;
import javax.swing.SwingUtilities;

/**
 * La classe Main è in punto di ingresso del programma.
 * Essa non fa altro che inizializzare la Homepage e creare l'oggetto controller che ha il compito di gestire la logica.
 * La classe presenta un metod invokeLater() chiamato con una lamba fuction che fa si che Swing gestisca correttamente l'esecuzione.
 */
public class  Main {

    /**
     * Costruttore privato. Non è possibile iniziare un oggetto della classe Main.
     */
    private Main(){}

    /**
     * Il punto di ingresso del programma. Inizializza solamente la homepage utilizzando una lambda function.
     * Il controllo del programma viene poi gestito dall'utente che interagisce con la GUI.
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            Controller controller = new Controller();
                Homepage homepage = new Homepage(controller);
                homepage.setVisible(true);
            });
    }
}