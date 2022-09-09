/********************************************************
* AUTHOR: LUKE SIMPSON 20171025                         *
* PURPOSE: Create a LinkedList ADT                      *
* DATE CREATED: 23/03/2021                              *
* LAST MODIFIED: 10/05/2021                             *
*********************************************************
*
* REFERENCES:
*
* File DSALinkedList.java 
* comprised of prevously submitted code by Luke Simpson in
* practical 4 - Linked Lists and Iterators - DSA COMP1002
*
*********************************************************/
import java.util.*;
import java.io.*;

class DSALinkedList implements Iterable, Serializable
{

    //Classfields
    private DSAListNode head;
    private DSAListNode tail;
    private int count;

    //Default constructor 
    public DSALinkedList()
    {
        head = null; 
        tail = null;
        count = 0;
    }

    /****************************************************
     * NAME: insertFirst
     * PURPOSE: insert value at front of list
     * IMPORT: newValue (Object)
     * EXPORT: none
     ***************************************************/
    public void insertFirst(Object newValue)
    {
        DSAListNode node = new DSAListNode(newValue);

        if(isEmpty())
        {
            head = node;
            tail = node;
            count++;
        }
        else
        {
            head.setPrev(node);
            node.setNext(head);
            head = node;
            count++;
        }
    }

    /****************************************************
     * NAME: insertLast
     * PURPOSE: insert value at end of list
     * IMPORT: newValue (Object)
     * EXPORT: none
     ***************************************************/
    public void insertLast(Object newValue)
    {
        DSAListNode node = new DSAListNode(newValue);

        if(isEmpty())
        {
            head = node;
            tail = node;
            count++;
        }
        else
        {
            tail.setNext(node);
            node.setNext(null);
            node.setPrev(tail);
            tail = node;
            count++;
        }
    }

    //accesoor
    public boolean isEmpty()
    {
        return (head == null);
    }

    public int length()
    {
        return (count);
    }

    /****************************************************
     * NAME: peekFirst
     * PURPOSE: view item at front of list
     * IMPORT: none
     * EXPORT: nodeValue (Object)
     ****************************************************/
    public Object peekFirst()
    {
        Object nodeValue;

        if(isEmpty())
        {
            throw new IllegalArgumentException("List is empty!");        
        }
        else
        {
            nodeValue = head.getValue();        
        }

        return nodeValue;
    }

    /****************************************************
     * NAME: peekLast
     * PURPOSE: view item at end of list
     * IMPORT: none
     * EXPORT: nodeValue (Object)
     ****************************************************/
    public Object peekLast()
    {
        Object nodeValue;

        if(isEmpty())
        {
            throw new IllegalArgumentException("List is empty!");
        }
        else
        {
            nodeValue = tail.getValue();        
        }

        return nodeValue;
    }

    /****************************************************
     * NAME: removeFirst
     * PURPOSE: remove item at front of list
     * IMPORT: none
     * EXPORT: nodeValue (Object)
     **************************************************/
    public Object removeFirst()
    {
        Object nodeValue;

        if(isEmpty())
        {
            throw new IllegalArgumentException("List is empty!");        
        }
        
        nodeValue = head.getValue();

        if(head.getNext() == null)
        {
            tail = null;        
        }
        else
        {
            head.setPrev(null);        
        }
        head = head.getNext();

        count--;

        return nodeValue;
    }

    /****************************************************
     * NAME: nodeValue
     * PURPOSE: remove item at end of list
     * IMPORT: none
     * EXPORT: nodeValue (Object)
     ****************************************************/
    public Object removeLast()
    {
        Object nodeValue;

        if(isEmpty())
        {
            throw new IllegalArgumentException("List is empty!");
        }
        
        nodeValue = tail.getValue();

        if(head.getNext() == null)
        {
            head = null;
        }
        else
        {
            tail.setNext(null);        
        }
        tail = tail.getPrev();

        count--;

        return nodeValue;
    }

    public Iterator iterator()
    {
        return new DSALinkedListIterator(this);        
    }

    private class DSALinkedListIterator implements Iterator
    {
        private DSAListNode iterNext;

        public DSALinkedListIterator(DSALinkedList theList)
        {
            iterNext = theList.head;        
        }

        //iterator interface implementation
        public boolean hasNext()
        {
            return iterNext != null;        
        }

        public Object next()
        {
            Object value;

            if(iterNext == null)
            {
                value = null;
            }
            else
            {
                value = iterNext.getValue();
                iterNext = iterNext.getNext();
            }

            return value;
        }

        public void remove()
        {
            throw new UnsupportedOperationException("Not supported");        
        }
    }

    private class DSAListNode implements Serializable
    {
        //Classfields
        private Object value;
        private DSAListNode next;
        private DSAListNode prev;

        //Constructor
        public DSAListNode(Object inValue)
        {
            value = inValue;
            next = null;
            prev = null;
        }

        //accessors
        public Object getValue()
        {
            return value;        
        }

        public DSAListNode getNext()
        {
            return next;
        }

        public DSAListNode getPrev()
        {
            return prev;        
        }

        //mutators
        public void setValue(Object inValue)
        {
            value = inValue;        
        }

        public void setNext(DSAListNode newNext)
        {
            next = newNext;        
        }

        public void setPrev(DSAListNode newPrev)
        {
            prev = newPrev;        
        }
    }
}
