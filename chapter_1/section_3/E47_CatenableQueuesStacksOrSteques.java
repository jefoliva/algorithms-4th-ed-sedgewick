/******************************************************************************
 *  Compilation:  javac chapter_1.section_3.E47_CatenableQueuesStacksOrSteques
 *  Execution:    java E47_CatenableQueuesStacksOrSteques
 *  Dependencies: StdOut Stack Queue
 *  
 *  Description: 1.3.47 Catenable queues, stacks, or steques. Add an extra 
 *  operation catenation that (destructively) concatenates two queues, stacks, 
 *  or steques (see Exercise 1.3.32). Hint : Use circular linked list, 
 *  maintaining a pointer to the last item.
 *
 *  Example execution:
 *  % java E47_CatenableQueuesStacksOrSteques
 *  Catenable collections:
 *   stackA  : d c b a
 *   stackB  : h g f e
 *   queueA  : 1 2 3 4
 *   queueB  : 5 6 7 8
 *   stequeA : 1.1 2.2 3.3 4.4
 *   stequeB : 5.5 6.6 7.7 8.8
 *
 *   catenate(stackA, stackB):    d c b a h g f e
 *   catenate(queueA, queueB):    1 2 3 4 5 6 7 8
 *   catenate(stequeA, stequeB):  1.1 2.2 3.3 4.4 5.5 6.6 7.7 8.8
 ******************************************************************************/
package chapter_1.section_3;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.Queue;
import java.util.NoSuchElementException;

/**
 * @author jefoliva
 */

public class E47_CatenableQueuesStacksOrSteques {

    public static <Item>  CircularLinkedList<Item> catenation(Queue<Item> a, Queue<Item> b) {
        if(a == null || b == null) 
            return null;

        var list = new CircularLinkedList<Item>();

        while(!a.isEmpty())
            list.add(a.dequeue());

        while(!b.isEmpty())
            list.add(b.dequeue());

        return list;
    }

    public static <Item> CircularLinkedList<Item> catenation(Stack<Item> a, Stack<Item> b) {
        if(a == null || b == null) 
            return null;

        var list = new CircularLinkedList<Item>();

        while(!a.isEmpty())
            list.add(a.pop());

        while(!b.isEmpty())
            list.add(b.pop());

        return list;
    }

    public static <Item> CircularLinkedList<Item> catenation(Steque<Item> a, Steque<Item> b) {
        if(a == null || b == null)
            return null;

        var list = new CircularLinkedList<Item>();

        while(!a.isEmpty())
            list.add(a.pop());

        while(!b.isEmpty())
            list.add(b.pop());

        return list;
    }

    public static void main(String[] args) {
        String[] strings = {"a", "b", "c", "d", "e", "f", "g", "h"};
        int[]       ints = {1, 2, 3, 4, 5, 6, 7, 8};
        double[] doubles = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8};

        var stackA  = new Stack<String>();
        var stackB  = new Stack<String>();
        var queueA  = new Queue<Integer>();
        var queueB  = new Queue<Integer>();
        var stequeA = new Steque<Double>();
        var stequeB = new Steque<Double>();

        for(int i = 0; i < strings.length/2; i++) {
            stackA.push(strings[i]);
            stackB.push(strings[i+strings.length/2-1]);
        }

        for(int i = 0; i < ints.length/2; i++) {
            queueA.enqueue(ints[i]);
            queueB.enqueue(ints[i+strings.length/2-1]);
        }

        for(int i = 0; i < doubles.length/2; i++) {
            stequeA.enqueue(doubles[i]);
            stequeB.enqueue(doubles[i+strings.length/2-1]);
        }

        StdOut.println("Catenable collections:");
        StdOut.println("stackA  : " + stackA);
        StdOut.println("stackB  : " + stackB);
        StdOut.println("queueA  : " + queueA);
        StdOut.println("queueB  : " + queueB);
        StdOut.println("stequeA : " + stequeA);
        StdOut.println("stequeB : " + stequeB);
        StdOut.println();

        StdOut.println("catenate(stackA, stackB):   " +  
            E47_CatenableQueuesStacksOrSteques.<String>catenation(stackA, stackB));

        StdOut.println("catenate(queueA, queueB):   " +  
            E47_CatenableQueuesStacksOrSteques.<Integer>catenation(queueA, queueB));

        StdOut.println("catenate(stequeA, stequeB):  " +  
            E47_CatenableQueuesStacksOrSteques.<Double>catenation(stequeA, stequeB));
    }
}