package uk.ac.aber.adb9.java.adt.trees;

import junit.framework.TestCase;

/**
 * @since Oct 10, 2010
 * @version 1
 * @author Alexander Brown
 */
public class AVLTreeTest extends TestCase
{
    AVLTree tree;
    
    public AVLTreeTest(String testName)
    {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        tree = new AVLTree();
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testInsert()
    {
        tree.insert(4);
        tree.insert(7);
        tree.insert(2);
        tree.insert(8);
        tree.insert(6);
        tree.insert(9);
        tree.insert(10);
        tree.insert(11);
        tree.insert(12);
        tree.insert(15);
        tree.inOrder();
    }
}
