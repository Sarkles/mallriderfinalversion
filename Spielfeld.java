import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * Beschreiben Sie hier die Klasse Spielfeld.
 * 
 * @author Moritz Rembold, Jakob Linz, Karl Konrad Hanka, Emma Holtz, Fabian Turfan
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Spielfeld extends JPanel implements KeyListener
{
    //Wie schnell der Spieler faellt
    private static final double GRAVITY = 0.1;
    //Die Fahrebene des Cops
    private static final float GROUNDY = 150;
    //Der Character
    private Character character;
    //Der Hindernismanager
    private HindernisManager hindernisManager;
    //Der Hintergund
    private Hintergrund hintergrund;
    
    private int score;
    
    /**
     * Konstruktor für Objekte der Klasse Spielfeld
     */
    public Spielfeld()
    {
        character = new Character(GRAVITY, GROUNDY);
        hindernisManager = new HindernisManager(GRAVITY, GROUNDY);
        hintergrund = new Hintergrund();
        score = 0;
    }
    
    //Getter fuer die Konstanten
    public double getGRAVITY(){return GRAVITY;}
    public float getGROUNDY(){return GROUNDY;}
    
    /**
     * Auf allen bekannten Objekten werden ihre update Methoden aufgerufen und prüft die Hitboxen
     *      @return boolean Sollte der Spieler mit einem Hindernis zusammenstoßen, so wird false zurueckgegeben
     */
    public boolean update()
    {
        character.update();
        hindernisManager.update();
        hintergrund.update();
        //Score wird erhoeht
        score += 1;
        //Es wird geprueft ob sich die Hitboxen von Character und Hinderniss ueberschneiden
        if(hindernisManager.getHindernis().getHitbox().intersects(character.getHitbox())){return false;}
        else return true;
    }
    
    /**
     * Ruft die repaint Methode auf
     */
    public void render()
    {
        repaint();
    }
    
    @Override
    /**
     * erstellt die Grafiken für das Spielfeld
     *      @param Graphics g wird benoetigt um darauf die Zeichenmethoden aufzurufen
     */
    public void paint(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0,0,getWidth(),getHeight()); //Erschafft weißen Hintergrund
        g.setColor(Color.red);
        g.drawLine(0, (int) GROUNDY, 600, (int) GROUNDY); //Erschafft rote Grundlinie (Boden)
        Image img = Resource.getResourceImage("boden.png");
        g.drawImage(img, 0,(int) GROUNDY, null);//Zeichnet den Boden auf dem der Cop faehrt
        hintergrund.paint(g);
        character.paint(g);
        hindernisManager.paint(g); //Wichtigste Bestandteile werden extern erschaffen
        g.setColor(Color.black);
        g.drawString("Score: " + Integer.toString(score), 500, 160); //Erschafft die Score-Anzeige
    }
    
    /**
     * Eine KeyListener-Methode, muss ueberschrieben werden, ist aber in unserem Falle nicht
     * benoetigt, also leer
     */
    @Override
    public void keyTyped(KeyEvent e){
    
    }
    
    /**
     * Eine KeyListener-Methode, muss ueberschrieben werden
     * Achtet ("Listened") auf Tastendruecke
     */
    @Override
    public void keyPressed(KeyEvent e){
        //Sollte die w Taste gedreuckt werden und der Character sich auf der Fahrebene 
        //Befinden, so wird die jump methode aufgerufen
        if(character.getYPos() == GROUNDY - character.getBild1().getHeight() && e.getKeyChar() == 'w' )character.jump();
        //Sollte die s Taste gedrueckt werden und der Character sich auf der Fahrebene 
        //Befinden, so wird die cower methode aufgerufen
        if(character.getYPos() == GROUNDY - character.getBild1().getHeight() && e.getKeyChar() == 's' )character.cower();
    }
    
    /**
     * Eine KeyListener-Methode, muss ueberschrieben werden
     * Achtet ("Listened") auf die losgelassenen Tasten
     */
    @Override
    public void keyReleased(KeyEvent e){
        //wird die s Taste losgelassen, so wir die aufstehen Methode aufgerufen
        //und die Spielfigur richtet sich wieder auf
        if(character.getYPos() == GROUNDY - character.getBild1().getHeight() && e.getKeyChar() == 's' )character.aufstehen();
    }
}

