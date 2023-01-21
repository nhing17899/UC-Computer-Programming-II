

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductReader {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";

        ArrayList<Product> productArr = new ArrayList<>();

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

                    String name = splitted[0];
                    String description = splitted[1];
                    String id = splitted[2];
                    double cost = Double.parseDouble(splitted[3]);

                    line++;

                    Product p = new Product(name, description, id, cost);
                    productArr.add(p);
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

        System.out.printf("\n%6s %12s %15s %12s", "ID", "Name", "Description", "Cost");

        for (Product p : productArr) {
            System.out.printf("\n%s %12s %15s %12s", p.getId(), p.getName(), p.getDescription(), p.getCost());
        }
    }
}
