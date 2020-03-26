package ui.todolist;

import program.events.Event;

import java.util.HashMap;
import java.util.Scanner;

public class EventModifier {
    private static Scanner scanner = new Scanner(System.in);

    //EFFECTS: delete the event
    //MODIFIES: events HashMap
    protected void eventDelete(HashMap<String, Event> eventsHashMap) {
        System.out.println("Please enter the name of the event that you want to delete");
        String answer = scanner.nextLine();
        if (eventsHashMap.containsKey(answer)) {
            eventsHashMap.remove(answer);
            System.out.println("Event " + answer + " deleted");
        } else {
            System.out.println("Event not found");
        }
    }

    //REQUIRES: At least 1 event added previously in the list
    //EFFECTS:  Set status of event object to be "done"
    //MODIFIES: The status of event object
    protected void eventDone(HashMap eventsHashMap) {
        System.out.println("Please enter the name of the event that is done");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        if (eventsHashMap.containsKey(answer)) {
            Event regularEvent = (Event) eventsHashMap.get(answer);
            regularEvent.setDone();
        } else {
            System.out.println("Item not found");
        }
    }
}
