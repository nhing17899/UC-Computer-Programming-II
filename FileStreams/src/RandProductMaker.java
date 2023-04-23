import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandProductMaker extends JFrame {
    JPanel mainPnl, topPnl, botPnl;

    JPanel namePnl, descPnl, idPnl, costPnl;
    JLabel nameLbl, descLbl, idLbl, costLbl;
    private static JTextField nameF, descF, idF, costF;

    JPanel btnPnl, recPnl;
    JButton quitBtn, writeBtn;
    JLabel recLbl;
    static JTextField recF;

    private static RandomAccessFile file;

    private static String id;
    private static String name;
    private static String desc;
    private static double cost;

    private static int recNum = 0;
    private static final String SAVE_LABEL = "Save";
    private static final String QUIT_LABEL = "Quit";

    public RandProductMaker() throws FileNotFoundException {
        mainPnl = new JPanel();
        mainPnl.setLayout(new GridLayout(2, 1));

        createTopPnl();
        mainPnl.add(topPnl);

        createBotPnl();
        mainPnl.add(botPnl);

        add(mainPnl);
        setSize(500,600);
        setVisible(true);
    }

    public void createTopPnl() {
        topPnl = new JPanel();
        topPnl.setLayout(new GridLayout(4, 1));

        idPnl = new JPanel();
        idLbl = new JLabel("ID: ");
        idF = new JTextField(6);
        idLbl.setHorizontalAlignment(SwingConstants.CENTER);
        idF.setHorizontalAlignment(SwingConstants.CENTER);
        idPnl.add(idLbl);
        idPnl.add(idF);
        topPnl.add(idPnl);

        namePnl = new JPanel();
        nameLbl = new JLabel("Name: ");
        nameF = new JTextField(35);
        nameLbl.setHorizontalAlignment(SwingConstants.CENTER);
        nameF.setHorizontalAlignment(SwingConstants.CENTER);
        namePnl.add(nameLbl);
        namePnl.add(nameF);
        topPnl.add(namePnl);

        descPnl = new JPanel();
        descLbl = new JLabel("Description: ");
        descF = new JTextField(75);
        descLbl.setHorizontalAlignment(SwingConstants.CENTER);
        descF.setHorizontalAlignment(SwingConstants.CENTER);
        descPnl.add(descLbl);
        descPnl.add(descF);
        topPnl.add(descPnl);

        costPnl = new JPanel();
        costLbl = new JLabel("Cost: ");
        costF = new JTextField(8);
        costLbl.setHorizontalAlignment(SwingConstants.CENTER);
        costF.setHorizontalAlignment(SwingConstants.CENTER);
        costPnl.add(costLbl);
        costPnl.add(costF);
        topPnl.add(costPnl);
    }


    public void createBotPnl() {
        botPnl = new JPanel();
        botPnl.setLayout(new GridLayout(2, 1));

        JButton saveBtn = new JButton(SAVE_LABEL);
        saveBtn.setHorizontalAlignment(JLabel.CENTER);
        saveBtn.addActionListener(e -> writeProduct());

        quitBtn = new JButton(QUIT_LABEL);
        quitBtn.setHorizontalAlignment(JLabel.CENTER);
        quitBtn.addActionListener(e -> System.exit(0));

        btnPnl = new JPanel(new GridLayout(1, 2));
        btnPnl.add(saveBtn);
        btnPnl.add(quitBtn);
        botPnl.add(btnPnl);

        recPnl = new JPanel();
        recLbl = new JLabel("Record Count: ");
        recF = new JTextField(8);
        recLbl.setHorizontalTextPosition(JLabel.CENTER);
        recF.setHorizontalAlignment(JLabel.CENTER);
        recF.setEditable(false);
        recPnl.add(recLbl);
        recPnl.add(recF);
        botPnl.add(recPnl);
    }


    public static void writeProduct(){
        if(checkProduct()){
            getFile();
            name = padName(nameF.getText());
            desc = padDesc(descF.getText());
            id = padID(idF.getText());
            cost = Double.parseDouble(costF.getText());

            writeFile();

            idF.setText("");
            nameF.setText("");
            descF.setText("");
            costF.setText("");
            recNum++;
            recF.setText(String.valueOf(recNum));
        }
        else{
            JOptionPane.showMessageDialog(null, "Invalid input! Remember ID must be 6 characters or less, name must be 35 characters or less, and description must be 75 characters or less. Please check your inputs and try again.");
            idF.setText("");
            nameF.setText("");
            descF.setText("");
            costF.setText("");

        }
    }

    public static boolean checkProduct(){
        String id = idF.getText();
        String n = nameF.getText();
        String d = descF.getText();

        if(id.length() <= 6 && n.length() <= 35 && d.length() <=75)
            return true;
        else
            return false;
    }

    public static String padName(String s){
        while (s.length() < 35){
            s = s + " ";
        }
        return s;
    }

    public static String padDesc(String s){
        while (s.length() < 75){
            s = s + " ";
        }
        return s;
    }

    public static String padID(String s){
        while (s.length() < 6){
            s = s + " ";
        }
        return s;
    }

    public static void writeFile(){
        try {
            file.seek(file.length());
            file.writeBytes(id + ", " + name + ", "  + desc + ", "  + String.format("%,.2f", cost) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getFile(){
        try {
            file = new RandomAccessFile("ProductData.txt", "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}