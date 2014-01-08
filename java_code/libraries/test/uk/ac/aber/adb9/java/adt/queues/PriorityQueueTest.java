package uk.ac.aber.adb9.java.adt.queues;

import junit.framework.TestCase;

/**
 * @since Oct 12, 2010
 * @version 1
 * @author Alexander Brown
 */
public class PriorityQueueTest extends TestCase
{
    PriorityQueue queue;
    
    public PriorityQueueTest(String testName)
    {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        queue = new UnboundPriorityQueue();
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * Test of insert method, of class PriorityQueue.
     */
    public void testInsert() throws Exception
    {
        queue.insert("Item 1", 1);
        queue.insert("Item 2", 2);
        queue.insert("Item 3", -1);
        assertEquals("Item 2" ,queue.getTop());
    }

    /**
     * Test of remove method, of class PriorityQueue.
     */
    public void testRemove() throws Exception
    {
        queue.insert("Item 1", 1);
        queue.insert("Item 2", 2);
        queue.insert("Item 3", -1);
        queue.remove();
        assertEquals("Item 1" ,queue.getTop());

    }

}
