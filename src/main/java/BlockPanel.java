import g4p_controls.GButton;
import g4p_controls.GEvent;
import g4p_controls.GPanel;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * @author Andy Duong
 */
public class BlockPanel extends GPanel {
    private GButton button;
    DragAndDropManager dragAndDropManager;
    private OriginalInstructions originalInstructions;
    private StepInstruction stepBlock;
    private TurnInstruction turnBlock;
    private PaintInstruction paintBlueBlock, paintGreenBlock, paintRedBlock, paintLightBlock, paintDarkBlock;
    public BlockPanel(PApplet pApplet, float v, float v1, float v2, float v3, PImage closedDelete) {
        super(pApplet, v, v1, v2, v3);
//        button = new GButton(pApplet, 10, 10, 100, 100);
//        button.addEventHandler(this, "handleButtonEvents");
//
//        this.addControl(button);
        PImage stepBlockImage = pApplet.loadImage("src/main/images/step.png");
        stepBlock = new StepInstruction(pApplet, 1000, 200, stepBlockImage);
        PImage turnBlockImage = pApplet.loadImage("src/main/images/turn.png");
        turnBlock = new TurnInstruction(pApplet, 1000, 275, turnBlockImage);
        PImage paintBlueBlockImage = pApplet.loadImage("src/main/images/paint_blue.png");
        paintBlueBlock = new PaintInstruction(pApplet, 1000, 350, paintBlueBlockImage, "blue");
        PImage paintGreenBlockImage = pApplet.loadImage("src/main/images/paint_green.png");
        paintGreenBlock = new PaintInstruction(pApplet, 1000, 425, paintGreenBlockImage, "green");
        PImage paintRedBlockImage = pApplet.loadImage("src/main/images/paint_red.png");
        paintRedBlock = new PaintInstruction(pApplet, 1000, 500, paintRedBlockImage, "red");
        PImage paintLightBlockImage = pApplet.loadImage("src/main/images/paint_light.png");
        paintLightBlock = new PaintInstruction(pApplet, 1000, 575, paintLightBlockImage, "light");
        PImage paintDarkBlockImage = pApplet.loadImage("src/main/images/paint_dark.png");
        paintDarkBlock = new PaintInstruction(pApplet, 1000, 650, paintDarkBlockImage, "dark");

        dragAndDropManager = new DragAndDropManager(pApplet, closedDelete);

        originalInstructions = new OriginalInstructions(stepBlock, turnBlock, paintBlueBlock, paintGreenBlock, paintRedBlock, paintLightBlock, paintDarkBlock);

        for (Instruction currInstruction : OriginalInstructions.getInstance()) {
            currInstruction.display();
        }
    }

//    @Override
//    public void draw() {
//        for (Instruction currInstruction : OriginalInstructions.getInstance()) {
//            currInstruction.display();
//        }
//        dragAndDropManager.makeDraggable();
//
//    }

    public void handleButtonEvents(GButton button, GEvent event) {
        System.out.println("hi");
    }

}
