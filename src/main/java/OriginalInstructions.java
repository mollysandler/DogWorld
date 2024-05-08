/**
 * @author Riya Badadare
 */
public class OriginalInstructions {

    private static Instruction[] instance;

    public OriginalInstructions(Instruction[] initInstructions) {
        instance = initInstructions;
    }

    public static Instruction[] getInstance() {
        return instance;
    }
}

