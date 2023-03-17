import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import static java.awt.BorderLayout.NORTH;

public class TicTacToeFrame extends JFrame
{
    JPanel mainPnl, titlePnl, buttonPnl, gamePnl;
    JLabel titleLbl;
    JButton quitBtn;

    static int moves = 0;
    final int WIN_MOVES = 5;
    final int TIE_MOVES = 7;
    static String player = "x";
    String playerOld = "o";
    private static final int COL = 3;
    private static final int ROW = 3;

    static TicTacToeTile[][] board = new TicTacToeTile[3][3];
    public TicTacToeFrame() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 900);
        setVisible(true);

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        mainPnl.setBackground(Color.white);
        add(mainPnl);

        createTitlePanel();
        createButtonPanel();
        createGamePanel();
        createCommandPanel();

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screen.width * 1 / 2, screen.width * 1 / 2);
        setLocationRelativeTo(null);
    }

    public void createTitlePanel() {
        titlePnl = new JPanel();
        titleLbl = new JLabel("Tic Tac Toe", JLabel.CENTER);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);
        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titleLbl.setFont(new Font("SANS_SERIF", Font.PLAIN, 50));

        mainPnl.add(titleLbl, NORTH);
    }
    private void createButtonPanel()
    {
        gamePnl = new JPanel();
        gamePnl.setLayout(new GridLayout(3,3));

        mainPnl.add(BorderLayout.CENTER, gamePnl);
    }
    public void createGamePanel() {
        for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 3; col++)
            {
                board[row][col] = new TicTacToeTile(row, col);
                board[row][col].setText("");
                board[row][col].setBackground(Color.white);

                board[row][col].addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton clicked = (JButton) e.getSource();
                        clicked.setText(player);
                        clicked.setEnabled(false);
                        moves++;
                        results();

                        if (player == "x")
                        {
                            player = "o";
                        }
                        else
                        {
                            player = "x";
                        }
                    }
                });

                board[row][col].setFont(new Font("SANS_SERIF", Font.PLAIN, 50));
                gamePnl.add(board[row][col]);
            }
        }

    }
    public void results() {
        if(moves >= WIN_MOVES)
        {
            if(isWin(player) == true) {
                JOptionPane pane = new JOptionPane();
                int resultDisplay = JOptionPane.showConfirmDialog(pane, "Congratulation player " + player + "! You win! \nDo you want to play again? ");
                if(resultDisplay == JOptionPane.YES_NO_OPTION)
                {
                    clearBoard();
                }
                else
                {
                    System.exit(0);
                }
            }
        }
        if(moves >= TIE_MOVES)
        {
            if(isTie() == true) {
                JOptionPane pane = new JOptionPane();
                int resultDisplay = JOptionPane.showConfirmDialog(pane, "Opps its a TIE! \nDo you want to play again? " );
                if(resultDisplay == JOptionPane.YES_NO_OPTION)
                {
                    clearBoard();
                }
                else
                {
                    System.exit(0);
                }
            }
        }
    }
    private static boolean isColWin(String Player) {
        for(int col = 0; col < COL; col++) {
            if(board[0][col].getText().equals(player) && board[1][col].getText().equals(player) && board[2][col].getText().equals(player))
            {
                return true;
            }
        }
        return false;
    }
    private static boolean isWin(String player) {
        if(isColWin(player) || isRowWin(player) || isDiagonalWin(player))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    private static boolean isRowWin(String player) {
        for(int row=0; row < ROW; row++) {
            if(board[row][0].getText().equals(player) && board[row][1].getText().equals(player) && board[row][2].getText().equals(player))
            {
                return true;
            }
        }
        return false;
    }
    private static boolean isDiagonalWin(String player) {
        if(board[0][0].getText().equals(player) && board[1][1].getText().equals(player) && board[2][2].getText().equals(player) )
        {
            return true;
        }
        if(board[0][2].getText().equals(player) && board[1][1].getText().equals(player) && board[2][0].getText().equals(player) )
        {
            return true;
        }
        return false;
    }
    private static boolean isTie() {
        boolean x = false;
        boolean o = false;

        for(int row = 0; row < ROW; row++) {
            if(board[row][0].getText().equals("x") || board[row][1].getText().equals("x") || board[row][2].getText().equals("x"))
            {
                x = true;
            }
            if(board[row][0].getText().equals("o") || board[row][1].getText().equals("o") || board[row][2].getText().equals("o"))
            {
                o = true;
            }
            if(!(x && o))
            {
                return false;
            }
        }

        x = o = false;

        for(int col = 0; col < COL; col++) {
            if(board[0][col].getText().equals("x") || board[1][col].getText().equals("x") || board[2][col].getText().equals("x"))
            {
                x = true;
            }
            if(board[0][col].getText().equals("o") || board[1][col].getText().equals("o") || board[2][col].getText().equals("o"))
            {
                o = true;
            }
            if(!(x && o))
            {
                return false;
            }
        }

        x = o = false;

        if(board[0][0].getText().equals("x") || board[1][1].getText().equals("x") || board[2][2].getText().equals("x"))
        {
            x = true;
        }
        if(board[0][0].getText().equals("o") || board[1][1].getText().equals("o") || board[2][2].getText().equals("o"))
        {
            o = true;
        }
        if(!(x && o))
        {
            return false;
        }

        x = o = false;

        if(board[0][2].getText().equals("x") || board[1][1].getText().equals("x") || board[2][0].getText().equals("x"))
        {
            x = true;
        }
        if(board[0][2].getText().equals("o") || board[1][1].getText().equals("o") || board[2][0].getText().equals("o"))
        {
            o = true;
        }
        if(!(x && o))
        {
            return false;
        }
        return true;
    }
    private static void clearBoard() {
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                board[row][col].setText(" ");
                board[row][col].setEnabled(true);
                moves = 0;
                player = "x";
            }
        }
        if(player == "x")
        {
            player = "o";
        }
        else
        {
            player = "x";
        }
    }
    public void createCommandPanel(){
        buttonPnl = new JPanel();
        buttonPnl.setLayout(new GridLayout(1, 2));
        quitBtn = new JButton("Quit");
        quitBtn.setBackground(Color.lightGray);
        quitBtn.setFont(new Font("SANS_SERIF", Font.PLAIN, 25));
        quitBtn.setForeground(Color.white);
        buttonPnl.add(quitBtn);
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));
        mainPnl.add(buttonPnl, BorderLayout.SOUTH);
    }
}