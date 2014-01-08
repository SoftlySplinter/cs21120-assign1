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
 * <DD>Oct 26, 2010 - Added an algorithm for obtaining Naked Triplets and Hidden Doubles</DD>
 * <DD>Oct 30, 2010 - Added an algorithm for obaining X Wings</DD>
 * <DD>Oct 30, 2010 - Added option which allows the user to change if they want a step to be a single change or a single pass.</DD>
 * </DL>
 * @version 5
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
     * If <tt>true</tt> tells the algorithm to break every time a change is made.
     */
    private boolean singleStep = false;

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

        if(!changed)
            getXWings();

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
        long sleepTime = 50;

        if(!singleStep)
            sleepTime*=10;

        try
        {
            while(!finished)
            {

                Thread.sleep(sleepTime);
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

                if(changed && singleStep)
                    return;
            }
        }
    }

    /**
     * Based on a similar principal Naked Singles but instead gets values which cannot be anywhere else in a row, column or sub grind.
     * @see Sudoku#findHidden(int, int) 
     */
    private void getHiddenSingles()
    {
        for(int x=0;x<Sudoku.GRID_SIZE;x++)
        {
            for(int y=0;y<Sudoku.GRID_SIZE;y++)
            {
                if(sudoku.findHidden(x,y))
                    changed = true;

                if(changed && singleStep)
                    return;
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

                if(changed && singleStep)
                    return;
            }
        }
    }

    /**
     * Triplets are based on the same principal as Doubles only, as the name suggest, there are three values instead of two.
     */
    private void getNakedTriplets()
    {
        for(int x=0;x<Sudoku.GRID_SIZE;x++)
            for(int y=0;y<Sudoku.GRID_SIZE;y++)
                if(sudoku.findNakedTriplets(x, y))
                    changed = true;

                if(changed && singleStep)
                    return;
    }

    /**
     * Hidden doubles are doubles that have other values around then, but for which can only be the other place for the double values, thereby allowing the algorithm to cancle out these other values and then use the doubles like normal doubles.
     */
    private void getHiddenDoubles()
    {
        for(int x=0;x<Sudoku.GRID_SIZE;x++)
            for(int y=0;y<Sudoku.GRID_SIZE;y++)
                if(sudoku.findHiddenDoubles(x, y))
                    changed = true;

                if(changed && singleStep)
                    return;
    }

    /**
     * An X Wing conﬁguration is one where the same value appears exactly twice in two rows and also appears in the same columns of these two rows (or exactly twice in two columns and appearing in the same rows of these columns).
     * You can then use this to remove any other possibles of this value in the rest of the columns (or rows).
     * <p>
     * To simplify - the values make a square or rectangle and connecting the values would create an X shape (hence the name). Since one pair of two corners must contain the value, no other tiles in the rows or columns, except the tiles at the corners, must contain the value.
     * 
     */
    private void getXWings()
    {
        for(int x=0;x<Sudoku.GRID_SIZE;x++)
            for(int y=0;y<Sudoku.GRID_SIZE;y++)
                if(sudoku.findXWings(x, y))
                    changed = true;

                if(changed && singleStep)
                    return;
    }

    public void setSingleStep(boolean s)
    {
        System.out.println("Changing single step...");
        singleStep = s;
    }

    public boolean getSingleStep()
    {
        return singleStep;
    }
}