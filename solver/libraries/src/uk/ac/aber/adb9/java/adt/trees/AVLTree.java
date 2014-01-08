package uk.ac.aber.adb9.java.adt.trees;

/**
 * @since Oct 10, 2010
 * @version 1
 * @author Alexander Brown
 */
public class AVLTree
{
    private AVLNode root = null;

    public void insert(Comparable element)
    {
        AVLNode node = new AVLNode(element);
        if(isEmpty())
        {
            root = node;
            root.setBalance(AVLBalance.EQUAL_HIGH);
        }
        else
        {
            insert(root, node, element);
        }
        AVLNode unbalancedNode = checkBalance();
        if(unbalancedNode != null)
        {
            rebalance(unbalancedNode);
        }
        else
        {
            //System.out.println("No Unbalanced Nodes after inserting "+element);
        }
    }

    public void insert(AVLNode node)
    {
        Comparable element = node.getData();
        if(isEmpty())
        {
            root = node;
            root.setBalance(AVLBalance.EQUAL_HIGH);
        }
        else
        {
            insert(root, node, element);
        }
    }

    private void insert(AVLNode cursor, AVLNode node, Comparable element)
    {
        Comparable cursorData = cursor.getData();

        if(cursorData.compareTo(element) <= 0)
        {
            if(cursor.left() == null)
            {
                cursor.setLeft(node);
            }
            else
            {
                insert((AVLNode) cursor.left(), node, element);
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
                insert((AVLNode) cursor.right(), node, element);
            }
        }
    }/**
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
    private boolean remove(AVLNode cursor, AVLNode parent, char direction, Comparable element)
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
                replace((AVLNode)cursor.left(),parent,direction);
                return true;
            }
            else if(cursor.left() == null)
            {
                replace((AVLNode)cursor.right(), parent, direction);
                return true;
            }
            else
            {
                AVLNode left = (AVLNode) cursor.left();
                replace((AVLNode)cursor.right(), parent, direction);
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
                    return remove((AVLNode) cursor.left(), cursor,'l', element);
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
                    return remove((AVLNode) cursor.right(), cursor, 'r', element);
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
    private void replace(AVLNode cursor, AVLNode parent, char direction)
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


    public boolean isEmpty()
    {
        return root==null;
    }

    private AVLNode checkBalance()
    {
        if(!isEmpty())
        {
            return checkBalance(root);
        }
        else
        {
            return null;
        }
    }

    private AVLNode checkBalance(AVLNode cursor)
    {
        int leftBalance;
        int rightBalance;
        AVLNode left = (AVLNode) cursor.left();
        AVLNode right = (AVLNode) cursor.right();
        
        if(left != null)
        {
            leftBalance = depthOf(left);
        }
        else
        {
            leftBalance = 0;
        }

        if(right != null)
        {
            rightBalance = depthOf(right);
        }
        else
        {
            rightBalance = 0;
        }

        //System.out.println("Node containing: "+cursor.getData()+"\nDepth of left: "+(leftBalance)+"\nDepth of right: "+(rightBalance)+"\n***");

        cursor.setBalance(rightBalance-leftBalance);

        if(cursor.isUnbalanced())
        {
            return cursor;
        }
        else
        {
            if(left != null)
            {
                left = checkBalance(left);
            }
            if(right != null)
            {
                right = checkBalance(right);
            }
        }

        if(left != null)
        {
            return left;
        }
        else if(right != null)
        {
            return right;
        }
        else
        {
            return null;
        }
    }

    private void rebalance(AVLNode unbalanced)
    {
        int direction = unbalanced.getBalance();

        AVLNode pivot = null;
        AVLNode swap = null;
        AVLNode parent = getParent(unbalanced, root, null);
        char parentDirection = getDirection(parent, unbalanced);

        if(direction < AVLBalance.LEFT_HIGH)
        {
            pivot = (AVLNode) unbalanced.right();
            swap = (AVLNode) pivot.left();

            unbalanced.setRight(swap);
            pivot.setLeft(unbalanced);
        }
        else
        {
            pivot = (AVLNode) unbalanced.left();
            swap = (AVLNode) pivot.right();

            unbalanced.setLeft(swap);
            pivot.setRight(unbalanced);
        }

        switch(parentDirection)
        {
            case 'l':
                parent.setLeft(pivot);
                break;
            case 'r':
                parent.setRight(pivot);
                break;
            default:
                root = pivot;
        }

        unbalanced = checkBalance();
        if(unbalanced != null)
        {
            rebalance(unbalanced);
        }
    }

    public int depth()
    {
        return depthOf(root);
    }

    public int depthOf(AVLNode cursor)
    {
        int i=0;
        i = depthOf(cursor, i);
        return i;
    }

    public int depthOf(BinaryTreeNode cursor, int i)
    {
        if(cursor==null)
        {
            return i;
        }

        i++;
        int left = depthOf(cursor.left(), i);
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

    /**
     * Prints out the whole Binary Tree in order.
     *
     * @see inOrder(BinaryTreeNode)
     */
    public void inOrder()
    {
        this.inOrder(root);
        System.out.println("");
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
        System.out.print(" (");
        inOrder(cursor.right());
        System.out.print(cursor.getData());
        inOrder(cursor.left());
        System.out.print(") ");
    }

    private char getDirection(AVLNode parent, AVLNode child)
    {
        if(parent == null)
        {
            return 'n';
        }
        else if(parent.left() == child)
        {
            return 'l';
        }
        else if(parent.right() == child)
        {
            return 'r';
        }
        else
        {
            return ' ';
        }
    }

    private AVLNode getParent(AVLNode node, AVLNode cursor, AVLNode parent)
    {
        if(cursor == node)
        {
            return parent;
        }
        else
        {
            try
            {
                AVLNode left = getParent(node, (AVLNode) cursor.left(), cursor);
                AVLNode right = getParent(node,(AVLNode) cursor.right(), cursor);

                if(left != null)
                {
                    return left;
                }
                if(right != null)
                {
                    return right;
                }
                else
                {
                    return null;
                }
            }
            catch(NullPointerException e)
            {
                return null;
            }
        }
    }
}
