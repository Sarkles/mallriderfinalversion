import java.util.List;
import java.util.ArrayList;
import java.awt.Graphics;
import java.util.Random;
import java.awt.image.BufferedImage;

/**
 * Beschreiben Sie hier die Klasse HindernisManager.
 * 
 * @author (Emma Holtz, Fabian Turfan) 
 * @version (13.5.2021, Prototyp 1)
 */
public class HindernisManager
{
    private List<Hindernis> hindernisse;
    private Random random;
    //BodenHindernisse
    private BufferedImage picture1;
    private BufferedImage picture2;
    private BufferedImage picture3;
    private BufferedImage picture4;
    private BufferedImage picture5;
    //Voegel/fliegende Hindernisse
    private BufferedImage picture6;
    private BufferedImage picture7;
    
    private double gravity;
    private float groundy;
    private float speedX;
    
    public HindernisManager(double gravity, float groundy)
    {
        //Liste fuer die Speicherung von beliebigen Hindernisobjekten
        hindernisse = new ArrayList<Hindernis>();
        this.gravity = gravity;
        this.groundy = groundy;
        //Random wird fuer Zufallsgenerator erstellt
        random = new Random();
        //verschiedene Bilder fuer verschiedene Hindernistypen
        picture1 = Resource.getResourceImage("MuellSaecke.png");
        
        picture2 = Resource.getResourceImage("Muellsack.png");
        
        picture3 = Resource.getResourceImage("Bank.png");
        
        picture4 = Resource.getResourceImage("Baum.png");
        
        picture5 = Resource.getResourceImage("Schuhboxen.png");
        
        picture6 = Resource.getResourceImage("Kraehe.png");
        
        picture7 = Resource.getResourceImage("Taube.png");
        //erstes Hindernis der Liste wird erstellt und eingefuegt
        Hindernis hindernis = new Hindernis(gravity, groundy);
        hindernisse.add(getRandomHindernis2());
        speedX = -3;
    }
    
    public Hindernis getHindernis()
    {
        return hindernisse.get(0);
    }
    
    /**
     * Nach einem Durchlauf werden Eingaben verarbeitet
     * Code uebernommen von: Tpsoft Tutorials
     */
    public void update()
    {
        if (speedX >= -10) //eigene Ergänzung, nicht von Tpsoft Tutorials
        {
            speedX -= 0.002;  //Geschwindigkeit wird mit jedem Update erhoeht, bis zum Maximum von -15 
        }
        for (Hindernis e : hindernisse) //Liste wird durchgegangen und update wird auf einzelnen Hindernissen aufgerufen
        {
            e.update(speedX);
        }
        Hindernis erste = hindernisse.get(0); //erstes Hindernis wird in Variable gespeichert
        if(erste.istAusDemFeld())
        {
            hindernisse.remove(erste); //erstes wird geloescht, wenn es Feld verlaesst
            hindernisse.add(getRandomHindernis2()); //hinten wird neues, zufaelig erzeugtes Hindernis eingefuegt
        }
    }
    
    /**
     * Hindernisse werden einzeln neu gezeichnet, mit veraenderten Koordinaten
     */
    public void paint(Graphics g)
    {
        for(Hindernis e : hindernisse)
        {
            e.paint(g); 
        }
    }
    
    ///**
    // * Ein Hindernis wird aus den verschiedenen Moeglichkeiten zufaellig erzeugt und zurueckgegeben
    // * Code uebernommen von: Tpsoft Tutorials --> eine Moeglichkeit
    //  */
    //public Hindernis getRandomHindernis()
    //{
    //    Hindernis hindernis = new Hindernis(gravity, groundy); //neues Hindernis wird erstellt
    //    hindernis.setXPos(600); //und am Ende des bildes eigefuegt
    //    if(random.nextBoolean()) //ein beliebiges Bild wird ausgewaehlt
    //    {
    //        hindernis.setImage(picture7, 70);
    //    }
    //    else
    //    {
    //        hindernis.setImage(picture2, picture2.getHeight());
    //    }
    //    return hindernis; //Hindernis wird wiedergegeben
    //}
    
    /**
     * Ein Hindernis wird aus den verschiedenen Moeglichkeiten zufaellig erzeugt und zurueckgegeben
     * andere Moeglichkeit zum erzeugen neuer Hindernisse, selbst ueberlegt, nicht uebernommen
     */
    public Hindernis getRandomHindernis2()
    {
        Hindernis hindernis = new Hindernis(gravity, groundy); //neues Hindernis wird erstellt
        hindernis.setXPos(600); //und am Ende des bildes eigefuegt
        int a = random.nextInt(7); //jedes mal wird neue zufaellige Zahl erzeugt
        switch(a) //jedes mal wird ein neues Hindernis mit anderen Bild erzeugt
        {
            case 0:
            hindernis.setImage(picture1, picture1.getHeight());
            break;
            case 1:
            hindernis.setImage(picture2, picture2.getHeight());
            break;
            case 2:
            hindernis.setImage(picture3, picture3.getHeight());
            break;
            case 3:
            hindernis.setImage(picture4, picture4.getHeight());
            break;
            case 4:
            hindernis.setImage(picture5, picture5.getHeight());
            break;
            case 5:
            hindernis.setImage(picture6, 70);
            break;
            case 6:
            hindernis.setImage(picture7, 70);
            break;
        }
        return hindernis; //Hindernis wird wiedergegeben
    }
    
    public float getSpeedX()
    {
        return speedX;
    }
}