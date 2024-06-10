import processing.core.PApplet;
import processing.core.PImage;

/**
 * @author Jemma Arona
 */

public class ElseInstruction extends Instruction{
    public ElseInstruction(PApplet screen, int xPos, int yPos, PImage img) {
        super(screen, xPos, yPos, img, null);
    }
    @Override
    public ElseInstruction clone() throws CloneNotSupportedException {
        return (ElseInstruction) super.clone();
    }
    @Override
    public String toString() {
        return "} else {";
    }

    @Override
    public void checkRunAction(int control ) {
        if ( control < 2 ) PlayButtonFunc.setControl( 1 - control );
    }

    @Override
    public void runAction() {}
}