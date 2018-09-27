/******************************************************************************
 *  Compilation:  javac Exercise_21.java
 *  Execution:    java Exercise_21 < studentsData.txt (or input data manually)
 *  Dependencies: StdOUt.java StdIn.java
 *
 *  Description: 1.1.21 Write a program that reads in lines from standard input
 *  with each line containing a name and two integers and then uses printf() to
 *  print a table with a column of the names, the integers, and the result of 
 *  dividing the first by the second, accurate to three decimal places. You could 
 *  use a program like this to tabulate batting averages for baseball players or 
 *  grades for students.
 ******************************************************************************/
package chapter_1.section_1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

/**
 * @author Jefoliva
 */

public class Exercise_21 {
    public static void main(String[] args) {
       String[] lines = StdIn.readAllLines();
       String name = "";
       double N1 = 0; 
       double N2 = 0;
       
      // Format column names 
      StdOut.printf("%-15s %8s %8s %8s\n", "NAME", "N1", "N2", "N1/N2");

      for(int i = 0; i < lines.length; i++) {
          // NOTE: Data should be separeted by one space (" ")
          String[] currentLineItems = lines[i].split(" ");
          
          name = currentLineItems[0];
          N1 = Double.parseDouble(currentLineItems[1]);
          N2 = Double.parseDouble(currentLineItems[2]);
          
          // Format output
          StdOut.printf("%-15s %8.3f %8.3f %8.3f\n", name, N1, N2, N1/N2);
      }
    }
}