import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Aayush Joshi
 */
public class LevelGenerator {
    public static void makeLevels() throws Exception {
        level1();
        level2();
        level3();
        level4();
        level5();
        level6();
        level7();
        level8();
        level9();
        level10();
        level11();
        level12();
        level13();
        level14();
        level15();
        level16();
        level17();
        level18();
        level19();
        level20();
    }

    public static void level1() throws Exception {
        LoadLevels lvl = new LoadLevels(1);

        // Create points
        Point bluePoint1 = new Point(4, 1);
        Point redPoint1 = new Point(1, 1);
        Point greenPoint1 = new Point(1, 2);
        Point spiderLoc = new Point(1, 3);
        Point spiderDirection = new Point(1, 0);

        // Create ArrayLists and add points
        ArrayList<Point> bluePoints = new ArrayList<>();
        bluePoints.add(bluePoint1);
        ArrayList<Point> redPoints = new ArrayList<>();
        redPoints.add(redPoint1);
        ArrayList<Point> greenPoints = new ArrayList<>();
        greenPoints.add(greenPoint1);
        ArrayList<Point> spider = new ArrayList<>();
        spider.add(spiderLoc);
        spider.add(spiderDirection);

        // Create map and add ArrayLists
        HashMap<String, ArrayList<Point>> map = new HashMap<>();
        map.put("blue", bluePoints);
        map.put("red", redPoints);
        map.put("green", greenPoints);
        map.put("spider", spider);

        Point rowsXcols = new Point(5, 5);
        ArrayList<Point> dimensions = new ArrayList<>();
        dimensions.add(rowsXcols);
        map.put("dimensions", dimensions);

        Point logXlat = new Point(331999, 0);
        ArrayList<Point> logXlats = new ArrayList<>();
        logXlats.add(logXlat);
        map.put("location", logXlats);

        double wea = Weather.getTemperature("331999");
        Point weather = new Point(wea, 0);
        ArrayList<Point> weathersX = new ArrayList<>();
        weathersX.add(weather);
        map.put("weather", weathersX);

        lvl.saveHashMap(map);
    }

    public static void level2() throws Exception {
        LoadLevels lvl = new LoadLevels(2);

        // Create points
        Point bluePoint1 = new Point(2, 1);
        Point redPoint1 = new Point(1, 4);
        Point greenPoint1 = new Point(1, 1);
        Point spiderLoc = new Point(3, 3);
        Point spiderDirection = new Point(1, 0);

        // Create ArrayLists and add points
        ArrayList<Point> bluePoints = new ArrayList<>();
        bluePoints.add(bluePoint1);
        ArrayList<Point> redPoints = new ArrayList<>();
        redPoints.add(redPoint1);
        ArrayList<Point> greenPoints = new ArrayList<>();
        greenPoints.add(greenPoint1);
        ArrayList<Point> spider = new ArrayList<>();
        spider.add(spiderLoc);
        spider.add(spiderDirection);

        // Create map and add ArrayLists
        HashMap<String, ArrayList<Point>> map = new HashMap<>();
        map.put("blue", bluePoints);
        map.put("red", redPoints);
        map.put("green", greenPoints);
        map.put("spider", spider);

        Point rowsXcols = new Point(5, 5);
        ArrayList<Point> dimentions = new ArrayList<>();
        dimentions.add(rowsXcols);
        map.put("dimensions", dimentions);

        Point logXlat = new Point(3569851, 0);
        ArrayList<Point> logXlats = new ArrayList<>();
        logXlats.add(logXlat);
        map.put("location", logXlats);

        double wea = Weather.getTemperature("3569851");
        Point weather = new Point(wea, 0);
        ArrayList<Point> weathersX = new ArrayList<>();
        weathersX.add(weather);
        map.put("weather", weathersX);

        lvl.saveHashMap(map);
    }
    public static void level3() throws Exception {
        LoadLevels lvl = new LoadLevels(3);

        // Create points
        Point bluePoint1 = new Point(4, 1);
        Point bluePoint2 = new Point(2, 2);
        Point redPoint1 = new Point(1, 1);
        Point redPoint2 = new Point(3, 1);
        Point spiderLoc = new Point(2, 3);
        Point spiderDirection = new Point(1, 0);

        // Create ArrayLists and add points
        ArrayList<Point> bluePoints = new ArrayList<>();
        bluePoints.add(bluePoint1);
        bluePoints.add(bluePoint2);
        ArrayList<Point> redPoints = new ArrayList<>();
        redPoints.add(redPoint1);
        redPoints.add(redPoint2);
        ArrayList<Point> greenPoints = new ArrayList<>();
        ArrayList<Point> spider = new ArrayList<>();
        spider.add(spiderLoc);
        spider.add(spiderDirection);


        // Create map and add ArrayLists
        HashMap<String, ArrayList<Point>> map = new HashMap<>();
        map.put("blue", bluePoints);
        map.put("red", redPoints);
        map.put("green", greenPoints);
        map.put("spider", spider);

        Point rowsXcols = new Point(5, 5);
        ArrayList<Point> dimensions = new ArrayList<>();
        dimensions.add(rowsXcols);
        map.put("dimensions", dimensions);

        Point logXlat = new Point(1113517, 0);
        ArrayList<Point> logXlats = new ArrayList<>();
        logXlats.add(logXlat);
        map.put("location", logXlats);

        double wea = Weather.getTemperature("1113517");
        Point weather = new Point(wea, 0);
        ArrayList<Point> weathersX = new ArrayList<>();
        weathersX.add(weather);
        map.put("weather", weathersX);

        lvl.saveHashMap(map);
    }
    public static void level4() throws Exception {
        LoadLevels lvl = new LoadLevels(4);

        // Create points
        Point bluePoint1 = new Point(1, 1);
        Point bluePoint2 = new Point(3, 1);
        Point spiderLoc = new Point(2, 3);
        Point spiderDirection = new Point(3, 0);

        // Create ArrayLists and add points
        ArrayList<Point> bluePoints = new ArrayList<>();
        bluePoints.add(bluePoint1);
        bluePoints.add(bluePoint2);
        ArrayList<Point> redPoints = new ArrayList<>();
        ArrayList<Point> greenPoints = new ArrayList<>();
        ArrayList<Point> spider = new ArrayList<>();
        spider.add(spiderLoc);
        spider.add(spiderDirection);


        // Create map and add ArrayLists
        HashMap<String, ArrayList<Point>> map = new HashMap<>();
        map.put("blue", bluePoints);
        map.put("red", redPoints);
        map.put("green", greenPoints);
        map.put("spider", spider);

        Point rowsXcols = new Point(5, 5);
        ArrayList<Point> dimensions = new ArrayList<>();
        dimensions.add(rowsXcols);
        map.put("dimensions", dimensions);

        Point logXlat = new Point(110286, 0);
        ArrayList<Point> logXlats = new ArrayList<>();
        logXlats.add(logXlat);
        map.put("location", logXlats);

        double wea = Weather.getTemperature("110286");
        Point weather = new Point(wea, 0);
        ArrayList<Point> weathersX = new ArrayList<>();
        weathersX.add(weather);
        map.put("weather", weathersX);

        lvl.saveHashMap(map);
    }
    public static void level5() throws Exception {
        LoadLevels lvl = new LoadLevels(5);

        // Create points
        Point bluePoint1 = new Point(4, 3);
        Point greenPoint1 = new Point(4, 4);
        Point greenPoint2 = new Point(2, 4);
        Point spiderLoc = new Point(4, 2);
        Point spiderDirection = new Point(3, 0);

        // Create ArrayLists and add points
        ArrayList<Point> bluePoints = new ArrayList<>();
        bluePoints.add(bluePoint1);
        ArrayList<Point> redPoints = new ArrayList<>();
        ArrayList<Point> greenPoints = new ArrayList<>();
        greenPoints.add(greenPoint1);
        greenPoints.add(greenPoint2);
        ArrayList<Point> spider = new ArrayList<>();
        spider.add(spiderLoc);
        spider.add(spiderDirection);


        // Create map and add ArrayLists
        HashMap<String, ArrayList<Point>> map = new HashMap<>();
        map.put("blue", bluePoints);
        map.put("red", redPoints);
        map.put("green", greenPoints);
        map.put("spider", spider);

        Point rowsXcols = new Point(5, 5);
        ArrayList<Point> dimensions = new ArrayList<>();
        dimensions.add(rowsXcols);
        map.put("dimensions", dimensions);

        Point logXlat = new Point(9598, 0);
        ArrayList<Point> logXlats = new ArrayList<>();
        logXlats.add(logXlat);
        map.put("location", logXlats);

        double wea = Weather.getTemperature("9598");
        Point weather = new Point(wea, 0);
        ArrayList<Point> weathersX = new ArrayList<>();
        weathersX.add(weather);
        map.put("weather", weathersX);

        lvl.saveHashMap(map);
    }
    public static void level6() throws Exception {
        LoadLevels lvl = new LoadLevels(6);

        // Create points
        Point bluePoint1 = new Point(1, 2);
        Point bluePoint2 = new Point(2, 2);
        Point bluePoint3 = new Point(3, 2);
        Point redPoint1 = new Point(1, 0);
        Point redPoint2 = new Point(1, 1);
        Point greenPoint1 = new Point(3, 0);
        Point greenPoint2 = new Point(3, 1);
        Point spiderLoc = new Point(0, 0);
        Point spiderDirection = new Point(0, 0);

        // Create ArrayLists and add points
        ArrayList<Point> bluePoints = new ArrayList<>();
        bluePoints.add(bluePoint1);
        bluePoints.add(bluePoint2);
        bluePoints.add(bluePoint3);
        ArrayList<Point> redPoints = new ArrayList<>();
        redPoints.add(redPoint1);
        redPoints.add(redPoint2);
        ArrayList<Point> greenPoints = new ArrayList<>();
        greenPoints.add(greenPoint1);
        greenPoints.add(greenPoint2);
        ArrayList<Point> spider = new ArrayList<>();
        spider.add(spiderLoc);
        spider.add(spiderDirection);

        // Create map and add ArrayLists
        HashMap<String, ArrayList<Point>> map = new HashMap<>();
        map.put("blue", bluePoints);
        map.put("red", redPoints);
        map.put("green", greenPoints);
        map.put("spider", spider);

        Point rowsXcols = new Point(5, 5);
        ArrayList<Point> dimensions = new ArrayList<>();
        dimensions.add(rowsXcols);
        map.put("dimensions", dimensions);

        Point logXlat = new Point(227856, 0);
        ArrayList<Point> logXlats = new ArrayList<>();
        logXlats.add(logXlat);
        map.put("location", logXlats);

        double wea = Weather.getTemperature("227856");
        Point weather = new Point(wea, 0);
        ArrayList<Point> weathersX = new ArrayList<>();
        weathersX.add(weather);
        map.put("weather", weathersX);

        lvl.saveHashMap(map);
    }
    public static void level7() throws Exception {
        LoadLevels lvl = new LoadLevels(7);

        // Create points
        Point redPoint1 = new Point(1, 0);
        Point spiderLoc = new Point(0, 0);
        Point spiderDirection = new Point(0, 0);
        Point rowsXcols = new Point(3, 3);

        // Create ArrayLists and add points
        ArrayList<Point> bluePoints = new ArrayList<>();
        ArrayList<Point> redPoints = new ArrayList<>();
        redPoints.add(redPoint1);
        ArrayList<Point> greenPoints = new ArrayList<>();
        ArrayList<Point> spider = new ArrayList<>();
        spider.add(spiderLoc);
        spider.add(spiderDirection);
        ArrayList<Point> dimensions = new ArrayList<>();
        dimensions.add(rowsXcols);

        // Create map and add ArrayLists
        HashMap<String, ArrayList<Point>> map = new HashMap<>();
        map.put("blue", bluePoints);
        map.put("red", redPoints);
        map.put("green", greenPoints);
        map.put("spider", spider);
        map.put("dimensions", dimensions);

        Point logXlat = new Point(299904, 0);
        ArrayList<Point> logXlats = new ArrayList<>();
        logXlats.add(logXlat);
        map.put("location", logXlats);

        double wea = Weather.getTemperature("299904");
        Point weather = new Point(wea, 0);
        ArrayList<Point> weathersX = new ArrayList<>();
        weathersX.add(weather);
        map.put("weather", weathersX);

        lvl.saveHashMap(map);
    }
    public static void level8() throws Exception {
        LoadLevels lvl = new LoadLevels(8);

        // Create points
        Point bluePoint1 = new Point(1, 0);
        Point bluePoint2 = new Point(2, 0);
        Point bluePoint3 = new Point(3, 0);

        Point redPoint1 = new Point(3, 3);
        Point spiderLoc = new Point(0, 0);
        Point spiderDirection = new Point(0, 0);
        Point rowsXcols = new Point(4, 4);

        // Create ArrayLists and add points
        ArrayList<Point> bluePoints = new ArrayList<>();
        bluePoints.add(bluePoint1);
        bluePoints.add(bluePoint2);
        bluePoints.add(bluePoint3);
        ArrayList<Point> redPoints = new ArrayList<>();
        redPoints.add(redPoint1);
        ArrayList<Point> greenPoints = new ArrayList<>();
        ArrayList<Point> spider = new ArrayList<>();
        spider.add(spiderLoc);
        spider.add(spiderDirection);
        ArrayList<Point> dimensions = new ArrayList<>();
        dimensions.add(rowsXcols);

        // Create map and add ArrayLists
        HashMap<String, ArrayList<Point>> map = new HashMap<>();
        map.put("blue", bluePoints);
        map.put("red", redPoints);
        map.put("green", greenPoints);
        map.put("spider", spider);
        map.put("dimensions", dimensions);

        Point logXlat = new Point(232837, 0);
        ArrayList<Point> logXlats = new ArrayList<>();
        logXlats.add(logXlat);
        map.put("location", logXlats);

        double wea = Weather.getTemperature("232837");
        Point weather = new Point(wea, 0);
        ArrayList<Point> weathersX = new ArrayList<>();
        weathersX.add(weather);
        map.put("weather", weathersX);

        lvl.saveHashMap(map);
    }
    public static void level9() throws Exception {
        LoadLevels lvl = new LoadLevels(9);

        // Create points
        Point bluePoint1 = new Point(1, 5);
        Point bluePoint2 = new Point(2, 4);
        Point bluePoint3 = new Point(3, 3);
        Point bluePoint4 = new Point(4, 2);
        Point bluePoint5 = new Point(5, 1);

        Point redPoint1 = new Point(6, 0);
        Point redPoint2 = new Point(0, 6);

        Point spiderLoc = new Point(0, 0);
        Point spiderDirection = new Point(3, 0);
        Point rowsXcols = new Point(7, 7);

        // Create ArrayLists and add points
        ArrayList<Point> bluePoints = new ArrayList<>();
        bluePoints.add(bluePoint1);
        bluePoints.add(bluePoint2);
        bluePoints.add(bluePoint3);
        bluePoints.add(bluePoint4);
        bluePoints.add(bluePoint5);

        ArrayList<Point> redPoints = new ArrayList<>();
        redPoints.add(redPoint1);
        redPoints.add(redPoint2);

        ArrayList<Point> greenPoints = new ArrayList<>();
        ArrayList<Point> spider = new ArrayList<>();
        spider.add(spiderLoc);
        spider.add(spiderDirection);
        ArrayList<Point> dimensions = new ArrayList<>();
        dimensions.add(rowsXcols);

        // Create map and add ArrayLists
        HashMap<String, ArrayList<Point>> map = new HashMap<>();
        map.put("blue", bluePoints);
        map.put("red", redPoints);
        map.put("green", greenPoints);
        map.put("spider", spider);
        map.put("dimensions", dimensions);

        Point logXlat = new Point(303868, 0);
        ArrayList<Point> logXlats = new ArrayList<>();
        logXlats.add(logXlat);
        map.put("location", logXlats);

        double wea = Weather.getTemperature("303868");
        Point weather = new Point(wea, 0);
        ArrayList<Point> weathersX = new ArrayList<>();
        weathersX.add(weather);
        map.put("weather", weathersX);

        lvl.saveHashMap(map);
    }
    public static void level10() throws Exception {
        LoadLevels lvl = new LoadLevels(10);

        // Create points
        Point redPoint1 = new Point(1, 0);
        Point redPoint2 = new Point(1, 1);
        Point redPoint3 = new Point(0, 1);

        Point spiderLoc = new Point(0, 0);
        Point spiderDirection = new Point(0, 0);
        Point rowsXcols = new Point(3, 3);

        // Create ArrayLists and add points
        ArrayList<Point> bluePoints = new ArrayList<>();
        ArrayList<Point> redPoints = new ArrayList<>();
        redPoints.add(redPoint1);
        redPoints.add(redPoint2);
        redPoints.add(redPoint3);

        ArrayList<Point> greenPoints = new ArrayList<>();
        ArrayList<Point> spider = new ArrayList<>();
        spider.add(spiderLoc);
        spider.add(spiderDirection);
        ArrayList<Point> dimensions = new ArrayList<>();
        dimensions.add(rowsXcols);

        // Create map and add ArrayLists
        HashMap<String, ArrayList<Point>> map = new HashMap<>();
        map.put("blue", bluePoints);
        map.put("red", redPoints);
        map.put("green", greenPoints);
        map.put("spider", spider);
        map.put("dimensions", dimensions);

        Point logXlat = new Point(866679, 0);
        ArrayList<Point> logXlats = new ArrayList<>();
        logXlats.add(logXlat);
        map.put("location", logXlats);

        double wea = Weather.getTemperature("866679");
        Point weather = new Point(wea, 0);
        ArrayList<Point> weathersX = new ArrayList<>();
        weathersX.add(weather);
        map.put("weather", weathersX);

        lvl.saveHashMap(map);
    }
    public static void level11() throws Exception {
        LoadLevels lvl = new LoadLevels(11);

        // Create points
        Point redPoint1 = new Point(1, 0);
        Point redPoint2 = new Point(2, 0);
        Point redPoint3 = new Point(2, 1);
        Point redPoint4 = new Point(2, 2);
        Point redPoint5 = new Point(1, 2);
        Point redPoint6 = new Point(0, 2);
        Point redPoint7 = new Point(0, 1);

        Point spiderLoc = new Point(0, 0);
        Point spiderDirection = new Point(0, 0);
        Point rowsXcols = new Point(3, 3);

        // Create ArrayLists and add points
        ArrayList<Point> bluePoints = new ArrayList<>();
        ArrayList<Point> redPoints = new ArrayList<>();
        redPoints.add(redPoint1);
        redPoints.add(redPoint2);
        redPoints.add(redPoint3);
        redPoints.add(redPoint4);
        redPoints.add(redPoint5);
        redPoints.add(redPoint6);
        redPoints.add(redPoint7);

        ArrayList<Point> greenPoints = new ArrayList<>();
        ArrayList<Point> spider = new ArrayList<>();
        spider.add(spiderLoc);
        spider.add(spiderDirection);
        ArrayList<Point> dimensions = new ArrayList<>();
        dimensions.add(rowsXcols);

        // Create map and add ArrayLists
        HashMap<String, ArrayList<Point>> map = new HashMap<>();
        map.put("blue", bluePoints);
        map.put("red", redPoints);
        map.put("green", greenPoints);
        map.put("spider", spider);
        map.put("dimensions", dimensions);

        Point logXlat = new Point(163530, 0);
        ArrayList<Point> logXlats = new ArrayList<>();
        logXlats.add(logXlat);
        map.put("location", logXlats);

        double wea = Weather.getTemperature("163530");
        Point weather = new Point(wea, 0);
        ArrayList<Point> weathersX = new ArrayList<>();
        weathersX.add(weather);
        map.put("weather", weathersX);

        lvl.saveHashMap(map);
    }
    public static void level12() throws Exception {
        LoadLevels lvl = new LoadLevels(12);

        // Create points
        Point redPoint1 = new Point(3, 1);
        Point bluePoint1 = new Point(3, 3);
        Point greenPoint1 = new Point(1, 3);

        Point spiderLoc = new Point(4, 0);
        Point spiderDirection = new Point(2, 0);
        Point rowsXcols = new Point(5, 5);

        // Create ArrayLists and add points
        ArrayList<Point> bluePoints = new ArrayList<>();
        bluePoints.add(bluePoint1);

        ArrayList<Point> redPoints = new ArrayList<>();
        redPoints.add(redPoint1);

        ArrayList<Point> greenPoints = new ArrayList<>();
        greenPoints.add(greenPoint1);

        ArrayList<Point> spider = new ArrayList<>();
        spider.add(spiderLoc);
        spider.add(spiderDirection);
        ArrayList<Point> dimensions = new ArrayList<>();
        dimensions.add(rowsXcols);

        // Create map and add ArrayLists
        HashMap<String, ArrayList<Point>> map = new HashMap<>();
        map.put("blue", bluePoints);
        map.put("red", redPoints);
        map.put("green", greenPoints);
        map.put("spider", spider);
        map.put("dimensions", dimensions);

        Point logXlat = new Point(1551040, 0);
        ArrayList<Point> logXlats = new ArrayList<>();
        logXlats.add(logXlat);
        map.put("location", logXlats);

        double wea = Weather.getTemperature("1551040");
        Point weather = new Point(wea, 0);
        ArrayList<Point> weathersX = new ArrayList<>();
        weathersX.add(weather);
        map.put("weather", weathersX);

        lvl.saveHashMap(map);
    }
    public static void level13() throws Exception {
        LoadLevels lvl = new LoadLevels(13);

        // Create points
        Point redPoint1 = new Point(0, 1);
        Point redPoint2 = new Point(0, 2);
        Point redPoint3 = new Point(0, 3);
        Point redPoint4 = new Point(0, 4);
        Point redPoint5 = new Point(0, 5);
        Point redPoint6 = new Point(0, 6);
        Point redPoint7 = new Point(1, 6);
        Point redPoint8 = new Point(2, 6);
        Point redPoint9 = new Point(3, 6);
        Point redPoint10 = new Point(4, 6);
        Point redPoint11 = new Point(5, 6);
        Point redPoint12 = new Point(6, 6);

        Point spiderLoc = new Point(0, 0);
        Point spiderDirection = new Point(0, 0);
        Point rowsXcols = new Point(7, 7);

        // Create ArrayLists and add points
        ArrayList<Point> bluePoints = new ArrayList<>();

        ArrayList<Point> redPoints = new ArrayList<>();
        redPoints.add(redPoint1);
        redPoints.add(redPoint2);
        redPoints.add(redPoint3);
        redPoints.add(redPoint4);
        redPoints.add(redPoint5);
        redPoints.add(redPoint6);
        redPoints.add(redPoint7);
        redPoints.add(redPoint8);
        redPoints.add(redPoint9);
        redPoints.add(redPoint10);
        redPoints.add(redPoint11);
        redPoints.add(redPoint12);

        ArrayList<Point> greenPoints = new ArrayList<>();

        ArrayList<Point> spider = new ArrayList<>();
        spider.add(spiderLoc);
        spider.add(spiderDirection);
        ArrayList<Point> dimensions = new ArrayList<>();
        dimensions.add(rowsXcols);

        // Create map and add ArrayLists
        HashMap<String, ArrayList<Point>> map = new HashMap<>();
        map.put("blue", bluePoints);
        map.put("red", redPoints);
        map.put("green", greenPoints);
        map.put("spider", spider);
        map.put("dimensions", dimensions);

        Point logXlat = new Point(1390076, 0);
        ArrayList<Point> logXlats = new ArrayList<>();
        logXlats.add(logXlat);
        map.put("location", logXlats);

        double wea = Weather.getTemperature("1390076");
        Point weather = new Point(wea, 0);
        ArrayList<Point> weathersX = new ArrayList<>();
        weathersX.add(weather);
        map.put("weather", weathersX);

        lvl.saveHashMap(map);
    }
    public static void level14() throws Exception {
        LoadLevels lvl = new LoadLevels(14);

        // Create points
        Point redPoint1 = new Point(1, 0);
        Point redPoint2 = new Point(2, 0);
        Point redPoint3 = new Point(3, 0);
        Point redPoint4 = new Point(2, 1);
        Point redPoint5 = new Point(1, 2);
        Point redPoint6 = new Point(0, 3);
        Point redPoint7 = new Point(1, 3);
        Point redPoint8 = new Point(2, 3);
        Point redPoint9 = new Point(3, 3);

        Point spiderLoc = new Point(0, 0);
        Point spiderDirection = new Point(0, 0);
        Point rowsXcols = new Point(4, 4);

        // Create ArrayLists and add points
        ArrayList<Point> bluePoints = new ArrayList<>();
        ArrayList<Point> redPoints = new ArrayList<>();
        redPoints.add(redPoint1);
        redPoints.add(redPoint2);
        redPoints.add(redPoint3);
        redPoints.add(redPoint4);
        redPoints.add(redPoint5);
        redPoints.add(redPoint6);
        redPoints.add(redPoint7);
        redPoints.add(redPoint8);
        redPoints.add(redPoint9);

        ArrayList<Point> greenPoints = new ArrayList<>();
        ArrayList<Point> spider = new ArrayList<>();
        spider.add(spiderLoc);
        spider.add(spiderDirection);
        ArrayList<Point> dimensions = new ArrayList<>();
        dimensions.add(rowsXcols);

        // Create map and add ArrayLists
        HashMap<String, ArrayList<Point>> map = new HashMap<>();
        map.put("blue", bluePoints);
        map.put("red", redPoints);
        map.put("green", greenPoints);
        map.put("spider", spider);
        map.put("dimensions", dimensions);

        Point logXlat = new Point(714090, 0);
        ArrayList<Point> logXlats = new ArrayList<>();
        logXlats.add(logXlat);
        map.put("location", logXlats);

        double wea = Weather.getTemperature("714090");
        Point weather = new Point(wea, 0);
        ArrayList<Point> weathersX = new ArrayList<>();
        weathersX.add(weather);
        map.put("weather", weathersX);

        lvl.saveHashMap(map);
    }
    public static void level15() throws Exception {
        LoadLevels lvl = new LoadLevels(15);

        // Create points
        Point redPoint1 = new Point(0, 3);
        Point redPoint2 = new Point(1, 2);
        Point redPoint3 = new Point(2, 1);
        Point redPoint4 = new Point(3, 0);

        Point bluePoint1 = new Point(0, 4);
        Point bluePoint2 = new Point(1, 3);
        Point bluePoint3 = new Point(2, 2);
        Point bluePoint4 = new Point(3, 1);
        Point bluePoint5 = new Point(4, 0);


        Point spiderLoc = new Point(0, 0);
        Point spiderDirection = new Point(0, 0);
        Point rowsXcols = new Point(5, 5);

        // Create ArrayLists and add points
        ArrayList<Point> bluePoints = new ArrayList<>();
        bluePoints.add(bluePoint1);
        bluePoints.add(bluePoint2);
        bluePoints.add(bluePoint3);
        bluePoints.add(bluePoint4);
        bluePoints.add(bluePoint5);

        ArrayList<Point> redPoints = new ArrayList<>();
        redPoints.add(redPoint1);
        redPoints.add(redPoint2);
        redPoints.add(redPoint3);
        redPoints.add(redPoint4);

        ArrayList<Point> greenPoints = new ArrayList<>();

        ArrayList<Point> spider = new ArrayList<>();
        spider.add(spiderLoc);
        spider.add(spiderDirection);
        ArrayList<Point> dimensions = new ArrayList<>();
        dimensions.add(rowsXcols);

        // Create map and add ArrayLists
        HashMap<String, ArrayList<Point>> map = new HashMap<>();
        map.put("blue", bluePoints);
        map.put("red", redPoints);
        map.put("green", greenPoints);
        map.put("spider", spider);
        map.put("dimensions", dimensions);

        Point logXlat = new Point(2653518, 0);
        ArrayList<Point> logXlats = new ArrayList<>();
        logXlats.add(logXlat);
        map.put("location", logXlats);

        double wea = Weather.getTemperature("2653518");
        Point weather = new Point(wea, 0);
        ArrayList<Point> weathersX = new ArrayList<>();
        weathersX.add(weather);
        map.put("weather", weathersX);

        lvl.saveHashMap(map);
    }
    public static void level16() throws Exception {
        LoadLevels lvl = new LoadLevels(16);

        // Create points
        Point redPoint1 = new Point(3, 1);
        Point bluePoint1 = new Point(1, 3);
        Point greenPoint1 = new Point(4, 2);

        Point spiderLoc = new Point(2, 2);
        Point spiderDirection = new Point(3, 0);
        Point rowsXcols = new Point(5, 5);

        // Create ArrayLists and add points
        ArrayList<Point> bluePoints = new ArrayList<>();
        bluePoints.add(bluePoint1);

        ArrayList<Point> redPoints = new ArrayList<>();
        redPoints.add(redPoint1);

        ArrayList<Point> greenPoints = new ArrayList<>();
        greenPoints.add(greenPoint1);

        ArrayList<Point> spider = new ArrayList<>();
        spider.add(spiderLoc);
        spider.add(spiderDirection);
        ArrayList<Point> dimensions = new ArrayList<>();
        dimensions.add(rowsXcols);

        // Create map and add ArrayLists
        HashMap<String, ArrayList<Point>> map = new HashMap<>();
        map.put("blue", bluePoints);
        map.put("red", redPoints);
        map.put("green", greenPoints);
        map.put("spider", spider);
        map.put("dimensions", dimensions);

        Point logXlat = new Point(696611, 0);
        ArrayList<Point> logXlats = new ArrayList<>();
        logXlats.add(logXlat);
        map.put("location", logXlats);

        double wea = Weather.getTemperature("696611");
        Point weather = new Point(wea, 0);
        ArrayList<Point> weathersX = new ArrayList<>();
        weathersX.add(weather);
        map.put("weather", weathersX);

        lvl.saveHashMap(map);
    }
    public static void level17() throws Exception {
        LoadLevels lvl = new LoadLevels(17);

        // Create points
        Point redPoint1 = new Point(1, 2);
        Point bluePoint1 = new Point(0, 1);
        Point redPoint2 = new Point(2, 0);

        Point spiderLoc = new Point(2, 2);
        Point spiderDirection = new Point(0, 0);
        Point rowsXcols = new Point(3, 3);

        // Create ArrayLists and add points
        ArrayList<Point> bluePoints = new ArrayList<>();
        bluePoints.add(bluePoint1);

        ArrayList<Point> redPoints = new ArrayList<>();
        redPoints.add(redPoint1);
        redPoints.add(redPoint2);

        ArrayList<Point> greenPoints = new ArrayList<>();

        ArrayList<Point> spider = new ArrayList<>();
        spider.add(spiderLoc);
        spider.add(spiderDirection);
        ArrayList<Point> dimensions = new ArrayList<>();
        dimensions.add(rowsXcols);

        // Create map and add ArrayLists
        HashMap<String, ArrayList<Point>> map = new HashMap<>();
        map.put("blue", bluePoints);
        map.put("red", redPoints);
        map.put("green", greenPoints);
        map.put("spider", spider);
        map.put("dimensions", dimensions);

        Point logXlat = new Point(58079, 0);
        ArrayList<Point> logXlats = new ArrayList<>();
        logXlats.add(logXlat);
        map.put("location", logXlats);

        double wea = Weather.getTemperature("58079");
        Point weather = new Point(wea, 0);
        ArrayList<Point> weathersX = new ArrayList<>();
        weathersX.add(weather);
        map.put("weather", weathersX);

        lvl.saveHashMap(map);
    }
    public static void level18() throws Exception {
        LoadLevels lvl = new LoadLevels(18);

        // Create points
        Point redPoint1 = new Point(1, 0);
        Point greenPoint1 = new Point(1, 1);

        Point spiderLoc = new Point(0, 0);
        Point spiderDirection = new Point(2, 0);
        Point rowsXcols = new Point(2, 2);

        // Create ArrayLists and add points
        ArrayList<Point> bluePoints = new ArrayList<>();

        ArrayList<Point> redPoints = new ArrayList<>();
        redPoints.add(redPoint1);

        ArrayList<Point> greenPoints = new ArrayList<>();
        greenPoints.add(greenPoint1);

        ArrayList<Point> spider = new ArrayList<>();
        spider.add(spiderLoc);
        spider.add(spiderDirection);
        ArrayList<Point> dimensions = new ArrayList<>();
        dimensions.add(rowsXcols);

        // Create map and add ArrayLists
        HashMap<String, ArrayList<Point>> map = new HashMap<>();
        map.put("blue", bluePoints);
        map.put("red", redPoints);
        map.put("green", greenPoints);
        map.put("spider", spider);
        map.put("dimensions", dimensions);

        Point logXlat = new Point(1876241, 0);
        ArrayList<Point> logXlats = new ArrayList<>();
        logXlats.add(logXlat);
        map.put("location", logXlats);

        double wea = Weather.getTemperature("1876241");
        Point weather = new Point(wea, 0);
        ArrayList<Point> weathersX = new ArrayList<>();
        weathersX.add(weather);
        map.put("weather", weathersX);

        lvl.saveHashMap(map);
    }
    public static void level19() throws Exception {
        LoadLevels lvl = new LoadLevels(19);

        // Create points
        Point redPoint1 = new Point(1, 0);
        Point greenPoint1 = new Point(1, 1);
        Point greenPoint2 = new Point(1, 2);

        Point spiderLoc = new Point(0, 0);
        Point spiderDirection = new Point(2, 0);
        Point rowsXcols = new Point(3, 3);

        // Create ArrayLists and add points
        ArrayList<Point> bluePoints = new ArrayList<>();

        ArrayList<Point> redPoints = new ArrayList<>();
        redPoints.add(redPoint1);

        ArrayList<Point> greenPoints = new ArrayList<>();
        greenPoints.add(greenPoint1);
        greenPoints.add(greenPoint2);

        ArrayList<Point> spider = new ArrayList<>();
        spider.add(spiderLoc);
        spider.add(spiderDirection);
        ArrayList<Point> dimensions = new ArrayList<>();
        dimensions.add(rowsXcols);

        // Create map and add ArrayLists
        HashMap<String, ArrayList<Point>> map = new HashMap<>();
        map.put("blue", bluePoints);
        map.put("red", redPoints);
        map.put("green", greenPoints);
        map.put("spider", spider);
        map.put("dimensions", dimensions);

        Point logXlat = new Point(3494974, 0);
        ArrayList<Point> logXlats = new ArrayList<>();
        logXlats.add(logXlat);
        map.put("location", logXlats);

        double wea = Weather.getTemperature("3494974");
        Point weather = new Point(wea, 0);
        ArrayList<Point> weathersX = new ArrayList<>();
        weathersX.add(weather);
        map.put("weather", weathersX);

        lvl.saveHashMap(map);
    }
    public static void level20() throws Exception {
        LoadLevels lvl = new LoadLevels(20);

        // Create points
        Point redPoint1 = new Point(1, 0);
        Point greenPoint1 = new Point(1, 1);
        Point greenPoint2 = new Point(2, 2);

        Point spiderLoc = new Point(0, 0);
        Point spiderDirection = new Point(3, 0);
        Point rowsXcols = new Point(3, 3);

        // Create ArrayLists and add points
        ArrayList<Point> bluePoints = new ArrayList<>();

        ArrayList<Point> redPoints = new ArrayList<>();
        redPoints.add(redPoint1);

        ArrayList<Point> greenPoints = new ArrayList<>();
        greenPoints.add(greenPoint1);
        greenPoints.add(greenPoint2);

        ArrayList<Point> spider = new ArrayList<>();
        spider.add(spiderLoc);
        spider.add(spiderDirection);
        ArrayList<Point> dimensions = new ArrayList<>();
        dimensions.add(rowsXcols);

        // Create map and add ArrayLists
        HashMap<String, ArrayList<Point>> map = new HashMap<>();
        map.put("blue", bluePoints);
        map.put("red", redPoints);
        map.put("green", greenPoints);
        map.put("spider", spider);
        map.put("dimensions", dimensions);

        Point logXlat = new Point(1507716, 0);
        ArrayList<Point> logXlats = new ArrayList<>();
        logXlats.add(logXlat);
        map.put("location", logXlats);

        double wea = Weather.getTemperature("1507716");
        Point weather = new Point(wea, 0);
        ArrayList<Point> weathersX = new ArrayList<>();
        weathersX.add(weather);
        map.put("weather", weathersX);

        lvl.saveHashMap(map);
    }
}
