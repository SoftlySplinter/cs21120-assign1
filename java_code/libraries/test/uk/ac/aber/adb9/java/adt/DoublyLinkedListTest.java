package uk.ac.aber.adb9.java.adt;

import junit.framework.TestCase;
import uk.ac.aber.adb9.java.adt.exceptions.LinkedListOutOfBoundsException;

/**
 * A test for the Doubly Linked List ADT.
 * @since 5 Oct, 2010
 * @version 1
 * @author Alexander Brown
 *
 * @see DoublyLinkedList
 */
public class DoublyLinkedListTest extends TestCase
{
    private DoublyLinkedList l;
    
    public DoublyLinkedListTest(String testName)
    {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        l = new DoublyLinkedList();
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testAddSingleToFront()
    {
        l.addToFront("Test 1");
        assertEquals("Test 1",l.getFront().toString());
    }

    public void testAddToFront()
    {
        l.addToFront("Test 1");
        l.addToFront("Test 2");
        l.addToFront("Test 3");

        assertEquals("Test 3",l.getFront().toString());
    }

    public void testAddSingleToBack()
    {
        l.addToBack("Test 1");
        assertEquals("Test 1",l.getFront().toString());
    }

    public void testAddToBack()
    {
        l.addToBack("Test 1");
        l.addToBack("Test 2");
        l.addToBack("Test 3");
        assertEquals("Test 1",l.getFront().toString());
    }

    public void testAddingToBackAndFront()
    {
        l.addToFront("Test 1");
        l.addToBack("Test 2");
        l.addToFront("Test 3");
        l.addToBack("Test 4");

        assertEquals("Test 3",l.getFront().toString());
    }

    public void testRemoveFromFront()
    {
        try
        {
            l.addToFront("Test 1");
            l.addToBack("Test 2");
            l.addToBack("Test 3");
            l.removeFromFront();
            assertEquals("Test 2",l.getFront().toString());
        }
        catch (LinkedListOutOfBoundsException ex)
        {
            assertTrue(false);
        }
    }

    public void testRemoveFromBack()
    {
        try
        {
            l.addToFront("Test 1");
            l.addToBack("Test 2");
            l.addToBack("Test 3");
            l.removeFromBack();
            assertEquals("Test 2",l.getBack().toString());
        }
        catch (LinkedListOutOfBoundsException ex)
        {
            assertTrue(false);
        }
    }

    public void testRemovalLimits()
    {
        try
        {
            l.addToFront("Test 1");
            l.removeFromFront();
        }
        catch (LinkedListOutOfBoundsException ex)
        {
            assertTrue(false);
        }
        try
        {
            l.removeFromFront();
            assertTrue(false);
        }
        catch (LinkedListOutOfBoundsException ex)
        {
            assertTrue(true);
        }
    }

    public void testFind()
    {
        l.addToFront("Test 1");
        l.addToBack("Test 2");
        l.addToBack("Test 3");
        String result = (String) l.find("Test 2").getData();
        assertEquals("Test 2",result);
    }

    public void testFindNotInList()
    {
        l.addToFront("Test 1");
        l.addToBack("Test 2");
        l.addToBack("Test 3");
        try
        {
            String result = (String) l.find("Test 4").getData();
            assertTrue(false);
        }
        catch (NullPointerException e)
        {
            assertTrue(true);
        }
    }

}
