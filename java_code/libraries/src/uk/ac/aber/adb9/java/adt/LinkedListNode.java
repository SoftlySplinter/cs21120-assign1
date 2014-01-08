/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.aber.adb9.java.adt;

/**
 * Defines a single Node in the normal Linked List <abbr title="Abstract Data Type">ADT</abbr>.
 * @since Oct 5, 2010
 * @version 1
 * @author Alexander Brown
 *
 * @see LinkedList
 */
public class LinkedListNode
{
    /**
     * The contents of the Node.
     */
    Object data;

    /**
     * The next Node in the list.
     */
    LinkedListNode next;

    /**
     * Default constructor will values of null.
     */
    public LinkedListNode()
    {
        this(null,null);
    }

    /**
     * Constructor which takes the contents of the Node, but not the next Node.
     * @param data The data to be stored in this Node.
     */
    public LinkedListNode(Object data)
    {
        this(data,null);
    }

    /**
     * Full constructor.
     * @param data The data stored in this Node.
     * @param next The next Node.
     */
    public LinkedListNode(Object data, LinkedListNode next)
    {
        this.data = data;
        this.next = next;
    }

    /**
     * @return The contents of this Node.
     */
    public Object getData()
    {
        return data;
    }

    /**
     * @param data The data to set as the contents of this Node.
     */
    public void setData(Object data)
    {
        this.data = data;
    }

    /**
     * @return The next Node in the Linked list.
     */
    public LinkedListNode getNext()
    {
        return next;
    }

    /**
     * @param next The next Node in the Linked List.
     */
    public void setNext(LinkedListNode next)
    {
        this.next = next;
    }

    /**
     * @return A String representation of the contents of this Node.
     * @see Object#toString() 
     */
    public String toString()
    {
        return data.toString();
    }
}
