package uk.ac.aber.adb9.java.adt.queues;

import uk.ac.aber.adb9.java.adt.DoublyLinkedList;
import uk.ac.aber.adb9.java.adt.exceptions.LinkedListOutOfBoundsException;
import uk.ac.aber.adb9.java.adt.exceptions.QueueOutOfBoundsException;

/**
 *
 *
 * @since Oct 10, 2010
 * @version 1
 * @author Alexander Brown
 */
public abstract class Deque
{
    DoublyLinkedList queue = new DoublyLinkedList();

    void insertToFront(Object o)
    {
        queue.addToFront(o);
    }

    void insertToBack(Object o)
    {
        queue.addToBack(o);
    }

    Object removeFromFront() throws QueueOutOfBoundsException
    {
        try
        {
            return queue.removeFromFront();
        }
        catch (LinkedListOutOfBoundsException ex)
        {
            throw new QueueOutOfBoundsException();
        }
    }

    Object removeFromBack() throws QueueOutOfBoundsException
    {
        try
        {
            return queue.removeFromBack();
        }
        catch(LinkedListOutOfBoundsException ex)
        {
            throw new QueueOutOfBoundsException();
        }
    }

    public Object getFront() throws QueueOutOfBoundsException
    {
        if(!isEmpty())
        {
            return queue.getFront();
        }
        else
        {
            throw new QueueOutOfBoundsException();
        }
    }

    public Object getBack() throws QueueOutOfBoundsException
    {
        if(!isEmpty())
        {
            return queue.getBack();
        }
        else
        {
            throw new QueueOutOfBoundsException();
        }
    }

    public int length()
    {
        return queue.length();
    }

    public boolean isEmpty()
    {
        return queue.isEmpty();
    }
}
