package uk.ac.aber.adb9.java.adt.trees;

/**
 * Defines a single node in a Binary Tree. Can hold pointers for up to two child nodes.
 *
 * @since Oct 5, 2010
 * @version 1
 * @author Alexander Brown
 *
 * @see BinaryTree
 */
public class BinaryTreeNode
{
    /**
     * The data held by this node.
     *
     * @see Comparable
     */
    private Comparable data;

    /**
     * The child node to the left of this node.
     *
     * @see BinaryTreeNode
     */
    private BinaryTreeNode left;

    /**
     * The child node to the right of this node.
     *
     * @see BinaryTreeNode
     */
    private BinaryTreeNode right;

    /**
     * Full constructor.
     *
     * @param data The data to be held by this node.
     * @param left The child node to the left of this node.
     * @param right The child node to the right of this node.
     */
    public BinaryTreeNode(Comparable data, BinaryTreeNode left, BinaryTreeNode right)
    {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    /**
     * Typical constructor which initilises the data held by this node, but not any of the children nodes. These are set to {@code null} so that the insert method in the Binary Tree.
     *
     * @see BinaryTree#insert(Comparable)
     * @param data The data to be held by this node.
     */
    public BinaryTreeNode(Comparable data)
    {
        this(data,null,null);
    }

    /**
     * Simple constructor which sets all instance variables to {@code null} (both data and children). Should not be used except for possibly initilising the root node.
     */
    public BinaryTreeNode()
    {
        this(null,null,null);
    }

    /**
     * @return The data contained within this node.
     */
    public Comparable getData()
    {
        return data;
    }

    /**
     * @return The child node to the left of this node.
     */
    public BinaryTreeNode left()
    {
        return left;
    }

    /**
     * @return The child node to the right of this node.
     */
    public BinaryTreeNode right()
    {
        return right;
    }

    /**
     * Sets the child node to the left of this node.
     *
     * @param left The child to add to the left.
     */
    public void setLeft(BinaryTreeNode left)
    {
        this.left = left;
    }


    /**
     * Sets the child node to the right of this node.
     *
     * @param right The child to add to the right.
     */
    public void setRight(BinaryTreeNode right)
    {
        this.right = right;
    }

    void setData(Comparable element)
    {
        this.data = element;
    }
}
