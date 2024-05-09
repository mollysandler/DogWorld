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


//        int[] dataSpider = myData.getSpider();
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

        ScoreChecker.logScore( instructions.size() );

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
}
