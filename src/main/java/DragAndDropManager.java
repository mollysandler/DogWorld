import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Riya Badadare
 */
public class DragAndDropManager {
    PImage closedDelete;
    private final InstructionList instructionCopies = InstructionList.getInstance();
    public List<Diamond> initialDiamonds = new ArrayList<>();
    public List<Diamond> addedDiamonds = new ArrayList<>();
    private Diamond currentDiamond = null;
    Diamond[][] diamondGrid = new Diamond[5][5];

    public DragAndDropManager(PApplet screen, PImage closedDelete) {
        this.closedDelete = closedDelete;
    }
    public void makeDraggable(boolean isSandbox) {
        // Instruction sidebar
        for (Instruction currInstruction : OriginalInstructions.getInstance()) {
            currInstruction.drag();
            currInstruction.display();
        }
        // Instructions you dragged
        for (Instruction currInstruction : InstructionList.getInstance().getSortedInstructions()) {
            currInstruction.drag();
            currInstruction.display();
        }

        // TODO: Go through diamond sidebar
        // TODO: GO through diamonds you dragged

        if (isSandbox) {
            for (Diamond diamond : initialDiamonds) {
                diamond.drag();
                diamond.display();
            }
            for (Diamond diamond : addedDiamonds) {
                diamond.drag();
                diamond.display();
            }
        }
    }

    public void mousePressed(boolean isSandbox) {
        //when on original blocks, will create copies and will automatically be dragging copies
        for(Instruction currInstruction: OriginalInstructions.getInstance()) {
            if (currInstruction.isMouseOver()) {
                Instruction copy; // Create a copy
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

        // Handle diamond dragging in sandbox mode
        if (isSandbox) {
            for (Diamond diamond : initialDiamonds) {
                if (diamond.isMouseOver()) {
                    try {
                        currentDiamond = diamond.clone();
                        currentDiamond.setxPos(diamond.getxPos());
                        currentDiamond.setyPos(diamond.getyPos());
                        currentDiamond.mousePressed();
                        //initialDiamonds.add(currentDiamond);
                        addedDiamonds.add(currentDiamond);
                        //System.out.println("Added diamond to both lists");
                    } catch (CloneNotSupportedException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
            }
            for (Diamond diamond : addedDiamonds) {
                if (diamond.isMouseOver()) {
                    currentDiamond = diamond;
                    currentDiamond.mousePressed();
                    break;
                }
            }
        }
    }

    public void mouseReleased(boolean isSandbox) {
        List<Instruction> instructions = instructionCopies.getSortedInstructions();
        List<Instruction> newInstructions = new ArrayList<>(instructions);

        for (Instruction currInstruction : instructions) {
            currInstruction.setIsDragging(false);

            if (!isSandbox && (currInstruction.getxPos() < 350 || currInstruction.getxPos() > 950
                    || currInstruction.getyPos() < 100 || currInstruction.getyPos() > 800)) {
                newInstructions.remove(currInstruction);
            }

            if (isSandbox && (currInstruction.getxPos() < 680 || currInstruction.getxPos() > 980
                    || currInstruction.getyPos() < 100 || currInstruction.getyPos() > 800)) {
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
                    if (a.getyPos() < b.getyPos()) {
                        b.setxPos(a.getxPos());
                        b.setyPos(a.getyPos() + 50);
                    } else {
                        a.setxPos(b.getxPos());
                        a.setyPos(b.getyPos() + 50);
                    }
                }
            }
        }

        // TODO : Add snapping to grid
        // Handle diamond releasing in sandbox mode
        if (isSandbox && currentDiamond != null) {
            currentDiamond.mouseReleased();
            // Calculate grid position
            int sandCell = 70;
            int sandX = 300;
            int gridX = ((currentDiamond.getxPos() - sandX) / sandCell);
            int sandY = 250;
            int gridY = ((currentDiamond.getyPos() - sandY) / sandCell);
            // Snap to grid
            if (0 <= gridX && gridX < 5 && 0 <= gridY && gridY < 5) {
                currentDiamond.snapToGrid(gridX, gridY, sandCell);
                diamondGrid[gridX][gridY] = currentDiamond;
            } else {
                    currentDiamond.setVisible(false);
                    //initialDiamonds.remove(currentDiamond);
                    addedDiamonds.remove(currentDiamond);
                }
            currentDiamond.setIsDragging(false);
            currentDiamond = null;
            System.out.println(gridX + " " + gridY);
        }
    }
}
