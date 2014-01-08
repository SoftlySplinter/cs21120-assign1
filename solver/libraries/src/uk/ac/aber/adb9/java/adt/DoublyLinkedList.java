package uk.ac.aber.adb9.java.adt;

import uk.ac.aber.adb9.java.adt.exceptions.LinkedListOutOfBoundsException;

/**
 * Defines the Doubly Linked List ADT.
 * <p>
 * Doubly Linked Lists act similary to linked lists. The main difference between between the two is that the nodes in a doubly linked list contain a pointer to both the next and previous node, rather than just the next.
 * <p>
 * This allows for easier removal from the back of the queue as it doesn't have to iterate through the entire list to find the second last element, unlike the linked list.
 * <p>
 * Though the list has no hard limits, it is still limited by the amount of memory avialable. As each node holds a pointer to two other nodes it is more limited in size the the normal linked list
 *
 * @since Oct 5, 2010
 * @version 1
 * @author Alexander Brown
 *
 * @see DoublyLinkedListNode
 * @see LinkedList
 */
public class DoublyLinkedList
{
    /**
     * A pointer to the first node in the list.
     * @see DoublyLinkedListNode
     */
    private DoublyLinkedListNode first;

    /**
     * A pointer to the last node in the list.
     * @see DoublyLinkedListNode
     */
    private DoublyLinkedListNode last;

    /**
     * The length of the list. Initilised to {@code 0}.
     */
    private int length = 0;

    /**
     * Adds a node containing {@code data} to the front of the list.
     * @param data The data to contain within a node.
     */
    public void addToFront(Object data)
    {
        DoublyLinkedListNode temp;
        if(length > 0)
        {
            temp = new DoublyLinkedListNode(data,first.getNext(),null);
            first.setPrevious(temp);
        }
        else
        {
            temp = new DoublyLinkedListNode(data);
            last = temp;
        }
        first = temp;
        length++;
    }

    /**
     * Adds a node containing {@code data} to the back of the list.
     * @param data The data to contain within a node.
     */
    public void addToBack(Object data)
    {
        if(length > 0)
        {
            DoublyLinkedListNode temp = new DoublyLinkedListNode(data,null,last);
            last.setNext(temp);
            last = temp;
            length++;
        }
        else
        {
            addToFront(data);
        }
    }


    /**
     * Removes a node from the front of the list and returns it. Done to allow ease of implmentations of queues and stacks.
     * @return The node at the front of the list.
     * @throws LinkedListOutOfBoundsException If the list is empty (and therefore can't remove any elements).
     */
    public DoublyLinkedListNode removeFromFront() throws LinkedListOutOfBoundsException
    {
        if(length > 0)
        {
            DoublyLinkedListNode temp = first;
            first = temp.getNext();
            if(first!=null)
            {
                first.setPrevious(null);
            }
            temp.setNext(null);
            
            length--;

            return temp;
        }
        else
        {
            throw new LinkedListOutOfBoundsException();
        }
    }

    /**
     * Removes a node from the back of the list and returns it. Done to allow ease of implmentations of queues and stacks.
     * @return The node at the back of the list.
     * @throws LinkedListOutOfBoundsException If the list is empty (and therefore can't remove any elements).
     */
    public DoublyLinkedListNode removeFromBack() throws LinkedListOutOfBoundsException
    {
        if(length > 0)
        {
            DoublyLinkedListNode temp = last;

            last = temp.getPrevious();
            if(last!=null)
            {
                last.setNext(null);
            }

            length--;

            temp.setPrevious(null);
            return temp;
        }
        else
        {
            throw new LinkedListOutOfBoundsException();
        }
    }

    /**
     * @return The node at the front of the list, without disturbing the list.
     */
    public DoublyLinkedListNode getFront()
    {
        return first;
    }

    /**
     * @return The node at the back of the list, without distrubing the list.
     */
    public DoublyLinkedListNode getBack()
    {
        return last;
    }

    /**
     * @return The length of the list.
     */
    public int length()
    {
        return length;
    }

    /**
     * @return {@code true} - If the list is empty.
     */
    public boolean isEmpty()
    {
        return length == 0;
    }

    /**
     * @return The linked list as a String value.
     * 
     */
    @Override
    public String toString()
    {
        String out = "";

        for(DoublyLinkedListNode current=first;current!=null;current.getNext())
        {
            out+=current.toString()+"\n";
        }

        return out;
    }

    /**
     * Finds an Object in the list.
     * <p>
     * Note that it uses the Object's equals method to evaluate the equality of them, not the <tt>==</tt> operator.
     *
     * @param toFind The Object to find in the list.
     * @return The Node containing the Object if it's found, <tt>null</tt> if it's not found.
     *
     * @see Object#equals(Object)
     */
    public DoublyLinkedListNode find(Object toFind)
    {
        DoublyLinkedListNode current = first;

        int i = 0;

        while(current != null && !(current.getData().equals(toFind)))
        {
            current = current.getNext();
        }
        
        return current;
    }
}
