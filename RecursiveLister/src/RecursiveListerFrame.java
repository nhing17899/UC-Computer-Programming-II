import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RecursiveListerFrame extends JFrame
{
    JPanel mainPanel, titlePanel, displayPanel, buttonPanel;
    JLabel titleLabel;
    JTextArea displayTextArea;
    JScrollPane scroller;
    JButton quitButton, fileButton;

    public RecursiveListerFrame()
    {
        setTitle("Recursive File Lister");
        setSize(800, 800);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        createTitlePanel();
        createDisplayPanel();
        createButtonPanel();

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screen.width * 1 / 2, screen.height * 3 / 4);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createTitlePanel()
    {
        titlePanel = new JPanel();

        titleLabel = new JLabel("Recursive File Lister");
        titleLabel.setFont(new Font("SANS_SERIF", Font.BOLD, 20));

        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel, BorderLayout.NORTH);
    }

    private void createDisplayPanel()
    {
        displayPanel = new JPanel();

        displayTextArea = new JTextArea(50, 80);
        displayTextArea.setEditable(false);

        scroller = new JScrollPane(displayTextArea);

        displayPanel.add(scroller);
        mainPanel.add(displayPanel, BorderLayout.CENTER);
    }

    private void createButtonPanel()
    {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));

        fileButton = new JButton("Search Directory");
        fileButton.setFont(new Font("SANS_SERIF", Font.BOLD, 18));
        fileButton.addActionListener(e -> directorySearch());

        quitButton = new JButton("Quit");
        quitButton.setFont(new Font("SANS_SERIF", Font.BOLD, 18));
        quitButton.addActionListener(new ActionListener()
        {
            JOptionPane optionPane = new JOptionPane();
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int result = JOptionPane.showConfirmDialog(optionPane, "You sure you want to exit?", "Exiting", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }
                else
                {
                    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                }
            }
        });

        buttonPanel.add(fileButton);
        buttonPanel.add(quitButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void directorySearch()
    {
        JFileChooser myChoice = new JFileChooser();
        myChoice.setDialogTitle("Pick your directory: ");
        myChoice.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        File workingDirectory = new File(System.getProperty("user.dir"));
        myChoice.setCurrentDirectory(workingDirectory);

        if (myChoice.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            File chosenDirectory = myChoice.getSelectedFile();
            displayTextArea.setText("--- Chosen Directory: \n" + chosenDirectory + "\n\n");
            displayTextArea.append("\n");

            displayTextArea.append("--- Files and sub directories from the directory you chose are listed below: \n");
            fileList(chosenDirectory);
            displayTextArea.append("\n");
        }
        else
        {
            displayTextArea.append("Couldn't find that file! Try again!");
        }
    }

    private void fileList(File file)
    {
        File FileNames[] = file.listFiles();

        if (FileNames != null)
        {
            for (File fileName : FileNames)
            {
                displayTextArea.append(fileName + "\n");

                if (fileName.isDirectory())
                {
                    fileList(fileName);
                }
            }
        }
    }
}