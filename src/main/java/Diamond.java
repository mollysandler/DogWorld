import processing.core.PApplet;
import processing.core.PImage;

public class Diamond implements Draggable, Cloneable {

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
        this.screen = screen;
        this.xPos = xPos;
        this.yPos = yPos;
        this.img = img;
        width = img.width;
        height = img.height;

    }

    //clone
    public Diamond clone() throws CloneNotSupportedException{
        return (Diamond) super.clone();
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }


    @Override
    public void display() {
        screen.image(img, xPos, yPos);
    }

    @Override
    public boolean isMouseOver() {
        return false;
    }

    @Override
    public void setxPos(int x) {

    }

    @Override
    public void setyPos(int y) {

    }

    public int getyPos() {
        return this.yPos;
    }
    public int getxPos() {
        return this.xPos;
    }

    public void setIsDragging(boolean newIsDragging) {
        this.isDragging = newIsDragging;
    }


    public void drag() {
        if (isDragging) {
            xPos = screen.mouseX - xOffset;
            yPos = screen.mouseY - yOffset;
        }
    }

    public void mousePressed() {
        if(isMouseOver()){ //calculate offset for the dragging
            xOffset = Math.abs(xPos - screen.mouseX);
            yOffset = Math.abs(yPos - screen.mouseY);
            isDragging = true;
        }
    }

}
