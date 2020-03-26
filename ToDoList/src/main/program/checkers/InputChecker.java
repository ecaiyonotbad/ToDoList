package program.checkers;


public class InputChecker {

    //EFFECTS: return true if the 1<=month<=12, false otherwise
    public boolean monthCheck(int month) {
        if (month > 12 || month < 1) {
            System.out.println("Please enter a value between 1 to 12\n");
            return false;
        }
        return true;
    }

    //EFFECTS: return true if 1<=date<=31, false otherwise
    public boolean dateCheck(int date) {
        int maxdate = 31;
        int mindate = 1;
        if (date > maxdate || date < mindate) {
            System.out.println("Please enter a value between 1 to 31\n");
            return false;
        }
        return true;
    }


}