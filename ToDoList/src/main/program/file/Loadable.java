package program.file;

import program.events.Event;

import java.io.IOException;
import java.util.HashMap;

public interface Loadable {
    void load(String filename, HashMap<String, Event> eventsHashMap) throws IOException;
}