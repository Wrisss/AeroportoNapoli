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

    /**
     * Costruttore della Homepage.  Viene passato come parametro un riferimento all'oggetto controller della classe Controller
     * Il passaggio come parametro di un riferimento a un'altra classe è una tecnica che permette la comunicazione tra
     * due oggetti di classi distinti –Dependency Injection tramite costruttore–.
     * @param controller il controller unico della classe Controller.
     */
    public Homepage(Controller controller){
        this.controller = controller;

    }
}

