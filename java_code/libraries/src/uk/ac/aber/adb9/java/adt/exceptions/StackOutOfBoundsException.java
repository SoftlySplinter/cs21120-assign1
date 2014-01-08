package uk.ac.aber.adb9.java.adt.exceptions;

/**
 * Defines the Exception what a Stack goes above or below it's bounds.
 * @since Oct 4, 2010
 * @version 1
 * @author Alexander Brown
 * @see Stack
 */
public class StackOutOfBoundsException
extends Exception
{
    /**
     * The default message to be shown for the Exception.
     */
    private static final String DEFAULT_MESSAGE = "Cannot perform action on Stack: Stack out of bounds";

    /**
     * Defualt constructor.
     */
    public StackOutOfBoundsException()
    {
        this(DEFAULT_MESSAGE);
    }

    /**
     * Base constructor.
     * @param message The message for the exception.
     */
    public StackOutOfBoundsException(String message)
    {
        super(message);
    }
}
