/******************************************************************************
 *  Compilation:  javac E20 
 *  Execution:    java E20  
 *  Dependencies: StdOut
 *	
 *  Description: 1.3.20 Write a method delete() that takes an int argument
 *  k and deletes the kth element in a linked list, if it exists
 *
 *  Example execution: 
 *  % java E20
 *  List contents: a b c d e
 *  delete third:  a b d e
 *  delete first:  b d e
 *  delete last:   b d
 ******************************************************************************/
package chapter_1.section_3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class E20 {
	public static void main(String[] args) {
		List<String> list = new List<>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");

		StdOut.print("List contents: ");
		for(String str : list)
			StdOut.print(str + " ");
		StdOut.println();

		// NOTE: delete() is zero-based index	
		StdOut.print("delete third:  ");
		list.delete(2);
		for(String str : list)
			StdOut.print(str + " ");
		StdOut.println();

		StdOut.print("delete first:  ");
		list.delete(0);
		for(String str : list)
			StdOut.print(str + " ");
		StdOut.println();

		StdOut.print("delete last:   ");
		list.delete(list.size()-1);
		for(String str : list)
			StdOut.print(str + " ");
		StdOut.println();
	}
}

class List<Item> implements Iterable<Item> {
	private Node first;
	private Node last;
	private int N;

	private class Node {
		Item item;
		Node next;
	}

	public boolean isEmpty() { return first == null; }
	public int size() { return N; }

	public void add(Item item) {
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if(isEmpty()) first = last;
		else          oldlast.next = last;
		N++;
	}

	/*
	 * Delete the kth item in the list, if exist
	 * this impelementation is zero-based index.
	 */
	public void delete(int k) {
		// if kth element not exists
		if(k > size()-1) throw new NoSuchElementException("Kth is greater than list's size");
		
		// Delete first element in the list
		if(k == 0) {
			first = first.next;
			N--;
			return;
		}

		Node head = first;
		for(int i = 0; i < k; i++, head = head.next ) {
			if(i == k-1) {
				head.next = head.next.next;
				N--;
				break;
			}
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
			if(!hasNext()) throw new NoSuchElementException();
			Item  item = current.item;
			current = current.next;
			return item;
		}
	}
}