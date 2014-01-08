package uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The GUI representation of a Tile in the Sudoku.
 * <DL>
 * <DT><b>Created:</b></DT>
 * <DD>Oct 18, 2010</DD>
 * </DL>
 * @version 1
 * @author Alexander Brown
 *
 * @see uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.sudoku.Tile
 */
public class TilePanel
extends JPanel
{
    /**
     * A GUI representation of the possibilities at the Tile.
     */
    private JLabel[][] possibles = new JLabel[3][3];

    /**
     * The Sudoku Panel this is contained in.
     */
    private SudokuPanel sudoku;

    /**
     * The possible values as a character array.
     */
    private char[] values;

    /**
     * The x coordinate of the Tile.
     */
    private int x;

    /**
     * The y coordinate of the Tile.
     */
    private int y;

    /**
     * Creates a now Tile Panel and sets it up accordingly.
     * @param p The link back to the Sudoku Panel.
     * @param x The x coordinate of the Tile.
     * @param y The y coordinate of the Tile.
     */
    public TilePanel(SudokuPanel p, int x, int y)
    {
        sudoku = p;
        this.x = x;
        this.y = y;

        this.setLayout(new GridLayout(3,3));

        //Sets up the border as needed.
        if(x==0 && y==0)
        {
            this.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
        }
        else if(x==0 && y%3==0)
        {
            this.setBorder(BorderFactory.createMatteBorder(1,3,1,1,Color.black));
        }
        else if(y==0 && x%3==0)
        {
            this.setBorder(BorderFactory.createMatteBorder(3,1,1,1,Color.black));
        }
        else if(x==0)
        {
            this.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
        }
        else if(y==0)
        {
            this.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
        }
        else if((x%3==0) && (y%3==0))
        {
            this.setBorder(BorderFactory.createMatteBorder(3,3,1,1,Color.black));
        }
        else if(x%3 == 0)
        {
            this.setBorder(BorderFactory.createMatteBorder(3,1,1,1,Color.black));
        }
        else if(y%3 == 0)
        {
            this.setBorder(BorderFactory.createMatteBorder(1,3,1,1,Color.black));
        }
        else
        {
            this.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
        }

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                possibles[i][j] = new JLabel("",JLabel.CENTER);
                possibles[i][j].setHorizontalTextPosition(JLabel.CENTER);
                this.add(possibles[i][j]);
            }
        }

        values = sudoku.getSudoku().getPossiblesAt(y, x);

        this.repaint();
    }

    /**
     * Repaints the panel, getting the new possible values and placing them in.
     * @param g
     *
     * @see uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.sudoku.Sudoku#isOriginal(int, int) 
     */
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        values = sudoku.getSudoku().getPossiblesAt(y, x);

        if(values.length > 1)
        {
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    possibles[i][j].setText("<html><font size=1>"+values[(i*3)+j]+"</font></html>");
                }
            }
        }
        else
        {
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    possibles[i][j].setText("");
                }
            }

            //Conditional statement to set the correct colour for the text. Blue is original, red is a later added value.
            String colour = sudoku.getSudoku().isOriginal(x,y)?"blue":"red";
            possibles[1][1].setText("<html><font size=4 color="+colour+"><b>"+values[0]+"</b></color></html>");
        }
        //DEBUGGING
        //System.out.printf("Tile Repainted at (%d,%d)\n",x,y);
    }
}
