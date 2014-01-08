package uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;

/**
 * Gets action based on user interaction with the menu. Generally associated with file handlling in the GUI.
 * <DL>
 * <DT><b>Created:</b></DT>
 * <DD>Oct 18, 2010</DD>
 * </DL>
 * @version 1
 * @author Alexander Brown
 */
public class SudokuMenuListener
implements ActionListener
{
    /**
     * The link back to the menu.
     */
    private SudokuMenu menu;

    /**
     * Sets up the link back to the menu.
     * @param m The menu this is attached to.
     */
    public SudokuMenuListener(SudokuMenu m)
    {
        menu = m;
    }

    /**
     * Calls the action methods in the menu based on what action have been perfomed.
     * <ul>
     *   <li>Load - Loads a Sudoku from a JFileChooser.</li>
     *   <li>Exit - Quits the program</li>
     * </ul>
     * @param ae
     */
    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals("Load Sudoku"))
        {
            final JFileChooser fileChooser = new JFileChooser(new File("sudokus/").getAbsolutePath());
            int result = fileChooser.showOpenDialog(menu);
            if(result == JFileChooser.APPROVE_OPTION)
            {
                menu.loadSudoku(fileChooser.getSelectedFile());
            }
        }
        if(command.equals("Break After Every Change"))
        {
            menu.setSingleStep();
        }
        if(command.equals("Exit"))
        {
            System.exit(0);
        }
    }
}
