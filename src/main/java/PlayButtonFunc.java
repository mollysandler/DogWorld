import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Andy Duong
 */
public class PlayButtonFunc implements Runnable{
    private static int control;
    private final boolean isSand;
    private Spider spider;
    public PlayButtonFunc( boolean isSand ){
        this.isSand = isSand;
    }
    public void setSpider(Spider spider) {
        this.spider = spider;
    }

    @Override
    public void run() {
        WorldData.getWorldData().getWeather().handleStart();
        InstructionList instructionList = InstructionList.getInstance();
        control = 0;

        List<Instruction> instructions = instructionList.getSortedInstructions();
        if(instructions.isEmpty()){
            WorldData.getWorldData().setGameState(true);
            return;
        }

        List<String> commands = new ArrayList<>();
        for (Instruction instruction : instructions) {
            if (instruction instanceof RepeatInstruction) ((RepeatInstruction) instruction).iterations = 0;
        }

        //per instruction send each instruction to their respective function
        int instructionIndex = 0;
        Stack<Integer> repeatStack = new Stack<>();
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
        }

        if ( isSand ) {
            try {
                Thread.sleep( 2000 );
                WorldData.getWorldData().resetWorld();
                WorldData.getWorldData().clearSpider();
                spider.setVisible( true );
                return;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        ScoreChecker.logScore( instructions.size() );
        commands.add("rest");
        commands.add("perform");
        WorldData.getWorldData().setCommands(commands);
        WorldData.getWorldData().setGameState(true);//comment out if using dog
    }
    public static void setControl( int c ) {
        control = c;
    }
}
