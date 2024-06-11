import processing.core.PApplet;
import processing.core.PImage;

/**
 * @author Saylor Benes
 */

public class Diamond implements Draggable, Cloneable {

    private final String color;
    PImage img;
    protected PApplet screen;
    private int xPos;
    private int yPos;
    private final int width;
    private final int height;
    private boolean isDragging;
    private int xOffset;
    private int yOffset;

    public Diamond(PApplet screen, int xPos, int yPos, PImage img, String color){
        this.screen = screen;
        this.xPos = xPos;
        this.yPos = yPos;
        this.img = img;
        width = img.width;
        height = img.height;
        this.color = color;
    }

    public Diamond clone() throws CloneNotSupportedException{
        return (Diamond) super.clone();
    }

    public void setVisible(boolean visible) {
    }

    @Override
    public void display() {
        screen.image(img, xPos, yPos);
    }

    @Override
    public boolean isMouseOver() {
        return (((xPos < screen.mouseX) && (screen.mouseX < xPos + width))
                && ((yPos < screen.mouseY) && (screen.mouseY < yPos + height)));
    }

    @Override
    public void setxPos(int x) {
        this.xPos = x;
    }

    @Override
    public void setyPos(int y) {
        this.yPos = y;
    }

    public int getyPos() {
        return this.yPos;
    }
    public int getxPos() {
        return this.xPos;
    }
    public String getColor() {
        return color;
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

    void snapToGrid(int gridX, int gridY, int cellSize) {
        setxPos(gridX * cellSize + 300);
        setyPos(gridY * cellSize + 250);
    }

    public void mouseReleased() {}
    public String serialize(){
        return xPos + "," + yPos + "," + color;
    }

    public static Diamond deserialize(String data, PApplet screen, PImage img){
        String[] parts = data.split(",");
        int xPos = Integer.parseInt(parts[0]);
        int yPos = Integer.parseInt(parts[1]);
        String color = parts[3];
        // Probably match image here
        return new Diamond(screen, xPos, yPos, img, color);
    }

    public static Diamond deserialize(String data, PApplet screen, PImage redImage, PImage blueImage, PImage greenImage) {
        String[] parts = data.split(",");
        int xPos = Integer.parseInt(parts[0]);
        int yPos = Integer.parseInt(parts[1]);
        String color = parts[2];
        PImage img = switch (color) {
            case "red" -> redImage;
            case "blue" -> blueImage;
            case "green" -> greenImage;
            default -> throw new IllegalArgumentException("Unknown color: " + color);
        };

        return new Diamond(screen, xPos, yPos, img, color);
    }
}
