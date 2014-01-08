package uk.ac.aber.adb9.java.adt;

import uk.ac.aber.adb9.java.adt.exceptions.StackOutOfBoundsException;
import uk.ac.aber.adb9.java.Utils;

/**
 * The <abbr title="Abstract Data Type">ADT</abbr> for a Stack.
 * </p>
 * <p>
 * Uses the <abbr title="Last In, First Out">LIFO</abbr> model, only elements at the top of the stack can be accessed and elements can only be added to the top of the stack. A stack is one of the simplest data structures as there are very few operations which can be performed on the stack.
 * </p>
 * <p>
 * The stack keeps track of the top element, incramenting it and decramenting it as elements are pushed and popped, which stops any unecessary shuffling of the array.
 * </p>
 * <p>
 *  <b>Revisions:</b>
 * </p>
 * <ul style="margin-left: 27px; margin-top:5px;margin-bottom: 0px;">
 *   <li style="list-style-type:none;">Oct 5, 2010: Implemented Stack Interface</li>
 * </ul>
 *
 * @since Oct 4, 2010
 * @version 1
 * @author Alexander Brown
 */
public class BoundStack
implements Stack
{
    /**
     * The Stack.
     */
    private Object[] stack;

    /**
     * The depth of the Stack.
     */
    private int depth;

    /**
     * The maximum depth of the Stack.
     */
    private int maxDepth;

    /**
     * @param maxDepth The maximum depth of the Stack.
     */
    public BoundStack(int maxDepth)
    {
        this.maxDepth = maxDepth;
        stack = new Object[maxDepth];
        depth = 0;
    }

    /**
     * Defualt constructor.
     *
     * @see Utils#DEFAULT_ARRAY_SIZE
     */
    public BoundStack()
    {
        this(Utils.DEFAULT_ARRAY_SIZE);
    }

    /**
     * Adds an Object to the top of the Stack.
     * @param o The Object to push.
     * @throws StackOutOfBoundsException
     */
    public void push(Object o) throws StackOutOfBoundsException
    {
        if(depth < maxDepth)
        {
            stack[depth++] = o;
        }
        else
        {
            throw new StackOutOfBoundsException();
        }
    }

    /**
     * @return The top Object.
     * @throws StackOutOfBoundsException
     */
    public Object pop() throws StackOutOfBoundsException
    {
        if(!this.isEmpty())
        {
            return stack[--depth];
        }
        else
        {
            throw new StackOutOfBoundsException();
        }
    }

    /**
     *
     * @return The top Object without disrupting the Stack.
     * @throws StackOutOfBoundsException
     */
    public Object peek() throws StackOutOfBoundsException
    {
        if(!this.isEmpty())
        {
            return stack[depth-1];
        }
        else
        {
            throw new StackOutOfBoundsException();
        }
    }

    /**
     * @return <b>true</b> is the Stack has no elements.<br /><b>false</b> if the Stack has one or more elements.
     */
    public boolean isEmpty()
    {
        return depth == 0;
    }

    /**
     * @return The current depth of the stack.
     */
    public int depth()
    {
        return depth;
    }

    /**
     *
     * @param other The Stack to compare to.
     * @return <b>true</b> if the two Stacks are identical in terms of their Object's equals methods.<br /><b>false</b> if not.
     *
     * @see Object#equals(java.lang.Object)
     */
    public boolean equals(BoundStack other)
    {
        if(depth == other.depth())
        {
            for(int i=0;i<depth;i++)
            {
                if(stack[i].equals(other.stack[i]))
                {
                    //do nothing
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }

        return true;
    }

    /**
     * @return The Stack in String form.
     */
    @Override
    public String toString()
    {
        String s = "";

        if(this.isEmpty())
        {
            return "Stack is Empty";
        }
        else
        {
            s+= "Top Stack Item: "+stack[0].toString();
            for(int i=1;i<depth;i++)
            {
                s += "Stack Item "+i+": "+stack[i].toString();
            }
        }

        return s;
    }
}
