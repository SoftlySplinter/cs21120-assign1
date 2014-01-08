package uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.sudoku;

/**
 * <DL>
 * <DT><b>Created:</b></DT>
 * <DD>Oct 18, 2010</DD>
 * </DL>
 * @version 1
 * @author Alexander Brown
 */
class SudokuCoordinate
{
    private int x;
    private int y;

    SudokuCoordinate(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    int getX()
    {
        return x;
    }

    int getY()
    {
        return y;
    }
}
