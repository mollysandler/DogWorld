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

        WorldData myData = WorldData.getWorldData();
        InstructionList instructionList = InstructionList.getInstance();
        control = 0;

        List<Instruction> instructions = instructionList.getSortedInstructions();

        if(instructions.isEmpty()){
            return;
        }

//        int[] dataSpider = myData.getSpider();
        List<String> commands = new ArrayList<>();

        //per instruction send each instruction to their respective function
        for(Instruction instruction : instructions) {
            instruction.checkRunAction( control );

//            if(instruction.getSkill() .equals( "turn")){
//                commands.add(instruction.getSkill());
//                commands.add(instruction.getSkill());
//                commands.add(instruction.getSkill());
//                commands.add(instruction.getSkill());
//                commands.add(instruction.getSkill());
//            }
//            commands.add(instruction.getSkill());
//            commands.add("perform");
//            WorldData.getWorldData().setCommands(commands);
//
//            try {
//                Thread.sleep(myData.getSpeed());
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            instruction.runAction();
//            commands.clear();
        }

        ScoreChecker.logScore( instructions.size() );
        commands.add("rest");
        commands.add("perform");
        WorldData.getWorldData().setCommands(commands);

//        WorldData.getWorldData().setGameState(true);//comment out if using dog
    }

    public static void setControl( int c ) {
        control = c;
    }
}
