package ui.todolist;

import program.events.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;


public class ToDoList {
    public static HashMap<String, Event> eventsHashMap = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Printer printer = new Printer();
    private static EventModifier eventModifier = new EventModifier();
    private static EventAdders eventAdders = new EventAdders();
    private static FileSaverLoader fileSaverLoader = new FileSaverLoader();

    //EFFECT: Run the program
    public void run() throws IOException {
        userInput();
        printer.goodByeMessage();
    }

    //EFFECTS: Let user to select desired option for the program to run
    //Press [1], adds an event into the list
    //Press [2], Set the desired event status to done
    //Press [3], Print all the events
    //Type [quit], The program ends
    private static void userInput() throws IOException {
        fileSaverLoader.loading(eventsHashMap);
        while (true) {
            printer.printHelper(eventsHashMap);
            String answer = scanner.nextLine();
            if ("1".equals(answer)) {
                eventAdders.adderAndChecker(eventsHashMap);
            } else if ("2".equals(answer)) {
                eventAdders.addUrgentEvent(eventsHashMap);
            } else if ("3".equals(answer)) {
                eventModifier.eventDone(eventsHashMap);
            } else if ("4".equals(answer)) {
                eventModifier.eventDelete(eventsHashMap);
            } else if ("quit".equals(answer)) {
                fileSaverLoader.saving(eventsHashMap);
                break;
            }
        }
    }

}

