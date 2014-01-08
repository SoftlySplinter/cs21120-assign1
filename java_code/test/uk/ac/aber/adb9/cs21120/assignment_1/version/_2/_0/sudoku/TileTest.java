package uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.sudoku;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
/**
 * <DL>
 * <DT><b>Created:</b></DT>
 * <DD>Oct 18, 2010</DD>
 * </DL>
 * @version 1
 * @author Alexander Brown
 */
public class TileTest
{
    Tile t;

    public TileTest()
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
        t = new Tile(0,0);
    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void testConstructWithChar()
    {
        t = new Tile(0,0,'2');

        Assert.assertEquals('2', t.getValue());
    }

    @Test
    public void testConstruct()
    {
        Assert.assertEquals(new char[]{'1','2','3','4','5','6','7','8','9'}, t.getPossibles());
    }
/*
    @Test
    public void testRemove()
    {
        t.removePossibles(new char[]{'1','3','4','5','6','7','8','9'});

        Assert.assertEquals('2', t.getValue());
    }*/

    @Test
    public void testRemoveComplex()
    {
        t = new Tile(0,0);
        t.removePossibles(new char[]{'3','4','5','6','7','8','9'});


        char[] possibles = new char[2];
        int no = 0;

        for(char c:t.getPossibles())
        {
            System.out.println(c);
            if(c != ' ')
            {
                possibles[no] = c;
                no++;
            }
        }

        Assert.assertEquals('1',possibles[0]);
        Assert.assertEquals('2', possibles[1]);
    }
}