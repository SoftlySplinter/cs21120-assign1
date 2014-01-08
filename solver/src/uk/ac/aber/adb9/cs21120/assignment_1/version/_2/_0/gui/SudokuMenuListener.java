package uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

/**
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
    private SudokuMenu menu;

    public SudokuMenuListener(SudokuMenu m)
    {
        menu = m;
    }

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
        if(command.equals("Exit"))
        {
            System.exit(0);
        }
    }
}
