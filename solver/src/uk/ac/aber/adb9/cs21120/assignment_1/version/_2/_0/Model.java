package uk.ac.aber.adb9.cs21120.assignment_1.version._2._0;

import java.io.File;
import javax.swing.JOptionPane;
import uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.algorithm.Algorithm;
import uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.gui.SudokuFrame;
import uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.sudoku.Sudoku;

/**
 * This model interfaces the GUI, Algorithm and Sudoku elements. Allowing each to pass methods and variables between each part.
 * </p>
 *
 * <DL>
 * <DT><b>Created:</b></DT>
 * <DD>Oct 18, 2010</DD>
 * </DL>
 * @version 1
 * @author Alexander Brown
 */
public class Model
{
    /**
     * The default sudoku to use.
     */
    private static final File DEFAULT_SUDOKU = new File("sudokus/web.sud");

    /**
     * The current sudoku
     */
    private Sudoku sudoku;

    /**
     * The GUI.
     */
    private SudokuFrame gui;

    /**
     * The thread for the GUI.
     */
    private Thread guiThread;

    /**
     * The algorithm to run on the sudoku.
     */
    private Algorithm algorithm;

    /**
     * The thread for the algorithm.
     */
    private Thread algorithmThread;

    /**
     * Sets up certain elements of the Sudoku Solver.
     */
    public Model()
    {
        sudoku = new Sudoku(DEFAULT_SUDOKU);
        gui = new SudokuFrame(this);
        algorithm = new Algorithm(this);

        guiThread = new Thread(gui);
        guiThread.start();
    }

    /**
     * Loads a new sudoku from the input file.
     * @param input The file to load the sudoku from.
     */
    public void loadSudoku(File input)
    {
        sudoku = new Sudoku(input);
        algorithm = new Algorithm(this);
        gui.repaint();
        System.out.println(sudoku);
    }

    /**
     * Returns the current sudoku.
     * @return The current sudoku.
     */
    public Sudoku getSudoku()
    {
        return sudoku;
    }

    /**
     * Runs a step of the algorithm.
     */
    public void step()
    {
        if(algorithm.isFinished())
        {
            JOptionPane.showMessageDialog(gui,"Cannot take step; algorithm has finished.","Algorithm Finished",JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            algorithm.step();
            gui.repaint();
        }
    }

    /**
     * Runs the algorithm until it finishes, or stop is called.
     *
     * @see #stop()
     */
    public void run()
    {if(algorithm.isFinished())
        {
            JOptionPane.showMessageDialog(gui,"Cannot run; algorithm has finished.","Algorithm Finished",JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            algorithmThread = new Thread(algorithm);
            algorithmThread.start();
        }
    }

    /**
     * Stops the algorithm.
     * @see #run()
     */
    public void stop()
    {
        algorithmThread.interrupt();
    }

    /**
     * Updates GUI elements.
     */
    public void update()
    {
        gui.repaint();
    }

    /**
     * Called when the algorithm has finished.
     */
    public void giveFinishedDialog()
    {
        JOptionPane.showMessageDialog(gui,"Algorithm has finished.","Algorithm Finished",JOptionPane.WARNING_MESSAGE);
    }
}
