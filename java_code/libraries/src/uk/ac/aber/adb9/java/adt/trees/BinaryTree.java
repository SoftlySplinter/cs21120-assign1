package uk.ac.aber.adb9.java.adt.trees;

/**
 * Defines the Binary Tree ADT, typically used for simple searches where there are very limited choices.
 * <p>
 * A Binary Tree is the simplest tree data structure wherein each node has, at most, two <em>child nodes</em>. Any node with children can be called a <em>parent node</em>. Note thaat currently the nodes of this binary tree do not point to their parent nodes.
 * <p>
 * The root node is the only node this class actually stores data about; all nodes below the root node are held in their respective parent nodes.
 *<p>
 * <b>Terminology</b>
 * <ul style="margin-left: 27px; margin-top: 5px; margin-bottom: 0px;">
 *  <li style="list-style-type:none;">Root Node: The ancestor of all nodes in the tree.</li>
 *  <li style="list-style-type:none;">Child Node: A node which the current node holds a pointer to.</li>
 *  <li style="list-style-type:none;">Parent Node: The node which holds a pointer to this node.</li>
 *  <li style="list-style-type:none;">Leaf Node: A node without child nodes.</li>
 *  <li style="list-style-type:none;">Depth: The length of the path from the root node to this node.</li>
 *  <li style="list-style-type:none;">Height: The length of the path from the root node to the deepest node in the tree.</li>
 * </ul>
 * <p>
 * <b>Revisions:</b>
 * <ul style="margin-left: 27px; margin-top: 5px; margin-bottom:0px;">
 * <li style="list-style-type:none;">
 * Oct 10, 2010 - Deprecated {@linkplain #insert(BinaryTreeNode, Comparable)} in favour of {@linkplain #insert(BinaryTreeNode, BinaryTreeNode, Comparable) } to reduce duplicated code.
 * </li>
 * </ul>
 *
 * @see BinaryHeap
 * @see BinaryTreeNode
 *
 * @since Oct 5, 2010
 * @version 2
 * @author Alexander Brown
 * @author Richard Shipman
 */
public class BinaryTree
{
    /**
     * The root node of this tree, from which all other nodes are acestors.
     *
     * @see BinaryTreeNode
     */
    BinaryTreeNode root = null;

    /**
     * Inserts an Comparable element into the Tree.
     * <p>
     * It should be noted that this is the base method that is called and which, unless the tree is empty, calls a more complex, overloaded method which decides where the node in which the element is stored.
     *
     * @param element The element to insert into a node, which will then be inserted into the tree as a node.
     *
     * @see insert(BinaryTreeNode, Comparable)
     */
    public void insert(Comparable element)
    {
        BinaryTreeNode node = new BinaryTreeNode(element);
        if(isEmpty())
        {
            root = node;
        }
        else
        {
            insert(root,node,element);
        }
    }

    /**
     * Inserts a Node into the tree.
     * <p>
     * Typically used for reinsertion of a node during removal.
     *
     * @param node The node to insert.
     *
     * @see #insert(BinaryTreeNode, BinaryTreeNode, Comparable)
     * @see #remove(BinaryTreeNode, BinaryTreeNode, char, Comparable)
     */
    private void insert(BinaryTreeNode node)
    {
        if(isEmpty())
        {
            root = node;
        }
        else
        {
            insert(root,node, node.getData());
        }
    }


    /**
     * A more complex insert method which actively decides where to insert the node which stores the input element based on the value of the element.
     * <p>
     * If the element is less than or equal to the element in the cursor node it traverses to the left of the cursor, otherwise it traverses to the right. When it reaches a cursor that is {@code null} it replaces that value with the node containing the element to store.
     * <p>
     * It should also be noted that this is a recursive function which updates the cursor as it needs to.
     *
     * @param cursor The current node to compare to. Know as the cursor.
     * @param node The node to insert into the tree.
     * @param element The data of the node. Used to save retrieving it on every call.
     *
     * @see #insert(BinaryTreeNode)
     * @see #insert(Comparable)
     */
    private void insert(BinaryTreeNode cursor, BinaryTreeNode node, Comparable element)
    {
        Comparable cursorData = cursor.getData();

        if(element.compareTo(cursorData) <= 0)
        {
            if(cursor.left() == null)
            {
                cursor.setLeft(node);
            }
            else
            {
                insert(cursor.left(),node, element);
            }
        }
        else
        {
            if(cursor.right() == null)
            {
                cursor.setRight(node);
            }
            else
            {
                insert(cursor.right(),node, element);
            }
        }
    }

    /**
     * Removes the node which stores the data matching {@code element}.
     * <p>
     * Note that this is the base method that should be called to initiate the removal procudure, and which calls more complex, overloaded methods.
     *
     * @param element The element which matches a value in the node to remove.
     * @return {@code true} - if the operation completed sucessfully.<br />{@code false} - if the element couldn't be found.
     *
     * @see #remove(BinaryTreeNode, BinaryTreeNode, char, Comparable)
     */
    public boolean remove(Comparable element)
    {
        if(isEmpty())
        {
            return false;
        }
        else
        {
            return remove(root, null, 'n', element);
        }
    }

    /**
     * The more complex remove method, which is called recursively until the desired node has been removed, or it can be decided that the element doesn't exist.
     *
     * @param cursor The current node being looked at.
     * @param parent The parent to the cursor.
     * @param direction The direction from which the cursor came from the parent.
     * @param element The Comparible element to search for.
     * @return <tt>true</tt> - if the element was found and removed.
     *
     * @see #replace(BinaryTreeNode, BinaryTreeNode, char)
     */
    private boolean remove(BinaryTreeNode cursor, BinaryTreeNode parent, char direction, Comparable element)
    {
        Comparable cursorData = cursor.getData();

        if(cursorData == element)
        {
            if((cursor.left() == null) && (cursor.right() == null))
            {
                replace(null,parent,direction);
                return true;
            }
            else if(cursor.right() == null)
            {
                replace(cursor.left(),parent,direction);
                return true;
            }
            else if(cursor.left() == null)
            {
                replace(cursor.right(), parent, direction);
                return true;
            }
            else
            {
                BinaryTreeNode left = cursor.left();
                replace(cursor.right(), parent, direction);
                insert(left);
                return true;
            }
        }
        else
        {
            if(element.compareTo(cursorData) <= 0)
            {
                if(cursor.left() != null)
                {
                    return remove(cursor.left(), cursor,'l', element);
                }
                else
                {
                    return false;
                }
            }
            else
            {
                if(cursor.right() != null)
                {
                    return remove(cursor.right(), cursor, 'r', element);
                }
                else
                {
                    return false;
                }
            }
        }
    }

    /**
     * Replaces the desired node depending on it's parent.
     *
     * @param cursor The node that will replace the desired node.
     * @param parent The parent of the desired node.
     * @param direction The direction from which the desired node came from the parent.
     *
     * @see #remove(BinaryTreeNode, BinaryTreeNode, char, Comparable)
     */
    private void replace(BinaryTreeNode cursor, BinaryTreeNode parent, char direction)
    {
        switch(direction)
        {
            case 'l':
                parent.setLeft(cursor);
                break;
            case 'r':
                parent.setRight(cursor);
                break;
            default:
                root = cursor;
        }
    }

    /**
     * @return {@code true} - if the tree contains no nodes.
     */
    public boolean isEmpty()
    {
        return root == null;
    }

    /**
     * Prints out the whole Binary Tree in order.
     *
     * @see inOrder(BinaryTreeNode)
     */
    public void inOrder()
    {
        this.inOrder(root);
    }

    /**
     * Prints out the Binary Tree in order.
     * <p>
     * Code by Richard Shipman.
     *
     * @param cursor The current node.
     */
    private void inOrder (BinaryTreeNode cursor)
    {
        if (cursor == null)
        {
            return;
        }
        inOrder(cursor.left());
        System.out.println(cursor.getData());
        inOrder(cursor.right());
    }

    /**
     * Prints the tree in the order it was entered, oldest node first.
     *
     * @see #preOrder(BinaryTreeNode) 
     */
    public void preOrder()
    {
        preOrder(root);
    }

    /**
     * Prints the tree out in the order it was entered, oldest node first.
     * @param cursor
     */
    private void preOrder(BinaryTreeNode cursor)
    {
        if(cursor==null)
        {
            return;
        }
        System.out.println(cursor.getData());
        preOrder(cursor.left());
        preOrder(cursor.right());
    }


    /**
     * Prints the tree out in the order it was entered, youngest node first.
     *
     * @see #postOrder(BinaryTreeNode)
     */
    public void postOrder()
    {
        postOrder(root);
    }


    /**
     * Prints the tree out in the order it was entered, youngest node first..
     * @param cursor The current node.
     */
    private void postOrder(BinaryTreeNode cursor)
    {
        if(cursor==null)
        {
            return;
        }
        preOrder(cursor.left());
        preOrder(cursor.right());
        System.out.println(cursor.getData());
        
    }

    public int depth()
    {
        int i=0;
        i = depthOf(root, i);
        return i;
    }

    public int depthOf(BinaryTreeNode cursor, int i)
    {
        if(cursor==null)
        {
            return i;
        }

        int left = depthOf(cursor.left(), i);
        i++;
        int right = depthOf(cursor.right(), i);

        if(left>right)
        {
            return left;
        }
        else
        {
            return right;
        }
    }


    /*
     * ====================
     * DEPRECATED METHODS
     * ====================
     */

    /**
     * A more complex insert method which actively decides where to insert the node which stores the input element based on the value of the element.
     * <p>
     * If the element is less than or equal to the element in the cursor node it traverses to the left of the cursor, otherwise it traverses to the right. When it reaches a cursor that is {@code null} it replaces that value with the node containing the element to store.
     * <p>
     * It should also be noted that this is a recursive function which updates the cursor as it needs to.
     *
     * @param cursor The current node to compare to. Know as the cursor.
     * @param element The element to insert into a node, which will then be inserted into the tree as a node.
     *
     * @see insert(BinaryTreeNode, Comparable)
     * @deprecated
     */
    private void insert(BinaryTreeNode cursor, Comparable element)
    {
        Comparable cursorData = cursor.getData();

        if(element.compareTo(cursorData) <= 0)
        {
            if(cursor.left() == null)
            {
                cursor.setLeft(new BinaryTreeNode(element));
            }
            else
            {
                insert(cursor.left(),element);
            }
        }
        else
        {
            if(cursor.right() == null)
            {
                cursor.setRight(new BinaryTreeNode(element));
            }
            else
            {
                insert(cursor.right(),element);
            }
        }
    }
}
