package ui.todolist;

import network.Weather;
import network.WeatherMonitor;
import program.events.Event;
import program.file.FileIO;
import program.file.Loadable;
import program.file.Saveable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class FileSaverLoader {
    private static Scanner scanner = new Scanner(System.in);
    private static Loadable fileLoad = new FileIO();
    private static Saveable fileSave = new FileIO();
    private WeatherMonitor weatherMonitor;


    //EFFECTS: save hashmap into a txt file
    protected void saving(HashMap<String, Event> eventsHashMap) throws IOException {
        System.out.println("Do you want to save the data?[y/n]");
        String input = scanner.nextLine();
        if ("y".equals(input)) {
            System.out.println("Please provide a output name for the file");
            String filename = scanner.nextLine();
            fileSave.save(filename, eventsHashMap);
        }
    }


    //EFFECTS: load txt file into a hashmap
    protected void loading(HashMap<String, Event> eventsHashMap) throws IOException {
        System.out.println("Welcome to the to-do list program!");
        getWeather();
        System.out.println("Do you want to load a previously stored data?[y/n]");
        String answer = scanner.nextLine();
        if ("y".equals(answer)) {
            System.out.println("Please provide the file name you wish to load");
            String filename = scanner.nextLine();
            fileLoad.load(filename, eventsHashMap);
        }
    }

    private void getWeather() {
        try {
            weatherMonitor = new WeatherMonitor("Edward");
            Weather weather = new Weather(weatherMonitor);
            weather.loadWeather("Vancouver", "ca");

        } catch (IOException e) {
            System.out.println("Error: Connect Wi-Fi to display current weather");
        }

    }

}
