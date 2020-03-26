import program.events.Event;
import program.events.RegularEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import program.events.UrgentEvent;
import program.file.FileIO;
import program.file.Loadable;
import program.file.Saveable;
import ui.todolist.ToDoList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class TestFileIO {
    Loadable fileLoad;
    Saveable fileSave;
    FileIO file;
    ToDoList todoList;
    Event regularEvent;
    HashMap<String,Event> eventsHashMap;

    @BeforeEach
    public void setUp() {
        fileLoad = new FileIO();
        fileSave = new FileIO();
        todoList = new ToDoList();
        file = new FileIO();
        eventsHashMap = new HashMap<>();
    }

    @Test
    public void testSplitOnDash() {
        String testStringTest = "Hi-my-name-is-edward";
        ArrayList<String> partsOfLine = file.splitOnDash(testStringTest);
        assertTrue(partsOfLine.get(0).equals("Hi"));
        assertTrue(partsOfLine.get(1).equals("my"));
        assertTrue(partsOfLine.get(2).equals("name"));
        assertTrue(partsOfLine.get(3).equals("is"));
        assertTrue(partsOfLine.get(4).equals("edward"));


    }
    @Test
    public void testSaving() throws IOException {
        regularEvent = new RegularEvent();
        Event urgent = new UrgentEvent();
        regularEvent.setName("Midterm");
        regularEvent.setLocation("UBC");
        regularEvent.setStatus("Undone");
        regularEvent.setYear(2019);
        regularEvent.setMonth(12);
        regularEvent.setDate(30);
        urgent.setName("meow");
        urgent.setLocation("SFU");
        urgent.setStatus("Urgent");
        urgent.setYear(2019);
        urgent.setMonth(12);
        urgent.setDate(3);
        eventsHashMap.put(regularEvent.getName(),regularEvent);
        eventsHashMap.put(urgent.getName(),urgent);
        fileSave.save("testSaving.txt", eventsHashMap);
        HashMap<String,Event> eventsHashMap2 = new HashMap<>();
        fileLoad.load("testSaving.txt",eventsHashMap2);
        Event eventmeow;
        eventmeow = eventsHashMap2.get("meow");
        assertEquals(eventmeow.getName(),"meow");
        assertEquals(eventmeow.getStatus(),"Urgent");
        assertEquals(eventmeow.getLocation(),"SFU");
        assertEquals(eventmeow.getMonth(),12);
        assertEquals(eventmeow.getYear(),2019);
        assertEquals(eventmeow.getDate(),3);
        Event eventMidterm;
        eventMidterm = eventsHashMap2.get("Midterm");
        assertEquals(eventMidterm.getName(),"Midterm");
        assertEquals(eventMidterm.getStatus(),"Undone");
        assertEquals(eventMidterm.getLocation(),"UBC");
        assertEquals(eventMidterm.getMonth(),12);
        assertEquals(eventMidterm.getYear(),2019);
        assertEquals(eventMidterm.getDate(),30);
    }

    @Test
    public void testLoading() throws IOException {
        regularEvent = new RegularEvent();
        fileLoad.load("testSaving.txt", eventsHashMap);
        regularEvent = eventsHashMap.get("Midterm");
        assertTrue(eventsHashMap.get(regularEvent.getName()).getName().equals("Midterm"));
        assertTrue(eventsHashMap.get(regularEvent.getName()).getStatus().equals("Undone"));
        assertTrue(eventsHashMap.get(regularEvent.getName()).getYear()==2019);
        assertTrue(eventsHashMap.get(regularEvent.getName()).getMonth()==12);
        assertTrue(eventsHashMap.get(regularEvent.getName()).getDate()==30);
        FileIO fileloading = new FileIO();
        fileloading.load("testSaving.txt", eventsHashMap);
    }

}
