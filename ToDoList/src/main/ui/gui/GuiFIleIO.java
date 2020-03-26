package ui.gui;

import program.events.Event;
import program.events.RegularEvent;
import program.file.FileIO;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class GuiFIleIO {
    FileIO fileIO = new FileIO();

    //EFFECTS: Load the txt file into arraylist
    public void load(String filename, ArrayList<Event> list) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        for (String line : lines) {
            ArrayList<String> partsOfLine = fileIO.splitOnDash(line);
            Event event = new RegularEvent();
            event.setName(partsOfLine.get(0));
            event.setLocation((partsOfLine.get(1)));
            event.setStatus(partsOfLine.get(2));
            event.setYear(Integer.parseInt(partsOfLine.get(3)));
            event.setMonth(Integer.parseInt(partsOfLine.get(4)));
            event.setDate(Integer.parseInt(partsOfLine.get(5)));
            list.add(event);
        }
    }

    //EFFECTS: Save the the arraylist as a txt file
    public void save(String filename, ArrayList<Event> list) throws IOException {
        String bigString;
        String name;
        String status;
        String year;
        String month;
        String date;
        PrintWriter writer = new PrintWriter(filename, "UTF-8");
        for (int i = 0; i < list.size(); i++) {
            Event event = list.get(i);
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

}
