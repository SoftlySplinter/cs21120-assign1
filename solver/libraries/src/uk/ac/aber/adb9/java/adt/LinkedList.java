/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.aber.adb9.java.adt;

import uk.ac.aber.adb9.java.adt.exceptions.LinkedListOutOfBoundsException;

/**
 * Defines the Linked List <abbr title="Abstract Data Type">ADT</abbr>.
 * <p>
 * Linked Lists are theoretically unlimited (i.e. they are only limited by the amount of memory available to the program).
 * This ADT is very useful for items liked Queues and Stacks, as it is very easy to access both top and bottom of the list.
 * <p>
 * It is however more difficult to remove from the bottom of the list and to access specific elements in the middle of the list.
 * Doubly Linked Lists are useful for removing from the bottom, and Hash Tables are more effective at access specific elements.
 * For searching, Trees are a more powerful ADT.
 * 
 * <p>
 *  <b>Revisions:</b>
 * <ul style="margin-left: 27px; margin-top: 5px; margin-bottom:0px;">
 *   <li style="list-style-type:none;">Oct 5, 2010 - Added a pointer to last node to allow for easier access to the last element.</li>
 * </ul>
 * @since Oct 5, 2010
 * @version 2
 * @author Alexander Brown
 *
 * @see DoublyLinkedList
 * @see LinkedListNode 
 * @see UnboundQueue
 * @see UnboundStack
 */
public class LinkedList
{
    /**
     * The first Node in this Linked List.
     * @see LinkedListNode
     */
    private LinkedListNode firstNode = null;

    /**
     * The last Node int this Linked List
     */
    private LinkedListNode lastNode = null;
    
    /**
     * The length of the current Linked List.
     */    
    private int length = 0;

    /**
     * @param o The Object to add to the front.
     */
    public void addToFront(Object o)
    {
        LinkedListNode newFirst;
        if(length > 0)
        {
            newFirst = new LinkedListNode(o,firstNode);
        }
        else
        {
            newFirst = new LinkedListNode(o);
            //Make this the last node as well as the first.
            lastNode = newFirst;
        }
        
        firstNode = newFirst;
        length++;
    }

    /**
     * @param o The Object to add to the back.
     */
    public void addToBack(Object o)
    {
        if(length > 0)
        {
            LinkedListNode newBack = new LinkedListNode(o);
            this.getBack().setNext(newBack);
            length ++;
            lastNode = newBack;
        }
        else
        {
            addToFront(o);
        }
    }

    /**
     * @return The front Node.
     * @throws LinkedListOutOfBoundsException
     */
    public LinkedListNode removeFromFront() throws LinkedListOutOfBoundsException
    {
        LinkedListNode front = firstNode;
        if(length > 0)
        {
            firstNode = front.getNext();
            front.setNext(null);
            length--;
            return front;
        }
        else
        {
            throw new LinkedListOutOfBoundsException();
        }
    }

    /**
     * @return The back Node.
     * @throws LinkedListOutOfBoundsException
     *
     * @see #getSecondFromBack() 
     */
    public LinkedListNode removeFromBack() throws LinkedListOutOfBoundsException
    {
        if(length > 1)
        {
            LinkedListNode back = this.getBack();
            LinkedListNode newBack = this.getSecondFromBack();
            newBack.setNext(null);
            lastNode = newBack;
            length--;
            back.setNext(null);
            return back;
        }
        else
        {
            throw new LinkedListOutOfBoundsException();
        }
    }

    /**
     * @return The front Node without disturbing the List.
     */
    public LinkedListNode getFront()
    {
        return firstNode;
    }

    /**
     * @return The back Node without disturbing the List.
     */
    public LinkedListNode getBack()
    {
        return lastNode;
    }

    /**
     * @return The second to last Node.
     * @see removeFromBack()
     */
    private LinkedListNode getSecondFromBack()
    {
        LinkedListNode back= firstNode;
        LinkedListNode secondFromBack = null;
        if(length > 2)
        {
            while(back.getNext() != null)
            {
                secondFromBack = back;
                back = back.getNext();
            }
        }
        else if(length == 2)
        {
            return firstNode;
        }
        else
        {
            throw new NullPointerException();
        }

        return secondFromBack;
    }

    /**
     * @return The length of the list.
     */
    public int length()
    {
        return length;
    }

    /**
     * @return <b>true</b> if the list is empty.
     */
    public boolean isEmpty()
    {
        return length == 0;
    }

    /**
     * @return The list in a String form.
     */
    @Override
    public String toString()
    {
        String out = "";

        for(LinkedListNode current = firstNode;current != null;current.getNext())
        {
            out += current.toString()+"\n";
        }

        return out;
    }

    /**
     * @param toFind The Object to find.
     * @return <b>null</b> is the Object isn't found.
     * <br />
     * The <b>LinkedListNode</b> which the Object belongs to if it is found.
     *
     * @see Object#equals(java.lang.Object) 
     */
    public LinkedListNode find(Object toFind)
    {
        LinkedListNode current = firstNode;

        int i = 0;

        while(current != null && !(current.getData().equals(toFind)))
        {
            current = current.getNext();
        }
        return current;
    }
}
