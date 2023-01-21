import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductGenerator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductData.txt");

        ArrayList<Product> productArr = new ArrayList<>();

        String id = "", name = "", description = "";
        double cost = 0;

        String productRecord = "";

        boolean done = false;

        do {
            id = SafeInput.getNonZeroLenString(sc, "Enter ID [6 digits] ");
            name = SafeInput.getNonZeroLenString(sc, "Enter Name ");
            description = SafeInput.getNonZeroLenString(sc, "Enter Description ");
            cost = SafeInput.getRangedDouble(sc, "Enter Cost ", 0, 9999);

            Product product = new Product(name,description,id,cost);

            productArr.add(product);

            done = SafeInput.getYNConfirm(sc, "Are you done?: ");

        } while (!done);

        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for (Product product : productArr) {
                System.out.println(product);
                String record = product.toCSVDataRecord();

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