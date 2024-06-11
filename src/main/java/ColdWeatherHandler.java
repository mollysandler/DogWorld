import java.util.HashMap;

public class ColdWeatherHandler extends WeatherHandler {
    @Override
    public void handleStart() {
        WorldData worldData = WorldData.getWorldData();
        int rows = worldData.getNumRows();
        HashMap<Point, String> tileMap = new HashMap<>();
        for ( int i = 0; i < rows; i++ ) {
            for ( int j = 0; j < rows; j++ ) {
                tileMap.put( new Point( i, j ), "snow" );
            }
        }
        System.out.println( tileMap );
        worldData.setTileMap( tileMap );
        System.out.println( "added snow" );
    }
}
