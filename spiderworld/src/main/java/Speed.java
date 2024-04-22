import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;

public class Speed {
    private PImage img;
    private PApplet screen;
    protected int xPos;
    protected int yPos;

    public Speed(PApplet screen, int xPos, int yPos, PImage img) {
        this.screen = screen;
        this.img = img;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void display(){
        screen.image(img, xPos, yPos);
    }

}

