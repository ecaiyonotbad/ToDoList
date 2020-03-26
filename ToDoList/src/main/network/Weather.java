
package network;

import java.io.*;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import org.json.JSONObject;


public class Weather extends Observable {
    private JsonReader jsonReader = new JsonReader();
    private HashMap<String, String> weather = new HashMap<>();

    public Weather(Observer o) {
        addObserver(o);
    }

    //MODIFIES: this
    //EFFECTS:  get the weather data from the specific city, and save into weather (hashmap)
    public void loadWeather(String city, String country) throws IOException {
        JSONObject json = jsonReader.readJsonFromUrl(
                "https://api.openweathermap.org/data/2.5/weather?q=" + city
                        + "," + country
                        + "ca&appid=d0ada7f59cc62624b0355747441c7af7");
        weather.put("city", json.get("name").toString());
        weather.put("country", json.getJSONObject("sys").get("country").toString());
        weather.put("temperature", convertToCelsius(json.getJSONObject("main").get("temp").toString()));
        weather.put("description", json.getJSONArray("weather").getJSONObject(0).get("description").toString());
        setChanged();
        notifyObservers();
    }

    private String convertToCelsius(String temp) {
        Float floatTemp = Float.parseFloat(temp);
        int intTemp = Math.round(floatTemp) - 273;
        return Integer.toString(intTemp);
    }

    //Getters
    public String getDescription() {
        return weather.get("description");
    }

    public String getTemperature() {
        return weather.get("temperature");
    }

    public String getCountry() {
        return weather.get("country");
    }

    public String getCity() {
        return weather.get("city");
    }

}
