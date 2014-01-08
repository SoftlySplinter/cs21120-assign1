package uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.gui;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
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
    private SudokuFrame frame;
    private ControlListener listener;

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

    void step() {
        frame.step();
    }

    void run() {
        frame.runAlgorithm();
    }

    void stop() {
        frame.stop();
    }
}
