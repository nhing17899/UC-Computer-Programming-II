public class Worker extends Person {
    private double hourlyPayRate;

    public Worker(String firstName, String lastName, String id, String title, int yearOfBirth, double hourlyPayRate) {
        super(firstName, lastName, id, title, yearOfBirth);
        this.hourlyPayRate = hourlyPayRate;
    }

    public double getHourlyPayRate() {
        return hourlyPayRate;
    }

    public void setHourlyPayRate(double hourlyPayRate) {
        this.hourlyPayRate = hourlyPayRate;
    }

    public String toString() {
        return "Worker{" +
                super.toString() + ", hourlyPayRate=" + hourlyPayRate +
                '}';
    }

    public double calculateWeeklyPay(double hoursWorked) {
        if (hoursWorked > 40) return 40 * hourlyPayRate + (hoursWorked - 40) * hourlyPayRate * 1.5;
        return hoursWorked * hourlyPayRate;
    }

    public String displayWeeklyPay(double hoursWorked) {
        double regular = 40;
        double over = 0;
        if (hoursWorked > 40) over = hoursWorked - 40;
        else regular = hoursWorked;
        return "Hours of regular pay = " + regular + ", Regular pay = " + regular * hourlyPayRate + "\n" +
                "Hours of over pay = " + over + ", Over pay = " + over * hourlyPayRate * 1.5;
    }

}
