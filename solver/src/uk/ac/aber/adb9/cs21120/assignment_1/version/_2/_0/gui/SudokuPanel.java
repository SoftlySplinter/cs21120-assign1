package uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.sudoku.Sudoku;

/**
 * <DL>
 * <DT><b>Created:</b></DT>
 * <DD>Oct 18, 2010</DD>
 * </DL>
 * @version 1
 * @author Alexander Brown
 */
public class SudokuPanel
extends JPanel
{
    private SudokuFrame frame;

    private TilePanel[][] tiles = new TilePanel[Sudoku.GRID_SIZE][Sudoku.GRID_SIZE];

    public SudokuPanel(SudokuFrame f)
    {
        frame = f;

        this.setLayout(new GridLayout(Sudoku.GRID_SIZE,Sudoku.GRID_SIZE));

        for(int i=0;i<Sudoku.GRID_SIZE;i++)
        {
            for(int j=0;j<Sudoku.GRID_SIZE;j++)
            {
                tiles[i][j] = new TilePanel(this,i,j);
                this.add(tiles[i][j]);
            }
        }
    }

    Sudoku getSudoku()
    {
        return frame.getSudoku();
    }

    @Override
    public void paintComponents(Graphics g)
    {
        super.paintComponents(g);

        for(int i=0;i<Sudoku.GRID_SIZE;i++)
        {
            for(int j=0;j<Sudoku.GRID_SIZE;j++)
            {
                tiles[i][j].repaint();
            }
        }
    }
}
