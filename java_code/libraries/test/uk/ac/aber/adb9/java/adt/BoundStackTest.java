package uk.ac.aber.adb9.java.adt;

import uk.ac.aber.adb9.java.adt.exceptions.StackOutOfBoundsException;
import junit.framework.TestCase;

/**
 * A test for the Bound Stack ADT.
 * @version 1
 * @since 5 Oct, 2010
 * @author Alexander Brown
 *
 * @see BoundStack
 */
public class BoundStackTest extends TestCase
{
    BoundStack s;
    
    public BoundStackTest(String testName)
    {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        s = new BoundStack(5);
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testStackPushPop()
    {
        String testString = "Item 1";
        try
        {
            s.push("Test 2");
            s.push(testString);
            String result = (String) s.pop();
            this.assertEquals(result, testString);
        }
        catch (StackOutOfBoundsException ex)
        {
            assertTrue(false);
        }
    }

    public void testStackUpperLimit()
    {
        try
        {
            s.push("Item 1");
            s.push("Item 2");
            s.push("Item 3");
            s.push("Item 4");
            s.push("Item 5");
        }
        catch (StackOutOfBoundsException e)
        {
            assertTrue(false);
        }
        try
        {
            s.push("Out of limit Object");
            assertTrue(false);
        }
        catch (StackOutOfBoundsException ex)
        {
            assertTrue(true);
        }
    }

    public void testStackLowerLimit()
    {
        try
        {
            s.push("Item 1");
            s.pop();
        }
        catch (StackOutOfBoundsException ex)
        {
            assertTrue(false);
        }
        try
        {
            s.pop();
            assertTrue(false);
        }
        catch (StackOutOfBoundsException ex)
        {
            assertTrue(true);
        }
    }
    
    public void testStackPeek()
    {
        String one = "Test 1";
        String two = "Test 2";
        try
        {
            s.push(one);
            s.push(two);
            String peek = (String) s.peek();
            String pop = (String) s.pop();
            assertEquals(peek, pop);
        }
        catch (StackOutOfBoundsException ex)
        {
            assertTrue(false);
        }
    }
}
