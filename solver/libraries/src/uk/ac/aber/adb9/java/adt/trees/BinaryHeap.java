package uk.ac.aber.adb9.java.adt.trees;

import uk.ac.aber.adb9.java.Utils;
import uk.ac.aber.adb9.java.adt.exceptions.HeapOutOfBoundsException;

/**
 * A Bound Binary Heap ADT.
 * </p>
 * <p>
 * A heap data structure, created using a Binary Tree. However in the interests of memory management, it is mapped to an array rather than a proper Binary Tree.
 * </p>
 * <p>
 * In addition there are two constraints:
 * <ol style="margin-left: 20px; margin-top: 10px; margin-bottom:0px;">
 * <li>All levels of the tree, excluding the deepest, must be fully filled.</li>
 * <li>Each node is greater than, or equal to each of it's children.</li>
 * </ol>
 * <p>
 * It should also be noted that nodes are sorted immediately after they are inserted or removed.
 * </p>
 * <p>
 * <b>Revisions:</b>
 * <ul style="margin-left: 27px; margin-top: 5px; margin-bottom:0px;">
 * <li style="list-style-type:none;">
 * Oct 11, 2010 - Improved removal method, renamed sort to {@linkplain bubbleUp(int)} and added {@linkplain bubbleDown(int)} method as part of this.
 * </li>
 * </ul>
 *
 * @since Oct 11, 2010
 * @version 2
 * @author Alexander Brown
 *
 * @see BinaryTree
 * @see uk.ac.aber.adb9.java.adt.queues.BoundPriorityQueue
 */
public class BinaryHeap
{
    /**
     * The heap, mapped as an array.
     */
    private Comparable[] heap;

    /**
     * The current size of the heap.
     */
    private int size;

    /**
     * Full Constructor.
     * @param maxSize The maximum size of the heap.
     */
    public BinaryHeap(int maxSize)
    {
        heap = new Comparable[maxSize+0];
        size = 0;
    }

    /**
     * Default constructor using the deafualt array size to define the maximum size of the heap.
     * @see Utils#DEFAULT_ARRAY_SIZE
     */
    public BinaryHeap()
    {
        this(Utils.DEFAULT_ARRAY_SIZE);
    }

    /**
     * Inserts an element into the heap, retaining the order of the heap.
     *
     * @param element The element to insert.
     * @throws HeapOutOfBoundsException If the heap goes above it's limits.
     *
     * @see bubbleUp(int)
     */
    public void insert(Comparable element) throws HeapOutOfBoundsException
    {
        if(size<heap.length)
        {
            heap[size] = element;
            bubbleUp(size);
            size++;
        }
        else
        {
            throw new HeapOutOfBoundsException();
        }
    }

    /**
     * Removes the top element of the heap and returns it.
     * @return The top element of the heap.
     * @throws HeapOutOfBoundsException If the heap goes below it's limits.
     *
     * @see #bubbleDown(int) 
     */
    public Comparable remove() throws HeapOutOfBoundsException
    {
        if(!isEmpty())
        {
            Comparable top = heap[0];
            size--;
            heap[0] = heap[size];

            bubbleDown(0);

            return top;
        }
        else
        {
            throw new HeapOutOfBoundsException();
        }
    }

    /**
     * Moves an node up the heap if it's larger than it's parent. Recurses until the proper place for the node is found.
     *
     * @param loc The position in the array of the node.
     *
     * @see #swap(int, int) 
     */
    private void bubbleUp(int loc)
    {
        int parent = (loc-1)/2;
        int largest = parent;

        if(loc <= size && heap[loc].compareTo(heap[parent]) > 0)
        {
            largest = loc;
        }

        if(largest != parent)
        {
            swap(parent,largest);
            bubbleUp(parent);
        }
    }

    /**
     * Moves a node down if it's less than either of it's children. It also selects the largest of the two children to switch with if both should be larger than their parent.
     * @param loc The position in the array of the node.
     *
     * @see #swap(int, int)
     */
    private void bubbleDown(int loc)
    {
        int left = (loc*2)+1;
        int right = (loc*2)+2;

        int largest = loc;

        if(left <= size && heap[left].compareTo(largest) > 0)
        {
            largest = left;
        }
        if(right <= size && heap[right].compareTo(largest) > 0)
        {
            largest = right;
        }

        if(largest!=loc)
        {
            swap(loc,largest);
            bubbleDown(largest);
        }

    }

    /**
     * @return The top element without removal.
     */
    public Comparable getTop() throws HeapOutOfBoundsException
    {
        if(!isEmpty())
        {
            return heap[0];
        }
        else
        {
            throw new HeapOutOfBoundsException();
        }
    }

    /**
     * @return <tt>true</tt> - If the heap is empty.
     */
    public boolean isEmpty()
    {
        return size==0;
    }

    public int size()
    {
        return size;
    }

    /**
     * Swaps two elements in the heap.
     * @param o The original element.
     * @param n The element to swap the original element with.
     */
    private void swap(int o, int n)
    {
        Comparable temp = heap[o];
        heap[o] = heap[n];
        heap[n] = temp;
    }
}
