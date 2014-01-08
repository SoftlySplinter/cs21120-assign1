package uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.sudoku;

/**
 * Holds information on a single tile of a sudoku.
 * <DL>
 * <DT><b>Created:</b></DT>
 * <DD>Oct 18, 2010</DD>
 * </DL>
 * @version 1
 * @author Alexander Brown
 */
class Tile
{
    /**
     * The value of this tile
     */
    private char value;

    /**
     * The possible values of this tile
     */
    private char[] possibleValues = new char[9];

    /**
     * The coordinates of the sudoku.
     */
    private SudokuCoordinate coordinate;

    /**
     * <tt>true</tt> if the value of the tile is the one input from a file and not by the algorithm.
     */
    boolean original;

    /**
     * Full constructor taking all possible inputs
     *
     * @param x The X coordinate of this tile.
     * @param y The Y coordinate of this tile.
     * @param value The value of this tile.
     * @param possibleValues The possible values of this tile.
     * @param isOriginal If the tile is original.
     */
    Tile(int x, int y, char value, char[] possibleValues, boolean isOriginal)
    {
        coordinate = new SudokuCoordinate(x,y);
        this.value = value;
        this.possibleValues = possibleValues;
        this.original = isOriginal;
    }

    /**
     * Constructor for a value given by the file.
     * @param x The X coordinate of this tile.
     * @param y The Y coordinate of this tile.
     * @param value The value of this tile.
     */
    Tile(int x, int y, char value)
    {
        this(x,y,value,new char[]{value}, true);
    }

    /**
     * Constructor for a tile without a given value, setting possibles to 1-9.
     * @param x The X coordinate of this tile.
     * @param y The Y coordinate of this tile.
     */
    Tile(int x, int y)
    {
        this(x,y,' ',new char[]{'1','2','3','4','5','6','7','8','9'}, false);
    }

    /**
     * Returns the value of this tile.
     * @return The value of this tile.
     */
    char getValue()
    {
        return value;
    }

    /**
     * Sets the value of this tile.
     * @param c The value to set this tile to.
     */
    void setValue(char c)
    {
        value = c;
        possibleValues = new char[]{c};
    }

    /**
     * Returns all the possible values
     * @return All the possible values.
     */
    char[] getPossibles()
    {
        return possibleValues;
    }

    /**
     * Removes possibles from this tile.
     * @param array The values to remove from this tile.
     * @return <tt>true</tt> - If any values were removed.
     */
    boolean removePossibles(char[] array)
    {
        boolean removed = false;
        
        if(possibleValues.length > 1);
        {
            for(int i=0;i<possibleValues.length;i++)
            {
                if(' ' != possibleValues[i])
                {
                    for(char c:array)
                    {
                        if(c==possibleValues[i])
                        {
                            possibleValues[i] = ' ';
                            removed = true;
                        }
                    }
                }
            }

            checkHasSinglePossible();
        }
        return removed;
    }

    /**
     * Checks to see if the tile has a single possible value.
     */
    private void checkHasSinglePossible()
    {
        char value = ' ';
        int noPossibles = 0;
        for(int i=0;i<possibleValues.length;i++)
        {
            if(possibleValues[i] != ' ')
            {
                value = possibleValues[i];
                noPossibles++;
            }
        }

        if(noPossibles == 1)
        {
            this.value = value;
            this.possibleValues = new char[]{this.value};
        }
    }

    /**
     * Sets the possibles of this tile.
     * @param possibles The values of the possibles.
     */
    void setPossibles(char[] possibles)
    {
        this.possibleValues = possibles;
    }

    /**
     * Returns <tt>true</tt> if the value of this tile is original.
     * @return <tt>true</tt> - If the value of this tile is original.
     *
     * @see #original
     */
    boolean isOriginal()
    {
        return original;
    }

    /**
     * Returns the coordinates of this tile.
     * @return The coordinates of this tile.
     */
    SudokuCoordinate loction()
    {
        return coordinate;
    }
}
