package uk.ac.aber.adb9.java.adt.queues;

import uk.ac.aber.adb9.java.adt.exceptions.QueueOutOfBoundsException;

/**
 * A Deque which can only insert to the front of the queue, but which can remove from both front and back.
 *
 * @since Oct 10, 2010
 * @version 1
 * @author Alexander Brown
 */
public class InputRestrictedDeque
extends Deque
{
    public void insert(Object o)
    {
        this.insertToFront(o);
    }

    public Object removeFront() throws QueueOutOfBoundsException
    {
        return this.removeFromFront();
    }

    public Object removeBack() throws QueueOutOfBoundsException
    {
        return this.removeFromBack();
    }
}
