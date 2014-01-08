package uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import uk.ac.aber.adb9.java.file_handling.BasicFileReader;

/**
 * The data structure for a sudoku. Holds methods on getting information on the sudoku and manipulating the sudoku. The latter methods might have been better placed in the algorithm class, but for easy of access they are contained in this class instead.
 *
 * <DL>
 * <DT><b>Notes</b></DT>
 * <DD>The <tt>x</tt> and <tt>y</tt> values are typically to make it easier to write code logically (as x is usually a horizontal axis, and y the vertical). This is due to the nature of the 2D array which would normally have x as the vertical axis and y as the horizontal axis.</DD>
 * <DT><b>Created:</b></DT>
 * <DD>Oct 18, 2010</DD>
 * </DL>
 * @version 1
 * @author Alexander Brown
 *
 * @see Tile
 */
public class Sudoku
{
    /**
     * The size of the grid. (x or y size).
     */
    public static final int GRID_SIZE = 9;

    /**
     * The size of the sub grid (x or y).
     */
    public static final int SUB_GRID_SIZE = 3;

    /**
     * The sudoku data structure.
     * @see Tile
     */
    private Tile[][] sudoku = new Tile[GRID_SIZE][GRID_SIZE];
    private String name;

    /**
     * Loads a sudoku from a file.
     *
     * @see BasicFileReader
     * @see BasicFileReader#readLine()
     * @see File
     *
     * @param file The file from which to load from.
     */
    public Sudoku(File file)
    {
        try
        {
            BasicFileReader reader = new BasicFileReader(file);
            for(int i=0;i<GRID_SIZE;i++)
            {
                String line = reader.readLine();
                for(int j=0;j<GRID_SIZE;j++)
                {
                    char c = line.charAt(j);

                    if(c == ' ')
                    {
                        sudoku[i][j] = new Tile(i,j);
                    }
                    else
                    {
                        sudoku[i][j] = new Tile(i,j,c);
                    }
                }
            }

            name = file.getName();

            System.out.printf("Sudoku at '%s' loaded.\n",file.getAbsolutePath());
        }
        catch (FileNotFoundException ex)
        {
            System.err.printf("File \"%s\" was not found.\n",file.getAbsolutePath());
        }
        catch(IOException io)
        {
            System.err.printf("Error reading line.\n%s\n", io);
        }
    }

    public String name()
    {
        return name;
    }

    /**
     * @param x X coordinate of the tile to get the value at
     * @param y Y coordinate of the tile to get the value at
     * @return The value at (<tt>x</tt>,<tt>y</tt>)
     *
     * @see Tile#getValue() 
     */
    public char getValueAt(int x, int y)
    {
        return sudoku[y][x].getValue();
    }

    /**
     * Thes the value of (<tt>x</tt>,<tt>y</tt>) to be <tt>c</tt>.
     * @param x X coordinate of the tile to set the value of
     * @param y Y coordinate of the tile to set the value of
     * @param c The value to set
     *
     * @see Tile#setValue(char) 
     */
    public void setValueAt(int x, int y, char c)
    {
        sudoku[y][x].setValue(c);
    }

    /**
     *
     * @param x X coordinate of the tile to get the possible values of
     * @param y Y coordinate of the tile to get the possible values of
     * @return The possible values of (<tt>x</tt>,<tt>y</tt>)
     *
     * @see Tile#getPossibles() 
     */
    public char[] getPossiblesAt(int x, int y)
    {
        return sudoku[y][x].getPossibles();
    }

    /**
     * Removes an array of characters from the possible values at (<tt>x</tt>,<tt>y</tt>).
     * @param x X coordinate of the tile to remove the possible values of
     * @param y Y coordinate of the tile to remove the possible values of
     * @param c The character array to remove.
     * @return <tt>true</tt> - If value(s) have been removed.
     *
     * @see Tile#getPossibles()
     * @see Tile#setValue(char)
     * @see Tile#setPossibles(char[]) 
     */
    public boolean removePossiblesAt(int x, int y, char[] c)
    {
        boolean removed = false;
        char[] possibles = sudoku[y][x].getPossibles();

        if(sudoku[y][x].getValue()==' ')
        {
            for(int i=0;i<possibles.length;i++)
            {
                for(char p:c)
                {
                    if(possibles[i]!=' ' && possibles[i] == p)
                    {
                        possibles[i] = ' ';
                        removed = true;
                    }
                }
            }

            int no = 0;
            char val = ' ';

            for(char p:possibles)
            {
                if(p!=' ')
                {
                    no++;
                    val = p;
                }
            }

            if(no == 1)
            {
                sudoku[y][x].setValue(val);
            }
            else
            {
                sudoku[y][x].setPossibles(possibles);
            }
        }

        return removed;
    }

    /**
     * @param y The coordinate of the row to get.
     * @return The values of the row <tt>y</tt>.
     */
    public char[] getRow(int y)
    {
        char[] row = new char[9];

        for(int x=0;x<GRID_SIZE;x++)
        {
            row[x] = getValueAt(x,y);
        }

        return row;
    }

    /**
     * @param y The coordinate of the row to get.
     * @return The Tiles of the row <tt>y</tt>
     */
    private Tile[] getRowTiles(int y)
    {
        Tile[] row = new Tile[9];

        for(int x=0;x<GRID_SIZE;x++)
        {
            row[x] = sudoku[y][x];
        }

        return row;
    }

    /**
     * @param x The coordinate of the column to get.
     * @return The values of the column at <tt>x</tt>
     */
    public char[] getColumn(int x)
    {
        char[] column = new char[9];

        for(int y=0;y<GRID_SIZE;y++)
        {
            column[y] = getValueAt(x,y);
        }

        return column;
    }

    /**
     * @param x The coordinate of the column to get.
     * @return The Tiles of the column at <tt>c</yy>
     */
    private Tile[] getColumnTiles(int x)
    {
        Tile[] column = new Tile[9];

        for(int y=0;y<GRID_SIZE;y++)
        {
            column[y] = sudoku[y][x];
        }

        return column;
    }

    /**
     * @param x The X coordinate of the sub grid to get.
     * @param y The Y coordinate of the sub grid to get.
     * @return The values of the sub grid at(<tt>x</tt>,<tt>y</tt>)
     */
    public char[] getSubGrid(int x, int y)
    {
        char[] subGrid = new char[9];

        //Divide and times by the sub grid size to get the top left-hand tile.
        x = (x/SUB_GRID_SIZE)*SUB_GRID_SIZE;
        y = (y/SUB_GRID_SIZE)*SUB_GRID_SIZE;

        for(int i=x;i<(x/SUB_GRID_SIZE)*SUB_GRID_SIZE+SUB_GRID_SIZE;i++)
        {
            for(int j=y;j<(y/SUB_GRID_SIZE)*SUB_GRID_SIZE+SUB_GRID_SIZE;j++)
            {
                subGrid[((i-x)*3)+(j-y)]  = getValueAt(i,j);
            }
        }
        return subGrid;
    }

    /**
     * @param x The X coordinate of the sub grid to get.
     * @param y The Y coordinate of the sub grid to get.
     * @return The Tiles of the sub grid at(<tt>x</tt>,<tt>y</tt>)
     */
    private Tile[] getSubGridTiles(int x, int y)
    {
        Tile[] subGrid = new Tile[9];

        //Divide and times by the sub grid size to get the top left-hand tile.
        x = (x/SUB_GRID_SIZE)*SUB_GRID_SIZE;
        y = (y/SUB_GRID_SIZE)*SUB_GRID_SIZE;

        for(int i=x;i<(x/SUB_GRID_SIZE)*SUB_GRID_SIZE+SUB_GRID_SIZE;i++)
        {
            for(int j=y;j<(y/SUB_GRID_SIZE)*SUB_GRID_SIZE+SUB_GRID_SIZE;j++)
            {
                subGrid[((i-x)*3)+(j-y)]  = sudoku[j][i];
            }
        }
        return subGrid;
    }

    /**
     * @param x The X coordinate of the tile to get
     * @param y The Y coordinate of the tile to get
     * @return The tile at (<tt>x</tt>,<tt>y</tt>)
     */
    private Tile getTile(int x, int y)
    {
        return sudoku[y][x];
    }

    /**
     * @return The sudoku as a String.
     */
    @Override
    public String toString()
    {
        String su = "-------------------------------------\n";
        for(int i=0;i<GRID_SIZE;i++)
        {
            for(int j=0;j<GRID_SIZE;j++)
            {
                su += "| "+sudoku[i][j].getValue()+" ";
            }
            su+="|\n-------------------------------------\n";
        }

        return su;
    }

    /**
     * @param x The X coordinate of the Tile to check if the value is original.
     * @param y The Y coordinate of the Tile to check if the value is original.
     * @return <tt>true</tt> - If the value at (<tt>x</tt>,<tt>y</tt>) is original.
     *
     * @see Tile#isOriginal() 
     */
    public boolean isOriginal(int x, int y)
    {
        return sudoku[x][y].isOriginal();
    }

    public boolean findHidden(int x, int y)
    {
        return findHiddenInColumn(x,y) || findHiddenInRow(x,y) || findHiddenInSubGrid(x,y);
    }

    private boolean findHiddenInColumn(int x, int y)
    {
        Tile[] tiles =this.getColumnTiles(x);
        return getHiddenAt(x,y,tiles);
    }

    private boolean findHiddenInRow(int x, int y)
    {
        Tile[] tiles =this.getRowTiles(y);
        return getHiddenAt(x,y,tiles);
    }

    private boolean findHiddenInSubGrid(int x, int y)
    {
        Tile[] tiles =this.getSubGridTiles(x,y);
        return getHiddenAt(x,y,tiles);
    }

    private boolean getHiddenAt(int x, int y, Tile[] tiles)
    {
        char[] possibles = this.getPossiblesAt(x, y);

        if(possibles.length > 1)
        {
            char[] hidden = new char[9];
            int noHidden = 0;

            for(char p:possibles)
            {
                if(p!=' ')
                {
                    if(count(p,tiles) == 1)
                    {
                        hidden[noHidden] = p;
                        noHidden++;
                    }
                }
            }

            if(noHidden == 1)
            {
                this.setValueAt(x, y, hidden[0]);
                return true;
            }
        }
        return false;
    }

    private int count(char p, Tile[] tiles)
    {
        int count = 0;

        for(Tile t: tiles)
        {
            for(char c:t.getPossibles())
            {
                if(c == p)
                {
                    count++;
                }
            }
        }
        return count;
    }

    private int count(char p, char[] possibles)
    {
        int count = 0;
        {
            for(char c:possibles)
            {
                if(c == p)
                {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean findNakedDoubles(int x, int y)
    {
        return findNakedDoublesInColumn(x, y) || findNakedDoublesInRow(x, y) || findNakedDoubleInSubGrid(x, y);
    }

    public boolean findNakedDoublesInColumn(int x, int y)
    {
        Tile[] column = this.getColumnTiles(x);
        char[] possibles = this.getPossiblesAt(x, y);
        if(count(' ',possibles) == 7)
        {
            for(Tile t:column)
            {
                if(t.loction().getX() != y)
                {
                    char[] oppositeDouble = t.getPossibles();
                    boolean hasDouble = true;
                    for(int i=0;i<possibles.length && i<oppositeDouble.length;i++)
                    {
                        if(possibles[i] != oppositeDouble[i])
                        {
                            hasDouble = false;
                        }
                    }
                    if(hasDouble)
                        return removeDoubleFromColumn(x,y,t);
                }
            }
        }
        return false;
    }

    private boolean removeDoubleFromColumn(int x, int y, Tile secondTile)
    {
        char doubleOne = ' ';
        char doubleTwo = ' ';
        boolean changed = false;

        for(char c:secondTile.getPossibles())
        {
            if(c != ' ')
            {
                if(doubleOne == ' ')
                    doubleOne = c;
                else
                    doubleTwo = c;
            }
        }
        for(Tile t:this.getColumnTiles(x))
        {
            if(t.loction().getX()!=y && t!=secondTile && t.getPossibles().length>1)
            {
                if(t.removePossibles(new char[]{doubleOne,doubleTwo}))
                    changed = true;
            }
        }
        return changed;
    }

    public boolean findNakedDoublesInRow(int x, int y)
    {
        Tile[] row = this.getRowTiles(y);
        char[] possibles = this.getPossiblesAt(x, y);
        if(count(' ',possibles) == 7)
        {
            for(Tile t:row)
            {
                if(t.loction().getY() != x)
                {
                    char[] oppositeDouble = t.getPossibles();
                    boolean hasDouble = true;
                    for(int i=0;i<possibles.length && i<oppositeDouble.length;i++)
                    {
                        if(possibles[i] != oppositeDouble[i])
                        {
                            hasDouble = false;
                        }
                    }
                    if(hasDouble)
                        return removeDoubleFromRow(x,y,t);
                }
            }
        }
        return false;
    }

    private boolean removeDoubleFromRow(int x, int y, Tile secondTile)
    {
        char doubleOne = ' ';
        char doubleTwo = ' ';
        boolean changed = false;

        for(char c:secondTile.getPossibles())
        {
            if(c != ' ')
            {
                if(doubleOne == ' ')
                    doubleOne = c;
                else
                    doubleTwo = c;
            }
        }
        for(Tile t:this.getRowTiles(y))
        {
            if(t.loction().getY()!=x && t!=secondTile && t.getPossibles().length>1)
            {
                if(t.removePossibles(new char[]{doubleOne,doubleTwo}))
                    changed = true;
            }
        }
        return changed;
    }

    private boolean findNakedDoubleInSubGrid(int x, int y)
    {
        Tile[] subGrid = this.getSubGridTiles(x,y);
        char[] possibles = this.getPossiblesAt(x, y);
        if(count(' ',possibles) == 7)
        {
            for(Tile t:subGrid)
            {
                if(t.loction().getX() != y)
                {
                    char[] oppositeDouble = t.getPossibles();
                    boolean hasDouble = true;
                    for(int i=0;i<possibles.length && i<oppositeDouble.length;i++)
                    {
                        if(possibles[i] != oppositeDouble[i])
                        {
                            hasDouble = false;
                        }
                    }
                    if(hasDouble)
                        return removeDoubleFromSubGrid(x,y,t);
                }
            }
        }
        return false;
    }

    private boolean removeDoubleFromSubGrid(int x, int y, Tile secondTile)
    {
        char doubleOne = ' ';
        char doubleTwo = ' ';
        boolean changed = false;

        for(char c:secondTile.getPossibles())
        {
            if(c != ' ')
            {
                if(doubleOne == ' ')
                    doubleOne = c;
                else
                    doubleTwo = c;
            }
        }
        for(Tile t:this.getSubGridTiles(x, y))
        {
            if(t.loction().getY()!=x && t.loction().getY()!=y && t!=secondTile && t.getPossibles().length>1)
            {
                if(t.removePossibles(new char[]{doubleOne,doubleTwo}))
                    changed = true;
            }
        }
        return changed;
    }

    public boolean findNakedTriplets(int x, int y)
    {
        return getNakedTripletsInRow(x,y) || getNakedTripletsInColumn(x,y) || getNakedTripletsInSubGrid(x,y);
    }

    private boolean getNakedTripletsInRow(int x,int y)
    {
        Tile[] row = this.getRowTiles(y);
        char[] possibles = this.getPossiblesAt(x, y);
        char triplet1 = ' ';
        char triplet2 = ' ';
        char triplet3 = ' ';

        boolean hasTriplet1 = false;
        boolean hasTriplet2 = false;
        boolean hasTriplet3 = false;

        Tile tile = null;

        if(count(' ',possibles) == 7)
        {
            for(char p:possibles)
            {
                if(p!=' ')
                {
                    if(triplet1 != ' ')
                        triplet1 = p;
                    else
                        triplet2 = p;
                }
            }

            for(Tile t:row)
            {
                if(t.loction().getY() != x)
                {
                    char[] opposite = t.getPossibles();
                    if(count(' ', opposite) == 7)
                    {
                        if(triplet3 == ' ')
                        {
                            for(int i=0;i<opposite.length && i<possibles.length;i++)
                            {
                                if(hasTriplet1 || hasTriplet2)
                                    if(opposite[i] != ' ')
                                    {
                                        triplet3 = opposite[i];
                                        tile = t;
                                    }

                                else if(opposite[i] == triplet1)
                                    hasTriplet1 = true;

                                else if(opposite[i] == triplet2)
                                    hasTriplet2 = true;
                            }
                        }
                        else
                        {
                            for(int i=0;i<opposite.length && i<opposite.length;i++)
                            {
                                if(hasTriplet1)
                                {

                                }
                                else if(hasTriplet2)
                                {
                                    if(opposite[i] == triplet2)
                                        hasTriplet2 = true;
                                }
                                else if(hasTriplet3)
                                {
                                    if(opposite[i] == triplet3)
                                        hasTriplet3 = true;
                                }
                            }
                            if(hasTriplet1 && hasTriplet2 && hasTriplet3)
                                return removeTripletFromRow(x,y,tile,t,triplet1,triplet2,triplet3);
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean getNakedTripletsInColumn(int x,int y)
    {
        Tile[] column = this.getColumnTiles(x);
        char[] possibles = this.getPossiblesAt(x, y);
        char triplet1 = ' ';
        char triplet2 = ' ';
        char triplet3 = ' ';

        boolean hasTriplet1 = false;
        boolean hasTriplet2 = false;
        boolean hasTriplet3 = false;

        Tile tile = null;

        if(count(' ',possibles) == 7)
        {
            for(char p:possibles)
            {
                if(p!=' ')
                {
                    if(triplet1 != ' ')
                        triplet1 = p;
                    else
                        triplet2 = p;
                }
            }

            for(Tile t:column)
            {
                if(t.loction().getX() != y)
                {
                    char[] opposite = t.getPossibles();
                    if(count(' ', opposite) == 7)
                    {
                        if(triplet3 == ' ')
                        {
                            for(int i=0;i<opposite.length && i<possibles.length;i++)
                            {
                                if(hasTriplet1 || hasTriplet2)
                                    if(opposite[i] != ' ')
                                    {
                                        triplet3 = opposite[i];
                                        tile = t;
                                    }

                                else if(opposite[i] == triplet1)
                                    hasTriplet1 = true;

                                else if(opposite[i] == triplet2)
                                    hasTriplet2 = true;
                            }
                        }
                        else
                        {
                            for(int i=0;i<opposite.length && i<opposite.length;i++)
                            {
                                if(hasTriplet1)
                                {

                                }
                                else if(hasTriplet2)
                                {
                                    if(opposite[i] == triplet2)
                                        hasTriplet2 = true;
                                }
                                else if(hasTriplet3)
                                {
                                    if(opposite[i] == triplet3)
                                        hasTriplet3 = true;
                                }
                            }
                            if(hasTriplet1 && hasTriplet2 && hasTriplet3)
                                return removeTripletFromColumn(x,y,tile,t,triplet1,triplet2,triplet3);
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean getNakedTripletsInSubGrid(int x,int y)
    {
        Tile[] subGrid = this.getSubGridTiles(x,y);
        char[] possibles = this.getPossiblesAt(x, y);
        char triplet1 = ' ';
        char triplet2 = ' ';
        char triplet3 = ' ';

        boolean hasTriplet1 = false;
        boolean hasTriplet2 = false;
        boolean hasTriplet3 = false;

        Tile tile = null;

        if(count(' ',possibles) == 7)
        {
            for(char p:possibles)
            {
                if(p!=' ')
                {
                    if(triplet1 != ' ')
                        triplet1 = p;
                    else
                        triplet2 = p;
                }
            }

            for(Tile t:subGrid)
            {
                if(t.loction().getX() != y)
                {
                    char[] opposite = t.getPossibles();
                    if(count(' ', opposite) == 7)
                    {
                        if(triplet3 == ' ')
                        {
                            for(int i=0;i<opposite.length && i<possibles.length;i++)
                            {
                                if(hasTriplet1 || hasTriplet2)
                                    if(opposite[i] != ' ')
                                    {
                                        triplet3 = opposite[i];
                                        tile = t;
                                    }

                                else if(opposite[i] == triplet1)
                                    hasTriplet1 = true;

                                else if(opposite[i] == triplet2)
                                    hasTriplet2 = true;
                            }
                        }
                        else
                        {
                            for(int i=0;i<opposite.length && i<opposite.length;i++)
                            {
                                if(hasTriplet1)
                                {

                                }
                                else if(hasTriplet2)
                                {
                                    if(opposite[i] == triplet2)
                                        hasTriplet2 = true;
                                }
                                else if(hasTriplet3)
                                {
                                    if(opposite[i] == triplet3)
                                        hasTriplet3 = true;
                                }
                            }
                            if(hasTriplet1 && hasTriplet2 && hasTriplet3)
                                return removeTripletFromSubGrid(x,y,tile,t,triplet1,triplet2,triplet3);
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean removeTripletFromColumn(int x, int y, Tile tile1, Tile tile2, char triplet1, char triplet2, char triplet3)
    {
        boolean changed = false;

        for(Tile t:getColumnTiles(x))
        {
            if(getTile(x,y) != t && tile1 != t && tile2 != t)
            {
                if(t.removePossibles(new char[]{triplet1,triplet2,triplet3}))
                    changed = true;
            }
        }

        return changed;
    }

    private boolean removeTripletFromRow(int x, int y, Tile tile1, Tile tile2, char triplet1, char triplet2, char triplet3)
    {
        boolean changed = false;

        for(Tile t:getRowTiles(y))
        {
            if(getTile(x,y) != t && tile1 != t && tile2 != t)
            {
                if(t.removePossibles(new char[]{triplet1,triplet2,triplet3}))
                    changed = true;
            }
        }

        return changed;
    }

    private boolean removeTripletFromSubGrid(int x, int y, Tile tile1, Tile tile2, char triplet1, char triplet2, char triplet3)
    {
        boolean changed = false;

        for(Tile t:getSubGridTiles(x, y))
        {
            if(getTile(x,y) != t && tile1 != t && tile2 != t)
            {
                if(t.removePossibles(new char[]{triplet1,triplet2,triplet3}))
                    changed = true;
            }
        }

        return changed;
    }

    public boolean findHiddenDoubles(int x, int y)
    {
        return getHiddenDoublesInColumn(x,y) || getHiddenDoublesInRow(x,y) || getHiddenDoublesInSubGrid(x,y);
    }

    private boolean getHiddenDoublesInColumn(int x, int y)
    {
        Tile original = getTile(x,y);

        char[] possibles = original.getPossibles();

        if(count(' ',possibles) == 7)
        {
            Tile[] column = getColumnTiles(x);

            int count = 0;
            boolean hasHiddenDouble = true;
            Tile otherTile = null;

            for(Tile t:column)
            {
                char[] other = t.getPossibles();
                if(other.length>1)
                    hasHiddenDouble = contains(possibles,other);

                if(hasHiddenDouble)
                {
                    otherTile = t;
                    count++;
                }
            }

            if(count == 1)
            {
                otherTile.setPossibles(possibles);
                return this.findNakedDoublesInColumn(x, y);
            }
        }

        return false;
    }

    private boolean getHiddenDoublesInRow(int x, int y)
    {
        Tile original = getTile(x,y);

        char[] possibles = original.getPossibles();

        if(count(' ',possibles) == 7)
        {
            Tile[] column = getRowTiles(y);

            int count = 0;
            boolean hasHiddenDouble = true;
            Tile otherTile = null;

            for(Tile t:column)
            {
                char[] other = t.getPossibles();
                if(other.length>1)
                    hasHiddenDouble = contains(possibles,other);

                if(hasHiddenDouble)
                {
                    otherTile = t;
                    count++;
                }
            }

            if(count == 1)
            {
                otherTile.setPossibles(possibles);
                return this.findNakedDoublesInRow(x, y);
            }
        }

        return false;
    }

    private boolean getHiddenDoublesInSubGrid(int x, int y)
    {
        Tile original = getTile(x,y);

        char[] possibles = original.getPossibles();

        if(count(' ',possibles) == 7)
        {
            Tile[] column = getSubGridTiles(x, y);

            int count = 0;
            boolean hasHiddenDouble = true;
            Tile otherTile = null;

            for(Tile t:column)
            {
                char[] other = t.getPossibles();
                if(other.length>1)
                    hasHiddenDouble = contains(possibles,other);

                if(hasHiddenDouble)
                {
                    otherTile = t;
                    count++;
                }
            }

            if(count == 1)
            {
                otherTile.setPossibles(possibles);
                return this.findNakedDoubleInSubGrid(x, y);
            }
        }

        return false;
    }

    private boolean contains(char[] values, char[] array)
    {
        for(int i=0;i<values.length && i<array.length;i++)
        {
            if(values[i] != ' ')
                if(values[i] != array[i])
                    return false;
        }

        return true;
    }
}
