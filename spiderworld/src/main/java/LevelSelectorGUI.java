import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

/**
 * @author Aayush Joshi
 */
public class LevelSelectorGUI extends PApplet {

    private final PApplet screen;
    private final ArrayList<PImage> imgs;
    private final int xPos;
    private final int yPos;

    public LevelSelectorGUI(PApplet screen, int xPos, int yPos) {
        this.screen = screen;
        this.xPos = xPos;
        this.yPos = yPos;
        this.imgs = new ArrayList<>();
    }

    public void addToImg(PImage image) {
        imgs.add(image);
    }

    public void display() {
        screen.fill(200, 200, 200);
        screen.rect(xPos - 25, yPos - 10, 1130, 60);
        screen.circle(xPos - 20, yPos + 20, 62);
        screen.circle(xPos + 1095, yPos + 20, 62);

        int offset = 0;
        for (PImage image : imgs) {
            screen.image(image, xPos + offset, yPos);
            offset += 55;
        }
    }

    public void mousePressed() {
        int offset = 0;
        int level = 1;
        for (PImage image : imgs) {
            if ((screen.mouseX > 50 + offset) && (screen.mouseX < 50 + offset + image.width + 10) &&
                    (screen.mouseY > 15) && (screen.mouseY < 15 + image.height + 10)) {
                System.out.println(level);
                Driver.setLevel(level);
                LoadLevels l = new LoadLevels(level);
                WorldData.getWorldData().setLevel(l.loadHashMap());
                break;
            }
            offset += 55;
            level += 1;
        }
    }
}
