import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HotWeatherHandler extends WeatherHandler {
    int move = 0;

    @Override
    public void handleStep() {
        move = (move + 1)%4;
        if ( move == 0 ) {
            WorldData worldData = WorldData.getWorldData();
            Set<Point> toClear = new HashSet<>();
            for ( Point p : worldData.getTileMap().keySet() ) {
                String color = worldData.getTileMap().get( p );
                if ( "b".equals( color ) ) toClear.add( p );
                else worldData.setTile( p.getX(), p.getY(), color.replaceFirst( "b", ""));
            }
            worldData.getTileMap().keySet().removeAll( toClear );
        }
    }
}
