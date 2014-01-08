package uk.ac.aber.adb9.java.adt.trees;

import junit.framework.TestCase;

/**
 * @since Oct 11, 2010
 * @version 1
 * @author Alexander Brown
 */
public class BinaryHeapTest extends TestCase
{
    private BinaryHeap heap = new BinaryHeap();
    
    public BinaryHeapTest(String testName)
    {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * Test of insert method, of class BinaryHeap.
     */
    public void testInsert() throws Exception
    {
        heap.insert(1);
        heap.insert(2);
        heap.insert(-10);
        heap.insert(-5);
        heap.insert(0);
        heap.insert(20);

        assertEquals(20,heap.getTop());
    }

    public void testRemove() throws Exception
    {
        heap.insert(1);
        heap.insert(2);
        heap.insert(10);
        heap.insert(20);

        heap.remove();
        heap.remove();
        heap.remove();

        assertEquals(1,heap.getTop());
    }

}
