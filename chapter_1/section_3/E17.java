/******************************************************************************
 *  Compilation:  javac E17 
 *  Execution:    java E17  
 *  Dependencies: StdOut StdIn Date Transaction Queue
 *	
 *  Description: 1.3.17 Do Exercise 1.3.16 for Transaction.
 *
 *  Example execution: 
 *  % java E16
 *  Mariah 11/26/2018 1000.0 Nancy 11/27/2018 1500.0 
    Chloe 11/27/2018 900.0 April 11/26/2018 1000.0
 *  [ctrl-z]
 *  Results:
 *  Mariah     11/26/2018  1000,00
 *  Nancy      11/27/2018  1500,00
 *  Chloe      11/27/2018   900,00
 *  April      11/26/2018  1000,00
 ******************************************************************************/
package chapter_1.section_3;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.Transaction;
import edu.princeton.cs.algs4.Queue;

/**
 * @author Jefoliva
 */

public class E17 {
  public static Transaction[] readTransactions() {
    Queue<Transaction> q = new Queue<>();

    while(!StdIn.isEmpty()) {
      String who = StdIn.readString();
      Date when = new Date(StdIn.readString());
      double amount = StdIn.readDouble();
      q.enqueue(new Transaction(who, when, amount));
    }

    int N = q.size();
    Transaction[] t = new Transaction[N];
    for(int i = 0; i < N; i++)
      t[i] = q.dequeue();

    return t;
  }

  public static void main(String[] args) {
    Transaction[] transactions = readTransactions();

    StdOut.println("\nResults:");
    for(Transaction t : transactions)
      StdOut.println(t);
  }
}