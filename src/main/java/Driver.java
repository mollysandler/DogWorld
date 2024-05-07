import g4p_controls.*;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Molly Sandler
 */
public class Driver extends PApplet{

    private WorldData worldData;
    private final WorldView worldView = new WorldView(this);
    private LoadLevels level;
    private OriginalInstructions originalInstructions;
    private StepInstruction stepBlock;
    private TurnInstruction turnBlock;
    private PaintInstruction paintBlueBlock, paintGreenBlock, paintRedBlock, paintLightBlock, paintDarkBlock;
    private PImage closedDelete;
    private PImage openedDelete;
    private InstructionList instructionCopies = InstructionList.getInstance();

    private LevelSelector levelSelector;
    private static int currentLevel = 1;

    private GPanel blockPanel;
    private GImageButton btnPlay;

    private GImageButton sandboxBtn;

    private GImageButton mainWorldBtn;

    private GSlider speedSlider;

    enum ScreenState {
        MAIN,
        SANDBOX

    }

    ScreenState currentState = ScreenState.MAIN;
    DragAndDropManager dragAndDropManager;

    @Override
    public void settings(){
        size(1200, 900);
    }

    public void buttonDisplay() {
        //displaying all buttons
        PImage stepBlockImage = loadImage("src/main/images/step.png");
        stepBlock = new StepInstruction(this, 1000, 200, stepBlockImage);
        PImage turnBlockImage = loadImage("src/main/images/turn.png");
        turnBlock = new TurnInstruction(this, 1000, 275, turnBlockImage);
        PImage paintBlueBlockImage = loadImage("src/main/images/paint_blue.png");
        paintBlueBlock = new PaintInstruction(this, 1000, 350, paintBlueBlockImage, "blue");
        PImage paintGreenBlockImage = loadImage("src/main/images/paint_green.png");
        paintGreenBlock = new PaintInstruction(this, 1000, 425, paintGreenBlockImage, "green");
        PImage paintRedBlockImage = loadImage("src/main/images/paint_red.png");
        paintRedBlock = new PaintInstruction(this, 1000, 500, paintRedBlockImage, "red");
        PImage paintLightBlockImage = loadImage("src/main/images/paint_light.png");
        paintLightBlock = new PaintInstruction(this, 1000, 575, paintLightBlockImage, "light");
        PImage paintDarkBlockImage = loadImage("src/main/images/paint_dark.png");
        paintDarkBlock = new PaintInstruction(this, 1000, 650, paintDarkBlockImage, "dark");

        //drawing the trashcan images over the background
        closedDelete = loadImage("src/main/images/trash1.png");
        closedDelete.resize(100, 150);
        openedDelete = loadImage("src/main/images/trash2.png");
        openedDelete.resize(100, 150);

//        Driver.class.getClass().getClassLoader().getResource("fila name");
        String[] playButtonImgs = {"src/main/images/playButtonImg.png"};
        btnPlay = new GImageButton(this, 180, 615, playButtonImgs);
        btnPlay.addEventHandler(this, "handleButtonEvents");

        String[] sandboxButtonImage = {"src/main/images/sandbox.png"};
        String[] homeButtonImage = {"src/main/images/home.png"};

        sandboxBtn = new GImageButton(this, 1000, 100, 100, 100, sandboxButtonImage);
        sandboxBtn.addEventHandler(this, "handleSandboxEvents");

        mainWorldBtn = new GImageButton(this, 1000, 100, 100, 100, homeButtonImage);
        mainWorldBtn.addEventHandler(this, "handleMainWorldButtonEvents");
        mainWorldBtn.setVisible(false);

        speedSlider = new GSlider(this, 25, 475, 275, 100, 30);
        speedSlider.setLimits(50, 0, 100); // initial, left, right
        speedSlider.setNbrTicks(3);
        speedSlider.setShowTicks(true);
        speedSlider.setLocalColorScheme(GConstants.ORANGE_SCHEME);
        speedSlider.addEventHandler(this, "handleSliderEvents");
    }

    @Override
    public void setup(){

        worldData = WorldData.getWorldData();
        worldData.addPropertyChangeListener(worldView);
//        try {
//            LevelGenerator.makeLevels();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        level = new LoadLevels(currentLevel);

        //gets all the buttons and blocks on the board
        buttonDisplay();

        levelSelector = new LevelSelector(this, 60, 40);
        this.loadImages();

        HashMap<String, ArrayList<Point>> map = level.loadHashMap();
        worldData.setLevel(map);

        originalInstructions = new OriginalInstructions(stepBlock, turnBlock, paintBlueBlock, paintGreenBlock, paintRedBlock, paintLightBlock, paintDarkBlock);

        dragAndDropManager = new DragAndDropManager(this, closedDelete);
        levelSelector.displayButtons();

//        blockPanel = new BlockPanel(this, 100, 100, 600, 900);

    }

    public void loadImages() {
        for (int i = 1; i < 21; i++) {
            String fName = "src/main/images/numbers/" + i + ".png";
            switch (fName) {
                case "src/main/images/numbers/1.png", "src/main/images/numbers/5.png", "src/main/images/numbers/7.png" -> {
                    PImage img = loadImage(fName);
                    img.resize(30, 0);
                    levelSelector.addToImg(img);
                }
                case "src/main/images/numbers/3.png", "src/main/images/numbers/6.png" -> {
                    PImage img = loadImage(fName);
                    img.resize(35, 0);
                    levelSelector.addToImg(img);
                }
                default -> {
                    PImage img = loadImage(fName);
                    img.resize(40, 0);
                    levelSelector.addToImg(img);
                }
            }
        }
    }


    public void draw() {
        switch (currentState) {
            case MAIN:
                drawMain();
                break;
            case SANDBOX:
                drawSandbox();
                break;
        }

    }

    public void drawMain() {
        background(100, 100, 100);
        mainWorldBtn.setVisible(false);


        for (Instruction currInstruction : OriginalInstructions.getInstance()) {
            currInstruction.display();
        }

        //if the mouse is over the trashcan, display the opened can
        if (mouseX > 100 && mouseX < 100 + closedDelete.width && mouseY > 600 && mouseY < 600 + closedDelete.height) {
            image(openedDelete, 60, 600); //display the open trash can
        }
        else {
            //otherwise display the closed trashcan
            image(closedDelete, 60, 600);
        }

        worldView.drawWorld();
        sandboxBtn.setVisible(true);
        sandboxBtn.setEnabled(true);
        btnPlay.setEnabled(true);
        btnPlay.setVisible(true);
        speedSlider.setEnabled(true);
        speedSlider.setVisible(true);

        btnPlay.setEnabled(!WorldData.getWorldData().getGameState());

        dragAndDropManager.makeDraggable();

        levelSelector.displayNavBar();


    }

    public void drawSandbox(){
        background(190, 164, 132);
        worldView.drawSandGrid();
        mainWorldBtn.setVisible(true);
        levelSelector.hideButtons();
        PFont font = createFont("Arial-Bold", 48); // Load a bold Arial font at size 48
        textFont(font);
        textAlign(CENTER, TOP);

        text("Welcome to SandBox", (float) width / 2,  50);

    }
    public void handleButtonEvents(GImageButton imagebutton, GEvent event){
        if (imagebutton == btnPlay && event == GEvent.CLICKED){
            WorldData.getWorldData().resetWorld();
            WorldData.getWorldData().setGameState(true);
            PlayButtonFunc playButtonFunc = new PlayButtonFunc();
            Thread t1 = new Thread(playButtonFunc);
            t1.start();
        }
    }
    public void handleSliderEvents(GSlider slider, GEvent event){
        if (slider == speedSlider && event == GEvent.RELEASED){
            WorldData.getWorldData().setSpeed(slider.getValueI());
        }
    }

    public void handleSandboxEvents(GImageButton sandButton, GEvent event){
        if (sandButton == sandboxBtn && event == GEvent.CLICKED){
            cleanUpMain();
            currentState = ScreenState.SANDBOX;
            // println("Switched to sandbox");
        }
    }

    public void handleMainWorldButtonEvents(GImageButton mainButton, GEvent event){
        if (mainButton == mainWorldBtn && event == GEvent.CLICKED){
            currentState = ScreenState.MAIN;
            levelSelector.showButtons();
            // Clean up Sandbox
        }
    }

    public void cleanUpMain(){
        worldData.resetWorld();
        btnPlay.setVisible(false);
        btnPlay.setEnabled(false);
        sandboxBtn.setVisible(false);
        sandboxBtn.setEnabled(false);
        speedSlider.setVisible(false);
    }


    @Override
    public void mousePressed() {
        dragAndDropManager.mousePressed();
    }

    @Override
    public void mouseReleased() {
        dragAndDropManager.mouseReleased();
    }


    public static void main(String[] args) {
        String[] processingArgs = {"Driver"};
        Driver running = new Driver();
        PApplet.runSketch(processingArgs, running);
    }
}
