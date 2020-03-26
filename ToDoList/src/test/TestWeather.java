import network.WeatherMonitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import network.Weather;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestWeather {
    Weather weather;
    WeatherMonitor weatherMonitor;

    @BeforeEach
    public void setUp() {
        weatherMonitor = new WeatherMonitor("Edward");
        weather = new Weather(weatherMonitor);

    }

    @Test
    public void testVancouver() {
        try {
            weather.loadWeather("Vancouver", "ca");
            System.out.println(weather.getCountry());
            System.out.println(weather.getCity());
            System.out.println("Temperature is " + weather.getTemperature()+" degrees celsius");
            System.out.println("Weather description " + weather.getDescription());
            assertEquals(weather.getCity(),"Vancouver");
            assertEquals(weather.getCountry(),"CA");
            assertNotNull(weather.getDescription());

        } catch (IOException e) {
            System.out.println("No wifi");
        }
    }

    @Test
    public void testTaipei() {
        try {
            weather.loadWeather("Taipei","tw");
            System.out.println(weather.getCountry());
            System.out.println(weather.getCity());
            System.out.println("Temperature is " + weather.getTemperature()+" degrees celcius");
            System.out.println("Weather description " + weather.getDescription());
            assertEquals(weather.getCity(),"Taipei");
            assertEquals(weather.getCountry(),"TW");
            assertNotNull(weather.getDescription());

        } catch (IOException e) {
            System.out.println("No Wifi");
        }

    }


}
