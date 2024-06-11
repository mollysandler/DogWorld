import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author Jemma Arona
 */

public class IfInstruction extends Instruction {
    private int[] color;
    public enum Options {Edge, Diamond, R, G, B, D, L }
    private Options condition;
    private final int dropdownW = 35;
    private boolean showDropdown = false;
    private final int[][] colorValues = {
            {60, 72, 88}, {240, 240, 128}, {240, 80, 80}, {30, 180, 30}, {75, 150, 255},
            {20, 20, 20}, {240, 240, 240}
    };

    public IfInstruction(PApplet screen, int xPos, int yPos, PImage img, Options cond) {
        super(screen, xPos, yPos, img, null);
        this.condition = cond;
        width = img.width - 45;
        color = colorValues[ cond.ordinal() ];
    }
    private void dropdownClick() {
        var s = super.screen;
        if (s.mousePressed) {
            showDropdown = Math.abs(s.mouseX - getDropdownX()) <= ((float) dropdownW) / 2  &&
                    Math.abs(s.mouseY - getDropdownY()) <= ((float) dropdownW) / 2;
        }
    }
    private void colorOptionClicked(int x, int y, int w, Options value) {
        var s = super.screen;
        int[] c = colorValues[ value.ordinal() ];
        int dx = (s.mouseX - x); int dy = (s.mouseY - y);
        if ((dx*dx + dy*dy) <= w*w/4  && s.mousePressed) {
            condition = value;
            color = c;
        }
        super.screen.push();
        super.screen.translate(x, y);
        super.screen.noStroke();
        super.screen.fill( c[0], c[1], c[2] );
        super.screen.circle(0, 0, w);
        super.screen.fill(255);
        super.screen.textSize(18);
        super.screen.textAlign(PConstants.CORNER, PConstants.CENTER);
        if (value == Options.Edge) super.screen.text("Wall", 25, 0);
        if (value == Options.Diamond) super.screen.text("Diamond", 25, 0);
        super.screen.pop();
    }
    private void drawDropdown() {
        int i = 0;
        for ( Options o : Options.values() ) {
            colorOptionClicked(
                    getDropdownX() + dropdownW + 15,
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
        super.screen.translate(getDropdownX() - getxPos(), getDropdownY() - getyPos());
        super.screen.noStroke();
        super.screen.fill( color[0], color[1], color[2] );
        super.screen.circle(0, 0, dropdownW);
        super.screen.fill(condition == Options.L || condition == Options.Diamond ? 0 : 255);
        super.screen.triangle(
                -7, -4,
                7, -4,
                0, 4
        );
        super.screen.pop();
        if (showDropdown) drawDropdown();
        dropdownClick();
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
        return getxPos() + 128;
    }
    private int getDropdownY() {
        return getyPos() + 50;
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


