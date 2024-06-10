import processing.core.PApplet;
import processing.core.PImage;

/**
 * @author Jemma Arona
 */

public class EndifInstruction extends Instruction{
    public EndifInstruction(PApplet screen, int xPos, int yPos, PImage img) {
        super(screen, xPos, yPos, img, null);
    }
    @Override
    public EndifInstruction clone() throws CloneNotSupportedException {
        return (EndifInstruction) super.clone();
    }
    @Override
    public String toString() {
        return "}";
    }

    @Override
    public void checkRunAction( int control ) {
        if ( control > 0 ) PlayButtonFunc.setControl( control - 1 );
    }

    @Override
    public void runAction() {}
}