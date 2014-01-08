package uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.gui;

import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * <DL>
 * <DT><b>Created:</b></DT>
 * <DD>Oct 18, 2010</DD>
 * </DL>
 * @version 1
 * @author Alexander Brown
 */
public class SudokuMenu
extends JMenuBar
{
    private SudokuFrame frame;
    private JMenu file = new JMenu("File");
    SudokuMenuListener listener = new SudokuMenuListener(this);

    public SudokuMenu(SudokuFrame frame)
    {
        this.frame = frame;

        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem load = new JMenuItem("Load Sudoku");
        JMenuItem exit = new JMenuItem("Exit");
        load.addActionListener(listener);
        exit.addActionListener(listener);
        file.add(load);
        file.addSeparator();
        file.add(exit);

        this.add(file);
    }

    void loadSudoku(File file)
    {
        frame.loadSudoku(file);
    }
}
