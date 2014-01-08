package uk.ac.aber.adb9.java.adt.queues;

import uk.ac.aber.adb9.java.Utils;
import uk.ac.aber.adb9.java.adt.exceptions.HeapOutOfBoundsException;
import uk.ac.aber.adb9.java.adt.trees.BinaryHeap;

/**
 * A Priority Queue implemented using a Binary Heap.
 *
 * @since Oct 11, 2010
 * @version 1
 * @author Alexander Brown
 *
 * @see uk.ac.aber.adb9.java.adt.trees.BinaryHeap
 * @see PriorityQueue
 */
public class HeapedPriorityQueue
implements PriorityQueue
{
    BinaryHeap queue;

    public HeapedPriorityQueue(int maxSize)
    {
        queue = new BinaryHeap(maxSize);
    }

    public HeapedPriorityQueue()
    {
        this(Utils.DEFAULT_ARRAY_SIZE);
    }

    public void insert(Object data, int priority) throws HeapOutOfBoundsException
    {
        queue.insert(new PriorityQueueItem(data,priority));
    }

    public Object remove() throws HeapOutOfBoundsException
    {
        PriorityQueueItem item = (PriorityQueueItem) queue.remove();
        return item.getData();
    }

    public Object getTop() throws HeapOutOfBoundsException
    {
        PriorityQueueItem item = (PriorityQueueItem) queue.getTop();
        return item.getData();
    }

    public boolean isEmpty()
    {
        return queue.isEmpty();
    }

    public int size()
    {
        return queue.size();
    }
}
