package uk.ac.aber.adb9.java.adt;

import uk.ac.aber.adb9.java.adt.exceptions.LinkedListOutOfBoundsException;
import uk.ac.aber.adb9.java.adt.exceptions.StackOutOfBoundsException;

/**
 * A Stack ADT without an upper limit.
 * @since Oct 5, 2010
 * @version 1
 * @author Alexander Brown
 *
 * @see LinkedList
 */
public class UnboundStack
implements Stack
{
    private LinkedList stack = new LinkedList();

    public void push(Object o) throws StackOutOfBoundsException
    {
        stack.addToFront(o);
    }

    public Object pop() throws StackOutOfBoundsException
    {
        try
        {
            return stack.removeFromFront().getData();
        }
        catch (LinkedListOutOfBoundsException ex)
        {
            throw new StackOutOfBoundsException(ex.getMessage());
        }
    }

    public Object peek() throws StackOutOfBoundsException
    {
        if(this.depth() > 0)
        {
            return stack.getFront().getData();
        }
        else
        {
            throw new StackOutOfBoundsException();
        }
    }

    public boolean isEmpty()
    {
        return stack.isEmpty();
    }

    public int depth()
    {
        return stack.length();
    }
}
