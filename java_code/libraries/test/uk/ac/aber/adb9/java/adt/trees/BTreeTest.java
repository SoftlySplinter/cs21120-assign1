package uk.ac.aber.adb9.java.adt.trees;

import junit.framework.TestCase;

/**
 * @since Oct 12, 2010
 * @version 1
 * @author Alexander Brown
 */
public class BTreeTest extends TestCase
{
    private BTree tree;
    
    public BTreeTest(String testName)
    {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception
    {
        tree = new BTree(5);
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testInsert()
    {
        tree.insert(5);
        tree.insert(9);
    }

}
