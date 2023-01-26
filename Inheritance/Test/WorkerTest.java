import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorkerTest {

    Worker w = new Worker("Nhi", "Nguyen", "000002", "Stu", 1999, 16);

    @Test
    void setHourlyPayRate() {
        w.setHourlyPayRate(45);
        assertEquals(45, w.getHourlyPayRate());
    }

    @Test
    void testToString() {
        assertEquals("Worker{Person{firstName='Nhi', lastName='Nguyen', id='000002', title='Stu', yearOfBirth=1999}, hourlyPayRate=16.0}", w.toString());
    }

    @Test
    void calculateWeeklyPay() {
        assertEquals(40*w.getHourlyPayRate(), w.calculateWeeklyPay(40));
    }

    @Test
    void displayWeeklyPay() {
        String expected = "Hours of regular pay = 40.0, Regular pay = 640.0\nHours of over pay = 0.0, Over pay = 0.0";
        assertEquals(expected, w.displayWeeklyPay(40));
    }
}