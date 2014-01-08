package uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.sudoku;

/**
 * <DL>
 * <DT><b>Created:</b></DT>
 * <DD>Oct 18, 2010</DD>
 * </DL>
 * @version 1
 * @author Alexander Brown
 */
class Tile
{
    //public static final char[] DEFAULT_POSSIBLE = new char[]{'1','2','3','4','5','6','7','8','9'};
    private char value;

    private char[] possibleValues = new char[9];

    private SudokuCoordinate coordinate;

    boolean original;

    Tile(int x, int y, char value, char[] possibleValues, boolean isOriginal)
    {
        coordinate = new SudokuCoordinate(x,y);
        this.value = value;
        this.possibleValues = possibleValues;
        this.original = isOriginal;
    }

    Tile(int x, int y, char value)
    {
        this(x,y,value,new char[]{value}, true);
    }

    Tile(int x, int y)
    {
        this(x,y,' ',new char[]{'1','2','3','4','5','6','7','8','9'}, false);
    }

    char getValue()
    {
        return value;
    }

    void setValue(char c)
    {
        value = c;
        possibleValues = new char[]{c};
    }

    char[] getPossibles()
    {
        return possibleValues;
    }

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
                            //System.out.print(c);
                            possibleValues[i] = ' ';
                            removed = true;
                        }
                    }
                    //System.out.println();
                }
            }

            checkHasSinglePossible();
        }
        return removed;
    }

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

    void setPossibles(char[] possibles)
    {
        this.possibleValues = possibles;
    }

    boolean isOriginal()
    {
        return original;
    }

    SudokuCoordinate loction()
    {
        return coordinate;
    }
}
