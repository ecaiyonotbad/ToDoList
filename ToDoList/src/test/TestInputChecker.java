import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import program.checkers.InputChecker;

class TestInputChecker {
    InputChecker checker = new InputChecker();

    @Test
    public void testMonthCheck() {
        boolean wrongMonth = checker.monthCheck(13);
        assertFalse(wrongMonth);
        wrongMonth= checker.monthCheck(0);
        assertFalse(wrongMonth);
        boolean rightMonth = checker.monthCheck(1);
        assertTrue(rightMonth);
        rightMonth=checker.monthCheck(12);
        assertTrue(rightMonth);
    }

    @Test
    public void testDateCheck() {
        boolean wrongDate = checker.dateCheck(32);
        assertFalse(wrongDate);
        wrongDate=checker.dateCheck(0);
        assertFalse(wrongDate);
        boolean rightDate = checker.dateCheck(1);
        assertTrue(rightDate);
        rightDate=checker.dateCheck(31);
        assertTrue(rightDate);
    }


}
