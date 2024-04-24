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
    private boolean speed;

    public Speed(PApplet screen, int xPos, int yPos, PImage img, boolean speed) {
        this.screen = screen;
        this.img = img;
        this.xPos = xPos;
        this.yPos = yPos;
        this.speed = speed;
    }

    public boolean getSpeed(){
        return this.speed;
    }

    public void display(){
        screen.image(img, xPos, yPos);
    }
//
//    public boolean isMouseOverFast(){
//        return (((xPos < screen.mouseX) && (screen.mouseX < xPos + width))
//                && ((yPos < screen.mouseY) && (screen.mouseY < yPos + height)));
//    }

    public boolean isMouseOver(){
        return (((xPos < screen.mouseX) && (screen.mouseX < xPos + width))
                && ((yPos < screen.mouseY) && (screen.mouseY < yPos + height)));
    }

    public boolean mouseReleased(){
        return (((xPos < screen.mouseX) && (screen.mouseX < xPos + width))
                && ((yPos < screen.mouseY) && (screen.mouseY < yPos + height)));
    }
    public void mousePressed() {
        System.out.println( "Mouse Down");
        if(!isMouseOver()){
            return;
        }
        if(speed){
            if (!PlayButtonGUI.getSpeed()) System.out.println("fast");
            PlayButtonGUI.setSpeed(true);
        }
        else{
            if (PlayButtonGUI.getSpeed()) System.out.println("slow");
            PlayButtonGUI.setSpeed(false);
        }
        return;
    }
}
