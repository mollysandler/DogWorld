import java.util.ArrayList;
import java.util.List;

/**
 * @author Andy Duong
 */
public class PlayButtonFunc implements Runnable{

    private static int control;

    public PlayButtonFunc(){

    }
    @Override
    public void run() {

        InstructionList instructionList = InstructionList.getInstance();
        control = 0;

        List<Instruction> instructions = instructionList.getSortedInstructions();

        if(instructions.isEmpty()){
            WorldData.getWorldData().setGameState(true);
            return;
        }

        List<String> commands = new ArrayList<>();

        //per instruction send each instruction to their respective function
        for(Instruction instruction : instructions) {
            instruction.checkRunAction( control );

        }

        ScoreChecker.logScore( instructions.size() );
        commands.add("rest");
        commands.add("perform");
        WorldData.getWorldData().setCommands(commands);
        System.out.println("balls");
        WorldData.getWorldData().setGameState(true);//comment out if using dog
    }

    public static void setControl( int c ) {
        control = c;
    }
}
