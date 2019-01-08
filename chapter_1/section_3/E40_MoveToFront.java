/******************************************************************************
 *  Compilation:  javac E40_MoveToFront 
 *  Execution:    java E40_MoveToFront  
 *  Dependencies: StdOut
 *  
 *  Description: 1.3.40 Move-to-front. Read in a sequence of characters from 
 *	standard input and maintain the characters in a linked list with no duplicates.
 *	When you read in a previously unseen character, insert it at the front of the 
 *	list. When you read in a duplicate character, delete it from the list and 
 *	reinsert it at the beginning. Name your program MoveToFront: it implements 
 *	the well-known move-to-front strategy, which is useful for caching, data
 *	compression, and many other applications where items that have been recently
 *	accessed are more likely to be reaccessed.
 *
 *  Example execution:
 *  % java E40_MoveToFront 
 *  contents: 7 6 5 4 3 2 1 0
 *  add(8):   8 7 6 5 4 3 2 1 0
 *  add(4):   4 8 7 6 5 3 2 1 0
 *  add(2):   2 4 8 7 6 5 3 1 0
 *  add(0):   0 2 4 8 7 6 5 3 1
 ******************************************************************************/
package chapter_1.section_3;
import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * @author jefoliva
 */

public class E40_MoveToFront {
	public static void main(String[] args) {
		LinkedList.main(args);
	}
}

class LinkedList<Item> implements Iterable<Item> {
	Node first;
	int N;

	private class Node {
		Item item;
		Node next;
	}

	public boolean isEmpty() { return first == null; }
	public int size() { return N; }

	public void add(Item item) {
        // If element already exist, remove it and add it at the beginning
		if(contains(item))
			remove(item);

		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		N++;
	}

	public boolean contains(Item key) {
		Node current = first;
		while(current != null) {
			if(current.item.equals(key)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	public void remove(Item key) {
        if(isEmpty()) throw new NoSuchElementException("List is empty");
        Node head = first;

        // if first element in list equals to key remove
        if(head.item.equals(key)) {
            first = first.next;
            N--;
        }

        while(head.next != null) {
            if(head.next.item.equals(key)) {
                head.next = head.next.next;
                N--;    
                break;
            }
            head = head.next;
        }
    }

    public Iterator<Item> iterator() {
    	return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
    	Node current = first;

    	public boolean hasNext() { return current != null; }
    	public void remove() { throw new UnsupportedOperationException(); }

    	public Item next() {
    		Item item = current.item;
    		current = current.next;
    		return item;
    	}
    }

	public static void main(String[] args) {
		var list = new LinkedList<Integer>();

        for(int i = 0; i < 8; i++)
            list.add(i);

        StdOut.print("contents: ");
        for(int item : list)
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("add(8):   ");
        list.add(8);
        for(int item : list)
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("add(4):   ");
        list.add(4);
        for(int item : list)
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("add(2):   ");
        list.add(2);
        for(int item : list)
            StdOut.print(item + " ");
        StdOut.println();

        StdOut.print("add(0):   ");
        list.add(0);
        for(int item : list)
            StdOut.print(item + " ");
        StdOut.println();
	}
}