import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Beschreiben Sie hier die Klasse GUI.
 * 
 * @author Moritz Rembold, Jakob Linz, Karl Konrad Hanka
 * @version (eine Versionsnummer oder ein Datum)
 */
public class GUI extends JFrame
{
    private Spielfeld spielfeld;
    private Game game;
    
    /**
     * Startmethode
     */
    public static void main(String args[]){
        //GUI Objekt wird erstellt
        GUI gui = new GUI();
        //GUI wird sichtbar gestellt
        gui.setVisible(true);
        //Spiel wird gestartet
        gui.startGame();
    }
    
    /**
     * Konstruktor für Objekte der Klasse GUI
     */
    public GUI()
    {
        super("Mall Rider");
        //Fenstergroesse wird festgelegt
        setSize(600,200);
        
        //Groesse nicht mehr aenderabar
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Fenster erscheint in der Mitte des Bildschirms
        setLocationRelativeTo(null);
        
        //Spielfeld wird erstellt
        spielfeld = new Spielfeld();
        
        //Game wird erstellt
        game = new Game(spielfeld);
        
        //Spielfeld wird hinzugefuegt
        add(spielfeld);
        //Spielfeld als KeyListener hinzugefuegt
        addKeyListener(spielfeld);
    }
    
    /**
     * Auf dem Game Objekt wird das Spiel gestartet
     */
    public void startGame()
    {
        game.run();
    }
}
