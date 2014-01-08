package uk.ac.aber.adb9.java.adt.queues;

import uk.ac.aber.adb9.java.adt.exceptions.QueueOutOfBoundsException;

/**
 * An output restricted deque, which only allows removal from the front of the queue, but to which items can be added at both ends.
 *
 * @since Oct 10, 2010
 * @version 1
 * @author Alexander Brown
 */
public class OutputResrictedDeque
extends Deque
{
    public void insertFront(Object o)
    {
        this.insertToFront(o);
    }

    public void insertBack(Object o)
    {
        this.insertToBack(o);
    }

    public Object remove() throws QueueOutOfBoundsException
    {
        return this.removeFromFront();
    }
}
