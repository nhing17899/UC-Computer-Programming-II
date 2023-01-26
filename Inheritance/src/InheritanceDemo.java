import java.util.ArrayList;

public class InheritanceDemo {
    public static void main(String[] args) {
        ArrayList<Worker> workerArr = new ArrayList<>();

        Worker worker1 = new Worker("worker1", "nguyen", "000001", "Mrs.", 1999, 16);
        Worker worker2 = new Worker("worker2", "truong", "000002", "Mr.", 1972, 45);
        Worker worker3 = new Worker("worker3", "tran", "000003", "Ms.", 2000, 25);

        SalaryWorker sWorker1 = new SalaryWorker("sWorker1", "nguyen", "000001", "Mrs.", 1999, 16, 40000);
        SalaryWorker sWorker2 = new SalaryWorker("sWorker2", "truong", "000002", "Mr.", 1972, 45, 90000);
        SalaryWorker sWorker3 = new SalaryWorker("sWorker3", "tran", "000003", "Ms.", 2000, 25, 74000);

        workerArr.add(worker1);
        workerArr.add(worker2);
        workerArr.add(worker3);
        workerArr.add(sWorker1);
        workerArr.add(sWorker2);
        workerArr.add(sWorker3);

        int[] hoursArr = new int[] {40,50,40};
        for (int i = 0; i < hoursArr.length; i++) {
            int hour = hoursArr[i];
            System.out.println("Week " + (i + 1) + ":");
            for (int j = 0; j < workerArr.size(); j++) {
                Worker worker = workerArr.get(j);
                System.out.println("Worker " + worker.getFirstName() + ": " +  worker.calculateWeeklyPay(hour));
            }
            System.out.println();
        }

    }
}