import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

public class FortuneTellerFrame extends JFrame {
    // MAIN
    JPanel mainPanel;

    // TOP
    JPanel iconPanel;
    JLabel titleLabel;
    ImageIcon icon;

    // MIDDLE
    JPanel displayPanel;
    JTextArea display;
    JScrollPane scroller;

    // BOTTOM
    JPanel controlPanel;
    JButton readBtn;
    JButton quitBtn;

    // Fortune List
    ArrayList<String> fortuneList = new ArrayList<>();
    Random rand = new Random();
    int idxFortune = 0;
    int prevIdxFortune = 0;

    public FortuneTellerFrame() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        createTopPanel();
        mainPanel.add(iconPanel, BorderLayout.NORTH);

        createMiddlePanel();
        mainPanel.add(displayPanel, BorderLayout.CENTER);

        createBottomPanel();
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setTitle("Fortune Teller");

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screen.width * 3 / 4, screen.height * 3 / 5);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createTopPanel() {
        iconPanel = new JPanel();
        iconPanel.setBackground(Color.white);

        icon = new ImageIcon(new ImageIcon("src/fortune-teller.jpg").getImage().getScaledInstance(200,220, Image.SCALE_SMOOTH) );
        titleLabel = new JLabel("Fortune Teller", icon, JLabel.CENTER);
        titleLabel.setFont(new Font("SERIF", Font.BOLD, 36));
        titleLabel.setVerticalTextPosition(JLabel.BOTTOM);
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);

        iconPanel.add(titleLabel);
    }
    private void createMiddlePanel() {
        displayPanel = new JPanel();
        displayPanel.setBackground(Color.white);

        display = new JTextArea(10,75);
        display.setFont(new Font("SANS_SERIF", Font.PLAIN, 20));
        display.setBackground(new Color(240, 240, 240));
        display.setEditable(false);

        scroller = new JScrollPane(display);

        displayPanel.add(scroller);
    }

    private void createFortuneList() {
        fortuneList.add("Stay healthy. Walk a mile.");
        fortuneList.add("The family that plays together stays together.");
        fortuneList.add("Eat chocolate to have a sweeter life.");
        fortuneList.add("Once you make a decision the universe conspires to make it happen.");
        fortuneList.add("Make yourself necessary to someone.");
        fortuneList.add("The only way to have a friend is to be one.");
        fortuneList.add("Nothing great was ever achieved without enthusiasm.");
        fortuneList.add("Dance as if no one is watching.");
        fortuneList.add("A routine task will turn into an enchanting adventure.");
        fortuneList.add("Beware of all enterprises that require new clothes.");
        fortuneList.add("Be true to your work, your word, and your friend.");
        fortuneList.add("Goodness is the only investment that never fails.");
        fortuneList.add("A journey of a thousand miles begins with a single step.");
        fortuneList.add("Forget injuries; never forget kindnesses.");
        fortuneList.add("Respect yourself and others will respect you.");
        fortuneList.add("A man cannot be comfortable without his own approval.");
        fortuneList.add("Always do right. This will gratify some people and astonish the rest.");
        fortuneList.add("Sing everyday and chase the mean blues away.");
        fortuneList.add("A routine task will turn into an enchanting adventure.");
        fortuneList.add("You will receive money from an unexpected source.");
    }

    private void createBottomPanel() {
        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(1, 2));

        readBtn = new JButton("Read My Fortune!");
        createFortuneList();
        readBtn.addActionListener((ActionEvent readActionEvent) -> {
            while (idxFortune == prevIdxFortune) {
                idxFortune = rand.nextInt(fortuneList.size());
            }
            prevIdxFortune = idxFortune;
            display.append(fortuneList.get(idxFortune) + "\n");
        });


        quitBtn = new JButton("Quit");

        readBtn.setFont(new Font("SANS_SERIF", Font.PLAIN, 18));
        readBtn.setBackground(Color.LIGHT_GRAY);
        quitBtn.setFont(new Font("SANS_SERIF", Font.PLAIN, 18));
        quitBtn.setBackground(Color.LIGHT_GRAY);

        controlPanel.add(readBtn);
        controlPanel.add(quitBtn);

        quitBtn.addActionListener((ActionEvent quitActionEvent) -> System.exit(0));
    }
}
