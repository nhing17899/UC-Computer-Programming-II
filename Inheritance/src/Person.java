import java.time.Year;

public class Person {
    private String firstName;
    private String lastName;
    private String id;
    private String title;
    private int yearOfBirth;

    public Person(String firstName, String lastName, String id, String title, int yearOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.title = title;
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    /**
     * Method to get the Full Name from the Person object
     *
     * @return return first and last name as a full name String
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Method to get the Formal Name from the Person object
     *
     * @return return title, first and last name as a formal name String
     */
    public String getFormalName() {
        return title + " " + getFullName();
    }

    /**
     * Get the age as an int assumming that it is for the curr year
     *
     * @return return the age int assuming the current year
     */
    public int getAge() {
        return Integer.parseInt(Year.now().toString()) - yearOfBirth;
    }

    /**
     * Get the age as an int that is for the given year
     *
     * @return return the age int assuming the given year
     */
    public int getAge(int year) {
        return year - yearOfBirth;
    }
    
    public String toCSVDataRecord() {
        return firstName + ',' + lastName + ',' + id + ',' + title + ',' + yearOfBirth;
    }

}