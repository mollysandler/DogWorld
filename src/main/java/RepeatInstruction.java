import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

/**
 * @author Riya Badadere
 */

public class RepeatInstruction extends Instruction {
    public int iterations = 0;
    public int maxIterations = 1;
    private final int dropdownW = 30;
    private final int dropdownH = 24;
    private boolean input = false;
    private String repeatCount = "1";

    public RepeatInstruction(PApplet screen, int xPos, int yPos, PImage img) {
        super(screen, xPos, yPos, img, null);
        width = img.width - 45;
    }
    private void updateRepeat(String value) {
        repeatCount = value;
        maxIterations = !value.isEmpty() ? Integer.parseInt(value) : 0;
    }
    private void inputClick() {
        var s = super.screen;
        if (s.mousePressed) {
            input = s.mouseX > getDropdownX() && s.mouseX < getDropdownX() + dropdownW &&
                    s.mouseY > getDropdownY() && s.mouseY < getDropdownY() + dropdownH;
            if (input) updateRepeat("");
        }
    }

    @Override
    public void display() {
        super.screen.push();
        super.screen.translate(getxPos(), getyPos());
        super.screen.image(img, 0, 0);
        super.screen.translate(110, 38);
        super.screen.stroke(input ? 150 : 255);
        super.screen.fill(255);
        super.screen.rect(0, 0, dropdownW, dropdownH, 8);
        super.screen.fill(0);
        super.screen.noStroke();
        super.screen.textSize(15);
        super.screen.textAlign(PConstants.CENTER, PConstants.CENTER);
        super.screen.text(repeatCount, ((float)dropdownW) / 2, ((float)dropdownH) / 2);
        inputClick();
        if (input) {
            if (Driver.keyDown >= 48 && Driver.keyDown <= 57 && repeatCount.length() < 3) {
                updateRepeat(repeatCount + (char) Driver.keyDown);
            } else if (Driver.keyDown == 8 && !repeatCount.isEmpty()) {
                updateRepeat(repeatCount.substring(0, repeatCount.length() - 1));
            }
        }
        super.screen.pop();
    }
    @Override
    public RepeatInstruction clone() throws CloneNotSupportedException {
        return (RepeatInstruction) super.clone();
    }
    @Override
    public String toString(){
        return "if touching " + " {";
    }

    private int getDropdownX() {
        return getxPos() + 115;
    }

    private int getDropdownY() {
        return getyPos() + 38;
    }

    @Override
    public void checkRunAction(int control ) {
    }

    @Override
    public void runAction() {}

    @Override
    public void mousePressed() {
        super.mousePressed();
    }
}


