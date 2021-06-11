import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * Beschreiben Sie hier die Klasse Resource.
 * 
 * @author Emma Holtz und Karl Konrad Hanka
 * @version Prototyp 1 7.5.2021
 */
public class Resource
{
    /**
     * Konstruktor der Klasse Resource
     *      @param name Der Dateiname
     *      @return BufferedImage Das Bild als BufferedImage
     */
    public static BufferedImage getResourceImage(String name)
    {
        BufferedImage img = null;
        //Es wird versucht zu dem uebergebenen String die gleichnamige Datei im Ordner Images zu finden
        try{
            img = ImageIO.read(new File("images/" + name));
        } catch(IOException ex){
            //Bei misglücktem Versuch wird der Fehler "gecatched"
            ex.printStackTrace();
        }
        return img;
    }
}
