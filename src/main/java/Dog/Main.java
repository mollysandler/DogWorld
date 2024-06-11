package Dog;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.IOException;
/**
 * @author Ivan Martinez
 */

public class Main {
    public Main() {
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to an Ineractive RoboDog");
        System.out.println("Pick a list of commands from the available options to choose what skills you would like to see the RoboDog Perform");
        Skill sk = new Skill();
        sk.printAllSkills(); //prints all possible skills
        sk.RobotSkillSet();
    }
}
