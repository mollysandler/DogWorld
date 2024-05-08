import processing.core.PApplet;
import processing.core.PImage;

import java.util.Arrays;

/**
 * @author Riya Badadare
 */

public class PaintInstruction extends Instruction {
    private String color;
    private int[] colorValue = {255, 255, 255};
    private final String[] colors = {"red", "green", "blue", "dark", "light"};
    private final int[][] colorValues = {
            {240, 80, 80}, {30, 180, 30}, {75, 150, 255},
            {20, 20, 20}, {240, 240, 240}
    };
    private final int dropdownW = 30;
    private boolean showDropdown = false;

    public PaintInstruction(PApplet screen, int xPos, int yPos, PImage img, String color) {
        super(screen, xPos, yPos, img, "sit");
        this.color = color;

    }
    private void dropdownClick() {
        var s = super.screen;
        if (s.mousePressed) {
            int dropdownH = 46;
            showDropdown = s.mouseX > getDropdownX() && s.mouseX < getDropdownX() + dropdownW &&
                s.mouseY > getDropdownY() && s.mouseY < getDropdownY() + dropdownH;
        }
    }
    private void colorOptionClicked(int x, int y, int w, String value) {
        var s = super.screen;
        if (s.mouseX > x && s.mouseX < x + w &&
                s.mouseY > y && s.mouseY < y + w && s.mousePressed) {
            color = value;
            colorValue = colorValues[Arrays.asList(colors).indexOf(value)];
            super.changeColor(value);
        }
    }
    private void drawDropdown() {
        var s = super.screen;
        for (int i = 0; i < colors.length; i ++) {
            s.noStroke();
            s.fill(colorValues[i][0], colorValues[i][1], colorValues[i][2]);
            s.circle(dropdownW + 25, 25 + (i * 45), 40);
            colorOptionClicked(getDropdownX() + dropdownW, getDropdownY() + (i * 45), 40, colors[i]);
        }
    }
    @Override
    public void display() {
        super.screen.push();
        super.screen.translate(getxPos(), getyPos());
        super.screen.image(img, 0, 0);
        super.screen.translate(100, 26);
        super.screen.noStroke();
        super.screen.fill(colorValue[0], colorValue[1], colorValue[2]);
        super.screen.rect(0, 0, 30, 46);
        super.screen.fill(color.equals("light") ? 0 : 255);
        super.screen.triangle(
                8, 19,
                22, 19,
                15, 27
        );
        if (showDropdown) drawDropdown();
        dropdownClick();
        super.screen.pop();
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

    private int getDropdownX() {
        return getxPos() + 100;
    }

    private int getDropdownY() {
        return getyPos() + 26;
    }

    @Override
    public void runAction(){
        WorldData myData = WorldData.getWorldData();
        int[] dataSpider = myData.getSpider();
        myData.paintTile(dataSpider[0], dataSpider[1], color);
    }
}
