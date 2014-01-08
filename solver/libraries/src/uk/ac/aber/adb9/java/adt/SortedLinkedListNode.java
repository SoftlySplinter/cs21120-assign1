package uk.ac.aber.adb9.java.adt;

/**
 * @since Oct 12, 2010
 * @version 1
 * @author Alexander Brown
 */
public class SortedLinkedListNode
{
    Comparable data;

    SortedLinkedListNode next;

    public SortedLinkedListNode(Comparable data, SortedLinkedListNode next)
    {
        this.data = data;
        this.next = next;
    }

    public SortedLinkedListNode(Comparable data)
    {
        this(data,null);
    }

    public Comparable getData()
    {
        return data;
    }

    public SortedLinkedListNode getNext()
    {
        return next;
    }

    public void setNext(SortedLinkedListNode next)
    {
        this.next = next;
    }
}
