import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static java.nio.file.StandardOpenOption.CREATE;

public class TagExtractorFrame extends JFrame{
    JPanel mainPnl;
    JPanel topPnl;
    JPanel middlePnl;
    JPanel bottomPnl;

    JButton selectFileBtn;
    JButton selectStop;
    JTextArea fileNameTxt;

    JTextArea tagsTxt;
    JScrollPane tagsScrollTxt;

    JButton saveTagsBtn;

    private static ArrayList<String> stopWords = new ArrayList<>();
    private static ArrayList<String> wordsList = new ArrayList<>();
    private static Map<String, Integer> wordsMap = new HashMap<>();

    public TagExtractorFrame(){
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createTopPnl();
        mainPnl.add(topPnl, BorderLayout.NORTH);

        createMiddlePnl();
        mainPnl.add(middlePnl, BorderLayout.CENTER);

        createBotPnl();
        mainPnl.add(bottomPnl, BorderLayout.SOUTH);

        add(mainPnl);
        setSize(500,600);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void createTopPnl() {
        topPnl = new JPanel();
        topPnl.setLayout(new GridLayout(3, 1));

        selectStop = new JButton("Select Stop Words");
        selectStop.setFont(new Font("Dialog", Font.BOLD, 15));
        selectStop.addActionListener((ActionEvent e) ->
            {
                getStopWords();
            }
        );

        selectFileBtn = new JButton("Select File");
        selectFileBtn.setFont(new Font("Dialog", Font.BOLD, 15));
        selectFileBtn.addActionListener((ActionEvent e) ->
            {
                getWordList();
                sortWordsList();
                printWords();
            }
        );
        fileNameTxt = new JTextArea("", 1, 100);
        fileNameTxt.setEditable(false);

        topPnl.add(selectStop);
        topPnl.add(selectFileBtn);
        topPnl.add(fileNameTxt);
    }

    public void createMiddlePnl(){
        middlePnl = new JPanel();
        tagsTxt = new JTextArea("", 25, 42);
        tagsScrollTxt = new JScrollPane(tagsTxt);

        middlePnl.add(tagsScrollTxt);
    }

    public void createBotPnl() {
        bottomPnl = new JPanel();
        saveTagsBtn = new JButton("Save Data");

        bottomPnl.add(saveTagsBtn);
    }

    public void getWordList() {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";

        try
        {
            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));
                fileNameTxt.append(chooser.getSelectedFile().getName());

                // Finally we can read the file LOL!
                int line = 0;
                while(reader.ready())
                {
                    rec = reader.readLine();
                    line++;
                    String result = rec.replaceAll("[^\\p{L}\\p{Z}]","");
                    result = result.toLowerCase();
                    String[] Split = result.split("\\s+");

                    for (int x = 0; x <= Split.length-1; x++){
                        wordsList.add(Split[x]);
                    }
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

    public static void getStopWords(){
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";

        try
        {
            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
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
                    line++;

                    stopWords.add(rec);
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

    public static void sortWordsList(){
        for (int k = 0; k <= wordsList.size()-1; k++){
            if(wordsMap.containsKey(wordsList.get(k)))
                wordsMap.replace(wordsList.get(k), wordsMap.get(wordsList.get(k)), (wordsMap.get(wordsList.get(k)))+1);
            else
                wordsMap.put(wordsList.get(k),1);
        }

        for(int i = 0; i <= stopWords.size()-1; i++){
            if (wordsMap.containsKey(stopWords.get(i)))
                wordsMap.remove(stopWords.get(i));
        }
    }

    public void printWords() {
        for(String key: wordsMap.keySet()){
            tagsTxt.append(key + "\t" + "\t"+ "\t" + wordsMap.get(key) + "\n");
        }
    }
}
