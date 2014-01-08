package uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.algorithm;

import uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.Model;
import uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.sudoku.Sudoku;

/**
 * The algorithm which attemptes to solve the Sudoku.
 * <DL>
 * <DT><b>Created:</b></DT>
 * <DD>Oct 19, 2010</DD>
 * <DT><b>Revisions</b></DT>
 * <DD>Oct 21, 2010 - Added an algorithm for obtaining Hidden Singles.</DD>
 * <DD>Oct 24, 2010 - Added an algorithm for obtaining Naked Doubles.</DD>
 * </DL>
 * @version 3
 * @author Alexander Brown
 */
public class Algorithm
implements Runnable
{
    /**
     * The model controlling this algorithm.
     */
    private Model model;

    /**
     * The Sudoku to solve.
     */
    private Sudoku sudoku;

    /**
     * <tt>true</tt> - If the algorithm is finished.
     */
    private boolean finished;

    /**
     * <tt>true</tt> - If the algorithm made a change in the last step.
     */
    private boolean changed;

    /**
     * Sets up the algorithm.
     * @param m The Model to control this algorithm.
     */
    public Algorithm(Model m)
    {
        model = m;
        sudoku = model.getSudoku();
        finished = false;
        changed = false;
    }

    /**
     * Returns {@link #finished}
     * @return {@link #finished}
     */
    public boolean isFinished()
    {
        return finished;
    }

    /**
     * Takes a step of the algorithm.
     *
     * @see #getNakedSingles()
     * @see #getHiddenSingles()
     * @see #getNakedDoubles() 
     */
    public void step()
    {
        changed = false;
        
        getNakedSingles();

        if(!changed)
            getHiddenSingles();

        if(!changed)
            getNakedDoubles();

        if(!changed)
            getNakedTriplets();

        if(!changed)
            getHiddenDoubles();

        model.update();

        finished = !changed;
    }

    /**
     * Loops steps until the sudoku is solved. Runs as a thread.
     *
     * @see #step() 
     * @see Model#giveFinishedDialog() 
     */
    public void run()
    {
        try
        {
            while(!finished)
            {
                Thread.sleep(1000);
                step();
            }
            model.giveFinishedDialog();
        }
        catch (InterruptedException ex)
        {
            System.out.println("Stopped");
        }
    }

    /**
     * Removes possibles from the sudoku grid, then finds if a single value remains. If there is it places it as the actual value.
     * @see Sudoku#removePossiblesAt(int, int, char[]) 
     */
    private void getNakedSingles()
    {
        //System.out.println("Getting Naked Singles");
        boolean temp = false;

        for(int x=0;x<Sudoku.GRID_SIZE;x++)
        {
            for(int y=0;y<Sudoku.GRID_SIZE;y++)
            {
                char[] column = sudoku.getColumn(x);
                char[] row = sudoku.getRow(y);
                char[] subGrid = sudoku.getSubGrid(x, y);

                temp = sudoku.removePossiblesAt(x, y, column);
                if(temp)
                    changed = temp;

                temp = sudoku.removePossiblesAt(x, y, row);
                if(temp)
                    changed = temp;

                temp = sudoku.removePossiblesAt(x, y, subGrid);
                if(temp)
                    changed = temp;
            }
        }
    }

    /**
     * Based on a similar principal Naked Singles but instead gets values which cannot be anywhere else in a row, column or sub grind.
     * @see Sudoku#findHidden(int, int) 
     */
    private void getHiddenSingles()
    {
        //System.out.println("Getting Hidden Singles");
        for(int x=0;x<Sudoku.GRID_SIZE;x++)
        {
            for(int y=0;y<Sudoku.GRID_SIZE;y++)
            {
                if(sudoku.findHidden(x,y))
                    changed = true;
            }
        }
    }

    /**
     * Removes values from a double - two sets of the same two values which cannot go anywhere else a row or column - along a whole row or column.
     * @see Sudoku#findNakedDoubles(int, int)
     */
    private void getNakedDoubles()
    {
        for(int x=0;x<Sudoku.GRID_SIZE;x++)
        {
            for(int y=0;y<Sudoku.GRID_SIZE;y++)
            {
                if(sudoku.findNakedDoubles(x,y))
                    changed = true;
            }
        }
    }

    private void getNakedTriplets()
    {
        for(int x=0;x<Sudoku.GRID_SIZE;x++)
            for(int y=0;y<Sudoku.GRID_SIZE;y++)
                if(sudoku.findNakedTriplets(x, y))
                    changed = true;
    }

    private void getHiddenDoubles()
    {
        for(int x=0;x<Sudoku.GRID_SIZE;x++)
            for(int y=0;y<Sudoku.GRID_SIZE;y++)
                if(sudoku.findHiddenDoubles(x, y))
                    changed = true;
    }
}