import g4p_controls.GPanel;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * @author Andy Duong
 */
public class BlockPanel extends GPanel {

    PImage img;
    public BlockPanel(PApplet pApplet, float v, float v1, float v2, float v3) {
        super(pApplet, v, v1, v2, v3);
    }

    public void draw() {
        super.draw();
        OriginalInstructions.setInstructionImages(this.getPApplet());
    }
}
