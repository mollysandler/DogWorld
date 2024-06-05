import java.beans.PropertyChangeSupport;
import java.util.*;

import static processing.core.PApplet.println;

/**
 * @author Jemma Arona
 */
public final class WorldData extends PropertyChangeSupport {
    private static WorldData worldData;
    private int numRows;
    private HashMap <String, ArrayList <Point>> levelMap;
    private final HashMap <Point, String> tileMap;
    private final int[] spider;
    private final int[] bgColor;
    private int speed = 250;
    private boolean gameState = true;
    private List<String> commands;
    private boolean gameStatus;
    private int userScore;

    private WorldData() {
        super( new Object() );
        numRows = 0;
        tileMap = new HashMap<>();
        spider = new int[]{0, 0, 0};
        bgColor = new int[]{0, 0, 0};
        gameStatus = true;
    }

    public static WorldData getWorldData() {
        if ( worldData == null ) {
            worldData = new WorldData();
        }
        return worldData;
    }
    public void setSpeed(int speed) {
        this.speed = speed * 5;
    }

    public int getSpeed() {
        return speed;
    }

    public void setCommands(List<String> commandsInput){
        commands = commandsInput;
        firePropertyChange("commands", null, commands);
    }
    public void setScore(int score){
        userScore = score;
        firePropertyChange("score", null, userScore);
    }

    public void setLevel( HashMap <String, ArrayList<Point>> level ) {
        setLevel( level, 5, 26, 26, 26 );
    }

    public void setLevel( HashMap <String, ArrayList<Point>> level, int rows, int r, int g, int b ) {
        levelMap = level;
        if ( levelMap == null ) {
            levelMap = new HashMap<>();
        }
        numRows = rows;
        bgColor[0] = r;
        bgColor[1] = g;
        bgColor[2] = b;
        firePropertyChange( "levelMap", null, levelMap );
        firePropertyChange( "numRows", null, numRows );
        firePropertyChange( "bgColor", null, bgColor );
        resetWorld();
    }

    public int[] getSpider() {
        return spider;
    }

    public int[] getBgColor() {
        return bgColor;
    }

    public int getNumRows() {
        return numRows;
    }

    /**
     * @param rot: 0 = east, 1 = north, 2 = west, 3 = south
     */
    public void moveSpider( int x, int y, int rot ) {
        if ( x >= numRows || y >= numRows || x < 0 || y < 0 ) {
            throw new RuntimeException( "Spider Out of Bounds" );
        } else {
            this.spider[0] = x;
            this.spider[1] = y;
            this.spider[2] = rot%4;
        }
        firePropertyChange( "spider", null, spider );
        firePropertyChange( "visible", null, true );
    }

    public HashMap<String, ArrayList<Point>> getLevelMap() {
        return levelMap;
    }
    public HashMap<Point, String> getTileMap() {
        return tileMap;
    }

    public void setGameState(boolean gameState) {
        this.gameState = gameState;
    }
    public boolean getGameState(){
        return this.gameState;
    }

    public void paintTile( int x, int y, String color ) {
        PaintMixer.addPaint( x, y, color );
        firePropertyChange( "visible", null, true );
    }

    public void resetWorld() {
        tileMap.clear();
        firePropertyChange( "tileMap", null, tileMap);
        try {
            Point pos = levelMap.get("spider").get(0);
            Point rot = levelMap.get("spider").get(1);
            moveSpider(pos.getX(), pos.getY(), rot.getX());
        } catch ( NullPointerException e ) {
            moveSpider( 0, 0, 0 );
        }
    }

    public void sandboxWorld(){
        tileMap.clear();
        println("cleared");

    }

    public boolean getGameStatus() {
        return gameStatus;
    }
}

