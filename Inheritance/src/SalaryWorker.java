public class SalaryWorker extends Worker {
    private double annualSalary;
    public SalaryWorker(String firstName, String lastName, String id, String title, int yearOfBirth, double hourlyPayRate, double annualSalary) {
        super(firstName, lastName, id, title, yearOfBirth, hourlyPayRate);
        this.annualSalary = annualSalary;
    }
    public double getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(double annualSalary) {
        this.annualSalary = annualSalary;
    }

    public String toString() {
        return "SalaryWorker{" +
                super.toString() + ", annualSalary=" + annualSalary +
                '}';
    }

    public double calculateWeeklyPay(double hoursWorked) {
        return annualSalary / 52;
    }
    public String displayWeeklyPay(double hoursWorked) {
        return "Weekly pay = " + annualSalary + " / 52 = " + annualSalary/52;
    }
}
