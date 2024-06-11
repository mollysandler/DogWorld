import g4p_controls.GEvent;
import g4p_controls.GImageButton;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Aayush Joshi
 */
public class LevelSelector{

    private final PApplet screen;
    private final ArrayList<PImage> imgs;
    private final int xPos;
    private final int yPos;
    private final HashMap<GImageButton, Integer> buttons = new HashMap<>();

    public LevelSelector(PApplet screen, int xPos, int yPos) {
        this.screen = screen;
        this.xPos = xPos;
        this.yPos = yPos;
        this.imgs = new ArrayList<>();
    }

    public void displayButtons(){
        int level = 1;
        int offset = 0;
        for (PImage image : imgs){
            //add levels
            String[] imageLoc = {"src/main/images/numbers/" + level + ".png"};

            GImageButton levelButton = new GImageButton(screen, xPos + offset, yPos, image.width, image.height, imageLoc);
            levelButton.addEventHandler(this, "handleLevelBtnEvents");
            buttons.put(levelButton, level);

            level++;
            offset += 55;
        }
    }
    public void handleLevelBtnEvents(GImageButton button, GEvent event) {
        if (event == GEvent.CLICKED){
            int level = buttons.get(button);
            LoadLevels l = new LoadLevels(level);
            WorldData.getWorldData().setLevel(l.loadHashMap());
            double temperature = l.loadHashMap().get("weather").get(0).getX();
        }
    }

    public void addToImg(PImage image) {
        imgs.add(image);
    }
    public void displayNavBar() {
        screen.fill(200, 200, 200);
        screen.rect(xPos - 25, yPos - 10, 1130, 60);
        screen.circle(xPos - 20, yPos + 20, 62);
        screen.circle(xPos + 1095, yPos + 20, 62);
    }

    public void hideButtons(){
        for (GImageButton button : buttons.keySet()){
            button.setVisible(false);
        }
    }

    public void showButtons(){
        for (GImageButton button : buttons.keySet()){
            button.setVisible(true);
        }
    }
}
