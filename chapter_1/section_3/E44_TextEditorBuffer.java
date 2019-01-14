/******************************************************************************
 *  Compilation:  javac E44_TextEditorBuffer 
 *  Execution:    java E44_TextEditorBuffer  
 *  Dependencies: StdOut Stack
 *  
 *  Description: 1.3.44 Text editor buffer. Develop a data type for a buffer in 
 *   a text editor that implements the following API:
 *   public class Buffer
 *   Buffer()             create an empty buffer
 *   void insert(char c)  insert c at the cursor position
 *   char delete()        delete and return the character at the cursor
 *   void left(int k)     move the cursor k positions to the left
 *   void right(int k)    move the cursor k positions to the right
 *   int size()           number of characters in the buffer
 *   Hint : Use two stacks.
 *
 *  Example execution:
 *  % java E44_TextEditorBuffer
 *  FOR THE SAKE OF EXAMPLE '|' IS USED TO REPRESENT THE CURSOR POSITION.
 *  
 *  Buffer contents: To be or not to be, that is  VITAL question.|
 *  left(8):         To be or not to be, that is  VITAL q|uestion.
 *  left(6):         To be or not to be, that is  V|ITAL question.
 *  right(5):        To be or not to be, that is  VITAL |question.
 *  delete():        To be or not to be, that is  VITAL|question.
 *  delete():        To be or not to be, that is  VITA|question.
 *  delete():        To be or not to be, that is  VIT|question.
 *  delete():        To be or not to be, that is  VI|question.
 *  delete():        To be or not to be, that is  V|question.
 *  delete():        To be or not to be, that is  |question.
 *  delete():        To be or not to be, that is |question.
 *  insert('t'):     To be or not to be, that is t|question.
 *  insert('h'):     To be or not to be, that is th|question.
 *  insert('e'):     To be or not to be, that is the|question.
 *  right(10):       To be or not to be, that is the question.|
 ******************************************************************************/
package chapter_1.section_3;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stack;

/**
 * @author jefoliva
 */

public class E44_TextEditorBuffer {
    public static void main(String[] args) {
        Buffer.main(args);
    }
}

class Buffer {
    private Stack<Character> leftbuffer;
    private Stack<Character> rightbuffer;

    public Buffer() {
        leftbuffer = new Stack<Character>();
        rightbuffer = new Stack<Character>();
    }

    public int size() { 
        return leftbuffer.size() + rightbuffer.size(); 
    }

    public void insert(char c) {
        leftbuffer.push(c);
    }

    public char delete() {
        // If left stack is empty return null character, pop otherwise
        return leftbuffer.isEmpty() ? '\0' : leftbuffer.pop();     
    }

    public void left(int k) {
        // Move characters from left to right stack only if left stack is 
        // not empty, if is empty break the loop
        for(int i = 0; i < k; i++) {
            if(!leftbuffer.isEmpty()) rightbuffer.push(leftbuffer.pop());
            else break;
        }
    }

    public void right(int k) {
        // Move characters from right to left stack only if right stack is 
        // not empty, if is empty break the loop
        for(int i = 0; i < k; i++) {
            if(!rightbuffer.isEmpty()) leftbuffer.push(rightbuffer.pop());
            else break;
        }
    }

    private String serializeStack(Stack<Character> stack, boolean reverse) {
        StringBuilder sb = new StringBuilder();
        for(char c : stack)
            sb.append(c);
        return reverse ? sb.reverse().toString() : sb.toString();
    }

    @Override
    public String toString() {
        return  serializeStack(leftbuffer, true) + "|" +
                serializeStack(rightbuffer, false);
    }

    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        String str = "To be or not to be, that is VITAL question.";

        for(int i = 0; i < str.length(); i++)
            buffer.insert(str.charAt(i));

        StdOut.println("\nFOR THE SAKE OF EXAMPLE '|' IS USED TO REPRESENT " +
                        "THE CURSOR POSITION.\n");

        StdOut.println("Buffer contents: " + buffer);
        buffer.left(8);
        StdOut.println("left(8):         " + buffer);

        buffer.left(6);
        StdOut.println("left(6):         " + buffer);

        buffer.right(5);
        StdOut.println("right(5):        " + buffer);

        for(int i = 0; i < 6; i++) {
            buffer.delete();
            StdOut.println("delete():        " + buffer);
        }

        buffer.insert('t');
        StdOut.println("insert('t'):     " + buffer);

        buffer.insert('h');
        StdOut.println("insert('h'):     " + buffer);

        buffer.insert('e');
        buffer.insert(' ');
        StdOut.println("insert('e'):     " + buffer);

        buffer.right(10);
        StdOut.println("right(10):       " + buffer);
    }
}