package uk.ac.aber.adb9.java.adt.queues;

import uk.ac.aber.adb9.java.adt.queues.BoundQueue;
import uk.ac.aber.adb9.java.adt.exceptions.QueueOutOfBoundsException;
import junit.framework.TestCase;

/**
 * A test for the Bound Queue ADT.
 * @since 5 Oct, 2010
 * @version 1
 * @author Alexander Brown
 *
 * @see BoundQueue
 */
public class BoundQueueTest extends TestCase
{
    private BoundQueue queue;

    public BoundQueueTest(String testName)
    {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        queue = new BoundQueue(5);
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testQueueInsertAndRemove()
    {
        String input = "Test 1";
        try
        {
            queue.insert(input);
            queue.insert("Test 2");
            String result = (String) queue.remove();
            assertEquals(result, input);
        }
        catch (QueueOutOfBoundsException ex)
        {
            assertTrue(false);
        }
    }

    public void testQueueUpperLimit()
    {
        try
        {
            queue.insert("Item 1");
            queue.insert("Item 2");
            queue.insert("Item 3");
            queue.insert("Item 4");
            queue.insert("Item 5");
        }
        catch (QueueOutOfBoundsException e)
        {
            assertTrue(false);
        }
        try
        {
            queue.insert("Out of limit Object");
            assertTrue(false);
        }
        catch (QueueOutOfBoundsException ex)
        {
            assertTrue(true);
        }
    }

    public void testQueueInsertion()
    {
        try
        {
            queue.insert("Item 1");
            queue.insert("Item 2");
            queue.insert("Item 3");
            queue.insert("Item 4");
            queue.remove();
            queue.insert("Item 5");
            queue.remove();
            queue.insert("Item 6");
            queue.remove();
            queue.remove();
            queue.insert("Item 7");
            queue.remove();
            assertEquals("Item 6",queue.remove());
        }
        catch (QueueOutOfBoundsException e)
        {
            assertTrue(false);
        }
    }

    public void testQueueLowerLimit()
    {
        try
        {
            queue.insert("Test 1");
            queue.remove();
        }
        catch (QueueOutOfBoundsException ex)
        {
            assertTrue(false);
        }
        try
        {
            queue.remove();
            assertTrue(false);
        }
        catch (QueueOutOfBoundsException ex)
        {
            assertTrue(true);
        }
    }
}
