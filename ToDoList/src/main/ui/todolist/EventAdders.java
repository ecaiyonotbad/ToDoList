package ui.todolist;

import program.checkers.InputChecker;
import program.checkers.TooManyChecker;
import program.events.Event;
import program.events.RegularEvent;
import program.events.UrgentEvent;
import program.exceptions.*;

import java.util.HashMap;
import java.util.Scanner;

public class EventAdders {
    private static TooManyChecker tooManyChecker = new TooManyChecker();
    private static Printer printer = new Printer();
    private static InputChecker checker = new InputChecker();

    //EFFECTS: add an urgent event
    protected void addUrgentEvent(HashMap<String, Event> eventsHashMap) {
        Event urgentEvent = new UrgentEvent();
        System.out.println("Please enter the following information of the regular event:\n");
        System.out.println("Name of the event:");
        Scanner scanner = new Scanner(System.in);
        urgentEvent.setName(scanner.nextLine());
        System.out.println("Location of the event:");
        urgentEvent.setLocation(scanner.nextLine());
        urgentEvent.setFields();
        eventsHashMap.put(urgentEvent.getName(), urgentEvent);
    }

    //EFFECTS: add an regular event
    protected void adderAndChecker(HashMap<String, Event> eventsHashMap) {
        try {
            if (tooManyChecker.tooManyUndoneChecker(eventsHashMap)) {
                System.out.println("Too many events undone, set events to done!");
                throw new TooManyThingsUndoneException();
            } else if (tooManyChecker.tooManyDoneChecker(eventsHashMap)) {
                System.out.println("Too many events done, delete done events!");
                throw new TooManyThingsDoneException();
            }
        } catch (TooManyException e) {
            e.printStackTrace();
        } finally {
            eventAdder(eventsHashMap);
        }
    }

    private static void eventAdder(HashMap<String, Event> eventsHashMap) {
        Event event = new RegularEvent();
        printer.eventAdderPrint();
        Scanner scanner = new Scanner(System.in);
        event.setName(scanner.nextLine());
        System.out.println("What location is the event in?");
        event.setLocation(scanner.nextLine());
        System.out.println("Year of the event:");
        event.setYear(scanner.nextInt());
        System.out.println("Month of the event:");
        event.setMonth(scanner.nextInt());
        if (checker.monthCheck(event.getMonth())) {
            System.out.println("Date of the event:");
            event.setDate(scanner.nextInt());
            if (checker.dateCheck(event.getDate())) {
                eventAdderData(event, eventsHashMap);
            }
        }
    }

    private static void eventAdderData(Event event, HashMap<String, Event> eventsHashMap) {
        eventsHashMap.put(event.getName(), event);
        System.out.println("You have entered an event");
    }


}
