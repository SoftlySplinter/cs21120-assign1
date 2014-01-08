package uk.ac.aber.adb9.java.adt.exceptions;

/**
 * @since Oct 11, 2010
 * @version 1
 * @author Alexander Brown
 */
public class IncomparableTypesException extends Exception
{

    /**
     * Creates a new instance of <code>IncomparableTypesException</code> without detail message.
     */
    public IncomparableTypesException()
    {
        this("");
    }


    /**
     * Constructs an instance of <code>IncomparableTypesException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public IncomparableTypesException(String msg)
    {
        super(msg);
    }
}
