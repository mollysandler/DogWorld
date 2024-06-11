import g4p_controls.*;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * @author Molly Sandler
 */
public class Driver extends PApplet{

    private WorldData worldData;
    private final WorldView worldView = new WorldView(this);
    private Diamond diamondRed;
    private Diamond diamondBlue;
    private Diamond diamondGreen;
    private PImage closedDelete;
    private PImage openedDelete;
    private LevelSelector levelSelector;
    public List<Diamond> diamondList = new ArrayList<>();;
    private GImageButton btnPlay;
    private GImageButton sandboxBtn;
    private GImageButton mainWorldBtn;
    private GSlider speedSlider;
    private GImageButton resetBtn;
    private GImageButton saveBtn;
    public Modal currentModal;
    public static int keyDown = -1;
    private GTextField levelNameField;
    private GButton saveLevelBtnModal;
    private final List<Diamond[][]> savedGrids = new ArrayList<>();
    private final List<String> savedLevelNames = new ArrayList<>();
    private GImageButton storeBtn;
    private PFont font;
    private GButton buySpider;
    private GButton selectSpider;
    private GImageButton spiderIcon;
    private GButton selectDog;
    private GImageButton dogIcon;
    private SQSMessenger sqsMessenger;
    private CoinPanel coinPanel;
    private GImageButton onButton;
    private GImageButton offButton;
    enum ScreenState {
        MAIN,
        SANDBOX,
        STORE
    }
    ScreenState currentState = ScreenState.MAIN;
    DragAndDropManager dragAndDropManager;

    @Override
    public void settings(){
        size(1200, 900);
    }
    private boolean isSandboxMode() {
        return currentState == ScreenState.SANDBOX;
    }
    public void buttonDisplay() {
        PImage diamondRedImage = loadImage("src/main/images/red-diamond.png");
        diamondRedImage.resize(40, 40);
        diamondRed = new Diamond(this, 200, 250, diamondRedImage, "red");

        PImage diamondBlueImage = loadImage("src/main/images/blue-diamond.png");
        diamondBlueImage.resize(40, 40);
        diamondBlue = new Diamond(this, 200, 350, diamondBlueImage, "blue");

        PImage diamondGreenImage = loadImage("src/main/images/green-diamond.png");
        diamondGreenImage.resize(40, 40);
        diamondGreen = new Diamond(this, 200, 450, diamondGreenImage, "green");

        String[] resetImage = {"src/main/images/reset.png"};
        resetBtn = new GImageButton(this, 500, 625, 100, 100, resetImage);
        resetBtn.setVisible(false);
        resetBtn.addEventHandler(this, "handleResetButtonEvents");

        String[] saveImage = {"src/main/images/save.png"};
        saveBtn = new GImageButton(this, 350, 625, 100, 100, saveImage);
        saveBtn.setVisible(false);
        saveBtn.addEventHandler(this, "handleSaveButtonEvents");

        //drawing the trashcan images over the background
        closedDelete = loadImage("src/main/images/trash1.png");
        closedDelete.resize(100, 150);
        openedDelete = loadImage("src/main/images/trash2.png");
        openedDelete.resize(100, 150);

        String[] playButtonImgs = {"src/main/images/playButtonImg.png"};
        btnPlay = new GImageButton(this, 180, 615, playButtonImgs);
        btnPlay.addEventHandler(this, "handleButtonEvents");

        String[] sandboxButtonImage = {"src/main/images/sandbox.png"};
        String[] homeButtonImage = {"src/main/images/home.png"};

        sandboxBtn = new GImageButton(this, 950, 100, 100, 100, sandboxButtonImage);
        sandboxBtn.addEventHandler(this, "handleButtonEvents");

        mainWorldBtn = new GImageButton(this, 1000, 100, 100, 100, homeButtonImage);
        mainWorldBtn.addEventHandler(this, "handleButtonEvents");
        mainWorldBtn.setVisible(false);

        speedSlider = new GSlider(this, 25, 475, 275, 100, 30);
        speedSlider.setLimits(50, 0, 100); // initial, left, right
        speedSlider.setNbrTicks(3);
        speedSlider.setShowTicks(true);
        speedSlider.setLocalColorScheme(GConstants.ORANGE_SCHEME);
        speedSlider.addEventHandler(this, "handleSliderEvents");

        GImageButton coins = new GImageButton(this, 15, 775, 100, 100, new String[]{"src/main/images/coin.png"});
        storeBtn = new GImageButton(this, 1055, 110, 90, 90, new String[]{"src/main/images/store.png"});

        buySpider = new GButton(this, 800, 400, 200, 50);
        buySpider.addEventHandler(this, "handleTransaction");
        buySpider.setText("Buy Spider");
        buySpider.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        buySpider.setVisible(false);

        selectSpider = new GButton(this, 800, 400, 200, 50);
        selectSpider.addEventHandler(this, "handleTransaction");
        selectSpider.setText("Select Spider");
        selectSpider.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        selectSpider.setVisible(false);

        spiderIcon = new GImageButton(this, 820, 225, 150, 150, new String[]{"src/main/images/spider_north.png"});
        spiderIcon.setVisible(false);

        selectDog = new GButton(this, 400, 400, 200, 50);
        selectDog.addEventHandler(this, "handleTransaction");
        selectDog.setText("Select Dog");
        selectDog.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        selectDog.setVisible(false);

        dogIcon = new GImageButton(this, 420, 225, 150, 150, new String[]{"src/main/images/dog_north.png"});
        dogIcon.setVisible(false);

        onButton = new GImageButton(this, 1125, 250, 50, 50, new String[]{"src/main/images/onbutton.png"});
        offButton = new GImageButton(this, 1125, 250, 50, 50, new String[]{"src/main/images/offButton.png"});
        offButton.setVisible(false);
    }

    @Override
    public void keyPressed(processing.event.KeyEvent event) {
        keyDown = event.getKeyCode();
        super.keyPressed(event);
        System.out.println(mouseX + ", " + mouseY);
    }

    @Override
    public void setup(){
        worldData = WorldData.getWorldData();
        worldData.addPropertyChangeListener(worldView);
        int currentLevel = 1;
        LoadLevels level = new LoadLevels(currentLevel);

        //gets all the buttons and blocks on the board
        buttonDisplay();

        levelSelector = new LevelSelector(this, 60, 40);
        this.loadImages();

        HashMap<String, ArrayList<Point>> map = level.loadHashMap();
        worldData.setLevel(map);
//        OriginalInstructions originalInstructions = new OriginalInstructions();
        diamondList.add(diamondRed);
        diamondList.add(diamondGreen);
        diamondList.add(diamondBlue);

        OriginalInstructions.setInstructionImages(this);

        dragAndDropManager = new DragAndDropManager(this, closedDelete);
        dragAndDropManager.initialDiamonds = diamondList;// Pass diamondList
        levelSelector.displayButtons();

        sqsMessenger = SQSMessenger.getInstance();
        new Thread(() -> {
            while (true) {
                while (WorldData.getWorldData().getToggleMulti()) {
                    if (sqsMessenger.getiInvoked()) {
                        try {
                            sleep(5000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        sqsMessenger.setiInvoked(false);
                    }
                    String response = sqsMessenger.messageReceiver();
                    if (!response.isEmpty()) {
                        String scores = response;
                        System.out.println(scores);
                        int received_paint_score = Integer.parseInt(scores.substring(0, scores.indexOf(" ")));
                        scores = scores.substring(scores.indexOf(" "));
                        scores = scores.trim();
                        int received_coding_score = Integer.parseInt(scores);

                        WorldData.getWorldData().setOpponentScore(received_paint_score);
                    }

                    System.out.println("Thread going to sleep");
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("sleeping in infinite loop");
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();


//        OurSkill robotSkill = new OurSkill();
//        robotSkill.connectBluetooth();
//        worldData.addPropertyChangeListener(robotSkill);
//        Thread robotThread = new Thread(robotSkill);
//        robotThread.start();
        //uncomment if using dog

        // Initialize the panel but keep it hidden initially
        coinPanel = new CoinPanel(this, 480, 315, 300, 200);
        coinPanel.setVisible(false);
        worldData.addPropertyChangeListener(coinPanel);
        font = createFont("Comic Sans MS", 64);
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

    public void handleSaveButtonEvents(GImageButton save, GEvent event){
        if (save == saveBtn && event == GEvent.CLICKED) {
            currentModal = screen -> {
                screen.fill(255);
                screen.rect(0, 0, 600, 600, 20);
                screen.fill(0);
                screen.textSize(45);
                screen.text("Save level", 0, -250);
            };

            if (levelNameField == null) {
                levelNameField = new GTextField(this, width / 2 - 200, height / 2 - 50, 400, 60);
                levelNameField.setPromptText("Enter level name");
                levelNameField.setFont(new Font("Arial", Font.PLAIN, 24)); // Set text size
                levelNameField.setLocalColorScheme(GCScheme.GOLD_SCHEME); // Set color scheme for better visibility
            }
            levelNameField.setVisible(true);

            if (saveLevelBtnModal == null) {
                saveLevelBtnModal = new GButton(this, width / 2 - 75, height / 2 + 80, 150, 50, "Save level");
                saveLevelBtnModal.setFont(new Font("Arial", Font.PLAIN, 24));
                saveLevelBtnModal.addEventHandler(this, "handleLevelSave");
            }
            saveLevelBtnModal.setVisible(true);
        }
    }

    public void handleLevelSave(GButton button, GEvent event) {
        if (event == GEvent.CLICKED) {
            String levelName = levelNameField.getText();
            saveLevelData(levelName);
            savedLevelNames.add(levelName);
            System.out.println("Level name saved: " + levelName);

            levelNameField.dispose();
            levelNameField = null;
            saveLevelBtnModal.dispose();
            saveLevelBtnModal = null;
            currentModal = null;
        }
    }

    public void saveLevelData(String levelName) {
        Diamond[][] savedGrid = new Diamond[5][5];
        for (int i = 0; i < dragAndDropManager.diamondGrid.length; i++) {
            for (int j = 0; j < dragAndDropManager.diamondGrid[i].length; j++) {
                if (dragAndDropManager.diamondGrid[i][j] != null) {
                    try {
                        savedGrid[i][j] = dragAndDropManager.diamondGrid[i][j].clone();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        savedGrids.add(savedGrid);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/Levels/SandboxCreatedLevels/" + levelName + ".txt"))) {
            for (Diamond[] row : savedGrid) {
                for (int col = 0; col < row.length; col++) {
                    Diamond diamond = row[col];
                    if (diamond != null) {
                        writer.write(diamond.serialize());
                    } else {
                        writer.write("null");
                    }
                    if (col < row.length - 1) {
                        writer.write(" ");
                    }
                }
                writer.newLine();
            }
            System.out.println("Level saved successfully.");
        } catch (IOException e) {
            throw new RuntimeException(e);
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
            case STORE:
                drawStore();
                break;
        }
        if (currentModal != null) {
            fill(0, 100);
            rect(0, 0, width, height);
            push();
            translate((float) width / 2, (float) height / 2);
            rectMode(CENTER);
            textAlign(CENTER, CENTER);
            currentModal.display(this);
            textAlign(CORNER, CORNER);
            rectMode(CORNER);
            pop();

            // Modal boundaries
            float modalX = (float) width / 2 - 300;
            float modalY = (float) height / 2 - 300;
            float modalWidth = 600;
            float modalHeight = 600;

            if (mousePressed && (mouseX < modalX || mouseX > modalX + modalWidth || mouseY < modalY || mouseY > modalY + modalHeight)) {
                currentModal = null;
                levelNameField.setVisible(false);
                saveLevelBtnModal.setVisible(false);
            }
        }
        keyDown = -1;
        textFont(font);
        fill(255,255,255);
        textSize(64);
        text(String.valueOf(WorldData.getWorldData().getCoins()), 120, 850);
    }

    private void drawMain() {
        background(40,52,68);
        mainWorldBtn.setVisible(false);
        diamondRed.setVisible(false);
        resetBtn.setVisible(false);
        saveBtn.setVisible(false);
        storeBtn.setVisible(true);

        noStroke();
        fill(28, 37, 48);
        rect(350, 140, 600, 700, 20);
        noStroke();
        fill(255, 80);
        for (int i = 370; i < 950; i += 40) {
            for (int j = 170; j < 840; j += 40) {
                circle(i, j, 2);
            }
        }

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
        btnPlay.setEnabled(WorldData.getWorldData().getGameState());
        dragAndDropManager.makeDraggable(false);
        levelSelector.displayNavBar();
        levelSelector.showButtons();
//        sqsMessenger.update();
    }

    private void drawSandbox() {
        background(190, 164, 132);
        storeBtn.setVisible(false);
        noStroke();
        fill(0, 20);
        rect(680, 140, 300, 700, 20);
        noStroke();
        fill(255, 150);
        for (int i = 710; i < 980; i += 40) {
            for (int j = 170; j < 840; j += 40) {
                circle(i, j, 2);
            }
        }
        drawLevelButtons();

        // reset diamonds
        for (Diamond diamond : diamondList){
            diamond.display();
        }

        // Enable diamonds?
        worldView.drawSandGrid();
        saveBtn.setEnabled(currentModal == null);
        saveBtn.setVisible(currentModal == null);
        resetBtn.setVisible(currentModal == null);
        resetBtn.setEnabled(currentModal == null);

        for (int i = 0; i < OriginalInstructions.getInstance().length - 1; i++) {
            (OriginalInstructions.getInstance()[i]).display();
        }

        diamondRed.display();
        diamondGreen.display();
        diamondBlue.display();
        for ( Instruction inst : OriginalInstructions.getInstance() ) inst.display();
        // to edit the sandbox instruction images make a new setup method in OriginalInstructions
        mainWorldBtn.setVisible(true);
        levelSelector.hideButtons();

        //if the mouse is over the trashcan, display the opened can
        if (mouseX > 100 && mouseX < 100 + closedDelete.width && mouseY > 600 && mouseY < 600 + closedDelete.height) {
            image(openedDelete, 60, 600); //display the open trash can
        }
        else {
            //otherwise display the closed trashcan
            image(closedDelete, 60, 600);
        }
        dragAndDropManager.makeDraggable(true);
        text("Welcome to SandBox", 280,  80);
    }

    private void drawStore(){
        background(40,52,68);
        levelSelector.hideButtons();
        buySpider.setVisible(true);
        spiderIcon.setVisible(true);
        selectDog.setVisible(true);
        dogIcon.setVisible(true);

        if (WorldData.getWorldData().getUnlockedAvatars().contains("spider")){
            buySpider.setVisible(false);
            selectSpider.setVisible(true);
        }
    }
    public void handleTransaction(GButton button, GEvent event){
        if (button == buySpider && event == GEvent.CLICKED){
            WorldData.getWorldData().buyAvatar("spider");
        } else if (button == selectSpider && event == GEvent.CLICKED) {
            WorldData.getWorldData().setAvatar("spider");
        } else if (button == selectDog && event == GEvent.CLICKED) {
            WorldData.getWorldData().setAvatar("dog");
        }
    }

    private void drawLevelButtons() {
        push();
        translate((float) 200, (float) 200);
        rectMode(CENTER);
        textAlign(CENTER, CENTER);
        float off = 0;
        final float gap = 20;
        final float padding = 20;
        textSize(20);
        for (int i = 0; i < savedLevelNames.size(); i ++) {
            final String level = savedLevelNames.get(i);
            final float tw = textWidth(level);
            final float buttonWidth = tw + padding;
            off += tw/2 + gap;
            fill(255);
            rect(off, 0, buttonWidth, 60);
            fill(0);
            text(level, off, 0);

            if (abs(mouseX - ((float) 200 + off)) <= buttonWidth / 2 && abs(mouseY - (float) 200) <= 30 && mousePressed) {
                // On level selected
                dragAndDropManager.addedDiamonds.clear();
                loadLevelData(savedLevelNames.get(i));
            }
            off += tw/2 + gap;
        }
        rectMode(CORNER);
        textAlign(CORNER, CORNER);
        pop();
    }

    public void loadLevelData(String levelName) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/Levels/SandboxCreatedLevels/" + levelName + ".txt"))) {
            PImage diamondRedImage = loadImage("src/main/images/red-diamond.png");
            PImage diamondBlueImage = loadImage("src/main/images/blue-diamond.png");
            PImage diamondGreenImage = loadImage("src/main/images/green-diamond.png");
            diamondRedImage.resize(50, 50);
            diamondBlueImage.resize(50, 50);
            diamondGreenImage.resize(50, 50);
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null && row < 5) {
//                System.out.println("Processing line: " + line);
                String[] cells = line.split(" ");
                for (int col = 0; col < cells.length && col < 5; col++) {
                    if (!"null".equals(cells[col])) {
                        Diamond diamond = Diamond.deserialize(cells[col], this, diamondRedImage, diamondBlueImage, diamondGreenImage);
                        dragAndDropManager.addedDiamonds.add(diamond);
                    }
                }
                row++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void handleButtonEvents(GImageButton imagebutton, GEvent event){
        if (imagebutton == btnPlay && event == GEvent.CLICKED){
            WorldData.getWorldData().resetWorld();
            WorldData.getWorldData().setGameState(false);
            PlayButtonFunc playButtonFunc = new PlayButtonFunc();
            Thread t1 = new Thread(playButtonFunc);
            t1.start();
        } else if (imagebutton == storeBtn && event == GEvent.CLICKED) {
            cleanUpMap();
            if (currentState == ScreenState.STORE){
                currentState = ScreenState.MAIN;
            }else if (currentState == ScreenState.MAIN){
                currentState = ScreenState.STORE;
            }

        } else if (imagebutton == sandboxBtn && event == GEvent.CLICKED){
            cleanUpMap();
            currentState = ScreenState.SANDBOX;
            // println("Switched to sandbox");
        } else if (imagebutton == mainWorldBtn && event == GEvent.CLICKED){
            cleanUpMap();
            currentState = ScreenState.MAIN;
            levelSelector.showButtons();
            // Clean up Sandbox
        } else if (imagebutton == resetBtn && event == GEvent.CLICKED){
            dragAndDropManager.addedDiamonds.clear();
            diamondList.removeAll(dragAndDropManager.addedDiamonds);
            dragAndDropManager.diamondGrid = new Diamond[5][5];
        } else if (imagebutton == onButton && event == GEvent.CLICKED) {
            onButton.setVisible(false);
            offButton.setVisible(true);
            WorldData.getWorldData().setToggleMulti(true);

        } else if (imagebutton == offButton && event == GEvent.CLICKED) {
            offButton.setVisible(false);
            onButton.setVisible(true);
            WorldData.getWorldData().setToggleMulti(false);
        }
    }

    public void handleSliderEvents(GSlider slider, GEvent event){
        if (slider == speedSlider && event == GEvent.RELEASED){
            WorldData.getWorldData().setSpeed(slider.getValueI());
        }
    }

    public void cleanUpMap(){
        if (currentState == ScreenState.MAIN){
            //clean up main
            worldData.resetWorld();
            btnPlay.setVisible(false);
            btnPlay.setEnabled(false);
            sandboxBtn.setVisible(false);
            sandboxBtn.setEnabled(false);
            speedSlider.setVisible(false);
        }
        else if (currentState == ScreenState.STORE){
            spiderIcon.setVisible(false);
            buySpider.setVisible(false);
            selectSpider.setVisible(false);
            selectDog.setVisible(false);
            dogIcon.setVisible(false);

            //clean up store
        }else if (currentState == ScreenState.SANDBOX){
            dragAndDropManager.addedDiamonds.clear();
            diamondList.removeAll(dragAndDropManager.addedDiamonds);
            dragAndDropManager.diamondGrid = new Diamond[5][5];
        }
    }

    @Override
    public void mousePressed() {
        dragAndDropManager.mousePressed(isSandboxMode());
//        System.out.println(mouseX + ", " + mouseY);
    }

    @Override
    public void mouseReleased() {
        dragAndDropManager.mouseReleased(isSandboxMode());
    }

    public static void main(String[] args) {
        String[] processingArgs = {"Driver"};
        Driver running = new Driver();
        PApplet.runSketch(processingArgs, running);
    }
}
