import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class RandProductSearch extends JFrame {
    private JPanel mainPanel, topPanel, bottomPanel, namePanel;
    private JButton searchButton;
    private JTextField nameField;
    private JLabel nameLabel;
    private JTextArea displayArea;

    private final Path filePath = Paths.get("ProductData.txt");

    public RandProductSearch() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));

        createTopPanel();
        mainPanel.add(topPanel);

        createBottomPanel();
        mainPanel.add(bottomPanel);

        add(mainPanel);
        setSize(500, 600);
        setVisible(true);
    }

    private void createTopPanel() {
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1));

        namePanel = new JPanel();

        nameLabel = new JLabel("Name: ");
        nameField = new JTextField(35);
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        searchButton = new JButton("Search");
        searchButton.addActionListener((ActionEvent ae) ->
                searchFile()
        );
        topPanel.add(namePanel);
        topPanel.add(searchButton);
    }

    private void createBottomPanel() {
        bottomPanel = new JPanel();
        displayArea = new JTextArea(20, 130);
        bottomPanel.add(displayArea);
    }

    private ArrayList<String> readFile() throws IOException {
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            return reader.lines().collect(Collectors.toCollection(ArrayList::new));
        }
    }

    private void searchFile() {
        displayArea.selectAll();
        displayArea.replaceSelection("");

        String input = nameField.getText().toLowerCase();
        try {
            ArrayList<String> fileLines = readFile();
            for (String line : fileLines) {
                if (line.toLowerCase().contains(input)) {
                    displayArea.append(line + "\n");
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
