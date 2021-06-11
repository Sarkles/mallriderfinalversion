import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
/**
* Beschreiben Sie hier die Klasse Character.
* 
* @author Moritz Rembold, Jakob Linz, Emma Holtz, Fabian Turfan 
* @version (eine Versionsnummer oder ein Datum)
*/
public class Character extends Entity
{
    private float speedY;
    private double gravity;
    private float groundy;
    private boolean bildwechsel;
    private BufferedImage bild1;
    private BufferedImage bild2;
        
    /**
     * Konstruktor für Objekte der Klasse Character
     */
    public Character(double gravity, float groundy)
    {
         //Die uebergebenen Konstanten werden den Attributen zugewiesen
         this.gravity = gravity;
         this.groundy = groundy;
         //Startkoordinaten werden festgelegt
         xPos = 20;
         yPos = groundy;
         //Hitbox wird erstellt
         hitbox = new Rectangle();
         //Hilfsvariable fuer Animation um einen Bildwechsel zu ermoeglichen
         bildwechsel = true;
         //Den beiden sich abwechselnden Bilder werden BufferedImages zugewiesen
         bild1 = Resource.getResourceImage("normalcop1.png");
         bild2 = Resource.getResourceImage("normalcop2.png");
    }
    
    //Getter und Setter fuer verschiedene Attribute
    public float getSpeedY(){return speedY;}
        
    public void setSpeedY(float speedY){this.speedY = speedY;}
    public Rectangle getHitbox() {return hitbox;}
    public void setBild1(BufferedImage image){bild1 = image;}
    public void setBild2(BufferedImage image){bild2 = image;}
    public BufferedImage getBild1() {return bild1;}
        
    /**
     * Update jedesmal aufgerufen bei Gameloop durchlauf
     * durchfuehrung der Schwerkraftsimulation, damit der Cop springen kann
     */
    public void update()
    {
         if(yPos >= groundy - bild1.getHeight())
         { //100 fuer obere linke Ecke von Character
             speedY = 0;
             //Koordinate des Test-Quadrats (100x100) wird oben links gemessen
             yPos = groundy - bild1.getHeight(); 
         }
         else
         {
             speedY += gravity; //speedY = speedY + GRAVITY   
             yPos += speedY;
         }
         //Die Koordinaten und Groeße der Hitboxen wird der des Bildes angepasst
         hitbox.x = (int) xPos;
         hitbox.y = (int) yPos;
         hitbox.width = 15;
         hitbox.height = bild1.getHeight();
    }

    /**
     * Jump Methode sorgt dafür, 
     * dass er über Hindernisse am Boden springen kann
     */
    public void jump(){
        //Geschwindigkeit fuer Aufwaertsbewegung festelegen
        speedY = (float)-3.5;
        // auf Position draufrechenen
        yPos+= speedY;
    }
    
    /**
     * Die Bilder des stehenden Cops werden durch einen Duckenden ersetzt,
     * damit er den fliegenden Hindernissen ausweichen kann
     */
    public void cower()
    {
        setBild1(Resource.getResourceImage("sneakingcop1.png"));
        setBild2(Resource.getResourceImage("sneakingcop2.png"));
    }
    
    /**
     * Die Bilder des duckenden Cops werden durch einen stehenden ersetzt,
     * damit er wieder aufstehen kann
     */
    public void aufstehen()
    {
        bild1 = Resource.getResourceImage("normalcop1.png");
        bild2 = Resource.getResourceImage("normalcop2.png");
    }
    
    /**
     * Die Spielfigur wird gezeichnet, je nachdem ob Bildwechsel true oder false ist
     * wird eine anderes Bild gezeichent, um Illusion von Bewegung zu erzeugen
     *      @param Graphics g Wird benoetigt um darauf die Zeichenmethoden aufzurufen
     */
    public void paint(Graphics g){//feld auf dem der Character gezeichnet wird
        if (bildwechsel == true)
        {
            g.drawImage(bild1,(int) xPos - 20, (int) yPos, null);
            bildwechsel = false;
        }
        else 
        {
            g.drawImage(bild2,(int) xPos - 20, (int) yPos, null);
            bildwechsel = true;
        }
    }
}
