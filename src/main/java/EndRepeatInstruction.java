import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class EndRepeatInstruction extends Instruction {
    public EndRepeatInstruction(PApplet screen, int xPos, int yPos, PImage img) {
        super(screen, xPos, yPos, img, null);
    }
    @Override
    public EndRepeatInstruction clone() throws CloneNotSupportedException {
        return (EndRepeatInstruction) super.clone();
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


