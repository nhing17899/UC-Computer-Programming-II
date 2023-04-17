import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static java.awt.BorderLayout.NORTH;

public class TicTacToeGame extends JFrame
{
    JFrame gameFrame = new JFrame();
    JPanel mainPanel;
    JPanel titlePanel;
    JPanel buttonPanel;
    JPanel gamePanel;

    JButton quitButton;
    JLabel titleLabel;

    boolean done;

    TicTacToeBoard TicTacToeBoard = new TicTacToeBoard();

    public TicTacToeGame()
    {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());


        createTitlePanel();
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        createGamePanel();
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        createCommandPanel();
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screen.width * 2 / 5, screen.width * 2 / 5);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void createTitlePanel()
    {
        titlePanel = new JPanel();
        titleLabel = new JLabel("TIC TAC TOE");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        titleLabel.setVerticalTextPosition(JLabel.BOTTOM);
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titlePanel.add(titleLabel);
    }

    private void createGamePanel()
    {
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(3,3));

        for
        (int row = 0; row < 3; row++) for (int col = 0; col < 3; col++)
        {
            gamePanel.add(TicTacToeBoard.getBoard()[row][col]);
            TicTacToeBoard.getBoard()[row][col].addActionListener((ActionEvent ae) ->
            {
                Object source = ae.getSource();
                if (source instanceof JButton)
                {
                    JButton buttonClick = (JButton) source;

                    if (buttonClick.getText().equals(" "))
                    {
                        buttonClick.setText(TicTacToeBoard.getPlayer());
                        TicTacToeBoard.setMoves(TicTacToeBoard.getMoves() + 1);
                        if (TicTacToeBoard.getMoves() >= TicTacToeBoard.getMOVES_FOR_WIN())
                        {
                            if (isWin(TicTacToeBoard.getPlayer()))
                            {
                                int a = JOptionPane.showConfirmDialog(gameFrame, TicTacToeBoard.getPlayer() + " wins! Do you wanna play again?");
                                if(a == JOptionPane.YES_OPTION)
                                {
                                    TicTacToeBoard.clearBoard();
                                }
                                else if (a == JOptionPane.NO_OPTION)
                                {
                                    System.exit(0);
                                }
                            }
                        }
                        if (TicTacToeBoard.getMoves() >= TicTacToeBoard.getMOVES_FOR_TIE())
                        {
                            if (TicTacToeBoard.isTie())
                            {
                                int a = JOptionPane.showConfirmDialog(gameFrame, "Tie! Do you want to play again?");
                                if (a == JOptionPane.YES_OPTION)
                                {
                                    TicTacToeBoard.clearBoard();
                                }
                                else if (a == JOptionPane.NO_OPTION)
                                {
                                    System.exit(0);
                                }
                            }
                        }
                        if (TicTacToeBoard.getPlayer().equals("x") && !done)
                        {
                            TicTacToeBoard.setPlayer("o");
                        }
                        else
                        {
                            TicTacToeBoard.setPlayer("x");
                        }
                        done = false;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(gameFrame, "An invalid move! Please TRY AGAIN");
                    }
                }
            });
        }
    }
    private boolean isWin(String player)
    {
        if (TicTacToeBoard.isColWin(player) || TicTacToeBoard.isRowWin(player) || TicTacToeBoard.isDiagonalWin(player))
        {
            return true;
        }
        return false;
    }
    public void createCommandPanel()
    {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        quitButton = new JButton("QUIT");
        quitButton.setBackground(Color.GRAY);
        quitButton.setForeground(Color.white);
        buttonPanel.add(quitButton);
        quitButton.addActionListener((ActionEvent ae) -> System.exit(0));
    }
}