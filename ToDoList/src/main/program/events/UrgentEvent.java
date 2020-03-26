package program.events;

import java.util.Calendar;

//source:
//https://stackoverflow.com/questions/7182996/java-get-month-integer-from-date

public class UrgentEvent extends Event {

    //MODIFIES: this
    //EFFECTS: set the event status to Urgent and the date to current date
    @Override
    public void setFields() {
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH) + 1;
        date = cal.get(Calendar.DATE);
        status = "Urgent";
    }
}
