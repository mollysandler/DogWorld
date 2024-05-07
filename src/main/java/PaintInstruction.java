import processing.core.PApplet;
import processing.core.PImage;

/**
 * @author Riya Badadare
 */

public class PaintInstruction extends Instruction {
    private String color;

    public PaintInstruction(PApplet screen, int xPos, int yPos, PImage img, String color) {
        super(screen, xPos, yPos, img, "sit");
        this.color = color;
    }
    @Override
    public PaintInstruction clone() throws CloneNotSupportedException {
        return (PaintInstruction) super.clone();
    }
    @Override
    public String toString(){
        return "paint " + this.color;
    }
    public String getColor() {
        return this.color;
    }

    @Override
    public void runAction(){
        WorldData myData = WorldData.getWorldData();
        int[] dataSpider = myData.getSpider();
        myData.paintTile(dataSpider[0], dataSpider[1], color);
    }
}
