package uk.ac.aber.adb9.java.adt.queues;

import uk.ac.aber.adb9.java.adt.SortedLinkedList;

/**
 * @since Oct 12, 2010
 * @version 1
 * @author Alexander Brown
 */
public class UnboundPriorityQueue
implements PriorityQueue
{
    private SortedLinkedList queue = new SortedLinkedList();

    public void insert(Object data, int priority) throws Exception
    {
        queue.insert(new PriorityQueueItem(data,priority));
    }

    public Object remove() throws Exception
    {
        PriorityQueueItem top = (PriorityQueueItem) queue.remove();
        return top.getData();
    }

    public Object getTop() throws Exception
    {
        PriorityQueueItem top = (PriorityQueueItem) queue.getFirst();
        return top.getData();
    }
}
