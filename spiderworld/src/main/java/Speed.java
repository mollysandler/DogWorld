import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;

public class Speed {
    private final PImage img;
    private final PApplet screen;
    private int xPos;
    private int yPos;
    private int width = 150;
    private int height = 150;

    //fast = true and slow = false
    private static boolean speed = false;

    public Speed(PApplet screen, int xPos, int yPos, PImage img) {
        this.screen = screen;
        this.img = img;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public static boolean getSpeed(){
        return speed;
    }

    public void display(){
        screen.image(img, xPos, yPos);
    }

    public boolean isMouseOverFast(){
        return (((xPos < screen.mouseX) && (screen.mouseX < xPos + width))
                && ((yPos < screen.mouseY) && (screen.mouseY < yPos + height)));
    }

    public boolean isMouseOverSlow(){
        return (((xPos < screen.mouseX) && (screen.mouseX < xPos + width))
                && ((yPos < screen.mouseY) && (screen.mouseY < yPos + height)));
    }

    public boolean mouseReleased(){
        return (((xPos < screen.mouseX) && (screen.mouseX < xPos + width))
                && ((yPos < screen.mouseY) && (screen.mouseY < yPos + height)));
    }
    public void mousePressed() {
        if(isMouseOverFast()){
            if(mouseReleased()) {
                PlayButtonGUI.setSpeed(true);
            }
            //System.out.println("fast");
        }
        else if(isMouseOverSlow()){
            if(mouseReleased()) {
                PlayButtonGUI.setSpeed(false);
            }
            //System.out.println("slow");
        }
    }

}



