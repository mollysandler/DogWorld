import processing.core.PApplet;
import processing.core.PImage;
/**
 * @author Riya Badadare
 */
public class StepInstruction extends Instruction {
    public StepInstruction(PApplet screen, int xPos, int yPos, PImage img) {
        super(screen, xPos, yPos, img, "walk");
    }

    @Override
    public StepInstruction clone() throws CloneNotSupportedException {
        return (StepInstruction) super.clone();
    }

    @Override
    public String toString() {
        return "step";
    }

    @Override
    public void runAction() {
        WorldData myData = WorldData.getWorldData();
        int[] dataSpider = myData.getSpider();
        int rot = dataSpider[2];
        myData.moveSpider(
                dataSpider[0] + (rot + 1) % 2 * (1 - rot),  //+1 if right, -1 if left, +0 otherwise
                dataSpider[1] + rot % 2 * (rot - 2),           //+1 if down, -1 if up, +0 otherwise
                rot);
        myData.getWeather().handleStep();
    }

//    private int[] stepHandler(int x, int y, int currRot){
//        int[] newPos = new int[3];
//        if(currRot == 2){// west/left
//            newPos[0] = x - 1;
//            newPos[1] = y;
//        }else if(currRot == 1){ //north/up
//            newPos[0] = x;
//            newPos[1] = y - 1;
//        }else if(currRot == 0){ // east/right
//            newPos[0] = x + 1;
//            newPos[1] = y;
//        }else if(currRot == 3){ // south/down
//            newPos[0] = x;
//            newPos[1] = y + 1;
//        }
//        newPos[2] = currRot;
//        return newPos;
//    }
}