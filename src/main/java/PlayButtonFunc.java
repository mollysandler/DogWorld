import Dog.Skill;

import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Andy Duong
 */
public class PlayButtonFunc extends PropertyChangeSupport implements Runnable{
    public PlayButtonFunc(){
        super(new Object());
    }
    @Override
    public void run() {

        WorldData myData = WorldData.getWorldData();
        InstructionList instructionList = InstructionList.getInstance();

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
        
        OurSkill sk = new OurSkill();
        sk.connectBluetooth();
        try {
            sk.RobotSkillSet(commands);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        WorldData.getWorldData().setGameState(false);
    }
}
