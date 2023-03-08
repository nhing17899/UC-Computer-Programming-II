import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {
    // MAIN
    JPanel mainPanel;

    // TOP
    JPanel btnPanel;
    JButton rockBtn;
    JButton scissorsBtn;
    JButton paperBtn;
    JButton quitBtn;

    // MIDDLE
    JPanel displayPanel;
    JTextArea displayTA;
    JScrollPane scroller;

    // BOTTOM
    JPanel statPanel;
    JTextField playerCountTF, compCountTF, tieCountTF;
    JLabel playerLb, compLb, tieLb;

    // Fortune List
    ArrayList<String> gameList = new ArrayList<>();
    Random rand = new Random();
    int compMove, playerMove;
    int playerCount = 0;
    int compCount = 0;
    int tieCount = 0;

    public RockPaperScissorsFrame() {
        createGameList();

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        createTopPanel();
        mainPanel.add(btnPanel, BorderLayout.NORTH);

        createMiddlePanel();
        mainPanel.add(displayPanel, BorderLayout.CENTER);

        createBottomPanel();
        mainPanel.add(statPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setTitle("Rock Paper Scissors Game");

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screen.width * 1 / 2, screen.height * 3 / 4);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void createGameList() {
        gameList.add("R");
        gameList.add("P");
        gameList.add("S");
    }
    private void createTopPanel() {
        btnPanel = new JPanel();
        btnPanel.setBackground(Color.white);
        btnPanel.setLayout(new GridLayout(1,4));

        rockBtn = new JButton("Rock", new ImageIcon(new ImageIcon("src/rock.png").getImage().getScaledInstance(200,200, Image.SCALE_SMOOTH)));
        paperBtn = new JButton("Paper", new ImageIcon(new ImageIcon("src/paper.png").getImage().getScaledInstance(200,200, Image.SCALE_SMOOTH)));
        scissorsBtn = new JButton("Scissors", new ImageIcon(new ImageIcon("src/scissors.png").getImage().getScaledInstance(200,200, Image.SCALE_SMOOTH)));
        quitBtn = new JButton("Quit", new ImageIcon(new ImageIcon("src/quit.png").getImage().getScaledInstance(200,200, Image.SCALE_SMOOTH)));

        rockBtn.setVerticalTextPosition(SwingConstants.TOP);
        rockBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        rockBtn.setBackground(Color.white);
        rockBtn.addActionListener((ActionEvent quitActionEvent) -> {
            playerMove = 0;
            resolveMove(playerMove);
        });

        paperBtn.setVerticalTextPosition(SwingConstants.TOP);
        paperBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        paperBtn.setBackground(Color.white);
        paperBtn.addActionListener((ActionEvent quitActionEvent) -> {
            playerMove = 1;
            resolveMove(playerMove);
        });

        scissorsBtn.setVerticalTextPosition(SwingConstants.TOP);
        scissorsBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        scissorsBtn.setBackground(Color.white);
        scissorsBtn.addActionListener((ActionEvent quitActionEvent) -> {
            playerMove = 2;
            resolveMove(playerMove);
        });

        quitBtn.setVerticalTextPosition(SwingConstants.TOP);
        quitBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        quitBtn.setBackground(Color.white);
        quitBtn.addActionListener((ActionEvent quitActionEvent) -> System.exit(0));

        btnPanel.add(rockBtn);
        btnPanel.add(paperBtn);
        btnPanel.add(scissorsBtn);
        btnPanel.add(quitBtn);

        mainPanel.add(btnPanel, BorderLayout.NORTH);
    }
    private int getCompMove() {
        return rand.nextInt(3);
    }
    private void resolveMove(int playerMove) {
        compMove = getCompMove();
        displayTA.append("Player: " + gameList.get(playerMove) + " >>> <<< Computer: " + gameList.get(compMove) + "\n");

        // 0: rock || 1: paper || 2: scissors
        if (playerMove == compMove) {
            tieCount++;
            tieCountTF.setText(tieCount + "");
            displayTA.append("-> Ties\n");
        }
        else if (playerMove == 0) { // rock
            if (compMove == 1) { // paper
                compCount++;
                compCountTF.setText(compCount + "");
                displayTA.append("-> Paper covers Rock (Computer Wins)\n");
            }
            else { // scissors
                playerCount++;
                playerCountTF.setText(playerCount + "");
                displayTA.append("-> Rock breaks scissors (Player Wins)\n");
            }
        }
        else if (playerMove == 1) { // paper
            if (compMove == 0) { // rock
                playerCount++;
                playerCountTF.setText(playerCount + "");
                displayTA.append("-> Paper covers Rock (Player Wins)\n");
            }
            else { // scissors
                compCount++;
                compCountTF.setText(compCount + "");
                displayTA.append("-> Scissors cut Paper (Computer Wins)\n");
            }
        }
        else { // scissors
            if (compMove == 0) { // rock
                compCount++;
                compCountTF.setText(compCount + "");
                displayTA.append("-> Rock breaks scissors (Computer Wins)\n");
            }
            else { // paper
                playerCount++;
                playerCountTF.setText(playerCount + "");
                displayTA.append("-> Scissors cut Paper (Player Wins)\n");
            }
        }
    }
    private void createMiddlePanel() {
        displayPanel = new JPanel();
        displayPanel.setBackground(Color.white);

        displayTA = new JTextArea(17, 60);
        displayTA.setFont(new Font("SANS_SERIF", Font.PLAIN, 18));
        displayTA.setBackground(new Color(240, 240, 240));
        displayTA.setEditable(false);

        scroller = new JScrollPane(displayTA);

        displayPanel.add(scroller);
    }

    private void createBottomPanel() {
        statPanel = new JPanel();
        GridLayout layout = new GridLayout(3, 4);
        layout.setVgap(10);
        statPanel.setLayout(layout);

        playerLb = new JLabel("Player Wins");
        playerLb.setFont(new Font("SANS_SERIF", Font.BOLD, 18));
        playerCountTF = new JTextField(playerCount + "", 5);
        playerCountTF.setFont(new Font("SANS_SERIF", Font.BOLD, 18));

        compLb = new JLabel("Computer Wins");
        compLb.setFont(new Font("SANS_SERIF", Font.BOLD, 18));
        compCountTF = new JTextField(compCount + "", 5);
        compCountTF.setFont(new Font("SANS_SERIF", Font.BOLD, 18));

        tieLb = new JLabel("Ties");
        tieLb.setFont(new Font("SANS_SERIF", Font.BOLD, 18));
        tieCountTF = new JTextField(tieCount + "", 5);
        tieCountTF.setFont(new Font("SANS_SERIF", Font.BOLD, 18));

        statPanel.add(playerLb);
        statPanel.add(playerCountTF);

        statPanel.add(compLb);
        statPanel.add(compCountTF);

        statPanel.add(tieLb);
        statPanel.add(tieCountTF);
    }
}
