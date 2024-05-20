import Dog.Skill;

import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Andy Duong
 */
public class PlayButtonFunc implements Runnable{
    public PlayButtonFunc(){

    }
    @Override
    public void run() {

        WorldData myData = WorldData.getWorldData();
        InstructionList instructionList = InstructionList.getInstance();

        List<Instruction> instructions = instructionList.getSortedInstructions();

        if(instructions.isEmpty()){
            return;
        }

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
        WorldData.getWorldData().setCommands(commands);

        WorldData.getWorldData().setGameState(true);//comment out if using dog
    }
}
