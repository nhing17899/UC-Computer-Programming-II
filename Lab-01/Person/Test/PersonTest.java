import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class PersonTest {

    Person p = new Person("Nhi", "Nguyen", "000002", "Stu", 1999);
    @Test
    public void testToString() {
        assertEquals("Person{firstName='Nhi', lastName='Nguyen', id='000002', title='Stu', yearOfBirth=1999}", p.toString());
    }

    @Test
    public void setFirstName() {
        p.setFirstName("Nhii");
        assertEquals("Nhii", p.getFirstName());
    }

    @Test
    public void setLastName() {
        p.setLastName("Nguyenn");
        assertEquals("Nguyenn", p.getLastName());
    }

    @Test
    public void setId() {
        p.setId("000011");
        assertEquals("000011", p.getId());
    }

    @Test
    public void setTitle() {
        p.setTitle("Student");
        assertEquals("Student", p.getTitle());
    }

    @Test
    public void setYearOfBirth() {
        p.setYearOfBirth(2000);
        assertEquals(2000, p.getYearOfBirth());
    }

    @Test
    public void getFullName() {
        assertEquals("Nhi Nguyen", p.getFullName());
    }

    @Test
    public void getFormalName() {
        assertEquals("Stu Nhi Nguyen", p.getFormalName());
    }

    @Test
    public void toCSVDataRecord() {
        assertEquals("Nhi,Nguyen,000002,Stu,1999", p.toCSVDataRecord());
    }
    @Test
    public void toJSONRecord() {
        char DQ = '\u0022';
        assertEquals(
                "{" +
                    DQ + "firstName" + DQ + ":" + DQ + "Nhi" + DQ + "," +
                    DQ + "lastName" + DQ + ":" + DQ + "Nguyen" + DQ + "," +
                    DQ + "id" + DQ + ":" + DQ + "000002" + DQ + "," +
                    DQ + "title" + DQ + ":" + DQ + "Stu" + DQ + "," +
                    DQ + "yearOfBirth" + DQ + ":" + DQ + "1999" + DQ +
                "}",
                p.toJSONRecord());
    }

    @Test
    public void toXMLRecord() {
        assertEquals(
                "<Person>" +
                        "<firstName>Nhi</firstName>" +
                        "<lastName>Nguyen</lastName>" +
                        "<id>000002</id>" +
                        "<title>Stu</title>" +
                        "<yearOfBirth>1999</yearOfBirth>" +
                        "</Person>",
                p.toXMLRecord());
    }
}