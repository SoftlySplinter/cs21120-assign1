/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.aber.adb9.java.adt.exceptions;

/**
 * Thrown with a Queue goes out of it's bounds.
 * @since Oct 4, 2010
 * @version 1
 * @author Alexander Brown
 */
public class QueueOutOfBoundsException
extends Exception
{
    /**
     * The default message to be shown for the Exception.
     */
    private static final String DEFAULT_MESSAGE = "Cannot perform action on Queue: Queue out of bounds";

    public QueueOutOfBoundsException()
    {
        this(DEFAULT_MESSAGE);
    }

    public QueueOutOfBoundsException(String message)
    {
        super(message);
    }
    
}
