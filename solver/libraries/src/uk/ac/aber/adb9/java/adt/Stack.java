/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.aber.adb9.java.adt;

import uk.ac.aber.adb9.java.adt.exceptions.StackOutOfBoundsException;

/**
 * Interface for the Stack ADT.
 * @since Oct 5, 2010
 * @version 1
 * @author Alexander Brown
 */
public interface Stack
{
    public void push(Object o) throws StackOutOfBoundsException;

    public Object pop() throws StackOutOfBoundsException;

    public Object peek() throws StackOutOfBoundsException;

    public boolean isEmpty();

    public int depth();

    @Override
    public String toString();
}
