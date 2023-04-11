import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataStreamsGUI extends JFrame {
    private JTextArea originalTextArea, filteredTextArea;
    private JTextField searchTextField;
    private JButton loadButton, searchButton, quitButton;

    public DataStreamsGUI() {
        // Set the window properties
        setTitle("Data Streams");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Center the window on the screen
        setLocationRelativeTo(null);

        // Create the text areas and add them to scroll panes
        originalTextArea = new JTextArea();
        filteredTextArea = new JTextArea();

        originalTextArea.setEditable(false);
        filteredTextArea.setEditable(false);

        JScrollPane originalScrollPane = new JScrollPane(originalTextArea);
        JScrollPane filteredScrollPane = new JScrollPane(filteredTextArea);

        // Create the search text field
        searchTextField = new JTextField();
        searchTextField.setPreferredSize(new Dimension(200, 25));

        // Create the command buttons
        loadButton = new JButton("Load File");
        searchButton = new JButton("Search");
        searchButton.setEnabled(false);
        quitButton = new JButton("Quit");

        // Add the components to the content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(searchTextField);
        topPanel.add(loadButton);
        topPanel.add(searchButton);
        topPanel.add(quitButton);
        contentPane.add(topPanel, BorderLayout.NORTH);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, originalScrollPane, filteredScrollPane);
        splitPane.setDividerLocation(0.5); // set the divider location to be in the middle
        splitPane.setResizeWeight(0.5); //
        contentPane.add(splitPane, BorderLayout.CENTER);

        // Add action listeners to the buttons
        loadButton.addActionListener(e -> {
            // Show the file chooser dialog
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
            fileChooser.setFileFilter(filter);
            int result = fileChooser.showOpenDialog(DataStreamsGUI.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                // Get the selected file's path
                Path path = fileChooser.getSelectedFile().toPath();
                if (!path.toString().endsWith(".txt")) {
                    JOptionPane.showMessageDialog(DataStreamsGUI.this, "Please select a .txt file", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Read the lines of the file into a Stream object
                    try (Stream<String> lines = Files.lines(path)) {
                        // Display the lines in the original text area
                        originalTextArea.setText(lines.collect(Collectors.joining(System.lineSeparator())));
                        originalTextArea.setCaretPosition(0);
                        // Enable the search button
                        searchButton.setEnabled(true);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(DataStreamsGUI.this, "Error loading file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        });


        searchButton.addActionListener(e -> {
            // Get the search string from the text field
            String searchString = searchTextField.getText();

            // Filter the lines of the original text area to get the matching lines
            List<String> matchingLines = Arrays.stream(originalTextArea.getText().split(System.lineSeparator()))
                    .filter(line -> line.contains(searchString))
                    .collect(Collectors.toList());

            // Display the matching lines in the filtered text area
            filteredTextArea.setText(String.join(System.lineSeparator(), matchingLines));
            filteredTextArea.setCaretPosition(0); // set caret position to beginning
        });

        quitButton.addActionListener(e -> System.exit(0));
    }
}
