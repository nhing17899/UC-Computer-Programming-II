import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import static java.nio.file.StandardOpenOption.CREATE;

public class ShortLister {
    public static void collectAll(ArrayList<String> shortWordList) {
        for (String word : shortWordList) {
            System.out.println(word);
        }
    };
    public static void main(String[] args) {
        ShortWordFilter filter = new ShortWordFilter();
        ArrayList<String> shortWordList = new ArrayList<>();

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

                while(reader.ready())
                {
                    rec = reader.readLine();
                    String[] splitted = rec.split(" ");

                    for (String word : splitted) {
                        if (filter.accept(word)) shortWordList.add(word);
                    }
                }
                reader.close();

                collectAll(shortWordList);
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
