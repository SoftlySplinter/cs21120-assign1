package uk.ac.aber.adb9.java.adt.queues;

import uk.ac.aber.adb9.java.adt.exceptions.IncomparableTypesException;

/**
 * @since Oct 11, 2010
 * @version 1
 * @author Alexander Brown
 */
public class PriorityQueueItem
implements Comparable
{
    Object data;
    int priority;
    
    public PriorityQueueItem(Object data, int priority)
    {
        this.data = data;
        this.priority = priority;
    }

    public int compareTo(Object t)
    {
        PriorityQueueItem p = (PriorityQueueItem) t;
        return this.priority - p.getPriority();
    }

    public int getPriority()
    {
        return priority;
    }

    public Object getData()
    {
        return data;
    }
    
}
