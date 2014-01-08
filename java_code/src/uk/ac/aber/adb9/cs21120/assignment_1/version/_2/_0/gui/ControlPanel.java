package uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.gui;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * The controller for the Sudoku Solver. Passes actions up to the Model via the frame based on user input.
 * <DL>
 * <DT><b>Created:</b></DT>
 * <DD>Oct 19, 2010</DD>
 * </DL>
 * @version 1
 * @author Alexander Brown
 */
public class ControlPanel
extends JPanel
{
    /**
     * The frame this is attached to.
     */
    private SudokuFrame frame;

    /**
     * The listener to get use action from.
     */
    private ControlListener listener;

    /**
     * Constructor setting up the link back to the frame and the majority of the panel.
     * @param f
     */
    public ControlPanel(SudokuFrame f)
    {
        listener = new ControlListener(this);
        frame = f;

        JButton step = new JButton("Step");
        JButton run = new JButton("Run");
        JButton stop = new JButton("Stop");

        this.add(step);
        step.addActionListener(listener);
        this.add(run);
        run.addActionListener(listener);
        this.add(stop);
        stop.addActionListener(listener);
    }

    /**
     * Passes the step action up to the model via the frame.
     */
    void step()
    {
        frame.step();
    }

    /**
     * Passes the run action up to the model via the frame.
     */
    void run() {
        frame.runAlgorithm();
    }

    /**
     * Passes the stop action up to the model via the frame.
     */
    void stop() {
        frame.stop();
    }
}
