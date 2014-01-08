package uk.ac.aber.adb9.java.adt.queues;

import uk.ac.aber.adb9.java.Utils;
import uk.ac.aber.adb9.java.adt.exceptions.QueueOutOfBoundsException;

/**
 * @since Oct 12, 2010
 * @version 1
 * @author Alexander Brown
 */
public class UnsortedPriorityQueue
implements PriorityQueue
{
    private PriorityQueueItem[] queue;

    private int highestPriority;

    private int size;

    public UnsortedPriorityQueue(int maxSize)
    {
        queue = new PriorityQueueItem[maxSize];
    }

    public UnsortedPriorityQueue()
    {
        this(Utils.DEFAULT_ARRAY_SIZE);
    }

    public void insert(Object data, int priority) throws Exception
    {
        if(size < queue.length)
        {
            queue[size] = new PriorityQueueItem(data,priority);
            if(queue[highestPriority].compareTo(queue[size]) < 0)
            {
                highestPriority = size;
            }
            size++;
        }
    }

    public Object remove() throws Exception
    {
        if(!isEmpty())
        {
            PriorityQueueItem temp = queue[highestPriority];
            size--;
            queue[highestPriority] = queue[size];
            queue[size] = null;
            getHighestPriotity();
            return temp.getData();
        }
        else
        {
            throw new QueueOutOfBoundsException();
        }
    }

    public Object getTop() throws Exception
    {
        if(!isEmpty())
        {
            return queue[highestPriority].getData();
        }
        else
        {
            throw new QueueOutOfBoundsException();
        }
    }

    public boolean isEmpty()
    {
        return size==0;
    }

    private void getHighestPriotity()
    {
        highestPriority = 0;
        for(int i=0;i<size;i++)
        {
            if(queue[highestPriority].compareTo(queue[i]) < 0)
            {
                highestPriority = i;
            }
        }
    }
    
}
