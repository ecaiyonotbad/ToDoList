import program.events.Event;
import program.events.RegularEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import ui.todolist.ToDoList;

class TestRegularEvent {
    Event regularEvent;
    ToDoList todolist;

    @BeforeEach
    public void setUp() {
        regularEvent = new RegularEvent();
        todolist= new ToDoList();
    }

    @Test
    public void testEventSetName() {
        String desireName = "Math 200";
        regularEvent.setName(desireName);
        String name = regularEvent.getName();
        assertTrue(name == desireName);
    }

    @Test
    public void testEventSetYear() {
        int desireYear = 2019;
        regularEvent.setYear(desireYear);
        int year = regularEvent.getYear();
        assertTrue(year == desireYear);
    }

    @Test
    public void testEventSetMonth() {
        int desireMonth = 5;
        regularEvent.setMonth(desireMonth);
        int month = regularEvent.getMonth();
        assertTrue(month == desireMonth);
    }

    @Test
    public void testEventSetDate() {
        int desireDate = 29;
        regularEvent.setDate(desireDate);
        int date = regularEvent.getDate();
        assertTrue(date == desireDate);
    }

    @Test
    public void testEventLListOneEvent() {
        regularEvent.setName("Math 200 Midterm");
        regularEvent.setYear(2019);
        regularEvent.setMonth(10);
        regularEvent.setDate(3);
        todolist.eventsHashMap.put(regularEvent.getName(),regularEvent);
        Event test1;
        test1 = todolist.eventsHashMap.get(regularEvent.getName());
        assertTrue(test1.getName() == "Math 200 Midterm");
        assertTrue(test1.getYear() == 2019);
        assertTrue(test1.getMonth() == 10);
        assertTrue(test1.getDate() == 3);

        regularEvent.setDone();
        assertTrue(test1.getStatus().equals("Done"));
    }

    @Test
    public void testEventDone() {
        regularEvent.setName("Math 200 Final");
        regularEvent.setYear(2019);
        regularEvent.setMonth(12);
        regularEvent.setDate(5);
        todolist.eventsHashMap.put(regularEvent.getName(),regularEvent);
        Event test1;
        test1 = todolist.eventsHashMap.get(regularEvent.getName());
        test1.setDone();
        assertTrue(test1.getStatus() == "Done");
    }
    @Test
    public void testSetStatus(){
        regularEvent.setStatus("Undone");
        assertTrue(regularEvent.getStatus()=="Undone");
        regularEvent.setFields();
        assertTrue(regularEvent.getStatus()=="Undone");
    }
}