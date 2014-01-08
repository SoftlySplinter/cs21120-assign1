package uk.ac.aber.adb9.java.adt.queues;

/**
 * Defines the interface for a Priority Queue ADT.
 * </p>
 * <p>
 * Priority Queues are typically used for task scheduling, as their purpose is to find the highest priority task at the time. The implementation of how they are scheduled is up to the user.
 * </p>
 * <p>
 * Because there are several ways of implementing the Priority Queue ADT, this acts as the interface for the base implementation of a Priority Queue.
 * </p>
 *
 * @since Oct 11, 2010
 * @version 1
 * @author Alexander Brown
 */
interface PriorityQueue
{
    /**
     * Inserts an item with a priority.
     * @param data The data the item contains.
     * @param priority The priority of the item.
     * @throws Exception If the Priority Queue goes out of bounds.
     */
    public void insert(Object data, int priority) throws Exception;

    /**
     * Removes the highest priority item from the queue.
     * @return The data the highest priority item contains.
     * @throws Exception If the Priority Queue goes out of bounds.
     */
    public Object remove() throws Exception;

    /**
     * Gets the highest priority item from the queue without removing it.
     * @return The data the highest priority item contains.
     * @throws Exception If the Priority Queue goes out of bounds.
     */
    public Object getTop() throws Exception;
}
