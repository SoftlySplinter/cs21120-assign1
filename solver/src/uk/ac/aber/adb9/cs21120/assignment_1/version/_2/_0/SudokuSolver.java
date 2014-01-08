package uk.ac.aber.adb9.cs21120.assignment_1.version._2._0;

/**
 * <h2>CS21120: Data Structures and Algorithm Analysis<br />Assignment 1 - Sudoku</h2>
 * <h3>1. Background</h3>
 * <p>For this assignment you are required to do three things:</p>
 * <ol>
 * <li>Design an algorithm to solve a Sudoku puzzle.</li>
 * <li>Design the data structures that will enable your algorithm to work.</li>
 * <li>Produce a well structured and commented java program, with evidence of testing.</li>
 * </ol>
 *
 * <h3>2. The task in detail</h3>
 * <h4>2.1. Design an algorithm to solve Sudoku puzzles</h4>
 * <p>Sudoku puzzles are quite popular, they consist of a 9x9 grid of numbers, which in turn is subdivided into nine 3x3 grids.</p>
 * <p>The rules are very simple, “Fill in the grid so that every row, every column, and every 3x3 box contains the digits 1 through 9.”</p>
 * <p>You can ﬁnd out more information at <a href="http://www.sudoku.org.uk/">http://www.sudoku.org.uk/</a></p>
 * <p>Your algorithm to solve the puzzles must be presented as a ﬂowchart, pseudo code or some other recognized method of describing an algorithm.</p>
 * <h4>2.2. Design the data structures which will enable your algorithm to work</h4>
 * <p>Produce a design for your data that will allow you to store the grid and manipulate it in ways that are needed for your algorithm. If you need to address a 3x3 sub grid in the 9x9 main grid then there must be a simple way of addressing that subgrid.</p>
 * <p>Your design should be expressed as a set of UML class diagrams with supporting documentation.</p>
 * <p>You should provide written justiﬁcation for the design decisions that you have taken, and explain how your classes work together.</p>
 * <h4>2.3. Produce well a structured and commented java program, with evidence of testing</h4>
 * <p>Your program should read the data ﬁles provided on the course web site, and produce solutions to them in a similar form.</p>
 * <p>The data ﬁles are very simple, they consist of 9 rows, each of which has 9 characters on a row. If a square is unknown, the character will be a space, otherwise it will be the value contained within the square.</p>
 * <p>Your program does not have to have a Graphical User Interface, but for maximum marks you should provide one. Marks for the GUI will be a maximum of 10% of the marks for the whole assignment.</p>
 * <p>The program may either just display the problem and the solution, or for bonus marks it may show the algorithm progressing towards a solution.</p>
 * <p>If you choose this method, then you will need to investigate putting the solver into a separate thread. You could use a SwingWorker thread (<a href="http://java.sun.com/ products/jfc/tsc/articles/threads/threads2.html">http://java.sun.com/ products/jfc/tsc/articles/threads/threads2.html</a>).</p>
 * <p>If you do read this article, please note that SwingWorker has become part of the core API now (<a href="http://download.oracle.com/javase/7/docs/ api/">http://download.oracle.com/javase/7/docs/ api/</a>), so you don’t need to download the code and include it yourself.</p>
 * <p>If you use any code, then make sure that you credit the original source, and annotate where you have made alterations.
 *
 * <p><i>From CS21120 Assignment 1.pdf by Richard Shipman</i></p>

 *
 * <DL>
 * <DT><b>Created:</b></DT>
 * <DD>Oct 18, 2010</DD>
 * </DL>
 * @version 2.0
 * @author Alexander Brown
 */
public class SudokuSolver
{

    /**
     * Creates a new Model and runs it.
     * @param args the command line arguments
     *
     * @see Model
     */
    public static void main(String[] args)
    {
        Model mo = new Model();
    }

}
