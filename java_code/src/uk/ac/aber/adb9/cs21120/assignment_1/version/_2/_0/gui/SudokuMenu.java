package uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

/**
 * Creates and controls the menu (and all it's elements) for the GUI.
 *
 * <DL>
 * <DT><b>Created:</b></DT>
 * <DD>Oct 18, 2010</DD>
 * <DT><b>Revisions</b></DT>
 * <DD>Oct 30, 2010 - Added option which allows the user to change if they want a step to be a single change or a single pass.</DD>
 * </DL>
 * @version 2
 * @author Alexander Brown
 */
public class SudokuMenu
extends JMenuBar
{
    /**
     * The frame this is attached to.
     */
    private SudokuFrame frame;

    /**
     * The sudoku file to call load methods using.
     */
    private JMenu file = new JMenu("File");

    private JRadioButtonMenuItem singleStep;

    /**
     * The listener for this menu.
     */
    SudokuMenuListener listener = new SudokuMenuListener(this);

    /**
     * Sets up the menu and sets the link back to the frame.
     * @param frame The frame this menu is linked to.
     */
    public SudokuMenu(SudokuFrame frame)
    {
        this.frame = frame;

        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem load = new JMenuItem("Load Sudoku");
        singleStep = new JRadioButtonMenuItem("Break After Every Change",false);
        JMenuItem exit = new JMenuItem("Exit");

        load.addActionListener(listener);
        singleStep.addActionListener(listener);
        exit.addActionListener(listener);

        file.add(load);
        file.add(singleStep);
        file.addSeparator();
        file.add(exit);

        this.add(file);
    }

    /**
     * Passes the load sudoku method to the model via the frame.
     * @param file The File to load.
     */
    void loadSudoku(File file)
    {
        frame.loadSudoku(file);
        setSingleStep();
    }

    void setSingleStep()
    {
        frame.getAlgorithm().setSingleStep(singleStep.isSelected());
    }
}
