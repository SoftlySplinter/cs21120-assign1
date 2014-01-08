package uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.gui;

//awt/swing imports
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

//IO imports
import java.io.File;

//Project imports.
import uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.Model;
import uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.sudoku.Sudoku;

/**
 * Creates and controls the window for the GUI.
 * <DL>
 * <DT><b>Created:</b></DT>
 * <DD>Oct 18, 2010</DD>
 * </DL>
 * @version 1
 * @author Alexander Brown
 *
 *
 */
public class SudokuFrame
extends JFrame
implements Runnable
{
    /**
     * The Model this is controlled by.
     */
    private Model model;

    /**
     * The Menu of the window/
     */
    private SudokuMenu menu;

    /**
     * The panel for the Sudoku.
     */
    private SudokuPanel panel;

    /**
     * The controller.
     */
    private ControlPanel controlPanel;
    
    /**
     * Contains a little information about the sudoku.
     */
    private JLabel sudokuInfo;

    /**
     * Constructs the window, creating and adding elements and setting some of the basic behaviour.
     * @param model The Model which controls this GUI.
     */
    public SudokuFrame(Model model)
    {
        this.menu = new SudokuMenu(this);
        this.model = model;
        this.panel = new SudokuPanel(this);
        this.controlPanel = new ControlPanel(this);
        this.sudokuInfo = new JLabel("<html><h3>Current Sudoku: "+model.getSudoku().name()+"</h3></html>");
        sudokuInfo.setHorizontalAlignment(JLabel.CENTER);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setMinimumSize(new Dimension(500,600));
        this.setResizable(false);
        this.setTitle("Sudoku Solver by Alexander Brown");
        this.setIconImage(new ImageIcon("icon.png").getImage());

        this.setJMenuBar(menu);
        this.add(panel,BorderLayout.CENTER);
        this.add(controlPanel,BorderLayout.SOUTH);
        this.add(sudokuInfo,BorderLayout.NORTH);
    }

    /**
     * Loads a new Sudoku.
     * @param input The File from which to load the sudoku from
     *
     * @see Model#loadSudoku(File)
     */
    public void loadSudoku(File input)
    {
        model.loadSudoku(input);
        sudokuInfo.setText("<html><h3>Current Sudoku: "+input.getName()+"</h3></html>");
    }

    /**
     * Runs the thread.
     */
    public void run()
    {
        this.setVisible(true);
    }

    /**
     * @return The sudoku held by the model.
     * @see Model#getSudoku()
     */
    Sudoku getSudoku()
    {
        return model.getSudoku();
    }

    /**
     * Paints the components of this window.
     * @param g
     */
    @Override
    public void paintComponents(Graphics g)
    {
        super.paintComponents(g);

        panel.repaint();
    }

    /**
     * Alerts the model that a step should be taken.
     * @see Model#step()
     */
    void step()
    {
        model.step();
    }

    /**
     * Alerts the model that the algorithm should be run.
     * @see Model#run()
     */
    void runAlgorithm()
    {
        model.run();
    }

    /**
     * Alerts the model that the algorithm should be stopped.
     * @see Model#stop() 
     */
    void stop()
    {
        model.stop();
    }
}
