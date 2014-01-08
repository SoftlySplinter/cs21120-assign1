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

    /**
     * The name of the sudoku.
     */
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

    /**
     * Returns the name of the sudoku file.
     * @return The name of the sudoku file.
     */
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
     * Returns the values of the row <tt>y</tt>.
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
     * Returns the Tiles of the row <tt>y</tt>
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
     * Returns the values of the column at <tt>x</tt>
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
     * Returns the Tiles of the column at <tt>c</yy>
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
     * Returns the values of the sub grid at(<tt>x</tt>,<tt>y</tt>)
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
     * Returns the Tiles of the sub grid at(<tt>x</tt>,<tt>y</tt>)
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
     * Returns the tile at (<tt>x</tt>,<tt>y</tt>)
     * @param x The X coordinate of the tile to get
     * @param y The Y coordinate of the tile to get
     * @return The tile at (<tt>x</tt>,<tt>y</tt>)
     */
    private Tile getTile(int x, int y)
    {
        return sudoku[y][x];
    }

    /**
     * Return the sudoku as a String.
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
     * Returns <tt>true</tt> If the value at (<tt>x</tt>,<tt>y</tt>) is original.
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

    /**
     * Returns <tt>true</tt> if (<tt>x</tt>, <tt>y</tt>) has a hidden single and it was removed.
     * @param x The X coordinate of the tile to find hidden singles for.
     * @param y The Y coordinate of the tile to find hidden singles for.
     * @return <tt>true</tt> -  if (<tt>x</tt>, <tt>y</tt>) has a hidden single and it was removed.
     */
    public boolean findHidden(int x, int y)
    {
        return findHiddenInColumn(x,y) || findHiddenInRow(x,y) || findHiddenInSubGrid(x,y);
    }

    /**
     * Looks for a hidden single in a column.
     *
     * @param x The X coordinate of the tile to find hidden singles for.
     * @param y The Y coordinate of the tile to find hidden singles for.
     * @return <tt>true</tt> -  if (<tt>x</tt>, <tt>y</tt>) has a hidden single and it was removed.
     */
    private boolean findHiddenInColumn(int x, int y)
    {
        Tile[] tiles =this.getColumnTiles(x);
        return getHiddenAt(x,y,tiles);
    }

    /**
     * Looks for  hidden single in a row.
     *
     * @param x The X coordinate of the tile to find hidden singles for.
     * @param y The Y coordinate of the tile to find hidden singles for.
     * @return <tt>true</tt> -  if (<tt>x</tt>, <tt>y</tt>) has a hidden single and it was removed.
     */
    private boolean findHiddenInRow(int x, int y)
    {
        Tile[] tiles =this.getRowTiles(y);
        return getHiddenAt(x,y,tiles);
    }

    /**
     * Looks for  hidden single in a sub-grid.
     *
     * @param x The X coordinate of the tile to find hidden singles for.
     * @param y The Y coordinate of the tile to find hidden singles for.
     * @return <tt>true</tt> -  if (<tt>x</tt>, <tt>y</tt>) has a hidden single and it was removed.
     */
    private boolean findHiddenInSubGrid(int x, int y)
    {
        Tile[] tiles =this.getSubGridTiles(x,y);
        return getHiddenAt(x,y,tiles);
    }

    /**
     * Looks for a hidden single in a certain set of tiles
     * @param x The X coordinate of the tile to find hidden singles for.
     * @param y The Y coordinate of the tile to find hidden singles for.
     * @param tiles The tiles to compare against.
     * @return <tt>true</tt> -  if (<tt>x</tt>, <tt>y</tt>) has a hidden single and it was removed.
     */
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

    /**
     * Counts the number of times a certain value appears in the possibles of a section of tiles.
     * @param p The value to search for.
     * @param tiles The tiles to seach in.
     * @return The number of times the value was found.
     */
    private int count(char p, Tile[] tiles)
    {
        int count = 0;
        for(Tile t: tiles)
            for(char c:t.getPossibles())
                if(c == p)
                    count++;
        return count;
    }

    /**
     * Counts the number of time a certain value appears in the possibles of a Tile.
     * @param p The value.
     * @param possibles The possibles values to look in.
     * @return The number of time the value was found.
     */
    private int count(char p, char[] possibles)
    {
        int count = 0;
        for(char c:possibles)
            if(c == p)
                count++;
        return count;
    }

    /**
     * Returns <tt>true</tt> if the tile at (<tt>x</tt>, <tt>y</tt>) has a naked double with another tile and if theses values were subsequently removed from a row, column or sub-grid.
     * @param x The X coordinate of the tile to look for naked doubles at
     * @param y The Y coordinate of the tile to look for naked doubles at
     * @return <tt>true</tt> - if the tile at (<tt>x</tt>, <tt>y</tt>) has a naked double with another tile and if theses values were subsequently removed from a row, column or sub-grid.
     */
    public boolean findNakedDoubles(int x, int y)
    {
        return findNakedDoublesInColumn(x, y) || findNakedDoublesInRow(x, y) || findNakedDoubleInSubGrid(x, y);
    }

    /**
     * Returns <tt>true</tt> if the tile at (<tt>x</tt>, <tt>y</tt>) has a naked double with another tile and if theses values were subsequently removed from its column.
     * @param x The X coordinate of the tile to look for naked doubles at
     * @param y The Y coordinate of the tile to look for naked doubles at
     * @return <tt>true</tt> - if the tile at (<tt>x</tt>, <tt>y</tt>) has a naked double with another tile and if theses values were subsequently removed from its column.
     */
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

    /**
     * Removes a double from a column.
     * @param x The X coordinate of the first tile.
     * @param y The Y coordiante of the second tile.
     * @param secondTile The second tile
     * @return <tt>true</tt> - if any values were removed
     */
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

    /**
     * Returns <tt>true</tt> if the tile at (<tt>x</tt>, <tt>y</tt>) has a naked double with another tile and if theses values were subsequently removed from its row.
     * @param x The X coordinate of the tile to look for naked doubles at
     * @param y The Y coordinate of the tile to look for naked doubles at
     * @return <tt>true</tt> - if the tile at (<tt>x</tt>, <tt>y</tt>) has a naked double with another tile and if theses values were subsequently removed from its row.
     */
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

    /**
     * Removes a double from a row.
     * @param x The X coordinate of the first tile.
     * @param y The Y coordiante of the second tile.
     * @param secondTile The second tile
     * @return <tt>true</tt> - if any values were removed
     */
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

    /**
     * Returns <tt>true</tt> if the tile at (<tt>x</tt>, <tt>y</tt>) has a naked double with another tile and if theses values were subsequently removed from its sub-grid.
     * @param x The X coordinate of the tile to look for naked doubles at
     * @param y The Y coordinate of the tile to look for naked doubles at
     * @return <tt>true</tt> - if the tile at (<tt>x</tt>, <tt>y</tt>) has a naked double with another tile and if theses values were subsequently removed from its sub-grid.
     */
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

    /**
     * Removes a double from a sub-grid.
     * @param x The X coordinate of the first tile.
     * @param y The Y coordiante of the second tile.
     * @param secondTile The second tile
     * @return <tt>true</tt> - if any values were removed
     */
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


    /**
     * Returns <tt>true</tt> if the tile at (<tt>x</tt>, <tt>y</tt>) has a naked triplet with another tile and if theses values were subsequently removed from a row, column or sub-grid.
     * @param x The X coordinate of the tile to look for naked triplets at
     * @param y The Y coordinate of the tile to look for naked triplets at
     * @return <tt>true</tt> - if the tile at (<tt>x</tt>, <tt>y</tt>) has a naked triplet with another tile and if theses values were subsequently removed from a row, column or sub-grid.
     */
    public boolean findNakedTriplets(int x, int y)
    {
        return getNakedTripletsInRow(x,y) || getNakedTripletsInColumn(x,y) || getNakedTripletsInSubGrid(x,y);
    }

    /**
     * Returns <tt>true</tt> if the tile at (<tt>x</tt>, <tt>y</tt>) has a naked triplet with another tile and if theses values were subsequently removed from its row.
     * @param x The X coordinate of the tile to look for naked triplets at
     * @param y The Y coordinate of the tile to look for naked triplets at
     * @return <tt>true</tt> - if the tile at (<tt>x</tt>, <tt>y</tt>) has a naked triplet with another tile and if theses values were subsequently removed from its row.
     */
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

    /**
     * Returns <tt>true</tt> if the tile at (<tt>x</tt>, <tt>y</tt>) has a naked triplet with another tile and if theses values were subsequently removed from its column.
     * @param x The X coordinate of the tile to look for naked triplets at
     * @param y The Y coordinate of the tile to look for naked triplets at
     * @return <tt>true</tt> - if the tile at (<tt>x</tt>, <tt>y</tt>) has a naked triplet with another tile and if theses values were subsequently removed from its column.
     */
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
                                    ;//Do nothing - just saves adding in superflous conditionals
                                else if(hasTriplet2)
                                    if(opposite[i] == triplet2)
                                        hasTriplet2 = true;
                                else if(hasTriplet3)
                                    if(opposite[i] == triplet3)
                                        hasTriplet3 = true;
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


    /**
     * Returns <tt>true</tt> if the tile at (<tt>x</tt>, <tt>y</tt>) has a naked triplet with another tile and if theses values were subsequently removed from its sub-grid.
     * @param x The X coordinate of the tile to look for naked triplets at
     * @param y The Y coordinate of the tile to look for naked triplets at
     * @return <tt>true</tt> - if the tile at (<tt>x</tt>, <tt>y</tt>) has a naked triplet with another tile and if theses values were subsequently removed from its sub-grid.
     */
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
                                {
                                    if(opposite[i] != ' ')
                                    {
                                        triplet3 = opposite[i];
                                        tile = t;
                                    }
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
                                    ;//Do nothing - just saves adding in superflous conditionals
                                else if(hasTriplet2)
                                    if(opposite[i] == triplet2)
                                        hasTriplet2 = true;
                                else if(hasTriplet3)
                                    if(opposite[i] == triplet3)
                                        hasTriplet3 = true;
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

    /**
     * Removes a triplet from the column
     * @param x The X coordinate of the first tile
     * @param y The Y coordinate of the first tile
     * @param tile1 The second tile
     * @param tile2 The third tile
     * @param triplet1 The first value
     * @param triplet2 The second value
     * @param triplet3 The third value
     * @return <tt>true</tt> - if values are removed.
     */
    private boolean removeTripletFromColumn(int x, int y, Tile tile1, Tile tile2, char triplet1, char triplet2, char triplet3)
    {
        boolean changed = false;

        for(Tile t:getColumnTiles(x))
            if(getTile(x,y) != t && tile1 != t && tile2 != t)
                if(t.removePossibles(new char[]{triplet1,triplet2,triplet3}))
                    changed = true;

        return changed;
    }


    /**
     * Removes a triplet from the row
     * @param x The X coordinate of the first tile
     * @param y The Y coordinate of the first tile
     * @param tile1 The second tile
     * @param tile2 The third tile
     * @param triplet1 The first value
     * @param triplet2 The second value
     * @param triplet3 The third value
     * @return <tt>true</tt> - if values are removed.
     */
    private boolean removeTripletFromRow(int x, int y, Tile tile1, Tile tile2, char triplet1, char triplet2, char triplet3)
    {
        boolean changed = false;

        for(Tile t:getRowTiles(y))
            if(getTile(x,y) != t && tile1 != t && tile2 != t)
                if(t.removePossibles(new char[]{triplet1,triplet2,triplet3}))
                    changed = true;

        return changed;
    }


    /**
     * Removes a triplet from the sub-grid
     * @param x The X coordinate of the first tile
     * @param y The Y coordinate of the first tile
     * @param tile1 The second tile
     * @param tile2 The third tile
     * @param triplet1 The first value
     * @param triplet2 The second value
     * @param triplet3 The third value
     * @return <tt>true</tt> - if values are removed.
     */
    private boolean removeTripletFromSubGrid(int x, int y, Tile tile1, Tile tile2, char triplet1, char triplet2, char triplet3)
    {
        boolean changed = false;

        for(Tile t:getSubGridTiles(x, y))
            if(getTile(x,y) != t && tile1 != t && tile2 != t)
                if(t.removePossibles(new char[]{triplet1,triplet2,triplet3}))
                    changed = true;

        return changed;
    }

    /**
     * Returns <tt>true</tt> if there are hidden doubles at the current tile.
     * @param x The X coordinate of the tile.
     * @param y The Y coordinate of the tile.
     * @return <tt>true</tt> - if there are hidden doubles at the current tile.
     */
    public boolean findHiddenDoubles(int x, int y)
    {
        return getHiddenDoublesInColumn(x,y) || getHiddenDoublesInRow(x,y) || getHiddenDoublesInSubGrid(x,y);
    }

    /**
     * Looks for hidden doubles in a column
     * @param x The X coordinate of the tile.
     * @param y The Y coordinate of the tile.
     * @return <tt>true</tt> - if there are hidden doubles at the current tile.
     */
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


    /**
     * Looks for hidden doubles in a row
     * @param x The X coordinate of the tile.
     * @param y The Y coordinate of the tile.
     * @return <tt>true</tt> - if there are hidden doubles at the current tile.
     */
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


    /**
     * Looks for hidden doubles in a sub-grid
     * @param x The X coordinate of the tile.
     * @param y The Y coordinate of the tile.
     * @return <tt>true</tt> - if there are hidden doubles at the current tile.
     */
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

    /**
     * Returns <tt>true</tt> if an array conatins a certain set of values.
     * @param values The values to look for.
     * @param array The array to look in
     * @return <tt>true</tt> - if an array conatins a certain set of values.
     */
    private boolean contains(char[] values, char[] array)
    {
        for(int i=0;i<values.length && i<array.length;i++)
            if(values[i] != ' ')
                if(values[i] != array[i])
                    return false;

        return true;
    }

    public boolean findXWings(int x, int y)
    {
        return findXWingsColumn(x,y) || findXWingsRow(x,y);
    }

    private boolean findXWingsColumn(int x, int y)
    {
        char[] possibles = getPossiblesAt(x,y);
        if(possibles.length > 1)
        {
            Tile other = null;
            
            Tile column1[] = getColumnTiles(x);
            
            for(char possible:possibles)
            {
                if(count(possible, column1) == 2)
                {
                    for(Tile t:column1)
                    {
                        if(this.contains(possible, t.getPossibles()) && t!=getTile(x,y))
                        {
                            other = t;
                            for(int i=0;i<Sudoku.GRID_SIZE;i++)
                            {
                                if(i!=x)
                                {
                                    Tile[] column2 = getColumnTiles(i);
                                    if(count(possible, column2) == 2)
                                    {
                                        boolean isInRowOfMain = false;
                                        Tile oppositeMain = null;
                                        boolean isInRowOfOther = false;
                                        Tile oppositeOther = null;

                                        for(Tile colTile:column2)
                                        {
                                            if(contains(possible,colTile.getPossibles()))
                                            {
                                                if(colTile.loction().getX() == y)
                                                {
                                                    isInRowOfMain = true;
                                                    oppositeMain = colTile;
                                                }
                                                if(colTile.loction().getX() == other.loction().getX())
                                                {
                                                    isInRowOfOther = true;
                                                    oppositeOther = colTile;
                                                }
                                            }
                                        }

                                        if(isInRowOfMain && isInRowOfOther)
                                        {
                                            boolean changed = false;

                                            for(Tile row:getRowTiles(y))
                                                if(row != getTile(x,y) && row != oppositeMain)
                                                    if(row.removePossibles(new char[]{possible}))
                                                        changed = true;

                                            for(Tile row:getRowTiles(other.loction().getX()))
                                                if(row != other && row != oppositeOther)
                                                    if(row.removePossibles(new char[]{possible}))
                                                        changed = true;

                                            return changed;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            return false;
        }
        else
        {
            return false;
        }
    }

    private boolean findXWingsRow(int x, int y)
    {
        char[] possibles = getPossiblesAt(x,y);
        if(possibles.length > 1)
        {
            Tile other = null;

            Tile row1[] = getRowTiles(y);

            for(char possible:possibles)
            {
                if(count(possible, row1) == 2)
                {
                    for(Tile t:row1)
                    {
                        if(this.contains(possible, t.getPossibles()) && t!=getTile(x,y))
                        {
                            other = t;
                            for(int i=0;i<Sudoku.GRID_SIZE;i++)
                            {
                                if(i!=y)
                                {
                                    Tile[] row2 = getRowTiles(i);
                                    if(count(possible, row2) == 2)
                                    {
                                        boolean isInColumnOfMain = false;
                                        Tile oppositeMain = null;
                                        boolean isInColumnOfOther = false;
                                        Tile oppositeOther = null;

                                        for(Tile rowTile:row2)
                                        {
                                            if(contains(possible,rowTile.getPossibles()))
                                            {
                                                if(rowTile.loction().getY() == x)
                                                {
                                                    isInColumnOfMain = true;
                                                    oppositeMain = rowTile;
                                                }
                                                if(rowTile.loction().getY() == other.loction().getY())
                                                {
                                                    isInColumnOfOther = true;
                                                    oppositeOther = rowTile;
                                                }
                                            }
                                        }

                                        if(isInColumnOfMain && isInColumnOfOther)
                                        {
                                            boolean changed = false;

                                            for(Tile column:getColumnTiles(x))
                                                if(column != getTile(x,y) && column != oppositeMain)
                                                    if(column.removePossibles(new char[]{possible}))
                                                        changed = true;

                                            for(Tile column:getColumnTiles(other.loction().getY()))
                                                if(column != other && column != oppositeOther)
                                                    if(column.removePossibles(new char[]{possible}))
                                                        changed = true;

                                            return changed;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            return false;
        }
        else
        {
            return false;
        }
    }

    private boolean contains(char possible, char[] possibles)
    {
        for(char c:possibles)
            if(c == possible)
                return true;

        return false;
    }
}
