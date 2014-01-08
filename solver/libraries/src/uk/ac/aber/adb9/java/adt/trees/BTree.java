package uk.ac.aber.adb9.java.adt.trees;

/**
 *
 * B Trees have the following rules:
 * </p>
 * <ol>
 * <li>The number of keys in each non-leaf node is one less than the number of its children and these keys partition the keys in the children in the fashion of a search tree</li>
 * <li>All leaves are on the same level</li>
 * <li>All non-leaf nodes except the root have at least [m/2] children</li>
 * <li>The root is either a leaf node, or it has from two to m children</li>
 * <li>A leaf node contains no more than m – 1 keys</li>
 * </ol>
 * <p>
 * The number m should always be odd
 *
 *
 * @since Oct 12, 2010
 * @version 1
 * @author Alexander Brown
 */
public class BTree
{

    private int order;

    private BTreeNode root;

    public BTree(int order)
    {
        this.order = order-(1*((order+1)%2));
        root = new BTreeNode(this.order);
    }

    public void insert(Comparable key)
    {
        if(isEmpty())
        {
            root.insert(key);
        }
        else
        {
            insert(key, root, null, 'n');
        }
        
        
    }

    private boolean isEmpty()
    {
        return root.isEmpty();
    }

    private void insert(Comparable key, BTreeNode cursor, BTreeNode parent, char parentDirection)
    {
        int direction = root.compareTo(key);
        if(direction < 0)
        {
            System.out.println("Moving left");
            insert(key,cursor.getLeft(), cursor, 'l');
        }
        else if(direction > 0)
        {
            System.out.println("Moving right");
            insert(key, cursor.getRight(), cursor, 'r');
        }
        else
        {
            if(cursor.insert(key))
            {
                System.out.println("Insertion Sucessful.");
            }
            else
            {
                System.out.println("Splitting Cursor Node.");
                switch(parentDirection)
                {
                    case 'l':
                        break;
                    case 'r':
                        break;
                    default:
                        //root = new BTreeNode(order,cursor.splitLeft(),cursor.splitRight());
                        root.insert(key);
                }
            }
        }
    }
}
