package uk.ac.aber.adb9.java.adt.queues;

import uk.ac.aber.adb9.java.adt.exceptions.QueueOutOfBoundsException;

/**
 * Interface for the Queue ADT.
 * @since Oct 5, 2010
 * @version 1
 * @author Alexander Brown
 */
public interface Queue
{
    public void insert(Object o) throws QueueOutOfBoundsException;

    public Object remove() throws QueueOutOfBoundsException;

    public Object getFront() throws QueueOutOfBoundsException;

    public int length();

    public boolean isEmpty();

    public String toString();
}
