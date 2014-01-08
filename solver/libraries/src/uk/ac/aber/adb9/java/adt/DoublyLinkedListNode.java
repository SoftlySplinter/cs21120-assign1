/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.aber.adb9.java.adt;

/**
 * Defines a Node in a Doubly Linked List
 * @since Oct 5, 2010
 * @version 1
 * @author Alexander Brown
 *
 * @see DoublyLinkedList
 */
public class DoublyLinkedListNode
{
    private Object data;

    private DoublyLinkedListNode next;

    private DoublyLinkedListNode previous;

    public DoublyLinkedListNode(Object data, DoublyLinkedListNode next, DoublyLinkedListNode previous)
    {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }

    public DoublyLinkedListNode(Object data)
    {
        this(data, null, null);
    }

    public DoublyLinkedListNode()
    {
        this(null, null, null);
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    public DoublyLinkedListNode getNext()
    {
        return next;
    }

    public void setNext(DoublyLinkedListNode next)
    {
        this.next = next;
    }

    public DoublyLinkedListNode getPrevious()
    {
        return previous;
    }

    public void setPrevious(DoublyLinkedListNode previous)
    {
        this.previous = previous;
    }

    @Override
    public String toString()
    {
        return data.toString();
    }
}
