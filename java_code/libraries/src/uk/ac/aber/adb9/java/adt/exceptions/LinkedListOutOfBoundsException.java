package uk.ac.aber.adb9.java.adt.exceptions;

/**
 * Thrown when a Linked List goes out of bounds (generally below 0).
 * @since Oct 5, 2010
 * @version 1
 * @author Alexander Brown
 *
 * @see uk.ac.aber.adb9.java.adt.LinkedList
 */
public class LinkedListOutOfBoundsException
extends Exception
{
    private static final String DEFAULT_MESSAGE = "Cannot perform action on Linked List: Node out of bounds";

    public LinkedListOutOfBoundsException()
    {
        this(DEFAULT_MESSAGE);
    }

    public LinkedListOutOfBoundsException(String message)
    {
        super(message);
    }

    
}
