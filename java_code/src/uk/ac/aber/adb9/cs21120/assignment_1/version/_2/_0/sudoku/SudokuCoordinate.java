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
    /**
     * The X coordinate of a tile.
     */
    private int x;

    /**
     * The Y coordinate of a tile.
     */
    private int y;

    /**
     * Creates a new coordinate for a tile.
     * @param x The X coordinate.
     * @param y The Y coordinate.
     */
    SudokuCoordinate(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the X coordinate.
     * @return The X coordinate.
     */
    int getX()
    {
        return x;
    }

    /**
     * Returns the Y coordinate.
     * @return The Y coordinate.
     */
    int getY()
    {
        return y;
    }
}
