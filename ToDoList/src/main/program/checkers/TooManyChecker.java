package program.checkers;

import program.events.Event;

import java.util.HashMap;

public class TooManyChecker {

    //EFFECTS: returns true if # of undone events in the hashmap >= 7
    public boolean tooManyUndoneChecker(HashMap<String, Event> eventsHashMap) {
        if (tooManyChecker(eventsHashMap, "Undone")) {
            return true;
        }
        return false;
    }

    //EFFECTS: returns true if # of done events in the hashmap >= 7
    public boolean tooManyDoneChecker(HashMap<String, Event> eventsHashMap) {
        if (tooManyChecker(eventsHashMap, "Done")) {
            return true;
        }
        return false;
    }

    private boolean tooManyChecker(HashMap<String, Event> eventsHashMap, String status) {
        int accumulator = 0;
        for (Object key : eventsHashMap.keySet()) {
            Event event = eventsHashMap.get(key);
            if (event.getStatus().equals(status)) {
                accumulator++;
            }
        }
        if (accumulator >= 7) {
            return true;
        } else {
            return false;
        }
    }
}
