import processing.core.PApplet;
import processing.core.PImage;

/**
 * @author Andy Duong
 */
public class PlayButtonGUI {
    public PApplet screen;
    private int xPos, yPos, width, height;
    private PImage img;
    private static boolean speed;

    public PlayButtonGUI(PApplet screen, int xPos, int yPos, PImage img){
        this.screen = screen;
        this.xPos = xPos;
        this.yPos = yPos;
        this.img = img;
        this.width = img.width;
        this.height = img.height;
        //PlayButtonGUI.speed = Speed.getSpeed();
    }

    public void display(){screen.image(img, xPos, yPos);}

    private boolean isMouseOver() {
        return (((xPos < screen.mouseX) && (screen.mouseX < xPos + width))
                && ((yPos < screen.mouseY) && (screen.mouseY < yPos + height)));
    }

    public void mousePressed(){
        if(isMouseOver()){
            WorldData.getWorldData().resetWorld();
            PlayButtonFunc playButtonFunc = new PlayButtonFunc(speed);
            Thread t1 = new Thread(playButtonFunc);
            t1.start();
        }
    }

    public static void setSpeed(boolean newSpeed){
        //System.out.println("speed changing");
        PlayButtonGUI.speed = newSpeed;
    }

    public static boolean getSpeed(){
        //System.out.println("speed changing");
        return speed;
    }
}
