package uk.ac.aber.adb9.java.adt.queues;

import uk.ac.aber.adb9.java.adt.queues.UnboundQueue;
import junit.framework.TestCase;
import uk.ac.aber.adb9.java.adt.exceptions.QueueOutOfBoundsException;

/**
 * @since Oct 5, 2010
 * @version 1
 * @author Alexander Brown
 */
public class UnboundQueueTest extends TestCase
{
    UnboundQueue queue;

    public UnboundQueueTest(String testName)
    {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        queue = new UnboundQueue();
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
