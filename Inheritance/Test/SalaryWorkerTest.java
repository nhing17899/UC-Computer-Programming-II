import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalaryWorkerTest {

    SalaryWorker sw = new SalaryWorker("Nhi", "Nguyen", "000002", "Stu", 1999, 16, 80000);

    @Test
    void setAnnualSalary() {
        sw.setAnnualSalary(7000);
        assertEquals(7000, sw.getAnnualSalary());
    }

    @Test
    void testToString() {
        assertEquals("SalaryWorker{Worker{Person{firstName='Nhi', lastName='Nguyen', id='000002', title='Stu', yearOfBirth=1999}, hourlyPayRate=16.0}, annualSalary=80000.0}", sw.toString());
    }

    @Test
    void calculateWeeklyPay() {
        assertEquals(sw.getAnnualSalary()/52, sw.calculateWeeklyPay(40));
    }

    @Test
    void displayWeeklyPay() {
        String expected = "Weekly pay = " + sw.getAnnualSalary() + " / 52 = " + sw.getAnnualSalary()/52;
        assertEquals(expected, sw.displayWeeklyPay(40));
    }
}