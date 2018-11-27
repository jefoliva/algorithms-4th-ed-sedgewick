/******************************************************************************
 *  Compilation:  javac E11 
 *  Execution:    java E11  
 *  Dependencies: StdOut StdIn
 *	
 *  Description: 1.3.11 Write a program EvaluatePostfix that takes a postfix expression from standard
 *  input, evaluates it, and prints the value. (Piping the output of your program from
 *  the previous exercise to this program gives equivalent behavior to Evaluate.
 *
 *  Example execution: 
 *  % java E11
 *  2 3 1 * + 9 -
 * [ctrl+z]
 * -4.0
 ******************************************************************************/
package chapter_1.section_3;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Stack;

/**
 * @author Jefoliva
 */

public class E11 {
  public static boolean isOperator(String str) {
    switch(str) {
      case "+":
      case "-":
      case "*":
      case "/":
      case "^":
      return true;
    }
    return false;
  }

  /**
   * This function match a number with optional '-'
   * and decimal
   */
  public static boolean isNumeric(String str) {
    return str.matches("-?\\d+(\\.\\d+)?");
  }

  public static void main(String[] args) {
    Stack<Double> vals = new Stack<>();

    while(!StdIn.isEmpty()) {
      String token = StdIn.readString();
      // Read token, push if operand
      if(isNumeric(token))
        vals.push(Double.parseDouble(token));
      else if(isOperator(token)) {
          // pop evaluate and push if token is operator
        double v = vals.pop();
        if     (token.equals("+")) v = vals.pop() + v;
        else if(token.equals("-")) v = vals.pop() - v;
        else if(token.equals("*")) v = vals.pop() * v;
        else if(token.equals("/")) v = vals.pop() / v;
        else if(token.equals("^")) v = Math.pow(v, vals.pop());
        else if(token.equals("sqrt")) v = Math.sqrt(v);
        vals.push(v);
      }
    }
    StdOut.println(vals.pop());
  }
}