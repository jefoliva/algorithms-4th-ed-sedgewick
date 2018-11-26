/******************************************************************************
 *  Compilation:  javac E09 
 *  Execution:    java E09  
 *  Dependencies: StdOut
 *	
 *  1.3.9 Write a program that takes from standard input an expression without left 
 *  parentheses and prints the equivalent infix expression with the parentheses inserted. 
 *  For example, given the input: 1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
 *  your program should print   : ( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) )
 *
 *  Example execution: 
 *  % java E09
 *  1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
 *  [ctrl+z]
 *  ( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) )
 ******************************************************************************/
package chapter_1.section_3;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Stack;

/**
 * @author Jefoliva
 */

public class E09 {
	public static void main(String[] args) {
		Stack<String> ops = new Stack<>();
		Stack<String> vals = new Stack<>();
		
		while(!StdIn.isEmpty()) {
			String item = StdIn.readString();
			
			if(isOperator(item)) {
				ops.push(item);
			}
			else if(item.equals(")")) {
				String val1 = vals.pop();
				String val2 = vals.pop();
				String op = ops.pop();
				vals.push("( " + val2 + " " + op + " " + val1 + " )");
			} else {
				vals.push(item);
			}
		}

		StdOut.println(vals.pop());

	}

	public static boolean isOperator(String str) {
		switch(str) {
			case "+":
			case "-":
			case "*":
			case "/":
				return true;
		}
		return false;
	}
}