/**
 * Write a description of class Game here.
 *
 * @author Karl Konrad Hanka, jakob Linz, Moritz Rembold
 * @version (a version number or a date)
 */
public class Game implements Runnable {
    //Frames Per Second, d.h. Durchlaeufe der Game Loop pro Sekunde
    private static final int FPS = 60;
    //Eine Sekunde geteilt durch die Anzahl der Durchlaeufe ergibt die Laenge eines Durchlaufs
    private static final long maxLoopTime = 1000 / FPS;
    //Abbruchbedingung des Game Loops
    private boolean running = true;
    //Das Spielfeld-Attribut
    private Spielfeld spielfeld;

    public Game(Spielfeld spielfeld) {
        //Spielfeld wird zugewiesen
        this.spielfeld = spielfeld;
    }
    
    /**
     * Die run Methode aus Runnable wird ueberschrieben
     */
    @Override
    public void run() {
        //Timestamps werden erstellt
        long timestamp;
        long oldTimestamp;
        //Der gameloop laeuft als while-Schleife mit der Abbruchbedingung "running"
        while(running) {
            //Einem Timestamp wird eine aktuelle Zahl zugewiesen
            oldTimestamp = System.currentTimeMillis();
            //Update wird aufgerufen
            update();
            //Einem Timestamp wird eine aktuelle Zahl zugewiesen
            timestamp = System.currentTimeMillis();
            //Sollte das updaten nicht die maximale Zeit fuer einen Durchlauf erreichen wird weitergemacht
            if(timestamp-oldTimestamp > maxLoopTime) {
                continue;
            }
            //Render wird aufgerufen
            render();
            //Einem Timestamp wird eine aktuelle Zahl zugewiesen
            timestamp = System.currentTimeMillis();
            //Wird die maximale Zeit fuer einen Durchlauf nicht erreicht wird, so wird die uebrige Zeit ueberbrueckt
            if(timestamp-oldTimestamp <= maxLoopTime) {
                try {
                    //Thread wird fuer die uebriggebliebene Zeit "schlafen" gelegt
                    Thread.sleep(maxLoopTime - (timestamp-oldTimestamp) );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Sollte die update Methode von spielfeld false zurueckgeben, so wird die Gameloop angehalten, dies passiert dadurch dass running auf false gesetzt wird
     */
    public void update()
    {
        if(!spielfeld.update()) running = false;
    }

    /**
     * Die repaint Methode in Spielfeld wird aufgerufen
     */
    public void render()
    {
        spielfeld.repaint();
    }
}