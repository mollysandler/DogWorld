import processing.core.PApplet;
import processing.core.PImage;
/**
 * @author Jemma Arona
 */
public class EraseInstruction extends Instruction{
    public EraseInstruction(PApplet screen, int xPos, int yPos, PImage img) {
        super(screen, xPos, yPos, img, "walk");
    }
    @Override
    public StepInstruction clone() throws CloneNotSupportedException {
        return (StepInstruction) super.clone();
    }
    @Override
    public String toString() {
        return "erase";
    }

    @Override
    public void runAction(){
        WorldData myData = WorldData.getWorldData();
        int[] dataSpider = myData.getSpider();
        myData.paintTile(dataSpider[0], dataSpider[1], "erase");
    }
}