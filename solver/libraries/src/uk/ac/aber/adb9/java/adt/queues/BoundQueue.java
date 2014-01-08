package uk.ac.aber.adb9.java.adt.queues;

import uk.ac.aber.adb9.java.adt.exceptions.QueueOutOfBoundsException;
import uk.ac.aber.adb9.java.Utils;

/**
 * The <abbr title="Abstract Data Type">ADT</abbr> for a Queue
 * with a maximum size limit.
 * <p>
 * Uses the <abbr title="First In, First Out">FIFO</abbr> model,
 * which only allows elements at the top of the queue to be accessed.
 * Elements added to the queue are added at the bottom, therefore forcing
 * all elements to wait in the queue until it is their turn to be accessed.
 * <p>
 * In this implementation of the queue, the top is not nexessarily the first
 * element in the array. Instead the queue hold information on which element
 * is the top and moves it as it needs to. This stops all unecessary shuffling
 * of the array.
 * <p>
 * <b>Revisions:</b>
 * <ul style="margin-left: 27px; margin-top: 5px; margin-bottom:0px;">
 * <li style="list-style-type:none;">
 * Oct 5, 2010 - Implemented Queue Interface
 * </li>
 * </ul>
 * @since Oct 4, 2010
 * @version 2
 * @author Alexander Brown
 * 
 * @see Queue
 * @see QueueOutOfBoundsException
 * @see UnboundQueue
 */
public class BoundQueue
implements Queue
{
    /**
     * The queue in a bound array form.
     */
    private Object[] queue;

    /**
     * The length of the current queue.
     */
    private int length;

    /**
     * The maximum size of the queue.
     */
    private int maximumSize;

    /**
     * The top element in the queue (as an interger due to the bound array).
     */
    private int top;

    /**
     * Full Constructor
     * @param maxSize The maximum size the queue should be.
     */
    public BoundQueue(int maxSize)
    {
        this.maximumSize = maxSize;
        queue = new Object[maxSize];
        length = 0;
    }

    /**
     * Default Cosntructor which uses the predefined default array size as the
     * limit for the queue
     * @see Utils#DEFAULT_ARRAY_SIZE
     */
    public BoundQueue()
    {
        this(Utils.DEFAULT_ARRAY_SIZE);
    }

    /**
     * @param o The item to insert into the queue.
     * @throws QueueOutOfBoundsException If the queue goes above the limit.
     *
     * @see #getBottom()
     */
    public void insert(Object o) throws QueueOutOfBoundsException
    {
        if(length < maximumSize)
        {
            queue[getBottom()] = o;
            length++;
        }
        else
        {
            throw new QueueOutOfBoundsException();
        }
    }

    /**
     * @return The item removed from the queue.
     * @throws QueueOutOfBoundsException If there are no items to return.
     *
     * @see #nextTop() 
     */
    public Object remove() throws QueueOutOfBoundsException
    {
        if(length > 0)
        {
            length--;
            Object o = queue[top];
            nextTop();
            return o;
        }
        else
        {
            throw new QueueOutOfBoundsException();
        }
    }

    /**
     * @return The top item without disturbing the queue.
     * @throws QueueOutOfBoundsException If there is no item to return.
     */
    public Object getFront() throws QueueOutOfBoundsException
    {
        if(length > 0)
        {
            Object o = queue[top];
            return o;
        }
        else
        {
            throw new QueueOutOfBoundsException();
        }
    }

    /**
     * Gets the address of the next top item. This stops unecessary shuffling
     * of the array.
     * <p>
     * It works by incramenting top if it's going to be under the limit of the array,
     * otherwise it resets to <tt>0</tt>.
     *
     * @see #getBottom() 
     * @see #remove() 
     */
    private void nextTop()
    {
        if(top+1 < maximumSize)
        {
            top++;
        }
        else
        {
            top = 0;
        }
    }

    /**
     * @return The address of bottom element of the queue.
     *
     * @see #getFront() 
     * @see #insert(Object) 
     */
    private int getBottom()
    {
        if(top+length < maximumSize)
        {
            return top+length;
        }
        else
        {
            return (top+length)-maximumSize;
        }
    }

    /**
     * @return The length of the queue.
     */
    public int length()
    {
        return length;
    }

    /**
     * @return <tt>true</tt> - If the queue is empty.
     */
    public boolean isEmpty()
    {
        return length == 0;
    }
}
