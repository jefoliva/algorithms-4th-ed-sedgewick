/******************************************************************************
 *  Compilation:  javac E31 
 *  Execution:    java E31  
 *  Dependencies: StdOut
 *  
 *  Description: 1.3.31 Implement a nested class DoubleNode for building doubly-
 *  linked lists, where each node contains a reference to the item preceding it and
 *  the item following it in the list (null if there is no such item). Then implement
 *  static methods for the following tasks: insert at the beginning, insert at the
 *  end, remove from the beginning, remove from the end, insert before a given node,
 *  insert after a given node, and remove a given node
 *
 *  Example execution:
 *  % java E31 
 *  list contents:             b d f g h
 *  iterate backwards:         h g f d b
 *  insertAtTheBeginning(a):   a b d f g h
 *  insertAtTheEnd(i):         a b d f g h i
 *  insertAfter(b, c):         a b c d f g h i
 *  insertBefore(f, e):        a b c d e f g h i
 *  removeFromTheBeginning():  b c d e f g h i
 *  removeFromTheEnd():        b c d e f g h
 *  remove(d):                 b c e f g h
 ******************************************************************************/
package chapter_1.section_3;

import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * @author jefoliva
 */

public class E31 {
    public static void main(String[] args) {
    	DoublyLinkedList.main(args);
    }
}

class DoublyLinkedList<Item> implements Iterable<Item> {
    private DoubleNode first;
    private DoubleNode last;
    int N;

    private class DoubleNode {
        Item item;
        DoubleNode next;
        DoubleNode prev;
    }

    public boolean isEmpty() { return first == null; }
    public int size() { return N; }
    public DoubleNode first() { return first; }
    public DoubleNode last() { return last; }

    public void add(Item item) {
        insertAtTheEnd(item);
    }

    public void insertAtTheBeginning(Item item) {
        if(isEmpty()) {
        	first = new DoubleNode();
            first.item = item;
            first.next = null;
            first.prev = null;
            last = first;
        } else {
	        DoubleNode oldfirst = first;
	        first = new DoubleNode();
	        first.item = item;
	        first.next = oldfirst;
	        first.prev = null;
	        oldfirst.prev = first;	
        }
        N++;
    } 	

    public void insertAtTheEnd(Item item) {
        if(isEmpty()) {
        	last = new DoubleNode();
            last.item = item;
            last.next = null;
            last.prev = null;
            first = last;
        } else {
	        DoubleNode oldlast = last;
	        last = new DoubleNode();
	        last.item = item;
	        last.next = null;
	        last.prev = oldlast;
	        oldlast.next = last;
        }
        N++;
    }

    public Item removeFromTheBeginning() {
        if(isEmpty()) throw new NoSuchElementException("List Underflow");
        Item item;
        if(size() == 1) {
        	item = first.item;
        	first = null;
        	last = null;
        } else {
	        item = first.item;
	        first = first.next;
	        first.prev = null;
        }
        N--;
        return item;
    }

    public Item removeFromTheEnd() {
        if(isEmpty()) throw new NoSuchElementException("List Underflow");
        Item item;
        if(size() == 1) {
        	item = last.item;
        	last = null;
        	first = last;
        } else {
	        item = last.item;
	        last = last.prev;
	        last.next = null;
        }
        N--;
        return item;
    }

    public void insertAfter(Item item, Item newitem) {
        if(isEmpty()) return;

        DoubleNode current = first;
        while(current != null) {
            if(current.item == item) {
                DoubleNode newnode = new DoubleNode();
                newnode.item = newitem;
                newnode.next = current.next;
                newnode.prev = current;
                if(current.next != null) current.next.prev = newnode;
                else                     last = newnode;
                current.next = newnode;
                N++;
                break;
            }
            current = current.next;
        }
    }

    public void insertBefore(Item item, Item newitem) {
    	if(isEmpty()) return;

    	DoubleNode current = first;
    	while(current != null) {
    		if(current.item == item) {
    			DoubleNode newnode = new DoubleNode();
    			newnode.item = newitem;
    			newnode.next = current;
                newnode.prev = current.prev;
                if(current.prev != null) current.prev.next = newnode;
                else                     first = newnode;
                current.prev = newnode;
                N++;
		    	break;
    		}
    		current = current.next;
    	}   
    }

    public void remove(Item item) {
        if(isEmpty()) throw new NoSuchElementException("List is Empty");    

        DoubleNode head = first;

        // if first element in list equals to item, remove
        if(head.item.equals(item)) {
            first = first.next;
            if(first.prev != null) first.prev = null;
            N--;
        }

        while(head.next != null) {
            if(head.next.item.equals(item)) {
                head.next = head.next.next;
                if(head.next != null) head.next.prev = head;
                else                  last = head;
                N--;    
                continue;
            }
            head = head.next;
        }
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        DoubleNode current = first;
        private int size = N;

        public boolean hasNext() { return current != null; }
        public void remove() {}
        
        public Item next() { 
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
    	var doublyList = new DoublyLinkedList<String>();
        doublyList.add("b");
        doublyList.add("d");
        doublyList.add("f");
        doublyList.add("g");
        doublyList.add("h");

        StdOut.print("list contents:             ");
        for(String item : doublyList) 
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("iterate backwards:         ");
        var last = doublyList.last();
        while(last != null) {
            StdOut.print(last.item + " ");
            last = last.prev;
        }
        StdOut.println();

        StdOut.print("insertAtTheBeginning(a):   ");
        doublyList.insertAtTheBeginning("a");
        for(String item : doublyList) 
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("insertAtTheEnd(i):         ");
        doublyList.insertAtTheEnd("i");
        for(String item : doublyList) 
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("insertAfter(b, c):         ");
        doublyList.insertAfter("b", "c");
        for(String item : doublyList) 
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("insertBefore(f, e):        ");
        doublyList.insertBefore("f", "e");
        for(String item : doublyList) 
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("removeFromTheBeginning():  ");
        doublyList.removeFromTheBeginning();
        for(String item : doublyList) 
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("removeFromTheEnd():        ");
        doublyList.removeFromTheEnd();
        for(String item : doublyList) 
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("remove(d):                 ");
        doublyList.remove("d");
        for(String item : doublyList) 
            StdOut.print(item + " ");
        StdOut.println();
    }
}