/******************************************************************************
 *  Compilation:  javac E48_TwoStacksWithADeque.java
 *  Execution:    java E48_TwoStacksWithADeque
 *  Dependencies: StdOut Stack Queue
 *  
 *  Description: Implement two stacks with a single deque so that each
 *  operation takes a constant number of deque operations
 *
 *  Example execution:
 *  % java E47_CatenableQueuesStacksOrSteques
 *  stackLeft.push(c):  c
 *  stackRight.push(d): d
 *  Deque contents:     c d
 *  
 *  stackLeft.push(b):  b c
 *  stackRight.push(e): d e
 *  Deque contents:     b c d e
 *  
 *  stackLeft.push(a):  a b c
 *  stackRight.push(f): d e f
 *  Deque contents:     a b c d e f
 *  
 *  stackLeft.pop():    b c
 *  stackRigh.pop():    d e
 *  Deque contents:     b c d e
 ******************************************************************************/
package chapter_1.section_3;
import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;

/**
 * @author jefoliva
 */

public class E48_TwoStacksWithADeque {
    public static void main(String[] args) {
        var deque      = new Deque<String>();

        // These stacks use the same deque to store elements
        var stackLeft  = new StackLeft<String>(deque);
        var stackRight = new StackRight<String>(deque);

        stackLeft.push("c");
        StdOut.println("stackLeft.push(c):  " + stackLeft.traverse());
        stackRight.push("d");
        StdOut.println("stackRight.push(d): " + stackRight.traverse());
        StdOut.println("Deque contents:     " + deque);
        StdOut.println();

        stackLeft.push("b");
        StdOut.println("stackLeft.push(b):  " + stackLeft.traverse());
        stackRight.push("e");
        StdOut.println("stackRight.push(e): " + stackRight.traverse());
        StdOut.println("Deque contents:     " + deque);
        StdOut.println();

        stackLeft.push("a");
        StdOut.println("stackLeft.push(a):  " + stackLeft.traverse());
        stackRight.push("f");
        StdOut.println("stackRight.push(f): " + stackRight.traverse());
        StdOut.println("Deque contents:     " + deque);
        StdOut.println();

        stackLeft.pop();
        StdOut.println("stackLeft.pop():    " + stackLeft.traverse());
        stackRight.pop();
        StdOut.println("stackRigh.pop():    " + stackRight.traverse());
        StdOut.println("Deque contents:     " + deque);
    } 
}

class StackLeft<Item> {
    Deque<Item> deque;
    int n;

    StackLeft(Deque<Item> deque) {
        this.deque = deque;
    }

    public boolean isEmpty() { return n == 0; }
    public int size() { return n; }

    public void push(Item item) {
        deque.pushLeft(item);
        n++;
    }

    public Item pop() {
        if(isEmpty()) throw new NoSuchElementException("Left stack is empty");

        Item item = deque.popLeft();
        n--;
        return item;
    }

    public String traverse() {
        int size = n;
        String out = "";
        for(Item item : deque) {
            if(size > 0) {
                out += item + " ";
                size--;
            } else {
                break;
            }
        }
        return out;
    }
}

class StackRight<Item> {
    Deque<Item> deque;
    int n;

    StackRight(Deque<Item> deque) {
        this.deque = deque;
    }

    public boolean isEmpty() { return n == 0; }
    public int size() { return n; }

    public void push(Item item) {
        deque.pushRight(item);
        n++;
    }

    public Item pop() {
        if(isEmpty()) throw new NoSuchElementException("Left stack is empty");

        Item item = deque.popRight();
        n--;
        return item;
    }

    public String traverse() {
        int size = n;
        int leftStackSize = deque.size() - size;
        String out = "";

        for(Item item : deque) {
            if(leftStackSize > 0) {
                leftStackSize--;
            } else {
                out += item + " ";
            }
        }
        return out;
    }            
}
