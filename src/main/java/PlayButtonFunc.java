import Dog.Skill;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Andy Duong
 */
public class PlayButtonFunc implements Runnable{
    public PlayButtonFunc(){}
    @Override
    public void run() {

        WorldData myData = WorldData.getWorldData();
        InstructionList instructionList = InstructionList.getInstance();

        List<Instruction> instructions = instructionList.getSortedInstructions();


        int[] dataSpider = myData.getSpider();
        List<String> commands = new ArrayList<>();

        //per instruction send each instruction to their respective function
        for(Instruction instruction:instructions) {
            commands.add(instruction.getSkill());
            try {
                Thread.sleep(myData.getSpeed());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            instruction.runAction();
        }



        commands.add("perform");
        commands.add("rest");
        commands.add("perform");
        commands.add("exit");
        OurSkill sk = new OurSkill();
        try {
            sk.RobotSkillSet(commands);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WorldData.getWorldData().setGameState(false);
    }


    private int[] stepHandler(int x, int y, int currRot){
        int[] newPos = new int[3];
        if(currRot == 2){// west/left
            newPos[0] = x - 1;
            newPos[1] = y;
        }else if(currRot == 1){ //north/up
            newPos[0] = x;
            newPos[1] = y - 1;
        }else if(currRot == 0){ // east/right
            newPos[0] = x + 1;
            newPos[1] = y;
        }else if(currRot == 3){ // south/down
            newPos[0] = x;
            newPos[1] = y + 1;
        }
        newPos[2] = currRot;
        return newPos;
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
