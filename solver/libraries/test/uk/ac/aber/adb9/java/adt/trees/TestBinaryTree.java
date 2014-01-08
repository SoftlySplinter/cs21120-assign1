package uk.ac.aber.adb9.java.adt.trees;

import uk.ac.aber.adb9.java.adt.trees.BinaryTree;
import junit.framework.TestCase;

/**
 * @since Oct 5, 2010
 * @version 1
 * @author Alexander Brown
 */
public class TestBinaryTree extends TestCase
{
    BinaryTree tree;
    
    public TestBinaryTree(String testName)
    {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        tree = new BinaryTree();
        tree.insert(1);
        tree.insert(2);
        tree.insert(4);
        tree.insert(3);
        tree.insert(-1);        
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testSimpleRemoval()
    {
        assertTrue(tree.remove(-1));
    }

    public void testRemoval()
    {
        assertTrue(tree.remove(2));
    }

    public void testComplexRemoval()
    {
        assertTrue(tree.remove(1));
    }

    public void testTest()
    {
        tree.inOrder();
        System.out.println(tree.depth());
    }
}
