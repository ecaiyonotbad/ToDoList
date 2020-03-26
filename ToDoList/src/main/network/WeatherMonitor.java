package network;

import java.util.Observable;
import java.util.Observer;

public class WeatherMonitor implements Observer {
    String name;

    public WeatherMonitor(String name) {
        this.name = name;
    }

    //EFFECTS: update the weather for its observers
    @Override
    public void update(Observable o, Object arg) {
        Weather weather = (Weather) o;
        System.out.println(name + "'s " + weather.getCity() + " weather retrieved.");
        System.out.println("Weather description in " + weather.getCity() + ": " + weather.getDescription());
        System.out.println("Temperature: " + weather.getTemperature() + " degrees C");
        printAdvise(weather);
    }

    private void printAdvise(Weather weather) {
        System.out.println(weather.getCountry());
        if (weather.getDescription().contains("rain")) {
            System.out.println("Remember to bring umbrella");
        } else if (weather.getDescription().contains("snow")) {
            System.out.println("Remember to wear appropriate clothing (e.g. boots, coat)");
        } else if (weather.getDescription().contains("sunny")
                || weather.getDescription().contains("cloud")
                || weather.getDescription().contains("clear")) {
            System.out.println("Good weather condition");
        }
    }
}
