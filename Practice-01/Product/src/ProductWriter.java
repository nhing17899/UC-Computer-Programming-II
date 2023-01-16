import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt");
        ArrayList<String> record = new ArrayList<>();

        String id = "", name = "", description = "";
        double cost = 0;

        String productRec = "";

        boolean done = false;

        do {
            id = SafeInput.getNonZeroLenString(sc, "Enter ID [6 digits] ");
            name = SafeInput.getNonZeroLenString(sc, "Enter Name ");
            description = SafeInput.getNonZeroLenString(sc, "Enter Description ");
            cost = SafeInput.getDouble(sc, "Enter Cost");

            productRec = id + ", " + name + ", " + description + ", " + Math.round(cost * 10) / 10.0;
            record.add(productRec);

            done = SafeInput.getYNConfirm(sc, "Are you done?: ");

        } while (!done);

        for (String p : record) {
            System.out.println(p);
            try
            {
                OutputStream out =
                        new BufferedOutputStream(Files.newOutputStream(file, CREATE));
                BufferedWriter writer =
                        new BufferedWriter(new OutputStreamWriter(out));

                for(String rec : record)
                {
                    writer.write(rec, 0, rec.length());
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
}
