import processing.core.PApplet;

/**
 * @author Riya Badadare
 */
public final class OriginalInstructions {

    private static Instruction[] instance;

    OriginalInstructions() {}

    public static Instruction[] getInstance() {
        return instance;
    }

    public static void setInstructionImages( PApplet screen ) {
        Instruction stepBlock = new StepInstruction(screen, 1000, 250,
                screen.loadImage("src/main/images/step.png"));
        Instruction turnBlock = new TurnInstruction(screen, 1000, 350,
                screen.loadImage("src/main/images/turn.png"));
        Instruction paintBlock = new PaintInstruction(screen, 1000, 450,
                screen.loadImage("src/main/images/paint_light.png"), "light");
        Instruction eraseBlock = new EraseInstruction(screen, 1000, 550,
                screen.loadImage("src/main/images/erase.png"));
        instance = new Instruction[]{ stepBlock, turnBlock, paintBlock, eraseBlock };
    }
}

