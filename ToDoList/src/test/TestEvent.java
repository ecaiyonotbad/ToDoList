import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import program.events.Event;
import program.events.RegularEvent;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class TestEvent {
    Event event;
    @BeforeEach
    public void setUp(){
        event = new RegularEvent();
    }
    @Test
    public void testEventequalsTrue(){
        event.setName("Math 200 Final");
        event.setLocation("UBC");
        event.setStatus("Undone");
        event.setYear(2019);
        event.setMonth(12);
        event.setDate(5);
        Event event2 = new RegularEvent();
        event2.setName("Math 200 Final");
        event2.setLocation("UBC");
        event2.setStatus("Undone");
        event2.setYear(2019);
        event2.setMonth(12);
        event2.setDate(5);
        assertTrue(event.equals(event2));
    }
    @Test
    public void testEventequalsFalse(){
        event.setName("Math 200 Final");
        event.setLocation("UBC");
        event.setStatus("Undone");
        event.setYear(2019);
        event.setMonth(12);
        event.setDate(5);
        Event event2 = new RegularEvent();
        event2.setName("Math 200 Final");
        event2.setLocation("UBC");
        event2.setStatus("Undone");
        event2.setYear(2019);
        event2.setMonth(12);
        event2.setDate(6);
        assertFalse(event.equals(event2));
    }

    @Test
    public void testHashCode() {
        event.setName("Math 200 Final");
        event.setLocation("UBC");
        event.setStatus("Undone");
        event.setYear(2019);
        event.setMonth(12);
        event.setDate(5);
        assertEquals(event.hashCode(), Objects.hash(event.getName(),
                event.getLocation(), event.getStatus(), event.getYear()
                , event.getMonth(), event.getDate()));
    }

    @Test
    public void testEquals() {
        event.setName("Math 200 Final");
        event.setLocation("UBC");
        event.setStatus("Undone");
        event.setYear(2019);
        event.setMonth(12);
        event.setDate(5);
        assertFalse(event.equals(null));
        assertTrue(event.equals(event));

    }

}
