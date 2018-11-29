/******************************************************************************
 *  Compilation:  javac E21 
 *  Execution:    java E21  
 *  Dependencies: StdOut
 *	
 *  Description: 1.3.21 Write a method find() that takes a linked list and a string
 *  key as arguments and returns true if some node in the list has key as its item
 *  field, false otherwise
 *
 *  Example execution: 
 *  % java E21
 *  List contents:
 *  [Kashmir] [Lucy in the sky with Diamonds] [Layla] [Light my fire] [Hey Joe]
 *  find('Lucy in the sky with Diamonds'): true
 *  find('Hey Joe'): true
 *  find('Yesterday'): false
 ******************************************************************************/
package chapter_1.section_3;

import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class E21 {
	public static boolean find(List<String> list,  String key) {
		for(String str : list) 
			if(str.equals(key)) return true;
		
		return false;
	}

	public static void main(String[] args) {
		List<String> songsList = new List<>();
		songsList.add("Kashmir");
		songsList.add("Lucy in the sky with Diamonds");
		songsList.add("Layla");
		songsList.add("Light my fire");
		songsList.add("Hey Joe");

		StdOut.println("List contents:");
		for(String song : songsList) 
			StdOut.print("[" + song + "] ");
		StdOut.println("\n");

		StdOut.println("find('Lucy in the sky with Diamonds'): " + 
			find(songsList, "Lucy in the sky with Diamonds"));
		StdOut.println("find('Hey Joe'): " + find(songsList, "Hey Joe"));
		StdOut.println("find('Yesterday'): " + find(songsList, "Yesterday"));
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