import program.events.UrgentEvent;
import program.events.Event;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

public class TestUrgentEvent {
    Event urgent;

    @BeforeEach
    public void setUp(){
        urgent = new UrgentEvent();
    }

    @Test
    public void testSetField(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int date = cal.get(Calendar.DATE);

        urgent.setFields();
        urgent.setName("hi");
        assertSame(urgent.getStatus(),"Urgent");
        assertSame(urgent.getName(),"hi");
        assertTrue(urgent.getYear()==year);
        assertTrue(urgent.getMonth()==month);
        assertTrue(urgent.getDate()==date);


    }

}
