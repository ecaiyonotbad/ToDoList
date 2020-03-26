package program.file;


import program.events.Event;

import java.io.IOException;
import java.util.HashMap;

public interface Saveable {
    void save(String filename, HashMap<String, Event> eventsHashMap) throws IOException;
}
