import processing.core.PApplet;
import processing.core.PImage;

import java.util.HashMap;

/**
 * @author Riya Badadare
 */
public class TurnInstruction extends Instruction {
    public TurnInstruction(PApplet screen, int xPos, int yPos, PImage img) {
        super(screen, xPos, yPos, img, "stand");
    }
    @Override
    public TurnInstruction clone() throws CloneNotSupportedException {
        return (TurnInstruction) super.clone();
    }
    @Override
    public String toString() {
        return "turn";
    }

    @Override
    public void runAction(){
        WorldData myData = WorldData.getWorldData();
        int[] dataSpider = myData.getSpider();
        myData.moveSpider( dataSpider[0], dataSpider[1], (dataSpider[2] + 1)%4);
    }

    private int turnHandler(int currRot){
        HashMap<String, String> turnMap = new HashMap<>();
        //(rot: 0 = east, 1 = north, 2 = west, 3 = south)
        turnMap.put("0", "1");
        turnMap.put("1", "2");
        turnMap.put("2", "3");
        turnMap.put("3", "0");
        String in = Integer.toString(currRot);
        return Integer.parseInt(turnMap.get(in));
    }
}