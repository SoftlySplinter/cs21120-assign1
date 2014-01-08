package uk.ac.aber.adb9.java.adt.queues;

import uk.ac.aber.adb9.java.adt.LinkedList;
import uk.ac.aber.adb9.java.adt.exceptions.LinkedListOutOfBoundsException;
import uk.ac.aber.adb9.java.adt.exceptions.QueueOutOfBoundsException;

/**
 * A Queue ADT without a maximum limit.
 * @since Oct 5, 2010
 * @version 1
 * @author Alexander Brown
 *
 * @see LinkedList
 */
public class UnboundQueue
implements Queue
{
    /**
     * The storage for the queue.
     *
     * @see LinkedList
     */
    private LinkedList queue = new LinkedList();

    /**
     * @param o The item to insert.
     * @throws QueueOutOfBoundsException
     */
    public void insert(Object o) throws QueueOutOfBoundsException
    {
        queue.addToBack(o);
    }

    /**
     * @return The Item from the top of the queue.
     * @throws QueueOutOfBoundsException
     */
    public Object remove() throws QueueOutOfBoundsException
    {
        try
        {
            return queue.removeFromFront().getData();
        }
        catch (LinkedListOutOfBoundsException ex)
        {
            throw new QueueOutOfBoundsException(ex.getMessage());
        }
    }

    /**
     * @return The top item without disturbing the queue.
     * @throws QueueOutOfBoundsException
     */
    public Object getFront() throws QueueOutOfBoundsException
    {
        if(queue.length() > 0)
        {
            return queue.getFront().getData();
        }
        else
        {
            throw new QueueOutOfBoundsException();
        }
    }

    /**
     * @return The length of the queue
     * @see LinkedList#length()
     */
    public int length()
    {
        return queue.length();
    }

    /**
     * @return <b>true</b> if the queue is empty.
     * @see LinkedList#isEmpty() 
     */
    public boolean isEmpty()
    {
        return queue.isEmpty();
    }
}
