import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Beschreiben Sie hier die Klasse Hindernis.
 * 
 * @author Moritz Rembold, Jakob Linz, Emma Holtz und Fabian Turfan
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Hindernis extends Entity
{
    private Spielfeld spielfeld;
    private BufferedImage image;
    private double gravity;
    private float groundy;
    
    /**
     * Konstruktor um ein Hindernis zu erstellen
     */
    public Hindernis(double gravity, float groundy){
        this.gravity = gravity;
        this.groundy = groundy;
        xPos = 600;
        yPos = groundy -20;
        hitbox = new Rectangle();
    }
    
    /**
     * Dem Hindernis wird per Parameter ein Bild zugewiesen,
     * welche in Hindernismanager gespeichert sind.
     * es gitb mehrere Arten von Bildern, also auch mehrere Hindernisse
     */
    public void setImage(BufferedImage image, float a) {
        this.image = image;
        setYPos(groundy-a);//sicherstellen, dass das ganze Bild ueber dem Boden liegt
    }
    
    /**
     * Nach einem Durchlauf werden Eingaben verarbeitet
     */
    public void update(float speedX){
        xPos += speedX; //druch Verringerung der xKoordinate entsteht Bewegung nach links
        //Stelle des Hindernisses wird an Hitbox uebergeben, sodass diese sich mitbewegt
        hitbox.x = (int) xPos -15;
        hitbox.y = (int) yPos;
        //Hitbox Groesse wird angepasst
        hitbox.width = image.getWidth();
        hitbox.height = image.getHeight();
    }
    
    /**
     * Ueberpruefung, ob Hindernis sich ausserhalb des Spielfeldes befindet
     */
    public boolean istAusDemFeld()
    {
        if(xPos <= -22) return true;
        else return false;     
    }
    
    /**
     * Hindernis wird mit neuen Koordinaten neu gezeichnet
     */
    public void paint(Graphics g){
        //Graphik wird auf Leinwand gemalt
        g.drawImage(image,(int) xPos - 20, (int) yPos, null);
    }
}
