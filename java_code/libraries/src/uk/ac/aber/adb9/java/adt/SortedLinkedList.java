package uk.ac.aber.adb9.java.adt;

import uk.ac.aber.adb9.java.adt.exceptions.LinkedListOutOfBoundsException;

/**
 * @since Oct 12, 2010
 * @version 1
 * @author Alexander Brown
 */
public class SortedLinkedList
{
    private SortedLinkedListNode first;

    private int length;

    public void insert(Comparable item)
    {
        if(isEmpty())
        {
            first = new SortedLinkedListNode(item);
        }
        else
        {
            if(first.getData().compareTo(item) < 0)
            {
                first = new SortedLinkedListNode(item, first);
            }
            else
            {
                insert(item, first);
            }
        }

        length++;
    }

    private void insert(Comparable item, SortedLinkedListNode cursor)
    {
        SortedLinkedListNode next = cursor.getNext();

        if(next == null || next.getData().compareTo(item) < 0)
        {
            SortedLinkedListNode insert = new SortedLinkedListNode(item, next);
            cursor.setNext(insert);
        }
        else
        {
            insert(item, next);
        }
    }

    public Comparable remove() throws LinkedListOutOfBoundsException
    {
        if(!isEmpty())
        {
            SortedLinkedListNode temp = first;
            first = first.getNext();
            return temp.getData();
        }
        else
        {
            throw new LinkedListOutOfBoundsException();
        }
    }

    public Comparable getFirst() throws LinkedListOutOfBoundsException
    {
        if(!isEmpty())
        {
            return first.getData();
        }
        else
        {
            throw new LinkedListOutOfBoundsException();
        }
    }

    public int length()
    {
        return length;
    }

    public boolean isEmpty()
    {
        return length==0;
    }
}
