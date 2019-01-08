/******************************************************************************
 *  Compilation:  javac E39_RingBuffer 
 *  Execution:    java E39_RingBuffer  
 *  Dependencies: StdOut
 *  
 *  Description: 1.3.39 Ring buffer. A ring buffer, or circular queue, is a 
 * 	FIFO data structure of a fixed size N. It is useful for transferring data 
 * 	between asynchronous processes or for storing log files. When the buffer is
 * 	empty, the consumer waits until data is deposited; when the buffer is full,
 * 	the producer waits to deposit data. Develop an API for a RingBuffer and an
 * 	implementation that uses an array representation (with circular wrap-around).
 *
 *  Example execution:
 *  % java E39_RingBuffer 
 *  capacity: 6
 *  contents:   a b c d e
 *  insert(f):  a b c d e f
 *  insert(g):  [buffer full, wait...  Producer queue: g ] a b c d e f
 *  insert(h):  [buffer full, wait...  Producer queue: g h ] a b c d e f
 *  extract():  b c d e f g   [ Producer: h ]
 *  extract():  c d e f g h   [ Producer: ]
 ******************************************************************************/
package chapter_1.section_3;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * @author jefoliva
 */

public class E39_RingBuffer {
	public static void main(String[] args) {
		RingBuffer.main(args);
	}
}

class RingBuffer<Item> implements Iterable<Item> {
	private int first;
	private int last;
	private int n;
	private Item[] q;
    private Queue<Item> producer = new Queue<>();

	RingBuffer(int capacity) {
		q = (Item[]) new Object[capacity+1];
		last = first = 0;
	}

	public int 	   size()     { return n;        }
	public int 	   capacity() { return q.length-1; }
	public boolean isEmpty()  { return n == 0;   }

	public boolean isFull() { 
		return last+1==first | 
               ((last==q.length-1) & (first==0)); 
    }
 
	public void insert(Item item) {
		if(isFull()) {
            producer.enqueue(item);
			StdOut.print("[buffer full, wait...  ");
            StdOut.print("Producer queue: ");
            for(Item current : producer) 
                StdOut.print(current + " ");
            StdOut.print("] ");

		} else {
			q[last++] = item;
			if(last == q.length) last = 0;		// wrap around
			n++;
		}
	}

	public Item extract() {
		if(isEmpty()) {
			return null;
		} else {
			Item item = q[first];
			q[first] = null;
			n--;
			first++;
			if(first == q.length) first = 0;

            while(!isFull() && !producer.isEmpty()) {
                insert(producer.dequeue());
            }

            

			return item;
		}
	}

    public void printProducer() {
        StdOut.print("  [ Producer: ");
        for(Item current : producer) 
            StdOut.print(current + " ");
        StdOut.print("]");
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        int i = 0;

        public boolean hasNext() { return i < n; }
        public void remove() { throw new UnsupportedOperationException(); }
        public Item next() {
            Item item = q[(i + first) % q.length];
            i++;
            return item;
        }
    }

	public static void main(String[] args) {
		var ringBuffer = new RingBuffer<String>(6);

        StdOut.println("capacity: " + ringBuffer.capacity());

        ringBuffer.insert("a");
        ringBuffer.insert("b");
        ringBuffer.insert("c");
        ringBuffer.insert("d");
        ringBuffer.insert("e");

        StdOut.print("contents:   ");
        for(String str : ringBuffer)
            StdOut.print(str + " ");
        StdOut.println();

        StdOut.print("insert(f):  ");
        ringBuffer.insert("f");
        for(String str : ringBuffer)
            StdOut.print(str + " ");
        StdOut.println();

        StdOut.print("insert(g):  ");
        ringBuffer.insert("g");
        for(String str : ringBuffer)
            StdOut.print(str + " ");
        StdOut.println();

        StdOut.print("insert(h):  ");
        ringBuffer.insert("h");
        for(String str : ringBuffer)
            StdOut.print(str + " ");
        StdOut.println();

        StdOut.print("extract():  ");
        ringBuffer.extract();
        for(String str : ringBuffer)
            StdOut.print(str + " ");
        ringBuffer.printProducer();
        StdOut.println();

        StdOut.print("extract():  ");
        ringBuffer.extract();
        for(String str : ringBuffer)
            StdOut.print(str + " ");
        ringBuffer.printProducer();
        StdOut.println();
	}
}