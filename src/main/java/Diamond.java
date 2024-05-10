import processing.core.PApplet;
import processing.core.PImage;

public class Diamond extends Instruction {

    PImage img;
    protected PApplet screen;

    private int xPos;
    private int yPos;
    private final int width;
    private final int height;
    private boolean isDragging;
    private int xOffset;
    private int yOffset;
    private boolean visible = true;

    public Diamond(PApplet screen, int xPos, int yPos, PImage img){
        super(screen, xPos, yPos, img, null );
        width = img.width;
        height = img.height;

    }

    //clone
    public Diamond clone() throws CloneNotSupportedException{
        return (Diamond) super.clone();
    }


    @Override
    public void runAction() {

    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }


}
