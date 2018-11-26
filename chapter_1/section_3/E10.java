/******************************************************************************
 *  Compilation:  javac E10 
 *  Execution:    java E10  
 *  Dependencies: StdOut StdIn
 *	
 *  Description: 1.3.10 Write a filter InfixToPostfix that converts an arithmetic  
 *  expression from infix to postfix
 *
 *  Example execution: 
 *  % java E10
 *  3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3
 *  [crtl-z]
 *  3 4 2 * 1 5 -2 3 ^ ^ / +
 ******************************************************************************/
package chapter_1.section_3;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Stack;

/**
 * @author Jefoliva
 */

public class E10 {
  public static void main(String[] args) {
    Stack<String> stack = new Stack<>();
    String output = "";

    while(!StdIn.isEmpty()) {
      String token = StdIn.readString();

      if(isNumeric(token)) 
        output += token + " ";
      else if (token.equals("("))
        stack.push(token);
      else if (token.equals(")")) {
        //  If a closing parenthesis is found, pop operands and put them in the output
        //  until a closing parenthesis is found
        while(!stack.isEmpty() && !stack.peek().equals("("))
          output += stack.pop() + " ";

        // If there isn't a closing parenthesis, expression is invalid 
        if(!stack.isEmpty() && !stack.peek().equals("("))
          throw new RuntimeException("Invalid Expression");
        else
          stack.pop();
      }
      else if(isOperator(token)) {
        // while current operand has less or equal precedence than item on the top
        // of the stack
        while(!stack.isEmpty() && precedence(token) <= precedence(stack.peek())) {
          if(token.equals("^") && stack.peek().equals("^"))
            break;

          output += stack.pop() + " ";
        }
        stack.push(token);
      }
    }
    // while there are operators in the stack, pop them
    while(!stack.isEmpty())
      output += stack.pop() + " ";

    StdOut.println(output);
  }

  /**
   * This function match a number with optional '-'
   * and decimal
   */
  public static boolean isNumeric(String str) {
    return str.matches("-?\\d+(\\.\\d+)?");
  }

  public static int precedence(String str) {
    switch(str) {
      case "+":
      case "-":
        return 1;
      case "*":
      case "/":
        return 2;
      case "^":
        return 3;
    }

    return -1;
  }

  public static boolean isOperator(String str) {
    switch(str) {
      case "+":
      case "-":
      case "*":
      case "/":
      case "^":
      case "sqrt":
      return true;
    }
    return false;
  } 
}