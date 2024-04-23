import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Molly Sandler
 */
public class Driver extends PApplet{

    private WorldData worldData;
    private final WorldView worldView = new WorldView(this);
    private LoadLevels level;
    private Instruction[] originalInstructions;
    private StepInstruction stepBlock;
    private TurnInstruction turnBlock;
    private PaintInstruction paintBlueBlock;
    private PaintInstruction paintGreenBlock;
    private PaintInstruction paintRedBlock;
    private PImage closedDelete;
    private PImage openedDelete;
    private PlayButtonGUI playButton;
    private Speed fastButton;
    private Speed slowButton;
    private final InstructionList instructionCopies = InstructionList.getInstance();
    private boolean speed = false;

    @Override
    public void settings(){
        size(1200, 900);
    }

    public void buttonDisplay(){
        //displaying all buttons
        PImage stepBlockImage = loadImage("src/main/images/step.png");
        stepBlock = new StepInstruction(this, 1000, 200, stepBlockImage);
        PImage turnBlockImage = loadImage("src/main/images/turn.png");
        turnBlock = new TurnInstruction(this, 1000, 275, turnBlockImage);
        PImage paintBlueBlockImage = loadImage("src/main/images/paint_blue.png");
        paintBlueBlock = new PaintInstruction(this, 1000, 350, paintBlueBlockImage, "blue");
        PImage paintGreenBlockImage = loadImage("src/main/images/paint_green.png");
        paintGreenBlock = new PaintInstruction(this, 1000, 425, paintGreenBlockImage, "green");
        PImage paintRedBlockImage = loadImage("src/main/images/paint_red.png");
        paintRedBlock = new PaintInstruction(this, 1000, 500, paintRedBlockImage, "red");
        PImage startButtonImg = loadImage("src/main/images/playButtonImg.png");

        //displaying speed modes
        PImage fast = loadImage("src/main/images/hare.png");
        fastButton = new Speed(this, 250, 500, fast);
        PImage slow = loadImage("src/main/images/turtle.png");
        slowButton = new Speed(this, 60, 500, slow);

        playButton = new PlayButtonGUI(this, 250, 620, startButtonImg, speed);

        //drawing the trashcan images over the background
        closedDelete = loadImage("src/main/images/trash1.png");
        closedDelete.resize(100, 150);
        openedDelete = loadImage("src/main/images/trash2.png");
        openedDelete.resize(100, 150);
    }

    @Override
    public void setup(){
        worldData = WorldData.getWorldData();
        worldData.addPropertyChangeListener(worldView);
        LevelGenerator.makeLevels();
        level = new LoadLevels(1);

        //gets all the buttons and blocks on the board
        buttonDisplay();

        HashMap<String, ArrayList<Point>> map = level.loadHashMap();
        worldData.setLevel(map);
        level.saveHashMap(map);

        originalInstructions = new Instruction[]{stepBlock, turnBlock, paintBlueBlock, paintGreenBlock, paintRedBlock};
    }
    @Override
    public void draw() {
        background(100, 100, 100);
        playButton.display();

        for (Instruction currInstruction : originalInstructions) {
            currInstruction.display();
        }

        //if the mouse is over the trashcan, display the opened can
        if (mouseX > 100 && mouseX < 100 + closedDelete.width && mouseY > 600 && mouseY < 600 + closedDelete.height) {
            image(openedDelete, 60, 600); //display the open trash can
        }
        else {
            //otherwise display the closed trashcan
            image(closedDelete, 60, 600);
        }

        //make blocks draggable
        worldView.drawWorld();
        stepBlock.drag();
        turnBlock.drag();
        paintBlueBlock.drag();
        paintGreenBlock.drag();
        paintRedBlock.drag();

        for (Instruction currInstruction : InstructionList.getInstance().getSortedInstructions()) {
            currInstruction.drag();
            currInstruction.display();
        }
        fastButton.display();
        fastButton.mousePressed();
        slowButton.display();
        slowButton.mousePressed();
    }


    @Override
    public void mousePressed() {
        playButton.mousePressed();
        //when on original blocks, will create copies and will automatically be dragging copies
        for(Instruction currInstruction: originalInstructions) {
            if (currInstruction.isMouseOver()) {
                Instruction copy = null; // Create a copy
                try {
                    copy = currInstruction.clone();
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
                copy.mousePressed();
                instructionCopies.addInstruction(copy); // Add the copy to the list
                break;
            }
        }
        //lets you drag around copies that you've dropped
        for (Instruction copy : InstructionList.getInstance().getSortedInstructions()) {
            copy.mousePressed();
        }
    }

    @Override
    public void mouseReleased() {
        List<Instruction> instructions = instructionCopies.getSortedInstructions();
        List<Instruction> newInstructions = new ArrayList<>(instructions);

        //dealing with release of instruction over trash can
        for (Instruction currInstruction : instructions) {
            currInstruction.isDragging = false;
            if(currInstruction.getxPos() < 100 + closedDelete.width && currInstruction.getxPos() + 100 > 100 && currInstruction.getyPos() < 600 + closedDelete.height && currInstruction.getyPos() + 60 > 600 && currInstruction.isMouseOver()){
                newInstructions.remove(currInstruction);
            }
        }

        instructionCopies.setInstructions(newInstructions);
        instructions = instructionCopies.getSortedInstructions();

        // Snapping to other blocks
        for (int i = 0; i < instructions.size(); i++) {
            for (int j = 0; j < instructions.size(); j++) {
                if (i == j) continue;
                Instruction a = instructions.get(i);
                Instruction b = instructions.get(j);

                if (a.toSnap(b)) {
                    if (a.yPos < b.yPos) {
                        b.xPos = a.xPos;
                        b.yPos = a.yPos + 50;
                    } else {
                        a.xPos = b.xPos;
                        a.yPos = b.yPos + 50;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] processingArgs = {"Driver"};
        Driver running = new Driver();
        PApplet.runSketch(processingArgs, running);
    }
}
