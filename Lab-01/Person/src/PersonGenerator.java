import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\PersonData.txt");

        ArrayList<Person> personArr = new ArrayList<>();

        String id = "", firstName = "", lastName = "", title = "";
        int yearOfBirth = 0;

        String personRecord = "";

        boolean done = false;

        do {
            id = SafeInput.getNonZeroLenString(sc, "Enter ID [6 digits] ");
            firstName = SafeInput.getNonZeroLenString(sc, "Enter First Name ");
            lastName = SafeInput.getNonZeroLenString(sc, "Enter Last Name ");
            title = SafeInput.getNonZeroLenString(sc, "Enter Title ");
            yearOfBirth = SafeInput.getRangedInt(sc, "Enter Year of birth ", 1000, 9999);

            Person person = new Person(firstName,lastName,id,title,yearOfBirth);

            personArr.add(person);

            done = SafeInput.getYNConfirm(sc, "Are you done?: ");

        } while (!done);

        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for (Person person : personArr) {
                System.out.println(person);
                String record = person.toCSVDataRecord();

                writer.write(record, 0, record.length());
                writer.newLine();

            }
            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
