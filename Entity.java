import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Beschreiben Sie hier die Klasse Entity.
 * 
 * @author Jakob Linz, Moritz Rembold 
 * @version (eine Versionsnummer oder ein Datum)
 */
public abstract class Entity
{
    //Koordinaten der Entities also der Subklassen
    protected float xPos;
    protected float yPos;
    //Hitbox - Also der fuer den Spieler gefaerhliche Bereich der Entities
    protected Rectangle hitbox;
    //Getter fuer die Attribute
    public float getXPos(){return xPos;}
    public float getYPos(){return yPos;}
    public Rectangle getHitbox() {return hitbox;}
    //Setter für Koordinaten
    public void setXPos(float xPos){this.xPos = xPos;}
    public void setYPos(float yPos){this.yPos = yPos;}
}
