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
    private boolean visible;

    public Diamond(PApplet screen, int xPos, int yPos, PImage img){
        super(screen, xPos, yPos, img, null );
        width = img.width;
        height = img.height;
        this.isDragging = false;


    }


    //clone
    public Diamond clone() throws CloneNotSupportedException{
        return (Diamond) super.clone();
    }


    public void drag() {
        if (isDragging) {
            xPos = screen.mouseX - xOffset;
            yPos = screen.mouseY - yOffset;
        }
    }

    @Override
    public void runAction() {

    }

    public void setVisible(boolean b) {
        this.visible = visible;
    }
}
