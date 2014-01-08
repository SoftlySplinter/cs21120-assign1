package uk.ac.aber.adb9.java.file_handling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * A basic file reader.
 *
 * @since Oct 4, 2010
 * @version 1
 * @author Alexander Brown
 */
public class BasicFileReader
{
    /**
     * The base file.
     * @see File
     */
    private File file;

    /**
     * The Java libary for the file reader.
     * @see BufferedReader
     */
    private BufferedReader bufferedReader;
    
    /**
     * Constructor which takes a File.
     * @param file The input file.
     * @see File
     */
    public BasicFileReader(File file) throws FileNotFoundException
    {
        this.file = file;
        this.openReader();
    }

    /**
     * Constructor which takes a String.
     * @param fileName The path to the file.
     * @see #FileReader(File) FileReader(File)
     */
    public BasicFileReader(String fileName) throws FileNotFoundException
    {
        this(new File(fileName));
    }

    /**
     * Attempts to open the File Reader.
     *
     * @see BufferedReader
     * @see FileReader
     * @see FileNotFoundException
     */
    private void openReader() throws FileNotFoundException
    {
        bufferedReader = new BufferedReader(new FileReader(file));
    }

    /**
     * Reads the next line of the File.
     * @return The next line of the file.<br />null if the Reader doesn't exist.
     * @throws IOException
     */
    public String readLine() throws IOException
    {
        if(bufferedReader != null)
        {
            return bufferedReader.readLine();
        }
        else
        {
            System.err.println("Error reading File. File Reader does not exist");
            return null;
        }
    }

    /**
     * Closes the reader.
     */
    public void close() throws IOException
    {
        bufferedReader.close();
    }
}
