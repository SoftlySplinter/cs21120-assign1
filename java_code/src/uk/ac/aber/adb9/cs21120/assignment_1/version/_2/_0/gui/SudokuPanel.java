package uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.gui;

import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JPanel;
import uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.sudoku.Sudoku;

/**
 * The panel which holds the body of the sudoku. Each tile is represented at a TilePanel.
 * <DL>
 * <DT><b>Created:</b></DT>
 * <DD>Oct 18, 2010</DD>
 * </DL>
 * @version 1
 * @author Alexander Brown
 * @see TilePanel
 */
public class SudokuPanel
extends JPanel
{
    /**
     * The fFrame this is attached to.
     */
    private SudokuFrame frame;

    /**
     * The tiles in the sudoku, represented as TilePanels.
     */
    private TilePanel[][] tiles = new TilePanel[Sudoku.GRID_SIZE][Sudoku.GRID_SIZE];

    /**
     * Sets up a new sudoku panel.
     * @param f The frame this is attached to.
     */
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

    /**
     * Returns the Sudoku this is representing.
     * @return The Sudoku this is represtenting.
     */
    Sudoku getSudoku()
    {
        return frame.getSudoku();
    }

    /**
     * Calls repaint on all the tiles.
     * @param g The graphics to repaint.
     */
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
