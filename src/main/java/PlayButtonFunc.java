import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
            WorldData.getWorldData().setGameState(true);
            return;
        }

//        int[] dataSpider = myData.getSpider();
        List<String> commands = new ArrayList<>();

        for (Instruction instruction : instructions) {
            if (instruction instanceof RepeatInstruction) ((RepeatInstruction) instruction).iterations = 0;
        }

        //per instruction send each instruction to their respective function
        int instructionIndex = 0;
        Stack<Integer> repeatStack = new Stack();

        while (instructionIndex < instructions.size()) {
            Instruction instruction = instructions.get(instructionIndex);

            if (instruction instanceof RepeatInstruction) {
                repeatStack.push(instructionIndex);
                System.out.println("stack");
            }

            else if (instruction instanceof EndRepeatInstruction && !repeatStack.isEmpty()) {
                RepeatInstruction ri = (RepeatInstruction) instructions.get(repeatStack.peek());
                ri.iterations ++;
                if (ri.iterations >= ri.maxIterations) {
                    repeatStack.pop();
                    ri.iterations = 0;
                }
                else instructionIndex = repeatStack.peek();
                System.out.println(ri.iterations);
            }

            instruction.checkRunAction( control );
            instructionIndex ++;

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
        System.out.println("balls");
        WorldData.getWorldData().setGameState(true);//comment out if using dog
    }

    public static void setControl( int c ) {
        control = c;
    }
}
