package program.file;


import program.events.Event;
import program.events.RegularEvent;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class FileIO implements Loadable, Saveable {

    //MODIFIES: eventsHashMap
    //EFFECTS:  Take the previously saved txt file, load into eventsHashMap
    public void load(String filename, HashMap<String, Event> eventsHashMap) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnDash(line);
            Event event = new RegularEvent();
            event.setName(partsOfLine.get(0));
            event.setLocation((partsOfLine.get(1)));
            event.setStatus(partsOfLine.get(2));
            event.setYear(Integer.parseInt(partsOfLine.get(3)));
            event.setMonth(Integer.parseInt(partsOfLine.get(4)));
            event.setDate(Integer.parseInt(partsOfLine.get(5)));
            eventsHashMap.put(event.getName(), event);
        }
    }

    //MODIFIES: file.txt
    //EFFECTS:  Take eventsHashMap, save it as a txt file
    public void save(String filename, HashMap<String, Event> eventsHashMap) throws IOException {
        String bigString;
        String name;
        String status;
        String year;
        String month;
        String date;
        PrintWriter writer = new PrintWriter(filename, "UTF-8");
        for (Object key : eventsHashMap.keySet()) {
            Event event = eventsHashMap.get(key);
            name = event.getName() + "-";
            status = event.getStatus() + "-";
            year = (event.getYear()) + "-";
            month = (event.getMonth()) + "-";
            date = Integer.toString(event.getDate());
            bigString = (name + event.getLocation() + "-" + status + year + month + date);
            writer.println(bigString);
        }
        writer.close();
    }

    //EFFECTS: split a string with regex "-"
    public static ArrayList<String> splitOnDash(String line) {
        String[] splits = line.split("-");
        return new ArrayList<>(Arrays.asList(splits));
    }






}
