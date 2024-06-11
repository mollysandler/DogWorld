public class HotWeatherHandler extends WeatherHandler {
    int move = 0;

    @Override
    public void handleStep() {
        move = (move + 1)%3;
        if (move == 0) {

        }
    }
}
