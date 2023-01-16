import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonReader {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";

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

                System.out.printf("\n%6s %12s %12s %12s %12s", "ID", "First Name", "Last Name", "Title", "Year of birth");
                int line = 0;
                while(reader.ready())
                {
                    rec = reader.readLine();
                    String[] splitted = rec.split(", ");
                    String id = splitted[0];
                    String firstName = splitted[1];
                    String lastName = splitted[2];
                    String title = splitted[3];
                    String birth = splitted[4];

                    line++;

                    System.out.printf("\n%s %12s %12s %12s %12s", id, firstName, lastName, title, birth);
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
    }
}
