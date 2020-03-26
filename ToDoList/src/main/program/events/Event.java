package program.events;


import java.util.Objects;
import java.util.Observable;

public abstract class Event {
    protected String name;
    protected String location;
    protected String status = "undone";
    protected int year;
    protected int month;
    protected int date;

    //SETTERS
    public void setName(String inputname) {
        name = inputname;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStatus(String input) {
        status = input;
    }

    public void setDone() {
        this.status = "Done";
    }

    public void setMonth(int inputMonth) {
        month = inputMonth;
    }

    public void setDate(int inputDate) {
        date = inputDate;
    }

    public void setYear(int inputYear) {
        year = inputYear;
    }

    //END OF SETTERS

    //GETTERS
    public String getName() {
        return name;
    }

    public String getStatus() {
        return this.status;
    }

    public String getLocation() {
        return this.location;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }

    //MODIFIES: this
    //EFFECTS: set the fields
    public abstract void setFields();
    //end of getters

    //EFFECTS: Override equals
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Event event = (Event) o;
        return year == event.year
                && month == event.month
                && date == event.date
                && name.equals(event.name)
                && location.equals(event.location)
                && status.equals(event.status);
    }

    //EFFECTS: Override hashCode
    @Override
    public int hashCode() {
        return Objects.hash(name, location, status, year, month, date);
    }
}
