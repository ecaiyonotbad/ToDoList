package ui.todolist;

import program.events.Event;

import java.util.HashMap;


public class Printer {
    //EFFECTS: Prints all the events in the list
    //EFFECTS: Print the welcome message
    protected void eventPrintHashMap(HashMap<String, Event> eventsHashMap) {
        for (Object key : eventsHashMap.keySet()) {
            Event event = eventsHashMap.get(key);
            eventPrintBigStringHelper(event);
        }
    }

    //EFFECTS: print welcome message
    protected void printWelcomeMessage() {
        System.out.println("Enter [1] add a regular event.");
        System.out.println("Enter [2] to add an urgent event.");
        System.out.println("Enter [3] to cross off an event.");
        System.out.println("Enter [4] to delete an event.");
        System.out.println("Enter [quit] to quit the program.\n");
    }

    private void eventPrintBigStringHelper(Event event) {
        String bigString = event.getName() + "-"
                + event.getLocation() + "-"
                + event.getStatus() + "-"
                + event.getYear() + "-"
                + event.getMonth() + "-"
                + event.getDate();
        System.out.println(bigString);
    }

    //EFFECT: Print good-bye message once the program is done
    protected void goodByeMessage() {
        System.out.println("Thank you for using the program, good bye!");
    }

    //EFFECT: print event adding
    protected void eventAdderPrint() {
        System.out.println("Please enter the following information of the event:\n");
        System.out.println("Name of the event:");
    }

    //EFFECT: print hashmap
    protected void printHelper(HashMap<String, Event> eventsHashMap) {
        printWelcomeMessage();
        eventPrintHashMap(eventsHashMap);
    }

}
