package uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.sudoku;

import java.io.File;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * <DL>
 * <DT><b>Created:</b></DT>
 * <DD>Oct 18, 2010</DD>
 * </DL>
 * @version 1
 * @author Alexander Brown
 */
public class SudokuTest
{
    Sudoku s;

    public SudokuTest()
    {

    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }

    @Before
    public void setUp() throws Exception
    {
        s = new Sudoku(new File("sudokus/web.sud"));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetRow()
    {
        char[] row = s.getRow(2);

        Assert.assertEquals('2', row[0]);
    }

    @Test
    public void testGetColumn()
    {
//        char[] column = s.getColumn(1);

//        Assert.assertEquals('4', column[8]);
    }

    @Test
    public void testGetSubGrid()
    {
        char[] sub = s.getSubGrid(2, 4);

        Assert.assertEquals('8', sub[0]);
    }

    @Test
    public void removeValues()
    {
        s.removePossiblesAt(0, 0, new char[]{'2','3','4','5','6','7','8','9'});
        Assert.assertEquals('1', s.getValueAt(0, 0));
    }

}