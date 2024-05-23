import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class IfInstruction extends Instruction {
    private int[] color;
    public enum Options {Edge, Diamond, R, G, B, D, L }
    private Options condition;
    private final int[][] colorValues = {
            {40, 52, 68}, {240, 240, 128}, {255, 0, 0}, {0, 255, 0}, {0, 0, 255},
            {20, 20, 20}, {240, 240, 240}
    };
    private final int dropdownW = 40;
    private boolean showDropdown = false;

    public IfInstruction(PApplet screen, int xPos, int yPos, PImage img, Options cond) {
        super(screen, xPos, yPos, img, null);
        this.condition = cond;
        color = colorValues[ cond.ordinal() ];
    }
    private void dropdownClick() {
        var s = super.screen;
        if (s.mousePressed) {
            int dropdownH = 46;
            showDropdown = s.mouseX > getDropdownX() && s.mouseX < getDropdownX() + dropdownW &&
                    s.mouseY > getDropdownY() && s.mouseY < getDropdownY() + dropdownH;
        }
    }
    private void colorOptionClicked(int x, int y, int w, Options value) {
        var s = super.screen;
        if (s.mouseX > x && s.mouseX < x + w &&
                s.mouseY > y && s.mouseY < y + w && s.mousePressed) {
            condition = value;
            color = colorValues[ value.ordinal() ];
        }
    }
    private void drawDropdown() {
        int i = 0;
        for ( Options o : Options.values() ) {
            super.screen.stroke( 204, 204, 204 );
            super.screen.fill( colorValues[i][0], colorValues[i][1], colorValues[i][2] );
            super.screen.circle(dropdownW + 25, 25 + (i * 45), 40);
            colorOptionClicked(
                    getDropdownX() + dropdownW,
                    getDropdownY() + (i * 45),
                    40, o);
            i++;
        }
    }
    @Override
    public void display() {
        super.screen.push();
        super.screen.translate(getxPos(), getyPos());
        super.screen.image(img, 0, 0);
        super.screen.noStroke();
        super.screen.fill( color[0], color[1], color[2] );
        super.screen.circle(125, 49, 40);
        super.screen.translate(110, 27);
        super.screen.fill( 0, 0, 0 );
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
    public IfInstruction clone() throws CloneNotSupportedException {
        return (IfInstruction) super.clone();
    }
    @Override
    public String toString(){
        return "if touching " + this.condition + " {";
    }

    private int getDropdownX() {
        return getxPos() + 100;
    }

    private int getDropdownY() {
        return getyPos() + 26;
    }

    @Override
    public void checkRunAction(int control ) {
        if ( control > 0 ) {
            PlayButtonFunc.setControl( control + 1 );
            return;
        }
        int[] spider = WorldData.getWorldData().getSpider();
        Point p = new Point(spider[0], spider[1]);
        switch ( condition ) {
            case Edge:
                boolean onEdge = spider[0] == 0 || spider[1] == 0;
                onEdge = onEdge || spider[0] == WorldData.getWorldData().getNumRows() - 1;
                onEdge = onEdge || spider[1] == WorldData.getWorldData().getNumRows() - 1;
                if (!onEdge) PlayButtonFunc.setControl(control + 1);
                return;
            case Diamond:
                boolean onDiamond = false;
                HashMap<String, ArrayList<Point>> diamonds = WorldData.getWorldData().getLevelMap();
                for (String keyStr : diamonds.keySet()) {
                    if (!keyStr.startsWith("diamond_")) continue;
                    onDiamond = onDiamond || diamonds.get(keyStr).contains(p);
                }
                if (!onDiamond) PlayButtonFunc.setControl(control + 1);
                return;
            default:
                HashMap<Point, String> tiles = WorldData.getWorldData().getTileMap();
                if ( tiles.containsKey(p) ) {
                    if ( Objects.equals( tiles.get(p), condition.name().toLowerCase() ) ) return;
                }
                PlayButtonFunc.setControl(control + 1);
        }
    }

    @Override
    public void runAction() {}

    @Override
    public void mousePressed() {
        super.mousePressed();
    }
}


