import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ScoreChecker {
    public static int[] scoreSolution( int loc ) {
        int[] scores = new int[]{0,0};
        int colorCount = 0;
        HashMap<String, ArrayList<Point>> levelMap = WorldData.getWorldData().getLevelMap();
        int[] bgColor = WorldData.getWorldData().getBgColor();
        for ( String colorStr : levelMap.keySet() ) {
            if ( colorStr.charAt(0) != 'r' && colorStr.charAt(0) != 'g' && colorStr.charAt(0) != 'b') {
                continue;
            }
            int[] goalColor = new int[]{0, 0, 0};
            switch (colorStr) {
                case "red":
                    goalColor[0] = 255;
                    goalColor[1] = 89;
                    goalColor[2] = 94;
                    break;
                case "green":
                    goalColor[0] = 138;
                    goalColor[1] = 201;
                    goalColor[2] = 38;
                    break;
                case "blue":
                    goalColor[0] = 63;
                    goalColor[1] = 166;
                    goalColor[2] = 231;
                    break;
                default:
                    goalColor = PaintMixer.getPaintColor(colorStr);
            }
            for ( Point p : levelMap.get(colorStr)) {
                if ( !WorldData.getWorldData().getTileMap().containsKey(p) ) {
                    System.out.println( colorStr + " Point (" + p.getX() + ", " + p.getY() + "): -> " + scores[0]);
                    return new int[]{0,0};
                }
                int[] paintColor = PaintMixer.getPaintColor( p );
                scores[0] += scoreColor(goalColor, paintColor, bgColor);
                colorCount += 1;
                System.out.println( colorStr + " Point (" + p.getX() + ", " + p.getY() + "): " +
                        Arrays.toString(paintColor) + " -> " + scores[0]);
            }
        }
        scores[0] = scores[0] / colorCount;
        scores[1] = 25 + (int) (Math.round(100/(4.0/3 + Math.exp((double) loc /4 - 6))));
        return scores;
    }

    public static void logScore( int loc ) {
        int[] score = scoreSolution(loc);
        System.out.println( "Paint Accuracy Score: " + score[0] );
        System.out.println( "Coding Brevity Score: " + score[1] );
    }

    public static int scoreColor( int[] g, int[] p, int[] b ) {
        int score = 25;
        if (g[0] == b[0]) score += g[0] == p[0] ? 25 : 0;
        else score += Math.max(25-(25*Math.abs((g[0]-p[0])/(g[0]-b[0]))), 0);
        if (g[1] == b[1]) score += g[1] == p[1] ? 25 : 0;
        else score += Math.max(25-(25*Math.abs((g[1]-p[1])/(g[1]-b[1]))), 0);
        if (g[2] == b[2]) score += g[2] == p[2] ? 25 : 0;
        else score += Math.max(25-(25*Math.abs((g[2]-p[2])/(g[2]-b[2]))), 0);
        return score;
    }
}
