import java.util.HashMap;
import java.util.Objects;

/**
 * @author Jemma Arona
 */

public class PaintMixer {

    public static void addPaint( int x, int y, String newColor ) {
        HashMap<Point, String> tileMap = WorldData.getWorldData().getTileMap();
        Point p = new Point( x, y );
        if ( Objects.equals(newColor, "erase") ) {
            tileMap.remove( p );
        }
        if ( tileMap.containsKey( p ) ) {
            String oldColor = tileMap.get( p );
            if ( Objects.equals( oldColor, "snow" ) ) return;
            if ( oldColor.length() < 6 ) {
                tileMap.replace(p, newColor.charAt(0) + oldColor);
            }
        }
        else {
            tileMap.put(p, newColor.substring( 0, 1 ));
        }
    }
    public static int[] getPaintColor( Point p ) {
        HashMap<Point, String> tileMap = WorldData.getWorldData().getTileMap();
        if ( !tileMap.containsKey(p) ) return WorldData.getWorldData().getBgColor();
        return getPaintColor( tileMap.get(p) );
    }

    public static int[] getPaintColor( String colorStr ) {
        int[] color = new int[]{0, 0, 0};
        switch ( colorStr.charAt(0) ) {
            case 'r':
                color[0] = 256;
                break;
            case 'g':
                color[1] = 256;
                break;
            case 'b':
                color[2] = 256;
                break;
            case 'l':
                color = new int[]{256, 256, 256};
                break;
            case 'd':
            default:
                break;
        }
        for ( int i = 1; i < colorStr.length(); i++ ) {
            switch ( colorStr.charAt(i) ) {
                case 'r':
                    color[0] += 256;
                    color[1] += 128;
                    color[2] += 128;
                    break;
                case 'g':
                    color[0] += 128;
                    color[1] += 256;
                    color[2] += 128;
                    break;
                case 'b':
                    color[0] += 128;
                    color[1] += 128;
                    color[2] += 256;
                    break;
                case 'l':
                    color[0] += 256;
                    color[1] += 256;
                    color[2] += 256;
                    break;
                case 'd':
                default:
                    break;
            }
            color[0] = color[0]/2;
            color[1] = color[1]/2;
            color[2] = color[2]/2;
        }
        return color;
    }
}
