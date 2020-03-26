import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import program.events.Event;
import program.events.RegularEvent;
import program.exceptions.TooManyException;
import program.exceptions.TooManyThingsDoneException;
import program.exceptions.TooManyThingsUndoneException;
import program.file.FileIO;
import program.checkers.TooManyChecker;
import program.file.Loadable;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class TestExceptions {
    TooManyChecker tooManyChecker;
    Loadable fileLoad;
    HashMap<String, Event> eventsHashMap;

    @BeforeEach
    public void setUp() {
        tooManyChecker = new TooManyChecker();
        fileLoad = new FileIO();
        eventsHashMap = new HashMap<>();

    }

    @Test
    public void testTooManyUndoneNoException() {
        try {
            for (int i = 0; i < 5; i++) {
                Event regularevent = new RegularEvent();
                regularevent.setName("Math" + i);
                regularevent.setStatus("Undone");
                regularevent.setYear(2019);
                regularevent.setMonth(10);
                regularevent.setMonth(10);
                eventsHashMap.put(regularevent.getName(), regularevent);
            }
            if (tooManyChecker.tooManyUndoneChecker(eventsHashMap)) {
                throw new TooManyThingsUndoneException();
            }
        } catch (TooManyException e) {
            fail("Wrong exception");
        }
    }

    @Test
    public void testTooManyUndoneExceptionThrown() {
        try {
            for (int i = 0; i < 10; i++) {
                Event regularevent = new RegularEvent();
                regularevent.setName("Math" + i);
                regularevent.setStatus("Undone");
                regularevent.setYear(2019);
                regularevent.setMonth(10);
                regularevent.setMonth(10);
                eventsHashMap.put(regularevent.getName(), regularevent);
            }
            if (tooManyChecker.tooManyUndoneChecker(eventsHashMap)) {
                throw new TooManyThingsUndoneException();
            }
            fail("Didn't catch exception");
        } catch (TooManyThingsUndoneException e) {
            System.out.println("Right exception caught");
        }
    }

    @Test
    public void testTooManyDoneNoException() {
        try {
            for (int i = 0; i < 5; i++) {
                Event regularevent = new RegularEvent();
                regularevent.setName("Math"+i);
                regularevent.setStatus("Done");
                regularevent.setYear(2019);
                regularevent.setMonth(10);
                regularevent.setMonth(10);
                eventsHashMap.put(regularevent.getName(), regularevent);
            }
            if (tooManyChecker.tooManyDoneChecker(eventsHashMap)) {
                throw new TooManyThingsDoneException();
            }
            System.out.println("Test 3 done");
        } catch (TooManyThingsDoneException e) {
            fail("Wrong exception");
        }
    }

    @Test
    public void testTooManyDoneExceptionThrown() {
        try {
            for (int i = 0; i < 9; i++) {
                Event regularevent = new RegularEvent();
                regularevent.setName("Math" + i);
                regularevent.setStatus("Done");
                regularevent.setYear(2019);
                regularevent.setMonth(10);
                regularevent.setMonth(10);
                eventsHashMap.put(regularevent.getName(), regularevent);
            }
            if (tooManyChecker.tooManyDoneChecker(eventsHashMap)) {
                throw new TooManyThingsDoneException();
            }
            fail();
        } catch (TooManyThingsDoneException e) {
            System.out.println("Right exception caught");
        } finally {
            System.out.println("Test 4 done");
        }
    }

}
