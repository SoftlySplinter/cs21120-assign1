package uk.ac.aber.adb9.java.adt.trees;

/**
 * @since Oct 10, 2010
 * @version 1
 * @author Alexander Brown
 */
public class AVLNode
extends BinaryTreeNode
implements AVLBalance
{
    private int balanceFactor;

    public AVLNode(Comparable data, AVLNode left, AVLNode right)
    {
        super(data,left,right);
    }

    public AVLNode(Comparable data)
    {
        super(data);
    }

    public void setBalance(int balance)
    {
        this.balanceFactor = balance;
    }

    public int getBalance()
    {
        if(balanceFactor < LEFT_HIGH)
        {
            return -1;
        }
        else if(balanceFactor > RIGHT_HIGH)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public boolean isUnbalanced()
    {
        return (balanceFactor < LEFT_HIGH) && (balanceFactor < RIGHT_HIGH);
    }
}
