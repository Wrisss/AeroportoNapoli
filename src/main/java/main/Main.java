
package main;

import gui.Homepage;
import controller.Controller;
import javax.swing.SwingUtilities;

public class  Main {

    private Main(){}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            Controller controller = new Controller();
                Homepage homepage = new Homepage(controller);
                homepage.setVisible(true);
            });
    }
}