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
        OurSkill sk = new OurSkill();
        List<Instruction> instructions = instructionList.getSortedInstructions();


//        int[] dataSpider = myData.getSpider();
        List<String> commands = new ArrayList<>();

        //per instruction send each instruction to their respective function
        for(Instruction instruction:instructions) {
            if(instruction.getSkill() .equals( "turn")){
                commands.add(instruction.getSkill());
                commands.add(instruction.getSkill());
                commands.add(instruction.getSkill());
                commands.add(instruction.getSkill());
                commands.add(instruction.getSkill());
            }
            commands.add(instruction.getSkill());
            commands.add("rest");
            commands.add("perform");
            commands.add("exit");

            try {
                sk.RobotSkillSet(commands);
            } catch (IOException | InterruptedException e) {
                System.out.println("BBL Drizzy");
            }

            commands.clear();

            try {
                Thread.sleep(myData.getSpeed());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            instruction.runAction();
        }

        ScoreChecker.logScore( instructions.size() );
        WorldData.getWorldData().setGameState(false);
    }
}
