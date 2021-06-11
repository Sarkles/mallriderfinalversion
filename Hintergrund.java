import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import java.util.*;
/**
 * Beschreiben Sie hier die Klasse Hintergrund.
 * 
 * @author Jakob Linz, Moritz Rembold, Karl Konrad Hanka
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Hintergrund
{    
    //Geschwindigkeit mit der sich der Hintergrund bewegt
    private float speedX;
    //Startpositionen der vier verschiedenen Hintergrundbilder
    private int xPos1 = 0;
    private int xPos2 = 200;
    private int xPos3 = 400;
    private int xPos4 = 600;
    //Speicherung der vier sich in update() aendernden verschiedenen Hintergrundbilder
    private BufferedImage img1;
    private BufferedImage img2;
    private BufferedImage img3;
    private BufferedImage img4;
    //ArrayList um alle Bilder aus dem Images-Ordner als BifferedImage-Objekte zu sammeln 
    private ArrayList <BufferedImage> hg = new ArrayList();
    /**
     * Konstruktor für Objekte der Klasse Hintergrund
     */
    public Hintergrund()
    {
        //Die verschiedenen Bilder werden ins Array gepackt
        hg.add(Resource.getResourceImage("shop1-blau.png"));
        hg.add(Resource.getResourceImage("shop2-dunkelblau.png"));
        hg.add(Resource.getResourceImage("shop1-pink.png"));
        hg.add(Resource.getResourceImage("shop2-gruen.png"));
        hg.add(Resource.getResourceImage("shop2-orange.png"));
        hg.add(Resource.getResourceImage("mauer.png"));
        hg.add(Resource.getResourceImage("mauer.png"));
        hg.add(Resource.getResourceImage("mauer.png"));
        //Jedes der vier Images kriegt ein Zufaelligen Array-Index (damit ein Bild) zugewiesen
        img1 = hg.get(getRandom());
        img2 = hg.get(getRandom());
        img3 = hg.get(getRandom());
        img4 = hg.get(getRandom());
        //Die Geschwindigkeit des Hintergrunds wird festgelegt
        speedX = -3;
    }

    /**
     * "Malt" die vier BufferedImage-Attribute nebeneinander und fuellt so den Hintergrund
     */
    public void paint(Graphics g)
    {
        //Ueberpruefung ob die jeweiligen Bilder bereits mit gesamter Breite (200px) aus dem
        //sichtbaren Bereich sind
        if(xPos1 > -198)g.drawImage(img1, xPos1,0,null); //Attribut-Image 1 wird gezeichnet
        else g.drawImage(hg.get(getRandom()), xPos1,0,null); //Zufaelliges Bild wird gezeichnet
        
        if(xPos2 >-198)g.drawImage(img2, xPos2,0,null);//Attribut-Image 2 wird gezeichnet
        else g.drawImage(hg.get(getRandom()), xPos2,0,null);//Zufaelliges Bild wird gezeichnet
        
        if(xPos3 > -198)g.drawImage(img3, xPos3,0,null);//Attribut-Image 3 wird gezeichnet
        else g.drawImage(hg.get(getRandom()), xPos3,0,null);//Zufaelliges Bild wird gezeichnet
        
        if(xPos4 > -198)g.drawImage(img4, xPos4,0,null);//Attribut-Image 4 wird gezeichnet
        else g.drawImage(hg.get(getRandom()), xPos4,0,null);//Zufaelliges Bild wird gezeichnet
    }
    
    /**
     * Die x Positionen der Bilder werden ueberprueft und sollte es außerhalb des sichtbaren
     * Bereiches sein, wird es nach hinten verschoben und ein neues zufaelliges Bild aus 
     * der ArrayList hg gewaehlt
     */
    public void update(){
        //Ueberpruefung ob die jeweiligen Bilder bereits mit gesamter Breite (200px) aus dem
        //sichtbaren Bereich sind
        if(xPos1 >= -202)xPos1 += speedX;//Das Bild 1 wird um die gewaehlte Geschwindigkeit verschoben
        else {
            //Verschiebung zum Ende des Fensters (600px Breit)
            xPos1 = 594;
            //Zuweisung von Bild 1 mit einem zufaelligen Bild aus der ArrayList hg
            img1 = hg.get(getRandom());
        }
        if(xPos2 >= -202)xPos2 += speedX;//Das Bild 2 wird um die gewaehlte Geschwindigkeit verschoben
        else {
            //Verschiebung zum Ende des Fensters (600px Breit)
            xPos2 = 594;
            //Zuweisung von Bild 2 mit einem zufaelligen Bild aus der ArrayList hg
            img2 = hg.get(getRandom());
        }
        if(xPos3 >= -202)xPos3 += speedX;//Das Bild 3 wird um die gewaehlte Geschwindigkeit verschoben
        else {
            //Verschiebung zum Ende des Fensters (600px Breit)
            xPos3 = 594;
            //Zuweisung von Bild 3 mit einem zufaelligen Bild aus der ArrayList hg
            img3 = hg.get(getRandom());
        }
        if(xPos4 >= -202)xPos4 += speedX;//Das Bild 4 wird um die gewaehlte Geschwindigkeit verschoben
        else {
            //Verschiebung zum Ende des Fensters (600px Breit)
            xPos4 = 594;
            //Zuweisung von Bild 4 mit einem zufaelligen Bild aus der ArrayList hg
            img4 = hg.get(getRandom());
        }
    }

    /**
     * Eine zufaellige zahl mit Minimum 0 und Maximum der letzte Index der ArrayList hg
     *      @return int Die zufaellige Zahl wird zurueckgegeben
     */
    public int getRandom(){
        Random r = new Random();
        return r.nextInt((hg.size() - 1) + 1) + 0;
    }
}
