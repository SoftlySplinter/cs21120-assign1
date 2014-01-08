package uk.ac.aber.adb9.java.adt.trees;

/**
 *
 * Needs serious work.
 *
 * @since Oct 12, 2010
 * @version 1
 * @author Alexander Brown
 */
class BTreeNode
{
    private Comparable[] keys;
    private int size;
    
    private BTreeNode left;
    private BTreeNode right;

    public BTreeNode(int order, BTreeNode left, BTreeNode right)
    {
        keys = new Comparable[order];
        size = 0;
        
        this.left = left;
        this.right = right;
    }
    
    public BTreeNode(int order)
    {
        this(order,null,null);
    }

    public boolean insert(Comparable key)
    {
        if(size+1 < keys.length)
        {
            keys[size] = key;
            size++;
            sort();
            return true;
        }
        else
        {
            return false;
        }
    }

    private void sort()
    {
        for(int i=0;i<size;i++)
        {
           boolean swapped = false;
            for(int j=0;j<size;j++)
            {
                if(j!=i)
                {
                    if(keys[i].compareTo(keys[j]) < 0)
                    {
                        Comparable temp = keys[i];
                        keys[i] = keys[j];
                        keys[j] = temp;
                        swapped = true;
                    }
                }
                if(!swapped)
                {
                    return;
                }
            }
        }
    }

    public BTreeNode getLeft()
    {
        return left;
    }

    public BTreeNode getRight()
    {
        return right;
    }

    

    public int compareTo(Comparable key)
    {
            if(key.compareTo(keys[0]) < 0 && left != null)
            {
                return -1;
            }
            if(key.compareTo(keys[size-1]) > 0 && right != null)
            {
                return 1;
            }

            return 0;
    }

    public boolean isEmpty()
    {
        return size==0;
    }
}
