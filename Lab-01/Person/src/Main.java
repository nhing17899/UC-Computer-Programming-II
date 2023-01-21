public class Main {
    public static void main(String[] args) {
        Person person = new Person("Tom", "Wulf", "000001", "Prof.", 1960);
        System.out.println("Test 1 -----------");
        System.out.println(person);
        System.out.println(person.toCSVDataRecord());

        System.out.println("Test 2 -----------");
        person.setFirstName("Nhi");
        person.setLastName("Nguyen");
        person.setId("000002");
        person.setTitle("Ms.");
        person.setYearOfBirth(1999);

        System.out.println(person);
        System.out.println("Full name: " + person.getFullName());
        System.out.println("Formal name: " + person.getFormalName());
        System.out.println("Age: " + person.getAge());
        System.out.println("Age in year: " + person.getAge(2022));
        System.out.println("CSV record: " + person.toCSVDataRecord());
    }
}