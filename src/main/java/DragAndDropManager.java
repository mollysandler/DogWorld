import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Riya Badadare
 */
public class DragAndDropManager {
    private Driver screen;
//    private InstructionList instructions;
    PImage closedDelete;
    private final InstructionList instructionCopies = InstructionList.getInstance();
    public List<Diamond> initialDiamonds = new ArrayList<>();
    public List<Diamond> addedDiamonds = new ArrayList<>();
    private Diamond currentDiamond = null;
    private Spider spider;
    private boolean isSpiderDragging = false;
    Diamond[][] diamondGrid = new Diamond[5][5];
    private int sandGrid = 5;
    private int sandCell = 70;
    private int sandX = 300;
    private int sandY = 250;

    public DragAndDropManager(Driver screen, PImage closedDelete) {
        this.screen = screen;
        this.closedDelete = closedDelete;
    }

    public void setSpider(Spider spider) {
        this.spider = spider;
    }
//    Diamond[][] diamondGrid = new Diamond[5][5];

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
            // Handle spider dragging
            if (spider != null) {
                spider.drag();
                spider.display();
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

            // Handle spider dragging
            if (spider.isMouseOver()) {
                isSpiderDragging = true;
                spider.mousePressed();
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

            int gridX = (currentDiamond.getxPos() - 210) / 70 - 1;
            int gridY = (currentDiamond.getyPos() - 165) / 70 - 1;
            System.out.print( "(" + currentDiamond.getxPos() + ", " + currentDiamond.getyPos() );
            System.out.println( ") -> (" + gridX + ", " + gridY + ")");

            if (gridX >= 0 && gridX < diamondGrid.length && gridY >= 0 && gridY < diamondGrid[0].length) {
                // Snap to grid center
                currentDiamond.setxPos( gridX * 70 + 315 );
                currentDiamond.setyPos( gridY * 70 + 265 );
                diamondGrid[gridX][gridY] = currentDiamond;
            } else {
                currentDiamond.setVisible(false);
                addedDiamonds.remove( currentDiamond );
                screen.removeDiamond( currentDiamond );
            }
            // Calculate grid position
//            int sandCell = 70;
//            int sandX = 300;
//            int gridX = ((currentDiamond.getxPos() - sandX) / sandCell);
//            int sandY = 250;
//            int gridY = ((currentDiamond.getyPos() - sandY) / sandCell);
            // Snap to grid
//            if (0 <= gridX && gridX < 5 && 0 <= gridY && gridY < 5) {
//                currentDiamond.snapToGrid(gridX, gridY, sandCell);
//                diamondGrid[gridX][gridY] = currentDiamond;
//            } else {
//                    currentDiamond.setVisible(false);
//                    //initialDiamonds.remove(currentDiamond);
//                    addedDiamonds.remove(currentDiamond);
//                }
            currentDiamond.setIsDragging(false);
            currentDiamond = null;
        }

        if (isSandbox && isSpiderDragging && spider != null) {
            spider.mouseReleased();
            int gridX = (spider.getxPos() - 200) / 70 - 1;
            int gridY = (spider.getyPos() - 150) / 70 - 1;
            System.out.print( "(" + spider.getxPos() + ", " + spider.getyPos() );
            System.out.println( ") -> (" + gridX + ", " + gridY + ")");

            if (gridX >= 0 && gridX < diamondGrid.length && gridY >= 0 && gridY < diamondGrid[0].length) {
                // Snap to grid center
                spider.setxPos( gridX * 70 + 303 );
                spider.setyPos( gridY * 70 + 253 );
                spider.setGridX( gridX );
                spider.setGridY( gridY );
            } else {
                spider.goHome();
            }
            isSpiderDragging = false;
        }
    }
}
