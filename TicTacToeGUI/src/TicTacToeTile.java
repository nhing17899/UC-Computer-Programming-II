import javax.swing.*;

public class TicTacToeTile extends JButton
{
    private int col;
    private int row;
    public TicTacToeTile(int row, int col)
    {
        super();
        this.col = col;
        this.row = row;
    }
}