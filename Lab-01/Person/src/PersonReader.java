import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonReader {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";

        ArrayList<Person> personArr = new ArrayList<>();

        try
        {
            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                int line = 0;
                while(reader.ready())
                {
                    rec = reader.readLine();
                    String[] splitted = rec.split(",");

                    String firstName = splitted[0];
                    String lastName = splitted[1];
                    String id = splitted[2];
                    String title = splitted[3];
                    int yearOfBirth = Integer.parseInt(splitted[4]);

                    line++;

                    Person p = new Person(firstName, lastName, id, title, yearOfBirth);
                    personArr.add(p);
                }
                reader.close();
                System.out.println("\n\nData file read!");
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.printf("\n%6s %12s %12s %12s %15s", "ID", "First Name", "Last Name", "Title", "Year of birth");

        for (Person p : personArr) {
            System.out.printf("\n%s %12s %12s %12s %15s", p.getId(), p.getFirstName(), p.getLastName(), p.getTitle(), p.getYearOfBirth());
        }
    }
}
