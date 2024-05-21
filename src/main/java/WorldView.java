import processing.core.PApplet;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Jemma Arona
 */
public final class WorldView implements PropertyChangeListener {
    public final PApplet screen;
    private static final float leftPadding = 9;
    private static final float topPadding = 143;
    private final float tileWidth = 60;
    private int numRows;

    private int sandGrid = 5;
    private int sandCell = 70;
    private int sandX = 300;
    private int sandY = 250;
    private int bgColor;
    private int[] spider;
    private HashMap <String, ArrayList <Point>> levelMap;
    private HashMap <Point, String> tileMap;

    public WorldView(PApplet screen) {
        this.screen = screen;
    }


    public void drawGrid() {
        screen.fill( bgColor );
        screen.stroke( 204, 204, 204 );
        screen.rect( leftPadding, topPadding, tileWidth * numRows, tileWidth * numRows );
        for ( int row = 1; row < numRows; row++ ) {
            screen.line( leftPadding, topPadding + tileWidth * row,
                    leftPadding + tileWidth * numRows, topPadding + tileWidth * row );
        }
        for ( int col = 1; col < numRows; col++ ) {
            screen.line( leftPadding + tileWidth * col, topPadding,
                    leftPadding + tileWidth * col, topPadding + tileWidth * numRows );
        }
    }

    public void drawSandGrid(){

        screen.color(50, 50, 50);
        for (int i = 0; i < sandGrid; i++) {
            for (int j = 0; j < sandGrid; j++) {
                screen.stroke(0);
                screen.fill(211, 211, 210);
                screen.rect(sandX + i * sandCell, sandY + j * sandCell, sandCell, sandCell);
            }

        }


    }

    public void drawDiamonds() {
        screen.textSize( 12 );
        for ( String colorStr : levelMap.keySet() ) {
            switch (colorStr) {
                case "red":
                    screen.fill( screen.color( 255, 89, 94 ) );
                    break;
                case "green":
                    screen.fill( screen.color( 138, 201, 38 ) );
                    break;
                case "blue":
                    screen.fill( screen.color( 63, 166, 231 ) );
                    break;
                case "spider":
                    continue;
                default:
                    int[] color = PaintMixer.getPaintColor(colorStr);
                    screen.fill( screen.color( color[0], color[1], color[2] ) );
            }
            for ( Point p : levelMap.get(colorStr)) {
                float diamondX = (float) ( leftPadding + tileWidth * ( p.getX() + .5 ) - 5 );
                float diamondY = (float) ( topPadding + tileWidth * ( p.getY() + .5 ) + 5 );
                screen.text('◆', diamondX, diamondY );
            }
        }
    }

    public void drawSpider() {
        String IMAGEFOLDERPATH = "src/main/images/";
        String imgPath = IMAGEFOLDERPATH;
        switch ( spider[2] ) {
            case 1:
                imgPath += "spider_north.png";
                break;
            case 2:
                imgPath += "spider_west.png";
                break;
            case 3:
                imgPath += "spider_south.png";
                break;
            default:
                imgPath += "spider_east.png";
                break;
        }
        screen.image( screen.loadImage( imgPath ), leftPadding + spider[0]*tileWidth, topPadding + spider[1]*tileWidth );
    }

    public void drawPaint() {
        for ( Point p : tileMap.keySet() ) {
            int[] color = PaintMixer.getPaintColor(p);
            screen.fill( color[0], color[1], color[2] );
            screen.rect(leftPadding + tileWidth * ( p.getX() ), topPadding + tileWidth * ( p.getY() ), tileWidth, tileWidth );
        }
    }

    public void drawWorld() {
        drawGrid();
        drawPaint();
        drawDiamonds();
        drawSpider();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch ( evt.getPropertyName() ) {
            case "bgColor":
                bgColor = screen.color(((int[]) evt.getNewValue())[0], ((int[]) evt.getNewValue())[1], ((int[]) evt.getNewValue())[2]);
                break;
            case "numRows":
                numRows = (int) evt.getNewValue();
                break;
            case "levelMap":
                levelMap = (HashMap<String, ArrayList<Point>>) evt.getNewValue();
                break;
            case "tileMap":
                tileMap = (HashMap<Point, String>) evt.getNewValue();
                break;
            case "spider":
                spider = (int[]) evt.getNewValue();
                break;
            case "visible":
                boolean visible = (boolean) evt.getNewValue();
                if (visible) drawWorld();
                break;
        }
    }


}
