package uk.ac.aber.adb9.java.adt.exceptions;

/**
 * @since Oct 11, 2010
 * @version 1
 * @author Alexander Brown
 */
public class HeapOutOfBoundsException extends Exception
{

    /**
     * Creates a new instance of <code>HeapOutOfBoundsException</code> without detail message.
     */
    public HeapOutOfBoundsException()
    {
        this("");
    }


    /**
     * Constructs an instance of <code>HeapOutOfBoundsException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public HeapOutOfBoundsException(String msg)
    {
        super(msg);
    }
}
