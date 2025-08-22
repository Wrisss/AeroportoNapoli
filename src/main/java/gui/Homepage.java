package gui;

import controller.Controller;
import javax.swing.*;

public class Homepage extends JFrame {
    /**
     * Nella classe Homepage dell'applicativo è presente un riferimento hard-coded alla classe Controller che gestisce
     * interamente la logica dell'applicazione. L'inserimento di un riferimento alla classe Controller, tra gli attributi
     * della classe Homepage, crea un legame forte tra le due classi che possono scambiarsi il controllo vicendevolmente.
     */
    private Controller controller;
    private JPanel panelHomepage;

    public Homepage(Controller controller){
        this.controller = controller;

    }
}

